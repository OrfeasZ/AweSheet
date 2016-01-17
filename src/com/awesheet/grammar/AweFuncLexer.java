// Generated from AweFunc.g4 by ANTLR 4.5.1
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
		COMMA=1, EQUALS=2, LPAREN=3, RPAREN=4, IDENTIFIER=5, CELL_IDENTIFIER=6, 
		VALUE=7, WS=8;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"COMMA", "EQUALS", "LPAREN", "RPAREN", "IDENTIFIER", "Identifier_start_character", 
		"Identifier_part_character", "CELL_IDENTIFIER", "Cell_identifier_column", 
		"Cell_identifier_row", "VALUE", "Regular_value", "WS"
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


	public AweFuncLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AweFunc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\nN\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\7\6(\n\6\f\6\16\6+\13\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\n\6\n\65\n\n\r"+
		"\n\16\n\66\3\13\6\13:\n\13\r\13\16\13;\3\f\6\f?\n\f\r\f\16\f@\3\r\6\r"+
		"D\n\r\r\r\16\rE\3\16\6\16I\n\16\r\16\16\16J\3\16\3\16\3E\2\17\3\3\5\4"+
		"\7\5\t\6\13\7\r\2\17\2\21\b\23\2\25\2\27\t\31\2\33\n\3\2\7\5\2C\\aac|"+
		"\6\2\62;C\\aac|\3\2C\\\3\2\62;\5\2\13\f\17\17\"\"N\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\21\3\2\2\2\2\27\3\2\2\2\2"+
		"\33\3\2\2\2\3\35\3\2\2\2\5\37\3\2\2\2\7!\3\2\2\2\t#\3\2\2\2\13%\3\2\2"+
		"\2\r,\3\2\2\2\17.\3\2\2\2\21\60\3\2\2\2\23\64\3\2\2\2\259\3\2\2\2\27>"+
		"\3\2\2\2\31C\3\2\2\2\33H\3\2\2\2\35\36\7.\2\2\36\4\3\2\2\2\37 \7?\2\2"+
		" \6\3\2\2\2!\"\7*\2\2\"\b\3\2\2\2#$\7+\2\2$\n\3\2\2\2%)\5\r\7\2&(\5\17"+
		"\b\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\f\3\2\2\2+)\3\2\2\2,-"+
		"\t\2\2\2-\16\3\2\2\2./\t\3\2\2/\20\3\2\2\2\60\61\5\23\n\2\61\62\5\25\13"+
		"\2\62\22\3\2\2\2\63\65\t\4\2\2\64\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2\2"+
		"\2\66\67\3\2\2\2\67\24\3\2\2\28:\t\5\2\298\3\2\2\2:;\3\2\2\2;9\3\2\2\2"+
		";<\3\2\2\2<\26\3\2\2\2=?\5\31\r\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2"+
		"\2\2A\30\3\2\2\2BD\13\2\2\2CB\3\2\2\2DE\3\2\2\2EF\3\2\2\2EC\3\2\2\2F\32"+
		"\3\2\2\2GI\t\6\2\2HG\3\2\2\2IJ\3\2\2\2JH\3\2\2\2JK\3\2\2\2KL\3\2\2\2L"+
		"M\b\16\2\2M\34\3\2\2\2\t\2)\66;@EJ\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}