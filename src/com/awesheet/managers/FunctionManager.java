package com.awesheet.managers;

import com.awesheet.grammar.AweFuncLexer;
import com.awesheet.grammar.AweFuncParser;
import com.awesheet.grammar.AweVisitor;
import com.awesheet.models.DataFunction;
import com.awesheet.models.Sheet;
import com.awesheet.models.functions.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.HashMap;

public class FunctionManager {
    private static FunctionManager instance = null;

    protected HashMap<String, Class> registeredFunctions;

    public static FunctionManager getInstance() {
        if (instance == null) {
            instance = new FunctionManager();
        }

        return instance;
    }

    protected FunctionManager() {
        registeredFunctions = new HashMap<String, Class>();

        // Register all the built-in functions.
        registerBuiltins();
    }

    public void registerFunction(String name, Class type) {
        registeredFunctions.put(name, type);
    }

    public void registerBuiltins() {
        registerFunction(AbsFunction.getName(), AbsFunction.class);
        registerFunction(AndFunction.getName(), AndFunction.class);
        registerFunction(ConcatFunction.getName(), ConcatFunction.class);
        registerFunction(CosFunction.getName(), CosFunction.class);
        registerFunction(IncludesFunction.getName(), IncludesFunction.class);
        registerFunction(Log10Function.getName(), Log10Function.class);
        registerFunction(LogFunction.getName(), LogFunction.class);
        registerFunction(MaxFunction.getName(), MaxFunction.class);
        registerFunction(MeanFunction.getName(), MeanFunction.class);
        registerFunction(MedianFunction.getName(), MedianFunction.class);
        registerFunction(MinFunction.getName(), MinFunction.class);
        registerFunction(MultFunction.getName(), MultFunction.class);
        registerFunction(NotFunction.getName(), NotFunction.class);
        registerFunction(OrFunction.getName(), OrFunction.class);
        registerFunction(PowFunction.getName(), PowFunction.class);
        registerFunction(RemoveFunction.getName(), RemoveFunction.class);
        registerFunction(SinFunction.getName(), SinFunction.class);
        registerFunction(StddevFunction.getName(), StddevFunction.class);
        registerFunction(SumFunction.getName(), SumFunction.class);
        registerFunction(TanFunction.getName(), TanFunction.class);
        registerFunction(TrimFunction.getName(), TrimFunction.class);
        registerFunction(XorFunction.getName(), XorFunction.class);
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
        Class functionClass = registeredFunctions.get(type.trim());

        try {
            return (DataFunction) functionClass.newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
