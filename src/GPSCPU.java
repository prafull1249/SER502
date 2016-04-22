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
        // printByteCode();
        if(bytecode.getGlobalDeclaration().code.size() != 0){
            System.out.println(bytecode.getGlobalDeclaration().code.size());
            executeCode(bytecode.getGlobalDeclaration().code);
        }else{
            System.out.println("No global declarations found...");
        }

        if(bytecode.getFunctions().get("main").code.size() != 0){
            executeCode(bytecode.getFunctions().get("main").code);
        }else{
            System.out.println("Function main is empty!");
        }
    }

    public void executeCode(ArrayList<String> code){
        System.out.println(code);
        /*
        for(Opcode o : Opcode.values()){
            String t = o.name();
            System.out.println(t);
        }
        */
        ActivationFrame aFrame;
        for(String line: code){
            String[] tokens = line.split(" ");
            System.out.println("token extracted: " + tokens[0]);
            if(isValidOpcode(tokens[0])){
                System.out.println("Valid opcode found");
                Opcode opcode = getOpcode(tokens[0]);
                System.out.println("Identified opcode: " + opcode);
                switch (opcode){
                    case ISTORE:
                        System.out.println(opcode.name());
                        break;
                    case BSTORE:
                        System.out.println(opcode.name());
                        break;
                    case STORE:
                        System.out.println(opcode.name());
                        break;
                    case ASSIGN:
                        System.out.println(opcode.name());
                        break;
                    case ADD:
                        System.out.println(opcode.name());
                        break;
                    case SUB:
                        System.out.println(opcode.name());
                        break;
                    case MUL:
                        System.out.println(opcode.name());
                        break;
                    case EQ:
                        System.out.println(opcode.name());
                        break;
                    case GT:
                        System.out.println(opcode.name());
                        break;
                    case LT:
                        System.out.println(opcode.name());
                        break;
                    case OR:
                        System.out.println(opcode.name());
                        break;
                    case LOOP_START:
                        System.out.println(opcode.name());
                        break;
                    case LOOP_END:
                        System.out.println(opcode.name());
                        break;
                    case FUNC_START:
                        System.out.println(opcode.name());
                        break;
                    case FUNC_END:
                        System.out.println(opcode.name());
                        break;
                    case TRETURN:
                        System.out.println(opcode.name());
                        break;
                    case PARAM:
                        System.out.println(opcode.name());
                        break;
                    case RETURN:
                        System.out.println(opcode.name());
                        break;
                    case CALL:
                        System.out.println(opcode.name());
                        break;
                    case PRINT:
                        System.out.println(opcode.name());
                        // Need to check if any text will be present in the print statement. otherwise below code works fine
                        for (int i=1; i<tokens.length; i++){
                            System.out.print(getValue(tokens[i]));
                            System.out.print(" ");
                        }
                        break;
                    case IDEC:
                        System.out.println(opcode.name());
                        // creating variable of int type and adding it to the current block
                        Symbol sym = new Symbol(0,0);
                        aFrame = this.activationFrameStack.peek();
                        Block currBlock = aFrame.blockStack.peek();
                        currBlock.symbolTable.put(tokens[1], sym);
                        break;
                    case BDEC:
                        System.out.println(opcode.name());
                        // creating variable of bool type and adding it to the current block
                        Symbol boolSym = new Symbol(1,0);
                        aFrame = this.activationFrameStack.peek();
                        Block curBlock = aFrame.blockStack.peek();
                        curBlock.symbolTable.put(tokens[1], boolSym);
                        break;
                    case IF_START:
                        System.out.println(opcode.name());
                        // create new block and push it on the bloc stack of current activation record
                        this.activationFrameStack.peek().blockStack.push(new Block());
                        break;
                    case IF_END:
                        System.out.println(opcode.name());
                        // Remove the last block from the block stack of the current activation frame
                        aFrame = this.activationFrameStack.peek();
                        aFrame.blockStack.pop();
                        break;
                    case IF_BLOCK_START:
                        System.out.println(opcode.name());
                        // create new block and push it on the bloc stack of current activation record
                        this.activationFrameStack.peek().blockStack.push(new Block());
                        break;
                    case IF_BLOCK_END:
                        System.out.println(opcode.name());
                        // Remove the last block from the block stack of the current activation frame
                        aFrame = this.activationFrameStack.peek();
                        aFrame.blockStack.pop();
                        break;
                    case ELSE_BLOCK_START:
                        System.out.println(opcode.name());
                        // create new block and push it on the bloc stack of current activation record
                        this.activationFrameStack.peek().blockStack.push(new Block());
                        break;
                    case ELSE_BLOCK_END:
                        System.out.println(opcode.name());
                        // Remove the last block from the block stack of the current activation frame
                        aFrame = this.activationFrameStack.peek();
                        aFrame.blockStack.pop();
                        break;
                    case ILLEGAL:
                        System.out.println(opcode.name());
                        break;
                }
            }
        }
    }

    private boolean isValidOpcode(String str){
        boolean valid = false;

        for(Opcode o: Opcode.values()){
            if(o.name().equals(str.toUpperCase()))
                valid = true;
        }
        return valid;
    }

    private Opcode getOpcode(String token){
        Opcode tokenOpcode = Opcode.ILLEGAL;
        for(Opcode opcode: Opcode.values()){
            if(opcode.name().equals(token.toUpperCase())) {
                tokenOpcode = opcode;
            }
        }
        return tokenOpcode;
    }

    public void printByteCode(){
        System.out.println("Printing bytecode...");

        Set<Map.Entry<String, LinesOfCode>> entries = bytecode.getFunctions().entrySet();
        Iterator<Map.Entry<String, LinesOfCode>> it = entries.iterator();

        while(it.hasNext()) {
            Map.Entry<String, LinesOfCode> e = it.next();
            System.out.println("function name: " + e.getKey());
            //System.out.println(e.getValue().code);
            for(int i=0; i<e.getValue().code.size(); i++){

                System.out.printf("%03d %s", i, e.getValue().code.get(i));
                System.out.println();
            }
        }
    }

    private int getValue(String var){
        // keep searching from the most inner block. First encountered value is returned
        Stack<Block> blockStack = this.activationFrameStack.peek().blockStack;
        for (Block b:blockStack) {
            if(b.symbolTable.containsKey(var)){
                return b.symbolTable.get(var).getValue();
            }
        }
        // if not found print the error message. Need to check what needs to be returned
        System.out.println("Illegal usage of variable "+var);
        return 0;
    }
}
