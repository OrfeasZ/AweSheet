lexer grammar AweFuncLexer;

@header { package com.awesheet.grammar; }

/* Lexer Rules */

COMMA : ',' ;
EQUALS : '=' ;
LPAREN : '(' ;
RPAREN : ')' ;
DOT : '.' ;
PLUS : '+' ;
MINUS : '-' ;
QUOTES : '"' ;

CELL_IDENTIFIER
    : Cell_identifier_column Cell_identifier_row
    ;

fragment Cell_identifier_column
	: [A-Z]+
	;

fragment Cell_identifier_row
	: [0-9]+
	;

IDENTIFIER
	: Identifier_start_character Identifier_part_character*
	;

fragment Identifier_start_character
	: [a-zA-Z_]
	;

fragment Identifier_part_character
	: [a-zA-Z0-9_]
	;

NUMBER
    : Integer
    | MINUS Integer
    | PLUS Integer
    | '0x' Hex_digits
    | MINUS '0x' Hex_digits
    | Double
    | MINUS Double
    | PLUS Double
    ;

STRING
    : QUOTES ~[<"]* QUOTES
    ;

fragment Integer
    : [0-9]+
    ;

fragment Double
    : [0-9]+ DOT [0-9]+
    ;

fragment Hex_digits
	: HEX_DIGIT+
	;

fragment HEX_DIGIT
	: [0-9]
	| [A-F]
	| [a-f]
	;

WS
    : [ \t]+ -> skip
    ;