grammar AweFunc;

@header { package com.awesheet.grammar; }

awe
    : aweFunction
    ;

aweFunction
    : EQUALS IDENTIFIER LPAREN aweParameters RPAREN
    ;

aweParameters
    : aweParameter
    | aweParameter (COMMA aweParameter)*
    ;

aweParameter
    : CELL_IDENTIFIER
    | aweFunction
    | VALUE
    ;

/* Lexer Rules */

COMMA : ',' ;
EQUALS : '=' ;
LPAREN : '(' ;
RPAREN : ')' ;

IDENTIFIER
	: Identifier_start_character Identifier_part_character*
	;

fragment Identifier_start_character
	: [a-zA-Z_]
	;

fragment Identifier_part_character
	: [a-zA-Z0-9_]
	;

CELL_IDENTIFIER
    : Cell_identifier_column Cell_identifier_row
    ;

fragment Cell_identifier_column
	: [A-Z]+
	;

fragment Cell_identifier_row
	: [0-9]+
	;

VALUE
    : Regular_value+
    ;

fragment Regular_value
    : .+?
    ;

WS
	: [ \r\t\n]+ -> skip ;