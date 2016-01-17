package com.awesheet.grammar;

import com.awesheet.managers.FunctionManager;
import com.awesheet.models.DataFunction;
import com.awesheet.models.FunctionArgument;
import com.awesheet.models.arguments.CellFunctionArgument;
import com.awesheet.models.arguments.EvalFunctionArgument;
import com.awesheet.models.arguments.ValueFunctionArgument;

import java.util.LinkedList;
import java.util.Queue;

public class AweVisitor extends AweFuncBaseVisitor<Integer> {
    protected DataFunction baseFunction;
    protected DataFunction currentContext;
    protected Queue<DataFunction> functionQueue;

    public AweVisitor() {
        baseFunction = null;
        currentContext = null;
        functionQueue = new LinkedList<DataFunction>();
    }

    public DataFunction getBaseFunction() {
        return baseFunction;
    }

    @Override
    public Integer visitAwe(AweFuncParser.AweContext ctx) {
        return visitChildren(ctx);
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
        FunctionArgument argument = null;

        if (ctx.CELL_IDENTIFIER() != null) {
            argument = new CellFunctionArgument(ctx.CELL_IDENTIFIER().getText());
        } else if (ctx.VALUE() != null) {
            argument = new ValueFunctionArgument(ctx.VALUE().getText());
        } else if (ctx.aweFunction() != null) {
            argument = new EvalFunctionArgument(ctx.aweFunction().getText());
        } else {
            throw new RuntimeException("Tried to parse an argument of unknown type.");
        }

        // Continue parsing.
        Integer result = super.visitAweParameter(ctx);

        // Set inner function in the case of eval argument.
        if (ctx.aweFunction() != null) {
            DataFunction innerFunction = functionQueue.remove();

            if (!innerFunction.parse()) {
                throw new RuntimeException("Could not parse a given eval function argument.");
            }

            ((EvalFunctionArgument) argument).setInnerFunction(innerFunction);
        }

        // Add the argument to the currently parsed function.
        currentContext.addArgument(argument);

        return result;
    }

    @Override
    public Integer visitAweParameters(AweFuncParser.AweParametersContext ctx) {
        return super.visitAweParameters(ctx);
    }
}
