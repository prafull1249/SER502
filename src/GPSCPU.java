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
        for(String line: code){
            String[] tokens = line.split(" ");
            // System.out.println("token extracted: " + tokens[0]);
            if(isValidOpcode(tokens[0])){
                // System.out.println("Valid opcode found");
                Opcode opcode = getOpcode(tokens[0]);
                // System.out.println("Identified opcode: " + opcode);
                switch (opcode){
                    case ISTORE:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case BSTORE:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case STORE:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case ASSIGN:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case ADD:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case SUB:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case MUL:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case EQ:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case GT:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case LT:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case LOOP_START:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case LOOP_END:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case FUNC_START:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case FUNC_END:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case TRETURN:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case PARAM:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case RETURN:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case CALL:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case PRINT:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case IDEC:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case BDEC:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case IF_START:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case IF_END:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case IF_BLOCK_START:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case IF_BLOCK_END:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case ELSE_BLOCK_START:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case ELSE_BLOCK_END:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case IPARAM:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case BPARAM:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case LTE:
                        System.out.println("executing case:" + opcode.name());
                        break;
                    case GTE:
                        System.out.println("executing case:" + opcode.name());
                        break;

                    case ILLEGAL:
                        System.out.println("Illegal opcode found... Exiting code");
                        break;
                }
            }
        }
    }

    public boolean isValidOpcode(String str){
        boolean valid = false;

        for(Opcode o: Opcode.values()){
            if(o.name().equals(str.toUpperCase()))
                valid = true;
        }
        return valid;
    }

    public Opcode getOpcode(String token){
        Opcode tokenOpcode = Opcode.ILLEGAL;
        for(Opcode opcode: Opcode.values()){
            if(opcode.name().equals(token.toUpperCase())) {
                tokenOpcode = opcode;
            }
        }
        return tokenOpcode;
    }

    public void printByteCode(){
        // System.out.println("Printing bytecode...");

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
}


