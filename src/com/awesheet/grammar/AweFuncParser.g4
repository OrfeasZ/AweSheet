parser grammar AweFuncParser;

@header { package com.awesheet.grammar; }

options { tokenVocab=AweFuncLexer; }

/* Parser Rules */

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
    | NUMBER
    | STRING
    ;