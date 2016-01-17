// Generated from AweFuncLexer.g4 by ANTLR 4.5.1
 package com.awesheet.grammar; 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AweFuncLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMA=1, EQUALS=2, LPAREN=3, RPAREN=4, DOT=5, PLUS=6, MINUS=7, QUOTES=8, 
		CELL_IDENTIFIER=9, IDENTIFIER=10, NUMBER=11, STRING=12, WS=13;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMA", "EQUALS", "LPAREN", "RPAREN", "DOT", "PLUS", "MINUS", "QUOTES", 
		"CELL_IDENTIFIER", "Cell_identifier_column", "Cell_identifier_row", "IDENTIFIER", 
		"Identifier_start_character", "Identifier_part_character", "NUMBER", "STRING", 
		"Integer", "Double", "Hex_digits", "HEX_DIGIT", "WS"
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


	public AweFuncLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AweFuncLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\17\u0097\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\6\13B\n\13\r"+
		"\13\16\13C\3\f\6\fG\n\f\r\f\16\fH\3\r\3\r\7\rM\n\r\f\r\16\rP\13\r\3\16"+
		"\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20"+
		"n\n\20\3\21\3\21\7\21r\n\21\f\21\16\21u\13\21\3\21\3\21\3\22\6\22z\n\22"+
		"\r\22\16\22{\3\23\6\23\177\n\23\r\23\16\23\u0080\3\23\3\23\6\23\u0085"+
		"\n\23\r\23\16\23\u0086\3\24\6\24\u008a\n\24\r\24\16\24\u008b\3\25\5\25"+
		"\u008f\n\25\3\26\6\26\u0092\n\26\r\26\16\26\u0093\3\26\3\26\2\2\27\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\2\27\2\31\f\33\2\35\2\37\r!\16"+
		"#\2%\2\'\2)\2+\17\3\2\t\3\2C\\\3\2\62;\5\2C\\aac|\6\2\62;C\\aac|\4\2$"+
		"$>>\5\2\62;CHch\4\2\13\13\"\"\u009e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2"+
		"\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23"+
		"\3\2\2\2\2\31\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5"+
		"/\3\2\2\2\7\61\3\2\2\2\t\63\3\2\2\2\13\65\3\2\2\2\r\67\3\2\2\2\179\3\2"+
		"\2\2\21;\3\2\2\2\23=\3\2\2\2\25A\3\2\2\2\27F\3\2\2\2\31J\3\2\2\2\33Q\3"+
		"\2\2\2\35S\3\2\2\2\37m\3\2\2\2!o\3\2\2\2#y\3\2\2\2%~\3\2\2\2\'\u0089\3"+
		"\2\2\2)\u008e\3\2\2\2+\u0091\3\2\2\2-.\7.\2\2.\4\3\2\2\2/\60\7?\2\2\60"+
		"\6\3\2\2\2\61\62\7*\2\2\62\b\3\2\2\2\63\64\7+\2\2\64\n\3\2\2\2\65\66\7"+
		"\60\2\2\66\f\3\2\2\2\678\7-\2\28\16\3\2\2\29:\7/\2\2:\20\3\2\2\2;<\7$"+
		"\2\2<\22\3\2\2\2=>\5\25\13\2>?\5\27\f\2?\24\3\2\2\2@B\t\2\2\2A@\3\2\2"+
		"\2BC\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\26\3\2\2\2EG\t\3\2\2FE\3\2\2\2GH\3\2"+
		"\2\2HF\3\2\2\2HI\3\2\2\2I\30\3\2\2\2JN\5\33\16\2KM\5\35\17\2LK\3\2\2\2"+
		"MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2O\32\3\2\2\2PN\3\2\2\2QR\t\4\2\2R\34\3\2"+
		"\2\2ST\t\5\2\2T\36\3\2\2\2Un\5#\22\2VW\5\17\b\2WX\5#\22\2Xn\3\2\2\2YZ"+
		"\5\r\7\2Z[\5#\22\2[n\3\2\2\2\\]\7\62\2\2]^\7z\2\2^_\3\2\2\2_n\5\'\24\2"+
		"`a\5\17\b\2ab\7\62\2\2bc\7z\2\2cd\3\2\2\2de\5\'\24\2en\3\2\2\2fn\5%\23"+
		"\2gh\5\17\b\2hi\5%\23\2in\3\2\2\2jk\5\r\7\2kl\5%\23\2ln\3\2\2\2mU\3\2"+
		"\2\2mV\3\2\2\2mY\3\2\2\2m\\\3\2\2\2m`\3\2\2\2mf\3\2\2\2mg\3\2\2\2mj\3"+
		"\2\2\2n \3\2\2\2os\5\21\t\2pr\n\6\2\2qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st"+
		"\3\2\2\2tv\3\2\2\2us\3\2\2\2vw\5\21\t\2w\"\3\2\2\2xz\t\3\2\2yx\3\2\2\2"+
		"z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|$\3\2\2\2}\177\t\3\2\2~}\3\2\2\2\177\u0080"+
		"\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082"+
		"\u0084\5\13\6\2\u0083\u0085\t\3\2\2\u0084\u0083\3\2\2\2\u0085\u0086\3"+
		"\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087&\3\2\2\2\u0088\u008a"+
		"\5)\25\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c(\3\2\2\2\u008d\u008f\t\7\2\2\u008e\u008d\3\2\2\2"+
		"\u008f*\3\2\2\2\u0090\u0092\t\b\2\2\u0091\u0090\3\2\2\2\u0092\u0093\3"+
		"\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0096\b\26\2\2\u0096,\3\2\2\2\16\2CHNms{\u0080\u0086\u008b\u008e\u0093"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}