// Generated from AweFunc.g4 by ANTLR 4.5.1
 package com.awesheet.grammar; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AweFuncParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AweFuncVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AweFuncParser#awe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAwe(AweFuncParser.AweContext ctx);
	/**
	 * Visit a parse tree produced by {@link AweFuncParser#aweFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAweFunction(AweFuncParser.AweFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AweFuncParser#aweParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAweParameters(AweFuncParser.AweParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link AweFuncParser#aweParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAweParameter(AweFuncParser.AweParameterContext ctx);
}