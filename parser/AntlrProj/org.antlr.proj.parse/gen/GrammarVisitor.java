// Generated from C:/Users/Prafull/IdeaProjects/AntlrProj/org.antlr.proj.parse/src\Grammar.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GrammarParser#programBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramBody(GrammarParser.ProgramBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#subprogramBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprogramBody(GrammarParser.SubprogramBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#declarativePart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclarativePart(GrammarParser.DeclarativePartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#basicDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicDeclaration(GrammarParser.BasicDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#objectDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectDeclaration(GrammarParser.ObjectDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#subprogramSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprogramSpecification(GrammarParser.SubprogramSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#formalPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalPart(GrammarParser.FormalPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#parameterSpecification}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterSpecification(GrammarParser.ParameterSpecificationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#typeDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDeclaration(GrammarParser.TypeDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#typeDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDefinition(GrammarParser.TypeDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#enumerationTypeDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnumerationTypeDefinition(GrammarParser.EnumerationTypeDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#arrayTypeDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayTypeDefinition(GrammarParser.ArrayTypeDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#sequenceOfStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequenceOfStatements(GrammarParser.SequenceOfStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(GrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#simpleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStatement(GrammarParser.SimpleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#returnStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStatement(GrammarParser.ReturnStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(GrammarParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(GrammarParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStatement(GrammarParser.LoopStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(GrammarParser.ConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#iterationScheme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationScheme(GrammarParser.IterationSchemeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(GrammarParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#procedureCallStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureCallStatement(GrammarParser.ProcedureCallStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#actualParameterPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualParameterPart(GrammarParser.ActualParameterPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex(GrammarParser.IndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(GrammarParser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(GrammarParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(GrammarParser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(GrammarParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(GrammarParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(GrammarParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#unaryAddingOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryAddingOperator(GrammarParser.UnaryAddingOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#multiplyingOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplyingOperator(GrammarParser.MultiplyingOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#binaryAddingOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryAddingOperator(GrammarParser.BinaryAddingOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link GrammarParser#relationalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOperator(GrammarParser.RelationalOperatorContext ctx);
}