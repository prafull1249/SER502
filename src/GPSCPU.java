import java.util.*;

/**
 * Created by Satyam on 21-04-2016.
 */
public class GPSCPU {

    Map<String, Symbol> globalSymbolTable;
    Stack<ActivationFrame> activationFrameStack;
    Stack<Symbol> globalOperandStack;
    private static GPSCPU instance;
    Bytecode bytecode;
    private static int ip;


    GPSCPU(){
        globalSymbolTable = new HashMap<>();
        activationFrameStack = new Stack<>();
        globalOperandStack = new Stack<>();
    }

    public static synchronized GPSCPU getInstance(Bytecode bytecode) throws Exception {
        if (instance == null) {
            instance = new GPSCPU();
            instance.bytecode = bytecode;
        }
        return instance;
    }

    public void execute(){
        System.out.println("Executing bytecode...");
        //printByteCode();
        if(bytecode.getGlobalDeclaration().code.size() != 0){
            System.out.println(bytecode.getGlobalDeclaration().code.size());
            executeCode(bytecode.getGlobalDeclaration().code);
        }else{
            System.out.println("No global declarations found...");
        }

        if(bytecode.getFunctions().get("main").getCodeLines().code.size() != 0){
            ActivationFrame aframe = new ActivationFrame("main");
            activationFrameStack.push(aframe);
            executeCode(bytecode.getFunctions().get("main").getCodeLines().code);

        }else{
            System.out.println("Function main is empty!");
        }
    }

