grammar Math;

@header {
   package com.codit.gencode;
}

MATH  : 'Math';

LBRACE: '{';
RBRACE: '}';

LPAREN: '(';
RPAREN: ')';

NUM :   [0-9]+ ('.' [0-9]+)? ([eE] [+-]? [0-9]+)?;
ID  :   [a-zA-Z_][a-zA-Z_0-9]* ;

MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';

SEMI : ';';

ASSIGN : ':=';
PRINT : 'print';

BLOCK_COMMENT : '/*' .*? '*/' -> skip;
EOL_COMMENT : '//' .*? ('\n'|EOF) -> skip;
WS : [ \r\t\u000c\n]+ -> skip ;


compileUnit
    : MATH ID LBRACE (stm SEMI)* RBRACE EOF
    ;

stm
    : expr                     # simpleStm
    | ID ASSIGN expr           # assignStm
    | PRINT LPAREN expr RPAREN # printStm
    ;

expr
    : LPAREN expr RPAREN                 # parenExpr
    | op=(ADD|SUB) expr                  # unaryExpr
    | left=expr op=(MUL|DIV) right=expr  # infixExpr
    | left=expr op=(ADD|SUB) right=expr  # infixExpr
    | value=ID                           # idExpr
    | value=NUM                          # numberExpr
    ;