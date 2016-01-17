package com.awesheet.managers;

import com.awesheet.grammar.AweFuncLexer;
import com.awesheet.grammar.AweFuncParser;
import com.awesheet.grammar.AweVisitor;
import com.awesheet.models.DataFunction;
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
        registerFunction(AndFunction.getName(), AbsFunction.class);
        registerFunction(ConcatFunction.getName(), AbsFunction.class);
        registerFunction(CosFunction.getName(), AbsFunction.class);
        registerFunction(IncludesFunction.getName(), AbsFunction.class);
        registerFunction(Log10Function.getName(), AbsFunction.class);
        registerFunction(LogFunction.getName(), AbsFunction.class);
        registerFunction(MaxFunction.getName(), AbsFunction.class);
        registerFunction(MeanFunction.getName(), AbsFunction.class);
        registerFunction(MedianFunction.getName(), AbsFunction.class);
        registerFunction(MinFunction.getName(), AbsFunction.class);
        registerFunction(MultFunction.getName(), AbsFunction.class);
        registerFunction(NotFunction.getName(), AbsFunction.class);
        registerFunction(OrFunction.getName(), AbsFunction.class);
        registerFunction(PowFunction.getName(), AbsFunction.class);
        registerFunction(RemoveFunction.getName(), AbsFunction.class);
        registerFunction(SinFunction.getName(), AbsFunction.class);
        registerFunction(StddevFunction.getName(), AbsFunction.class);
        registerFunction(SumFunction.getName(), AbsFunction.class);
        registerFunction(TanFunction.getName(), AbsFunction.class);
        registerFunction(TrimFunction.getName(), AbsFunction.class);
        registerFunction(XorFunction.getName(), AbsFunction.class);
    }

    public DataFunction parseFunction(String value) {
        ANTLRInputStream inputStream = new ANTLRInputStream(value);

        AweFuncLexer lexer = new AweFuncLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        AweFuncParser parser = new AweFuncParser(tokens);
        AweFuncParser.AweContext tree = parser.awe();

        try {
            AweVisitor visitor = new AweVisitor();
            visitor.visit(tree);

            DataFunction function = visitor.getBaseFunction();

            if (function.parse()) {
                return null;
            }

            return function;
        } catch (RuntimeException e) {
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
