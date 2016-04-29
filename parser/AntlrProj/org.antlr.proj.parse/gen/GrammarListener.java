// Generated from C:/Users/Prafull/work/SER502/parser/AntlrProj/org.antlr.proj.parse/src\Grammar.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GrammarParser}.
 */
public interface GrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GrammarParser#programBody}.
	 * @param ctx the parse tree
	 */
	void enterProgramBody(GrammarParser.ProgramBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#programBody}.
	 * @param ctx the parse tree
	 */
	void exitProgramBody(GrammarParser.ProgramBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#subprogramBody}.
	 * @param ctx the parse tree
	 */
	void enterSubprogramBody(GrammarParser.SubprogramBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#subprogramBody}.
	 * @param ctx the parse tree
	 */
	void exitSubprogramBody(GrammarParser.SubprogramBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#declarativePart}.
	 * @param ctx the parse tree
	 */
	void enterDeclarativePart(GrammarParser.DeclarativePartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#declarativePart}.
	 * @param ctx the parse tree
	 */
	void exitDeclarativePart(GrammarParser.DeclarativePartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#basicDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterBasicDeclaration(GrammarParser.BasicDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#basicDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitBasicDeclaration(GrammarParser.BasicDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#objectDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterObjectDeclaration(GrammarParser.ObjectDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#objectDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitObjectDeclaration(GrammarParser.ObjectDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#subprogramSpecification}.
	 * @param ctx the parse tree
	 */
	void enterSubprogramSpecification(GrammarParser.SubprogramSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#subprogramSpecification}.
	 * @param ctx the parse tree
	 */
	void exitSubprogramSpecification(GrammarParser.SubprogramSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#formalPart}.
	 * @param ctx the parse tree
	 */
	void enterFormalPart(GrammarParser.FormalPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#formalPart}.
	 * @param ctx the parse tree
	 */
	void exitFormalPart(GrammarParser.FormalPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#parameterSpecification}.
	 * @param ctx the parse tree
	 */
	void enterParameterSpecification(GrammarParser.ParameterSpecificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#parameterSpecification}.
	 * @param ctx the parse tree
	 */
	void exitParameterSpecification(GrammarParser.ParameterSpecificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(GrammarParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(GrammarParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#typeDefinition}.
	 * @param ctx the parse tree
	 */
	void enterTypeDefinition(GrammarParser.TypeDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#typeDefinition}.
	 * @param ctx the parse tree
	 */
	void exitTypeDefinition(GrammarParser.TypeDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#enumerationTypeDefinition}.
	 * @param ctx the parse tree
	 */
	void enterEnumerationTypeDefinition(GrammarParser.EnumerationTypeDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#enumerationTypeDefinition}.
	 * @param ctx the parse tree
	 */
	void exitEnumerationTypeDefinition(GrammarParser.EnumerationTypeDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#arrayTypeDefinition}.
	 * @param ctx the parse tree
	 */
	void enterArrayTypeDefinition(GrammarParser.ArrayTypeDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#arrayTypeDefinition}.
	 * @param ctx the parse tree
	 */
	void exitArrayTypeDefinition(GrammarParser.ArrayTypeDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#sequenceOfStatements}.
	 * @param ctx the parse tree
	 */
	void enterSequenceOfStatements(GrammarParser.SequenceOfStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#sequenceOfStatements}.
	 * @param ctx the parse tree
	 */
	void exitSequenceOfStatements(GrammarParser.SequenceOfStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(GrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(GrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#globalStatement}.
	 * @param ctx the parse tree
	 */
	void enterGlobalStatement(GrammarParser.GlobalStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#globalStatement}.
	 * @param ctx the parse tree
	 */
	void exitGlobalStatement(GrammarParser.GlobalStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStatement(GrammarParser.SimpleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStatement(GrammarParser.SimpleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(GrammarParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(GrammarParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(GrammarParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(GrammarParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(GrammarParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(GrammarParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStatement(GrammarParser.LoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStatement(GrammarParser.LoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(GrammarParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(GrammarParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#iterationScheme}.
	 * @param ctx the parse tree
	 */
	void enterIterationScheme(GrammarParser.IterationSchemeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#iterationScheme}.
	 * @param ctx the parse tree
	 */
	void exitIterationScheme(GrammarParser.IterationSchemeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(GrammarParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(GrammarParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#procedureCallStatement}.
	 * @param ctx the parse tree
	 */
	void enterProcedureCallStatement(GrammarParser.ProcedureCallStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#procedureCallStatement}.
	 * @param ctx the parse tree
	 */
	void exitProcedureCallStatement(GrammarParser.ProcedureCallStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(GrammarParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(GrammarParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#stackStatement}.
	 * @param ctx the parse tree
	 */
	void enterStackStatement(GrammarParser.StackStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#stackStatement}.
	 * @param ctx the parse tree
	 */
	void exitStackStatement(GrammarParser.StackStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#actualParameterPart}.
	 * @param ctx the parse tree
	 */
	void enterActualParameterPart(GrammarParser.ActualParameterPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#actualParameterPart}.
	 * @param ctx the parse tree
	 */
	void exitActualParameterPart(GrammarParser.ActualParameterPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(GrammarParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(GrammarParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierList(GrammarParser.IdentifierListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#identifierList}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierList(GrammarParser.IdentifierListContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpression(GrammarParser.SimpleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpression(GrammarParser.SimpleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(GrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(GrammarParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(GrammarParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(GrammarParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(GrammarParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(GrammarParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#unaryAddingOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryAddingOperator(GrammarParser.UnaryAddingOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#unaryAddingOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryAddingOperator(GrammarParser.UnaryAddingOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#multiplyingOperator}.
	 * @param ctx the parse tree
	 */
	void enterMultiplyingOperator(GrammarParser.MultiplyingOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#multiplyingOperator}.
	 * @param ctx the parse tree
	 */
	void exitMultiplyingOperator(GrammarParser.MultiplyingOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#binaryAddingOperator}.
	 * @param ctx the parse tree
	 */
	void enterBinaryAddingOperator(GrammarParser.BinaryAddingOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#binaryAddingOperator}.
	 * @param ctx the parse tree
	 */
	void exitBinaryAddingOperator(GrammarParser.BinaryAddingOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link GrammarParser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void enterRelationalOperator(GrammarParser.RelationalOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GrammarParser#relationalOperator}.
	 * @param ctx the parse tree
	 */
	void exitRelationalOperator(GrammarParser.RelationalOperatorContext ctx);
}