grammar Grammar;

programBody : subprogramBody (subprogramBody)* ;

subprogramBody : (globalStatement (globalStatement)*)? subprogramSpecification '{' declarativePart sequenceOfStatements '}' ;

declarativePart : ( basicDeclaration )* ;

basicDeclaration : objectDeclaration
				 | typeDeclaration 
				 | subprogramBody
				 ;

objectDeclaration : typeDefinition identifierList ;

subprogramSpecification : 'func' Identifier formalPart ;

formalPart : '(' (parameterSpecification ( ',' parameterSpecification )* )* ':' <type>name ')' ;

parameterSpecification : <type>name identifierList  ;

typeDeclaration : typeDefinition Identifier ;

typeDefinition : enumerationTypeDefinition 
			   | arrayTypeDefinition
			   | <type>name
			   ;

enumerationTypeDefinition : '(' identifierList ')' ;

arrayTypeDefinition : 'arr' '[' index ']' <type>name ;

sequenceOfStatements : statement ( statement )*;

statement : simpleStatement
		  | compoundStatement
		  | stackStatement
		  | objectDeclaration
          | typeDeclaration
          | '{' statement (statement)*'}'
          ;

globalStatement : assignmentStatement
                | typeDeclaration
                ;

simpleStatement : assignmentStatement
				| procedureCallStatement 
				| returnStatement
				| printStatement
				;

returnStatement : 'return' expression ;

compoundStatement : ifStatement 
				  | loopStatement
				  ;

ifStatement : 'if' condition '{' sequenceOfStatements  '}' ( 'elif' condition '{' sequenceOfStatements '}')*  ('else' '{' sequenceOfStatements '}' )? ;

loopStatement : iterationScheme '{' sequenceOfStatements '}' ;

condition : <boolean>expression ;

iterationScheme : 'while' '(' condition ')' ;

assignmentStatement : <variable>name '=' expression
                    | <variable>name '=' procedureCallStatement
                    ;

procedureCallStatement : <procedure>name actualParameterPart ;

printStatement : 'print' identifierList ;

stackStatement : 'push' '(' Identifier ',' expression ')'
               |  'pop' '(' Identifier ')'
               | 'peek' '(' Identifier ')'
               ;

actualParameterPart : '(' ( expression ( ',' expression )* )* ')' ;

index : simpleExpression 
	  | <type>name
	  ;

identifierList : Identifier ( ',' Identifier )*;

expression : simpleExpression ( relationalOperator simpleExpression )? ;

simpleExpression : ( unaryAddingOperator )? term ( binaryAddingOperator term )* ;

term :  factor | factor ( multiplyingOperator factor )*  ;

factor : ( unaryAddingOperator )?NumericLiteral
	   | ( unaryAddingOperator )?BooleanLiteral
	   | ( unaryAddingOperator )?name
	   | '(' expression ')'
	   ;

name : Identifier;

unaryAddingOperator : '+' 
					| '-'
					;

multiplyingOperator : '*' 
					| '/'
					| '%'
					;

binaryAddingOperator : '+' 
					 | '-'
					 ;

relationalOperator : '=' 
				   | '/=' 
				   | '<' 
				   | '<=' 
				   | '>' 
				   | '>='
				   | '=='
				   ;
Identifier : [a-zA-Z][a-zA-Z0-9]* ;             // match lower-case identifiers
WhiteSpaces : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
BooleanLiteral : 'true' | 'false';
NumericLiteral : [0-9]+;