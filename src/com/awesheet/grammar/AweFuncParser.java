// Generated from AweFunc.g4 by ANTLR 4.5.1
 package com.awesheet.grammar; 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AweFuncParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, EQUALS=2, LPAREN=3, RPAREN=4, IDENTIFIER=5, CELL_IDENTIFIER=6, 
		VALUE=7, WS=8;
	public static final int
		RULE_awe = 0, RULE_aweFunction = 1, RULE_aweParameters = 2, RULE_aweParameter = 3;
	public static final String[] ruleNames = {
		"awe", "aweFunction", "aweParameters", "aweParameter"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'='", "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMA", "EQUALS", "LPAREN", "RPAREN", "IDENTIFIER", "CELL_IDENTIFIER", 
		"VALUE", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "AweFunc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AweFuncParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class AweContext extends ParserRuleContext {
		public AweFunctionContext aweFunction() {
			return getRuleContext(AweFunctionContext.class,0);
		}
		public AweContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_awe; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncListener ) ((AweFuncListener)listener).enterAwe(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncListener ) ((AweFuncListener)listener).exitAwe(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AweFuncVisitor ) return ((AweFuncVisitor<? extends T>)visitor).visitAwe(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AweContext awe() throws RecognitionException {
		AweContext _localctx = new AweContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_awe);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			aweFunction();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AweFunctionContext extends ParserRuleContext {
		public TerminalNode EQUALS() { return getToken(AweFuncParser.EQUALS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(AweFuncParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(AweFuncParser.LPAREN, 0); }
		public AweParametersContext aweParameters() {
			return getRuleContext(AweParametersContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(AweFuncParser.RPAREN, 0); }
		public AweFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aweFunction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncListener ) ((AweFuncListener)listener).enterAweFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncListener ) ((AweFuncListener)listener).exitAweFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AweFuncVisitor ) return ((AweFuncVisitor<? extends T>)visitor).visitAweFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AweFunctionContext aweFunction() throws RecognitionException {
		AweFunctionContext _localctx = new AweFunctionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_aweFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			match(EQUALS);
			setState(11);
			match(IDENTIFIER);
			setState(12);
			match(LPAREN);
			setState(13);
			aweParameters();
			setState(14);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AweParametersContext extends ParserRuleContext {
		public List<AweParameterContext> aweParameter() {
			return getRuleContexts(AweParameterContext.class);
		}
		public AweParameterContext aweParameter(int i) {
			return getRuleContext(AweParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AweFuncParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AweFuncParser.COMMA, i);
		}
		public AweParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aweParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncListener ) ((AweFuncListener)listener).enterAweParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncListener ) ((AweFuncListener)listener).exitAweParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AweFuncVisitor ) return ((AweFuncVisitor<? extends T>)visitor).visitAweParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AweParametersContext aweParameters() throws RecognitionException {
		AweParametersContext _localctx = new AweParametersContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_aweParameters);
		int _la;
		try {
			setState(25);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				aweParameter();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(17);
				aweParameter();
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(18);
					match(COMMA);
					setState(19);
					aweParameter();
					}
					}
					setState(24);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AweParameterContext extends ParserRuleContext {
		public TerminalNode CELL_IDENTIFIER() { return getToken(AweFuncParser.CELL_IDENTIFIER, 0); }
		public AweFunctionContext aweFunction() {
			return getRuleContext(AweFunctionContext.class,0);
		}
		public TerminalNode VALUE() { return getToken(AweFuncParser.VALUE, 0); }
		public AweParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aweParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncListener ) ((AweFuncListener)listener).enterAweParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncListener ) ((AweFuncListener)listener).exitAweParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AweFuncVisitor ) return ((AweFuncVisitor<? extends T>)visitor).visitAweParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AweParameterContext aweParameter() throws RecognitionException {
		AweParameterContext _localctx = new AweParameterContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_aweParameter);
		try {
			setState(30);
			switch (_input.LA(1)) {
			case CELL_IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(27);
				match(CELL_IDENTIFIER);
				}
				break;
			case EQUALS:
				enterOuterAlt(_localctx, 2);
				{
				setState(28);
				aweFunction();
				}
				break;
			case VALUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(29);
				match(VALUE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\n#\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4"+
		"\27\n\4\f\4\16\4\32\13\4\5\4\34\n\4\3\5\3\5\3\5\5\5!\n\5\3\5\2\2\6\2\4"+
		"\6\b\2\2\"\2\n\3\2\2\2\4\f\3\2\2\2\6\33\3\2\2\2\b \3\2\2\2\n\13\5\4\3"+
		"\2\13\3\3\2\2\2\f\r\7\4\2\2\r\16\7\7\2\2\16\17\7\5\2\2\17\20\5\6\4\2\20"+
		"\21\7\6\2\2\21\5\3\2\2\2\22\34\5\b\5\2\23\30\5\b\5\2\24\25\7\3\2\2\25"+
		"\27\5\b\5\2\26\24\3\2\2\2\27\32\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31"+
		"\34\3\2\2\2\32\30\3\2\2\2\33\22\3\2\2\2\33\23\3\2\2\2\34\7\3\2\2\2\35"+
		"!\7\b\2\2\36!\5\4\3\2\37!\7\t\2\2 \35\3\2\2\2 \36\3\2\2\2 \37\3\2\2\2"+
		"!\t\3\2\2\2\5\30\33 ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}