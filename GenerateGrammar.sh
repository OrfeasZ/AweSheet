#!/bin/sh

cd src/com/awesheet/grammar
java -jar ../../../../Depends/bin/antlr-4.5.1-complete.jar -visitor AweFuncLexer.g4
java -jar ../../../../Depends/bin/antlr-4.5.1-complete.jar -visitor AweFuncParser.g4