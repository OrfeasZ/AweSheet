// Generated from AweFuncParser.g4 by ANTLR 4.5.1
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
		COMMA=1, EQUALS=2, LPAREN=3, RPAREN=4, DOT=5, PLUS=6, MINUS=7, QUOTES=8, 
		CELL_IDENTIFIER=9, IDENTIFIER=10, NUMBER=11, STRING=12, WS=13;
	public static final int
		RULE_aweFunction = 0, RULE_aweParameters = 1, RULE_aweParameter = 2;
	public static final String[] ruleNames = {
		"aweFunction", "aweParameters", "aweParameter"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'='", "'('", "')'", "'.'", "'+'", "'-'", "'\"'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMA", "EQUALS", "LPAREN", "RPAREN", "DOT", "PLUS", "MINUS", "QUOTES", 
		"CELL_IDENTIFIER", "IDENTIFIER", "NUMBER", "STRING", "WS"
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
	public String getGrammarFileName() { return "AweFuncParser.g4"; }

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
			if ( listener instanceof AweFuncParserListener ) ((AweFuncParserListener)listener).enterAweFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncParserListener ) ((AweFuncParserListener)listener).exitAweFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AweFuncParserVisitor ) return ((AweFuncParserVisitor<? extends T>)visitor).visitAweFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AweFunctionContext aweFunction() throws RecognitionException {
		AweFunctionContext _localctx = new AweFunctionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_aweFunction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(6);
			match(EQUALS);
			setState(7);
			match(IDENTIFIER);
			setState(8);
			match(LPAREN);
			setState(9);
			aweParameters();
			setState(10);
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
			if ( listener instanceof AweFuncParserListener ) ((AweFuncParserListener)listener).enterAweParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncParserListener ) ((AweFuncParserListener)listener).exitAweParameters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AweFuncParserVisitor ) return ((AweFuncParserVisitor<? extends T>)visitor).visitAweParameters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AweParametersContext aweParameters() throws RecognitionException {
		AweParametersContext _localctx = new AweParametersContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_aweParameters);
		int _la;
		try {
			setState(21);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(12);
				aweParameter();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(13);
				aweParameter();
				setState(18);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(14);
					match(COMMA);
					setState(15);
					aweParameter();
					}
					}
					setState(20);
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
		public TerminalNode NUMBER() { return getToken(AweFuncParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(AweFuncParser.STRING, 0); }
		public AweParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aweParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncParserListener ) ((AweFuncParserListener)listener).enterAweParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AweFuncParserListener ) ((AweFuncParserListener)listener).exitAweParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AweFuncParserVisitor ) return ((AweFuncParserVisitor<? extends T>)visitor).visitAweParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AweParameterContext aweParameter() throws RecognitionException {
		AweParameterContext _localctx = new AweParameterContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_aweParameter);
		try {
			setState(27);
			switch (_input.LA(1)) {
			case CELL_IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(23);
				match(CELL_IDENTIFIER);
				}
				break;
			case EQUALS:
				enterOuterAlt(_localctx, 2);
				{
				setState(24);
				aweFunction();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(25);
				match(NUMBER);
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(26);
				match(STRING);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\17 \4\2\t\2\4\3\t"+
		"\3\4\4\t\4\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3\23\n\3\f\3\16\3"+
		"\26\13\3\5\3\30\n\3\3\4\3\4\3\4\3\4\5\4\36\n\4\3\4\2\2\5\2\4\6\2\2!\2"+
		"\b\3\2\2\2\4\27\3\2\2\2\6\35\3\2\2\2\b\t\7\4\2\2\t\n\7\f\2\2\n\13\7\5"+
		"\2\2\13\f\5\4\3\2\f\r\7\6\2\2\r\3\3\2\2\2\16\30\5\6\4\2\17\24\5\6\4\2"+
		"\20\21\7\3\2\2\21\23\5\6\4\2\22\20\3\2\2\2\23\26\3\2\2\2\24\22\3\2\2\2"+
		"\24\25\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\27\16\3\2\2\2\27\17\3\2\2\2"+
		"\30\5\3\2\2\2\31\36\7\13\2\2\32\36\5\2\2\2\33\36\7\r\2\2\34\36\7\16\2"+
		"\2\35\31\3\2\2\2\35\32\3\2\2\2\35\33\3\2\2\2\35\34\3\2\2\2\36\7\3\2\2"+
		"\2\5\24\27\35";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}