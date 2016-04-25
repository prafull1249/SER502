grammar Grammar;

subprogramBody : subprogramSpecification '{' declarativePart sequenceOfStatements '}' ;

declarativePart : ( basicDeclaration )* ;

basicDeclaration : objectDeclaration 
				 | numberDeclaration
				 | typeDeclaration 
				 | subprogramBody
				 ;

objectDeclaration : typeDefinition identifierList ;

numberDeclaration : identifierList  '=' <static>expression  ;

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

sequenceOfStatements : statement ( statement )* ;

statement : simpleStatement 
		  | compoundStatement
		  ;

simpleStatement : assignmentStatement
				| procedureCallStatement 
				| returnStatement
				;

returnStatement : 'return' expression ;

compoundStatement : ifStatement 
				  | loopStatement
				  ;

ifStatement : 'if' condition '{' sequenceOfStatements ( 'elif' condition sequenceOfStatements )*  ('else' sequenceOfStatements )? '}' ;

loopStatement : iterationScheme '{' sequenceOfStatements '}' ;

condition : <boolean>expression ;

iterationScheme : 'while' '(' condition ')' ;

assignmentStatement : <variable>name '=' expression ;

procedureCallStatement : <procedure>name actualParameterPart ;

actualParameterPart : '(' ( expression ( ',' expression )* )* ')' ;

index : simpleExpression 
	  | <type>name
	  ;

identifierList : Identifier ( ',' Identifier )*;

expression : simpleExpression ( relationalOperator simpleExpression )? ;

simpleExpression : ( unaryAddingOperator )? term ( binaryAddingOperator term )* ;

term : ( unaryAddingOperator )? factor | factor ( multiplyingOperator factor )*  ;

factor : NumericLiteral 
	   | BooleanLiteral 
	   | name 
	   | '(' expression ')'
	   ;

name : Identifier ( indexedComponent )? ;

indexedComponent : '(' expression ( ',' expression )* ')' ;

unaryAddingOperator : '+' 
					| '-'
					;

multiplyingOperator : '*' 
					| '/'
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
				   ;
Identifier : [a-zA-Z][a-zA-Z0-9]* ;             // match lower-case identifiers
WhiteSpaces : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
BooleanLiteral : 'true' | 'false';
NumericLiteral : [0-9]+;