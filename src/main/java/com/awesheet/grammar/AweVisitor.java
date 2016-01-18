/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.awesheet.grammar;

import com.awesheet.managers.FunctionManager;
import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;
import com.awesheet.models.Sheet;
import com.awesheet.models.arguments.CellFunctionArgument;
import com.awesheet.models.arguments.EvalFunctionArgument;
import com.awesheet.models.arguments.ValueFunctionArgument;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AweVisitor extends AweFuncParserBaseVisitor<Integer> {
    protected DataFunction baseFunction;
    protected DataFunction currentContext;
    protected Stack<DataFunction> functionQueue;
    protected Sheet currentSheet;

    public AweVisitor(Sheet sheet) {
        baseFunction = null;
        currentContext = null;
        functionQueue = new Stack<DataFunction>();
        currentSheet = sheet;
    }

    public DataFunction getBaseFunction() {
        return baseFunction;
    }

    @Override
    public Integer visitAweFunction(AweFuncParser.AweFunctionContext ctx) {
        DataFunction currentFunction = FunctionManager.getInstance().createFunctionInstance(ctx.IDENTIFIER().getText());

        // No function of matched type.
        if (currentFunction == null) {
            throw new RuntimeException("No function of type '" + ctx.IDENTIFIER().getText() + "' registered.");
        }

        if (baseFunction == null) {
            baseFunction = currentFunction;
        } else {
            functionQueue.add(currentFunction);
        }

        // Set the context.
        DataFunction previousContext = currentContext;
        currentContext = currentFunction;

        // Perform further parsing.
        Integer result = super.visitAweFunction(ctx);

        // Restore the context.
        currentContext = previousContext;

        return result;
    }

    @Override
    public Integer visitAweParameter(AweFuncParser.AweParameterContext ctx) {
        // Create our argument.
        FunctionArgument argument;

        if (ctx.CELL_IDENTIFIER() != null) {
            argument = new CellFunctionArgument(ctx.CELL_IDENTIFIER().getText(), currentSheet);
        } else if (ctx.NUMBER() != null) {
            argument = new ValueFunctionArgument(ctx.NUMBER().getText());
        } else if (ctx.STRING() != null) {
            argument = new ValueFunctionArgument(ctx.STRING().getText().substring(1, ctx.STRING().getText().length() - 1));
        } else if (ctx.aweFunction() != null) {
            argument = new EvalFunctionArgument(ctx.aweFunction().getText());
        } else {
            throw new RuntimeException("Tried to parse an argument of unknown type.");
        }

        // Continue parsing.
        Integer result = super.visitAweParameter(ctx);

        // Set inner function in the case of eval argument.
        if (ctx.aweFunction() != null) {
            ((EvalFunctionArgument) argument).setInnerFunction(functionQueue.pop());
        }

        if (!argument.isValid()) {
            throw new RuntimeException("Attempted to parse an invalid argument.");
        }

        // Add the argument to the currently parsed function.
        currentContext.addArgument(argument);

        return result;
    }
}