    public void executeCode(ArrayList<String> code){
        //System.out.println(code);
        /*
        for(Opcode o : Opcode.values()){
            String t = o.name();
            System.out.println(t);
        }
        */

        for(int ip=0; ip<code.size(); ip++){
            System.out.println(code.get(ip));
            String[] tokens = code.get(ip).split(" ");
            //System.out.println("token extracted: " + tokens[0]);
            if(isValidOpcode(tokens[0])){
                // System.out.println("Valid opcode found");
                Opcode opcode = getOpcode(tokens[0]);
                // System.out.println("Identified opcode: " + opcode);
                switch (opcode){

                    case ISTORE: {
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        int integerLiteral = Integer.parseInt(tokens[1]);
                        aFrame.operandStack.push(new Symbol(0, integerLiteral));
                        break;
                    }
                    case BSTORE: {
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        boolean booleanLiteral = Boolean.parseBoolean(tokens[1]);
                        int booleanToInteger = 0;
                        if (booleanLiteral) {
                            booleanToInteger = 1;
                        }
                        aFrame.operandStack.push(new Symbol(1, booleanToInteger));
                        break;
                    }
                    case STORE: {
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        String variableName = tokens[1];

                        if (isVariableDefined(variableName)) {
                            Symbol variable = getVariable(variableName);
                            aFrame.operandStack.push(variable);
                        }else{
                            System.out.println("Error: Variable " + variableName + " not found");
                            System.out.println("Exiting...");
                            System.exit(0);
                        }
                        break;
                    }

                    case ASSIGN: {
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        String variableName = tokens[1];

                        if(isVariableDefined(variableName)){
                            Symbol lhs = getVariable(variableName);
                            Symbol rhs = aFrame.operandStack.pop();
                            if(lhs.getType() != rhs.getType()){
                                System.out.println("Error: Incompatible type /nExiting...");
                                System.exit(0);
                            }else{
                                //System.out.println("Assigning value "+variableName+": "+rhs.getValue());
                                lhs.setValue(rhs.getValue());
                            }
                        }else{
                            System.out.println("Error: Variable " + variableName + " not found");
                            System.out.println("Exiting...");
                            System.exit(0);
                        }

                        break;
                    }

                    case ADD: {
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol operand1 = aFrame.operandStack.pop();
                        Symbol operand2 = aFrame.operandStack.pop();
                        if (operand1.getType() != 0 || operand2.getType() != 0) {
                            System.out.println("Error: Addition of only integers are supported");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        aFrame.operandStack.push(new Symbol(0, operand1.getValue() + operand2.getValue()));
                        break;
                    }

                    case SUB: {
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol operand1 = aFrame.operandStack.pop();
                        Symbol operand2 = aFrame.operandStack.pop();
                        if (operand1.getType() != 0 || operand2.getType() != 0) {
                            System.out.println("Error: Subtraction of only integers are supported");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        aFrame.operandStack.push(new Symbol(0, operand2.getValue() - operand1.getValue()));
                        break;
                    }

                    case MUL: {
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol operand1 = aFrame.operandStack.pop();
                        Symbol operand2 = aFrame.operandStack.pop();
                        if (operand1.getType() != 0 || operand2.getType() != 0) {
                            System.out.println("Error: Multiplication of only integers are supported");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        aFrame.operandStack.push(new Symbol(0, operand2.getValue() * operand1.getValue()));
                        break;
                    }

                    case DIV: {
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol operand1 = aFrame.operandStack.pop();
                        Symbol operand2 = aFrame.operandStack.pop();
                        if (operand1.getType() != 0 || operand2.getType() != 0) {
                            System.out.println("Error: Multiplication of only integers are supported");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }else if(operand1.getValue() == 0){
                            System.out.println("Error: Division by zero");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        aFrame.operandStack.push(new Symbol(0, operand2.getValue() / operand1.getValue()));
                        break;
                    }

                    case EQ:{
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol operand1 = formOperand(tokens[1]);
                        Symbol operand2 = formOperand(tokens[2]);

                        if (operand1.getType() != operand2.getType()) {
                            System.out.println("Error: Comparison between operands of different types");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        if(operand1.getValue() == operand2.getValue()){
                            aFrame.operandStack.push(new Symbol(1, 1));
                        }else{
                            aFrame.operandStack.push(new Symbol(1, 0));
                        }
                        break;
                    }

                    case GT:{
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol operand1 = formOperand(tokens[1]);
                        Symbol operand2 = formOperand(tokens[2]);

                        if (operand1.getType() != 0 || operand2.getType() != 0) {
                            System.out.println("Error: Operands are not of type integer");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        if(operand1.getValue() > operand2.getValue()){
                            aFrame.operandStack.push(new Symbol(1, 1));
                        }else{
                            aFrame.operandStack.push(new Symbol(1, 0));
                        }
                        break;
                    }

                    case GTE:{
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol operand1 = formOperand(tokens[1]);
                        Symbol operand2 = formOperand(tokens[2]);

                        if (operand1.getType() != 0 || operand2.getType() != 0) {
                            System.out.println("Error: Operands are not of type integer");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        if(operand1.getValue() >= operand2.getValue()){
                            aFrame.operandStack.push(new Symbol(1, 1));
                        }else{
                            aFrame.operandStack.push(new Symbol(1, 0));
                        }
                        break;
                    }

                    case LT:{
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();

                        Symbol operand1 = formOperand(tokens[1]);
                        Symbol operand2 = formOperand(tokens[2]);

                        if (operand1.getType() != 0 || operand2.getType() != 0) {
                            System.out.println("Error: Operands are not of type integer");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        if(operand1.getValue() < operand2.getValue()){
                            aFrame.operandStack.push(new Symbol(1, 1));
                        }else{
                            aFrame.operandStack.push(new Symbol(1, 0));
                        }
                        break;
                    }

                    case LTE:{
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();

                        Symbol operand1 = formOperand(tokens[1]);
                        Symbol operand2 = formOperand(tokens[2]);

                        if (operand1.getType() != 0 || operand2.getType() != 0) {
                            System.out.println("Error: Operands are not of type integer");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        if(operand1.getValue() <= operand2.getValue()){
                            aFrame.operandStack.push(new Symbol(1, 1));
                        }else{
                            aFrame.operandStack.push(new Symbol(1, 0));
                        }
                        break;
                    }

                    case OR:{
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();

                        Symbol operand1 = formOperand(tokens[1]);
                        Symbol operand2 = formOperand(tokens[2]);

                        if (operand1.getType() != 1 || operand2.getType() != 1) {
                            System.out.println("Error: Operands are not of type boolean");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        if(operand1.getValue() == 1 || operand2.getValue() == 1){
                            aFrame.operandStack.push(new Symbol(1, 1));
                        }else{
                            aFrame.operandStack.push(new Symbol(1, 0));
                        }
                        break;
                    }

                    case AND:{
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();

                        Symbol operand1 = formOperand(tokens[1]);
                        Symbol operand2 = formOperand(tokens[2]);

                        if (operand1.getType() != 1 || operand2.getType() != 1) {
                            System.out.println("Error: Operands are not of type boolean");
                            System.out.println("Exiting..");
                            System.exit(0);
                        }
                        if(operand1.getValue() == 1 && operand2.getValue() == 1){
                            aFrame.operandStack.push(new Symbol(1, 1));
                        }else{
                            aFrame.operandStack.push(new Symbol(1, 0));
                        }
                        break;
                    }

                    case LOOP_START: {
                        // System.out.println(opcode.name());
                        ActivationFrame aFrame = activationFrameStack.peek();

                        if(aFrame.blockStack.peek().startIndex != ip-2){

                            Block loopBlock = new Block();
                            loopBlock.startIndex = ip-2;
                            loopBlock.endIndex = 0;                             //temporary
                            aFrame.blockStack.push(loopBlock);
                        }

                        //printOperandStack(aFrame.operandStack);
                        Symbol ifExpressionResult = aFrame.operandStack.pop();

                        if(ifExpressionResult.getType()==1){
                            if(ifExpressionResult.getValue() == 0){
                                if(aFrame.blockStack.peek().endIndex <= aFrame.blockStack.peek().startIndex){
                                    String ifBlockStatement = code.get(++ip);
                                    String[] ifBlockStatementTokens = ifBlockStatement.split(" ");
                                    while(!(ifBlockStatementTokens[0].equalsIgnoreCase(Opcode.LOOP_END.toString()))){
                                        ifBlockStatement = code.get(++ip);
                                        ifBlockStatementTokens = ifBlockStatement.split(" ");
                                    }
                                }else{
                                    ip = aFrame.blockStack.peek().endIndex + 1;
                                    aFrame.blockStack.pop();
                                }
                            }
                            /*
                            else{
                                aFrame.blockStack.push(new Block());
                            }*/
                        }else{
                            System.out.println("Error: The loop-condition expression cannot be evaluated to a boolean value");
                            System.out.println("Exiting...");
                            System.exit(0);
                        }

                        break;
                    }

                    case LOOP_END:{
                        // System.out.println(opcode.name());
                        ActivationFrame aFrame = activationFrameStack.peek();
                        if(aFrame.blockStack.peek().endIndex <= aFrame.blockStack.peek().startIndex) {
                            aFrame.blockStack.peek().endIndex = ip;
                        }
                        ip = aFrame.blockStack.peek().startIndex;
                        break;
                    }

                    case FUNC_START: {
                        // System.out.println(opcode.name());
                        /*
                        String name = tokens[1];
                        if(name.equalsIgnoreCase("main")){
                            ActivationFrame aFrame = new ActivationFrame(name);
                            aFrame.blockStack.push(new Block());
                            activationFrameStack.push(aFrame);
                        }
                        */
                        break;
                    }

                    case FUNC_END: {
                        //System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol returnValue = aFrame.returnValue;
                        ip = aFrame.returnAddress;
                        activationFrameStack.pop();
                        activationFrameStack.peek().operandStack.push(returnValue);
                        break;
                    }

                    case TRETURN: {
                        //System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();

                        if(aFrame.blockStack.peek().endIndex == 0){
                            Function currentFunction = bytecode.getFunctions().get(aFrame.functionName);
                            String returnType = tokens[1];
                            if(returnType.equalsIgnoreCase("INT")){
                                currentFunction.setReturnType(0);
                            }else if(returnType.equalsIgnoreCase("BOOLEAN")){
                                currentFunction.setReturnType(1);
                            }else if(returnType.equalsIgnoreCase("VOID")){
                                currentFunction.setReturnType(-1);
                            }
                        }

                        break;
                    }

                    case IPARAM: {
                        //System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        if(aFrame.blockStack.peek().endIndex == 0){
                            String parameterName = tokens[1];
                            Block currentBlock = aFrame.blockStack.peek();

                            //System.out.println(aFrame.functionName);
                            //printOperandStack(aFrame.operandStack);
                            Symbol parameterPassed = aFrame.operandStack.pop();

                            if(parameterPassed.getType() != 0){
                                System.out.println("Error: Type of parameter passed and defined does not match./nExiting");
                                System.exit(0);
                            }
                            currentBlock.symbolTable.put(parameterName, parameterPassed);
                        }

                        break;
                    }

                    case BPARAM:{
                        System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();

                        if(aFrame.blockStack.peek().endIndex == 0){
                            String parameterName = tokens[1];
                            Block currentBlock = aFrame.blockStack.peek();
                            Symbol parameterPassed = aFrame.operandStack.pop();
                            if(parameterPassed.getType() != 1){
                                System.out.println("Error: Type of parameter passed and defined does not match./nExiting");
                                System.exit(0);
                            }
                            currentBlock.symbolTable.put(parameterName, parameterPassed);
                        }

                        break;
                    }

                    case RETURN:{
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        String returnValueString = tokens[1];
                        Symbol returnValue = formOperand(returnValueString);
                        String currentFunctionName = aFrame.functionName;

                        if(returnValue.getType() != bytecode.getFunctions().get(currentFunctionName).getReturnType()){
                            System.out.println("Error: Return type doesn't match with the definition./nExiting...");
                            System.exit(0);
                        }else{
                            aFrame.returnValue = returnValue;
                        }
                        break;
                    }

                    case CALL: {
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        String invokedFunctionName = tokens[1];
                        if(! bytecode.getFunctions().containsKey(invokedFunctionName)){
                            System.out.println("Error: Called function "+invokedFunctionName+" is not defined.\nExiting...");
                            System.exit(0);
                        }

                        int numberOfParameters = 0;
                        if(tokens.length > 2){
                            String numberOfParametersString = tokens[2];
                            if(isNumeric(numberOfParametersString)){
                                numberOfParameters = Integer.parseInt(numberOfParametersString);
                                if(numberOfParameters != bytecode.getFunctions().get(invokedFunctionName).getParams().size()){
                                    System.out.println("Error: Number of parameters passed doesn't match with the definition.\nExiting...");
                                    System.exit(0);
                                }
                            }
                        }

                        ActivationFrame calledFunctionFrame = new ActivationFrame(invokedFunctionName);
                        // System.out.println(invokedFunctionName);
                        for(int i=0; i<numberOfParameters; i++){
                            calledFunctionFrame.operandStack.push(aFrame.operandStack.pop());
                        }

                        // printOperandStack(calledFunctionFrame.operandStack);

                        calledFunctionFrame.returnAddress = ip+1;
                        activationFrameStack.push(calledFunctionFrame);
                        executeCode(bytecode.getFunctions().get(invokedFunctionName).getCodeLines().code);
                        break;
                    }

                    case PRINT:
                        //System.out.println(opcode.name());
                        // Need to check if any text will be present in the print statement. otherwise below code works fine

                        String printVariable = tokens[1];
                        if(isBoolean(printVariable) || isNumeric(printVariable)){
                            System.out.println(printVariable);
                        }else if(isVariableDefined(printVariable)){
                            Symbol variable = getVariable(printVariable);
                            if(variable.getType() == 1){
                                if(variable.getValue() == 0){
                                    System.out.println("false");
                                }else{
                                    System.out.println("true");
                                }
                            }else{
                                System.out.println(variable.getValue());
                            }
                        }

                        break;

                    case IDEC: {
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        // creating variable of int type and adding it to the current block
                        Symbol sym = new Symbol(0);
                        Block currBlock = aFrame.blockStack.peek();
                        currBlock.symbolTable.put(tokens[1], sym);

                        break;
                    }
                    case BDEC:{
                        // System.out.println(opcode.name());

                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol sym = new Symbol(1);
                        Block currBlock = aFrame.blockStack.peek();
                        currBlock.symbolTable.put(tokens[1], sym);

                        break;
                    }
                    case IF_START: {
                        // System.out.println(opcode.name());

                        // create new block and push it on the bloc stack of current activation record
                        // ActivationFrame aFrame = activationFrameStack.peek();
                        // aFrame.blockStack.push(new Block());
                        break;
                    }

                    case IF_END: {
                        // System.out.println(opcode.name());
                        // Remove the last block from the block stack of the current activation frame
                        ActivationFrame aFrame = activationFrameStack.peek();
                        aFrame.operandStack.pop();
                        break;
                    }

                    case IF_BLOCK_START:{
                        //System.out.println(opcode.name());

                        // create new block and push it on the bloc stack of current activation record
                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol ifExpressionResult = aFrame.operandStack.peek();
                        if(ifExpressionResult.getType()==1){
                            if(ifExpressionResult.getValue() == 0){
                                String ifBlockStatement = code.get(++ip);
                                String[] ifBlockStatementTokens = ifBlockStatement.split(" ");
                                while(!(ifBlockStatementTokens[0].equalsIgnoreCase(Opcode.IF_BLOCK_END.toString()))){
                                    ifBlockStatement = code.get(++ip);
                                    ifBlockStatementTokens = ifBlockStatement.split(" ");
                                }
                            }else{
                                aFrame.blockStack.push(new Block());
                            }
                        }else{
                            System.out.println("Error: The if-condition expression cannot be evaluated to a boolean value");
                            System.out.println("Exiting...");
                            System.exit(0);
                        }

                        break;
                    }

                    case IF_BLOCK_END:{
                        //System.out.println(opcode.name());
                        // Remove the last block from the block stack of the current activation frame
                        ActivationFrame aFrame = activationFrameStack.peek();
                        aFrame.blockStack.pop();
                        break;
                    }

                    case ELSE_BLOCK_START:{
                        //System.out.println(opcode.name());

                        // create new block and push it on the bloc stack of current activation record
                        ActivationFrame aFrame = activationFrameStack.peek();
                        Symbol ifExpressionResult = aFrame.operandStack.peek();
                        if(ifExpressionResult.getType()==1){
                            if(ifExpressionResult.getValue() == 1){
                                String ifBlockStatement = code.get(++ip);
                                String[] ifBlockStatementTokens = ifBlockStatement.split(" ");
                                while(!(ifBlockStatementTokens[0].equalsIgnoreCase(Opcode.ELSE_BLOCK_END.toString()))){
                                    ifBlockStatement = code.get(++ip);
                                    ifBlockStatementTokens = ifBlockStatement.split(" ");
                                }
                            }else{
                                aFrame.blockStack.push(new Block());
                            }
                        }else{
                            System.out.println("Error: The if expression cannot be evaluated to a boolean value");
                            System.out.println("Exiting...");
                            System.exit(0);
                        }

                        break;
                    }

                    case ELSE_BLOCK_END:{
                        //System.out.println(opcode.name());
                        // Remove the last block from the block stack of the current activation frame
                        ActivationFrame aFrame = activationFrameStack.peek();
                        aFrame.blockStack.pop();
                        break;
                    }

                    case ILLEGAL: {
                        System.out.println(opcode.name());
                        System.out.println("Illegal opocode: "+ tokens[0]);
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    }
                }
            }
        }
    }

    private boolean isValidOpcode(String str){
        boolean valid = false;

        for(Opcode o: Opcode.values()){
            if(o.name().equals(str.toUpperCase())) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    private Opcode getOpcode(String token){
        Opcode tokenOpcode = Opcode.ILLEGAL;
        for(Opcode opcode: Opcode.values()){
            if(opcode.name().equals(token.toUpperCase())) {
                tokenOpcode = opcode;
                break;
            }
        }
        return tokenOpcode;
    }

    public void printByteCode(){
        System.out.println("Printing bytecode...");

        Set<Map.Entry<String, Function>> entries = bytecode.getFunctions().entrySet(); //.entrySet();
        Iterator<Map.Entry<String, Function>> it = entries.iterator();

        while(it.hasNext()) {
            Map.Entry<String, Function> e = it.next();
            System.out.println("function name: " + e.getKey());
            //System.out.println(e.getValue().code);
            for(int i=0; i<e.getValue().getCodeLines().code.size(); i++){
                System.out.printf("%03d %s", i, e.getValue().getCodeLines().code.get(i));
                System.out.println();
            }
        }
    }
    /*
    private int getValue(String var){
        // keep searching from the most inner block. First encountered value is returned
        LinkedStack<Block> blockStack = this.activationFrameStack.peek().blockStack;

        for (Block b:blockStack) {
            if(b.symbolTable.containsKey(var)){
                return b.symbolTable.get(var).getValue();
            }
        }
        // if not found print the error message. Need to check what needs to be returned
        System.out.println("Illegal usage of variable "+var);
        return 0;
    }
    */
    public boolean isVariableDefined(String variableName){
        boolean variableFound = false;

        ActivationFrame aFrame = activationFrameStack.peek();
        /*
        while (aFrame.blockStack.iterator().hasNext()) {
            System.out.println("isVariableDefined while loop");
            Block block = aFrame.blockStack.iterator().next();
            if (block.symbolTable.containsKey(variableName)) {
                variableFound = true;
                break;
            }
        }
        */

        for(int i=aFrame.blockStack.size()-1; i>=0; i--){
            if(aFrame.blockStack.elementAt(i).symbolTable.containsKey(variableName)){
                variableFound = true;
                break;
            }
        }
        return variableFound;
    }

    public Symbol getVariable(String variableName){
        ActivationFrame aFrame = activationFrameStack.peek();
        Symbol variable = new Symbol();

        while (aFrame.blockStack.iterator().hasNext()) {
            Block block = aFrame.blockStack.iterator().next();
            if (block.symbolTable.containsKey(variableName)) {
                variable = block.symbolTable.get(variableName);
                break;
            }
        }

        return variable;
    }

    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static boolean isBoolean(String str)
    {
        if(str.equalsIgnoreCase("true")){
            return true;
        }else if(str.equalsIgnoreCase("false")){
            return true;
        }else{
            return false;
        }
    }

    public Symbol formOperand(String operandString){
        Symbol operand = new Symbol();
        if(isNumeric(operandString)){
            operand.setType(0);
            operand.setValue(Integer.parseInt(operandString));
        }else{
            operand = getVariable(operandString);
        }
        return operand;
    }

    public void printOperandStack(Stack<Symbol> stack){
        for(int i=stack.size()-1; i>=0; i--){
            System.out.print(stack.elementAt(i).getValue() + " ");
        }
        System.out.println();
    }
}
