import org.antlr.v4.misc.OrderedHashMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;


/**
 * Created by Prafull on 4/19/2016.
 */


public class visitMain extends GrammarBaseVisitor<String>{

    /**
     * Visit a parse tree produced by {@link GrammarParser#subprogramBody}.
     */
    private BufferedWriter bw = null;
    private FileWriter fw= null;
    private File file = null;
    private OrderedHashMap<String,FunctionClass> mapFunc = null;
    private boolean isMainDefined = false;

    public visitMain() throws IOException {
        file = new File("intermediate.sc");
        // if file doesnt exists, then create it
        if (file.exists()) {
            file.delete();
            file.createNewFile();
        }
        mapFunc = new OrderedHashMap<String, FunctionClass>();
    }

    private boolean isNumeric(String test){
        boolean flag = true;
        for(int i=0;i<test.length();i++){

            if(!Character.isDigit(test.charAt(i))){
                flag = false;
            }
        }
        return flag;
    }

    private void writeToFile(String content) throws IOException {
        try {
            fw = new FileWriter(file.getAbsoluteFile(),true);
            bw = new BufferedWriter(fw);
            bw.write(content+"\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String visitGlobalStatement(GrammarParser.GlobalStatementContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public String visitTypeDeclaration(GrammarParser.TypeDeclarationContext ctx) {
        try {
            String[] id = ctx.getChild(1).getText().split(",");
            for (String anId : id) {
                if (mapFunc.get(mapFunc.getKey(mapFunc.size() - 1)).getParamMap().containsKey(anId) ||
                        mapFunc.get(mapFunc.getKey(mapFunc.size() - 1)).getLocalMap().containsKey(anId)) {
                    throw new Exception("Variable" + anId + " already defined..!");
                }
                if (ctx.getChild(0).getText().equals("int")) {
                    mapFunc.get(mapFunc.getKey(mapFunc.size() - 1)).getLocalMap().put(anId, FunctionClass.typeData.INT);
                    writeToFile("idec " + anId);
                } else if (ctx.getChild(0).getText().equals("bool")) {
                    mapFunc.get(mapFunc.getKey(mapFunc.size() - 1)).getLocalMap().put(anId, FunctionClass.typeData.BOOL);
                    writeToFile("bdec " + anId);
                }
            }
            return null;
        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
    public String visitProgramBody(GrammarParser.ProgramBodyContext ctx) {
        try {
            /*
            for(int i = 0 ; i< ctx.getChildCount();i++){
                visitSubprogramBody((GrammarParser.SubprogramBodyContext) ctx.getChild(i));
            }
            */

            visitChildren(ctx);
            System.out.println("The total functions are " + ctx.getChildCount());
            System.out.println("Functions " + ctx.getChildCount());
            if(!isMainDefined)
                    throw new Exception("Main function not defined.!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public String visitSubprogramBody(GrammarParser.SubprogramBodyContext ctx){

        try {

            for (int i =0;i< ctx.getChildCount();i++) {
                if (ctx.getChild(i).getText().equals("{"))
                    if(ctx.getChild(ctx.getChildCount() - 1).getText().equals("}"))
                        System.out.println("we are good");
                    else {
                        System.out.println("no appropriate blocks for the blcoks.");
                        throw new Exception("no appropriate blocks for the blcoks.");
                    }
            }
            visitChildren(ctx);
            System.out.println("Subprogram" + ctx.getChildCount());
            if (ctx.getChildCount() == 1) {
                System.out.println(ctx.getChild(0));
            }
            writeToFile("func_end");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public String visitExpression(GrammarParser.ExpressionContext ctx){
        try {
           if( ctx.getChildCount() > 1 ){

               System.out.println("Visiting expression - following are the kids \n 0 -" + ctx.getChild(0).getText() + " \n1 - " + ctx.getChild(1).getText()+ " \n2 - "+ ctx.getChild(2).getText());
               if(ctx.getChild(0).getChildCount()>1 || ctx.getChild(2).getChildCount()>1)
                   throw new Exception("condition does  not allow expressions as operands");
               if(ctx.getChild(1).getText().equals(">")){
                    writeToFile("gt "+ctx.getChild(0).getText()+" "+ ctx.getChild(2).getText());
                }else if(ctx.getChild(1).getText().equals("<")){
                    writeToFile("lt "+ctx.getChild(0).getText()+" "+ ctx.getChild(2).getText());
                }else if(ctx.getChild(1).getText().equals(">=")){
                    writeToFile("gte "+ctx.getChild(0).getText()+" "+ ctx.getChild(2).getText());
                } else if(ctx.getChild(1).getText().equals("<=")){
                    writeToFile("lte "+ctx.getChild(0).getText()+" "+ ctx.getChild(2).getText());
                }else if(ctx.getChild(1).getText().equals("==")){
                    writeToFile("eq "+ctx.getChild(0).getText()+" "+ ctx.getChild(2).getText());
                }
            }
            else
                visitChildren(ctx);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String visitSimpleExpression(GrammarParser.SimpleExpressionContext ctx) {
        System.out.println("SimpleExpression " + ctx.getChildCount());
        System.out.println("SimpleExpression kids are" + ctx.getChild(0).getText());
        Stack<String> OpStack = new Stack<String>();
        Stack<String> operStack = new Stack<String>();

        if(ctx.getChildCount()>=3){

            for(int i = 0; i< ctx.getChildCount() ;i++) {
                System.out.println("i child " + ctx.getChild(i).getText());
                if(ctx.getChild(i).getChildCount()>=1) {
                    if(ctx.getChild(i).getText().equals("+")  || ctx.getChild(i).getText().equals("-")) {
                        operStack.push(ctx.getChild(i).getText());
                        System.out.println("pushing " + ctx.getChild(i).getText() + " in to stack");
                    }
                    else if(ctx.getChild(i).getText().equals("<=")
                            || ctx.getChild(i).getText().equals(">=") || ctx.getChild(i).getText().equals("<")
                            || ctx.getChild(i).getText().equals(">") || ctx.getChild(i).getText().equals("==")) {
                        operStack.push(ctx.getChild(i).getText());
                        System.out.println("pushing " + ctx.getChild(i).getText() + " in to stack");
                    }
                    else{
                        OpStack.push(null);
                        System.out.println("visiting chidren of "+ ctx.getChild(i).getText());
                        visitTerm((GrammarParser.TermContext) ctx.getChild(i));
                    }
                }
                if (OpStack.size() == 2) {
                    try {
                        OpStack.pop();
                        OpStack.pop();
                        if (!operStack.isEmpty()) {
                            if (operStack.pop().equals("+"))
                                writeToFile("add");
                            else
                                writeToFile("sub");
                            OpStack.push(null);
                        } else {
                            System.out.println("error");
                            throw new Exception("expression is not appropriate");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("should pop out of the stack here..!");
            }
        } else
            return super.visitSimpleExpression(ctx);
        return null;
    }


    @Override
    public String visitTerm(GrammarParser.TermContext ctx) {
        System.out.println("visitTerm" + ctx.getChildCount());
        Stack<String> OpStack = new Stack<String>();
        Stack<String> operStack = new Stack<String>();
        System.out.println("visiting the child " + ctx.getChild(0).getText());
        if(ctx.getChildCount()==1){

            if(ctx.getChild(0).getChildCount()>2){
                visitFactor((GrammarParser.FactorContext) ctx.getChild(0));
            }
            else{
                try {
                    if (isNumeric(ctx.getChild(0).getText()))
                        writeToFile("istore " + ctx.getChild(0).getText());
                    else {
                        String var = ctx.getChild(0).getText();
                        if(!mapFunc.get(mapFunc.getKey(mapFunc.size()-1)).getParamMap().containsKey(var) &&
                                !mapFunc.get(mapFunc.getKey(mapFunc.size()-1)).getLocalMap().containsKey(var) )
                        {
                            throw new Exception("Variable " + var + " not defined..!");
                        }
                        writeToFile("store " + var);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return null;
                }
            }

        }
        else if(ctx.getChildCount()>2){
            System.out.println(ctx.getChild(0).getText()    );
            for(int i = 0; i< ctx.getChildCount() ;i++){
                String child = ctx.getChild(i).getText();
                if(child.equals("*") || child.equals("/")){
                    operStack.push(child);
                }
                else if(ctx.getChild(i).getChildCount()>1){
                    visitFactor((GrammarParser.FactorContext) ctx.getChild(i));
                    OpStack.push(null);
                    System.out.println("Factor is visitied");
                }else{
                    OpStack.push(child);
                }
                if(OpStack.size()==2){
                    try {
                        String temp = OpStack.pop();
                        if(temp != null )
                            writeToFile("store "+temp);
                        temp = OpStack.pop();
                        if(temp != null )
                            writeToFile("store "+temp);
                        if(!operStack.isEmpty()){
                            if(operStack.pop().equals("*"))
                                writeToFile("mul");
                            else
                                writeToFile("divide");
                            OpStack.push(null);
                        }
                        else{
                            System.out.println("error");
                            throw new Exception("expression is not appropriate");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String visitIfStatement(GrammarParser.IfStatementContext ctx) {
        int i = 0;
        try {
            Stack<String>  st = new Stack<String>();
            Stack<String>  stIf = new Stack<String>();
            while(i<ctx.getChildCount()){
                System.out.println("while current child is"+ctx.getChild(i).getText());
                if(ctx.getChild(i).getText().equals("if") || ctx.getChild(i).getText().equals("elif") || ctx.getChild(i).getText().equals("else")  ) {
                    System.out.println("if elif else found");
                    stIf.push(ctx.getChild(i).getText());

                    if(ctx.getChild(i).getText().equals("else")){
                        if ( !ctx.getChild(i + 1).getText().equals("{")) {
                            throw new Exception("if statement is not appropriate");
                        }
                        //writeToFile(ctx.getChild(i).getText()+"_start");
                        writeToFile(ctx.getChild(i).getText()+"_block_start");
                        i = i + 2;
                    }else{
                        if ( !ctx.getChild(i + 2).getText().equals("{")) {
                            throw new Exception("if statement is not appropriate");
                        }
                        visitCondition((GrammarParser.ConditionContext) ctx.getChild(i+1));
                        writeToFile(ctx.getChild(i).getText()+"_start");
                        writeToFile(ctx.getChild(i).getText()+"_block_start");
                        i= i + 3;
                    }
                    st.push("{");
                    if(i < ctx.getChildCount()) {
                        visitSequenceOfStatements((GrammarParser.SequenceOfStatementsContext) ctx.getChild(i));
                    }
                    else{
                        throw new Exception("The Sequenceof statements is missing.");
                    }
                    i=i+1;
                }
                else if(ctx.getChild(i).getText().equals("}")) {
                    st.pop();
                    String temp = stIf.pop();
                    writeToFile(temp+"_block_end");
                    //writeToFile(temp+"_end");
                    i = i + 1;
                }
            }
            if(!st.isEmpty())
                throw new Exception("Extra braces in if statement");
            else
                writeToFile("if_end");
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public String visitCondition(GrammarParser.ConditionContext ctx) {


        return super.visitCondition(ctx);
    }

    @Override
    public String visitReturnStatement(GrammarParser.ReturnStatementContext ctx) {
        try {
            writeToFile("return "+ ctx.getChild(1).getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitFactor(GrammarParser.FactorContext ctx) {
        System.out.println("visitFactor" + ctx.getChildCount());
        return super.visitFactor(ctx);
    }

    @Override
    public String visitName(GrammarParser.NameContext ctx) {
        System.out.println("visitName" + ctx.getChildCount());
        return super.visitName(ctx);
    }

    @Override
    public String visitRelationalOperator(GrammarParser.RelationalOperatorContext ctx) {
        System.out.println("visitRelationalOperator "+ ctx.getChild(0).getText()+ " " + ctx.getChildCount());
        return super.visitRelationalOperator(ctx);
    }

    @Override
    public String visitMultiplyingOperator(GrammarParser.MultiplyingOperatorContext ctx) {
        System.out.println("multiply operator" + ctx.getChildCount());
        return super.visitMultiplyingOperator(ctx);
    }

    @Override
    public String visitSubprogramSpecification(GrammarParser.SubprogramSpecificationContext ctx) {

        System.out.println("subprogramSpecification "+ ctx.getText() +" " + ctx.getChildCount());

        try {
            if(!mapFunc.containsKey(ctx.getChild(1).getText()))
            {
                String name = ctx.getChild(1).getText();
                writeToFile("func_start "+ name);
                if(name.equals("main"))
                    isMainDefined = true;
                mapFunc.put(ctx.getChild(1).getText(), new FunctionClass(ctx.getChild(1).getText()));
            }
            else
                throw new Exception("function "+ ctx.getChild(1).getText() + "already defined !");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return super.visitSubprogramSpecification(ctx);
    }

    @Override
    public String visitStackStatement(GrammarParser.StackStatementContext ctx){
         try {

             if(ctx.getChild(0).getText().equals("push")){
                 visitExpression((GrammarParser.ExpressionContext) ctx.getChild(4));
                 writeToFile("push "+ctx.getChild(2).getText());
             }else  if(ctx.getChild(0).getText().equals("pop")){
                 //visitExpression((GrammarParser.ExpressionContext) ctx.getChild(2));
                 writeToFile("pop "+ctx.getChild(2).getText());
             }else  if(ctx.getChild(0).getText().equals("peek")){
                 //visitExpression((GrammarParser.ExpressionContext) ctx.getChild(2));
                 writeToFile("peek "+ctx.getChild(2).getText());
             }
             else{
                 throw new Exception("error in stackstatement");
             }
             System.out.println("push  "+ ctx.getChild(ctx.getChildCount()-2).getText() +" " + ctx.getChildCount());
        } catch (IOException e) {
            e.printStackTrace();
             return null;
        } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
        return null;
    }


    @Override
    public String visitFormalPart(GrammarParser.FormalPartContext ctx) {
        try {
            writeToFile("treturn "+ctx.getChild(ctx.getChildCount()-2).getText());
            System.out.println("formal part "+ ctx.getChild(ctx.getChildCount()-2).getText() +" " + ctx.getChildCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitFormalPart(ctx);
    }

    @Override
    public String visitDeclarativePart(GrammarParser.DeclarativePartContext ctx) {
        System.out.println("visitDeclarativePart: " + ctx.getChildCount());
        return super.visitDeclarativePart(ctx);
    }

    @Override
    public String visitObjectDeclaration(GrammarParser.ObjectDeclarationContext ctx) {
        try {
            System.out.println("visitObjectDeclarationPart: " + ctx.getChild(0).getText() + " " + ctx.getChild(1).getText() + " " + ctx.getChildCount());
            String[] id = ctx.getChild(1).getText().split(",");
            for (String anId : id) {
                if(mapFunc.get(mapFunc.getKey(mapFunc.size()-1)).getParamMap().containsKey(anId) ||
                        mapFunc.get(mapFunc.getKey(mapFunc.size()-1)).getLocalMap().containsKey(anId) )
                {
                    //dynamic scoping
                    //throw new Exception("Variable" + anId + " already defined..!");
                }
                if (ctx.getChild(0).getText().equals("int")) {
                    mapFunc.get(mapFunc.getKey(mapFunc.size()-1)).getLocalMap().put(anId, FunctionClass.typeData.INT);
                    writeToFile("idec " + anId);
                } else if (ctx.getChild(0).getText().equals("bool")) {
                    mapFunc.get(mapFunc.getKey(mapFunc.size()-1)).getLocalMap().put(anId, FunctionClass.typeData.BOOL);
                    writeToFile("bdec " + anId);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("exception encountered..!");
        }
        return super.visitObjectDeclaration(ctx);
    }

    @Override
    public String visitParameterSpecification(GrammarParser.ParameterSpecificationContext ctx) {
        try {
            if (ctx.getChild(0).getText().equals("int")){
                mapFunc.get(mapFunc.getKey(mapFunc.size()-1)).populateParamMap(ctx.getChild(1).getText(),0);
                writeToFile("iparam "+ctx.getChild(1).getText());
            }
            else if(ctx.getChild(0).getText().equals("bool")){
                mapFunc.get(mapFunc.getKey(mapFunc.size()-1)).populateParamMap(ctx.getChild(1).getText(),1);
                writeToFile("bparam "+ctx.getChild(1).getText());
            }
            //System.out.println("parameter specification "+ ctx.getChild(0).getText() +" " + ctx.getChild(1).getText() +" " + ctx.getChildCount());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.visitParameterSpecification(ctx);
    }

    @Override
    public String visitAssignmentStatement(GrammarParser.AssignmentStatementContext ctx) {
        //visitExpression((GrammarParser.ExpressionContext) ctx.getChild(2));
        visitChildren(ctx);
        try {
            writeToFile("assign "+ctx.getChild(0).getText());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String visitProcedureCallStatement(GrammarParser.ProcedureCallStatementContext ctx) {

        try{
            if(!mapFunc.containsKey(ctx.getChild(0).getText())){
                throw new Exception("The function " + ctx.getChild(0).getText() + "is not defined...!");
            }
            else if(!ctx.getChild(1).getChild(0).getText().equals("(") || !ctx.getChild(1).getChild(ctx.getChild(1).getChildCount()-1).getText().equals(")")){
                throw new Exception("The function " + ctx.getChild(0).getText() + " call does not have proper syntax");
            }
            else{
                //visitActualParameterPart((GrammarParser.ActualParameterPartContext) ctx.getChild(1));
                String temp = ctx.getChild(1).getText();
                String[] params = temp.substring(1,temp.length()-1).split(",");
                System.out.println("params = "+ Arrays.deepToString(params));
                int paramLen = 0;
                for(int i=0;i< params.length;i++){
                    if(params[i].length()!=0)
                        paramLen += 1;
                    if(params[i].equals("void"))
                        paramLen = paramLen-1;
                }
                int actualParamLen = mapFunc.get(ctx.getChild(0).getText()).getParamMap().size();
                HashMap<String,FunctionClass.typeData> paramMap =mapFunc.get(ctx.getChild(0).getText()).getParamMap();
                for(String s: paramMap.keySet()){
                    if(paramMap.get(s) == FunctionClass.typeData.VOID)
                        actualParamLen = actualParamLen -1;
                }
                if(paramLen!=actualParamLen) {
                    System.out.println("The mapfunc has "+actualParamLen);
                    System.out.println("params.length = "+ paramLen);
                    throw new Exception("Number of parameters of Function prototype and function call do not match.!");
                }
                visitActualParameterPart((GrammarParser.ActualParameterPartContext) ctx.getChild(1));
                writeToFile("call " + ctx.getChild(0).getText() + " "+ params.length);
            }
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public String visitLoopStatement(GrammarParser.LoopStatementContext ctx) {
        try {
            if (!ctx.getChild(1).getText().equals("{") || !ctx.getChild(3).getText().equals("}")) {
                throw new Exception("Check braces in While loop with condition "+ctx.getChild(0).getText());
            }
            visitIterationScheme((GrammarParser.IterationSchemeContext) ctx.getChild(0));
            writeToFile("loop_start");
            visitSequenceOfStatements((GrammarParser.SequenceOfStatementsContext) ctx.getChild(2));
            writeToFile("loop_end");
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public String visitStatement(GrammarParser.StatementContext ctx) {
        try {
            if(ctx.getChild(0).getText().equals("{"))
                if(ctx.getChild(ctx.getChildCount()-1).getText().equals("}"))
                {
                    writeToFile("block_start");
                    visitChildren(ctx);
                    writeToFile("block_end");
                }
                else
                    throw new Exception("check Braces in the function " + mapFunc.getKey(mapFunc.size()-1));
            else
                visitChildren(ctx);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
