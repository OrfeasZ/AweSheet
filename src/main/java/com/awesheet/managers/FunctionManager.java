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

package com.awesheet.managers;

import com.awesheet.actions.SetFunctionsAction;
import com.awesheet.grammar.AweFuncLexer;
import com.awesheet.grammar.AweFuncParser;
import com.awesheet.grammar.AweVisitor;
import com.awesheet.interfaces.IUIBindable;
import com.awesheet.models.DataFunction;
import com.awesheet.models.Sheet;
import com.awesheet.models.functions.*;
import com.awesheet.ui.UIFunction;
import com.awesheet.ui.UIModel;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.HashMap;

public class FunctionManager {
    protected class RegisteredFunction implements IUIBindable {
        protected String name;
        protected String description;
        protected String arguments[];
        protected Class type;

        public RegisteredFunction(String name, String description, String arguments[], Class type) {
            this.name = name;
            this.description = description;
            this.arguments = arguments;
            this.type = type;
        }

        public Class getType() {
            return type;
        }

        public String[] getArguments() {
            return arguments;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public UIModel bind() {
            return new UIFunction(description, arguments);
        }
    }

    private static FunctionManager instance = null;

    protected HashMap<String, RegisteredFunction> registeredFunctions;

    public static FunctionManager getInstance() {
        if (instance == null) {
            instance = new FunctionManager();
        }

        return instance;
    }

    protected FunctionManager() {
        registeredFunctions = new HashMap<String, RegisteredFunction>();

        // Register all the built-in functions.
        registerBuiltins();
    }

    public void registerFunction(String name, String description, String arguments[], Class type) {
        registeredFunctions.put(name, new RegisteredFunction(name, description, arguments, type));
    }

    public void registerBuiltins() {
        registerFunction(AbsFunction.getName(), AbsFunction.getDescription(), AbsFunction.getArgumentNames(), AbsFunction.class);
        registerFunction(AndFunction.getName(), AndFunction.getDescription(), AndFunction.getArgumentNames(), AndFunction.class);
        registerFunction(ConcatFunction.getName(), ConcatFunction.getDescription(), ConcatFunction.getArgumentNames(), ConcatFunction.class);
        registerFunction(CosFunction.getName(), CosFunction.getDescription(), CosFunction.getArgumentNames(), CosFunction.class);
        registerFunction(IncludesFunction.getName(), IncludesFunction.getDescription(), IncludesFunction.getArgumentNames(), IncludesFunction.class);
        registerFunction(Log10Function.getName(), Log10Function.getDescription(), Log10Function.getArgumentNames(), Log10Function.class);
        registerFunction(LogFunction.getName(), LogFunction.getDescription(), LogFunction.getArgumentNames(), LogFunction.class);
        registerFunction(MaxFunction.getName(), MaxFunction.getDescription(), MaxFunction.getArgumentNames(), MaxFunction.class);
        registerFunction(MeanFunction.getName(), MeanFunction.getDescription(), MeanFunction.getArgumentNames(), MeanFunction.class);
        registerFunction(MedianFunction.getName(), MedianFunction.getDescription(), MedianFunction.getArgumentNames(), MedianFunction.class);
        registerFunction(MinFunction.getName(), MinFunction.getDescription(), MinFunction.getArgumentNames(), MinFunction.class);
        registerFunction(MultFunction.getName(), MultFunction.getDescription(), MultFunction.getArgumentNames(), MultFunction.class);
        registerFunction(NotFunction.getName(), NotFunction.getDescription(), NotFunction.getArgumentNames(), NotFunction.class);
        registerFunction(OrFunction.getName(), OrFunction.getDescription(), OrFunction.getArgumentNames(), OrFunction.class);
        registerFunction(PowFunction.getName(), PowFunction.getDescription(), PowFunction.getArgumentNames(), PowFunction.class);
        registerFunction(RemoveFunction.getName(), RemoveFunction.getDescription(), RemoveFunction.getArgumentNames(), RemoveFunction.class);
        registerFunction(SinFunction.getName(), SinFunction.getDescription(), SinFunction.getArgumentNames(), SinFunction.class);
        registerFunction(StddevFunction.getName(), StddevFunction.getDescription(), StddevFunction.getArgumentNames(), StddevFunction.class);
        registerFunction(SumFunction.getName(), SumFunction.getDescription(), SumFunction.getArgumentNames(), SumFunction.class);
        registerFunction(TanFunction.getName(), TanFunction.getDescription(), TanFunction.getArgumentNames(), TanFunction.class);
        registerFunction(TrimFunction.getName(), TrimFunction.getDescription(), TrimFunction.getArgumentNames(), TrimFunction.class);
        registerFunction(XorFunction.getName(), XorFunction.getDescription(), XorFunction.getArgumentNames(), XorFunction.class);
    }

    public void setUIFunctions() {
        // Register functions with the UI.
        HashMap<String, UIFunction> functions = new HashMap<String, UIFunction>();

        for (RegisteredFunction function : registeredFunctions.values()) {
            functions.put(function.name, (UIFunction) function.bind());
        }

        UIMessageManager.getInstance().dispatchAction(new SetFunctionsAction(functions));
    }

    public DataFunction parseFunction(String value, Sheet sheet) {
        try {
            ANTLRInputStream inputStream = new ANTLRInputStream(value);

            AweFuncLexer lexer = new AweFuncLexer(inputStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            AweFuncParser parser = new AweFuncParser(tokens);
            AweFuncParser.AweFunctionContext tree = parser.aweFunction();

            AweVisitor visitor = new AweVisitor(sheet);
            visitor.visit(tree);

            DataFunction function = visitor.getBaseFunction();

            if (!function.parse()) {
                return null;
            }

            return function;
        } catch (RuntimeException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public DataFunction createFunctionInstance(String type) {
        // Do we have a registered class for this type?
        if (!registeredFunctions.containsKey(type.trim())) {
            return null;
        }

        // If we do, try to create a new instance.
        Class functionClass = registeredFunctions.get(type.trim()).getType();

        try {
            return (DataFunction) functionClass.newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
