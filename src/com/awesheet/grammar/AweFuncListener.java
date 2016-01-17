// Generated from AweFunc.g4 by ANTLR 4.5.1
 package com.awesheet.grammar; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AweFuncParser}.
 */
public interface AweFuncListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AweFuncParser#awe}.
	 * @param ctx the parse tree
	 */
	void enterAwe(AweFuncParser.AweContext ctx);
	/**
	 * Exit a parse tree produced by {@link AweFuncParser#awe}.
	 * @param ctx the parse tree
	 */
	void exitAwe(AweFuncParser.AweContext ctx);
	/**
	 * Enter a parse tree produced by {@link AweFuncParser#aweFunction}.
	 * @param ctx the parse tree
	 */
	void enterAweFunction(AweFuncParser.AweFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AweFuncParser#aweFunction}.
	 * @param ctx the parse tree
	 */
	void exitAweFunction(AweFuncParser.AweFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AweFuncParser#aweParameters}.
	 * @param ctx the parse tree
	 */
	void enterAweParameters(AweFuncParser.AweParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link AweFuncParser#aweParameters}.
	 * @param ctx the parse tree
	 */
	void exitAweParameters(AweFuncParser.AweParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link AweFuncParser#aweParameter}.
	 * @param ctx the parse tree
	 */
	void enterAweParameter(AweFuncParser.AweParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link AweFuncParser#aweParameter}.
	 * @param ctx the parse tree
	 */
	void exitAweParameter(AweFuncParser.AweParameterContext ctx);
}