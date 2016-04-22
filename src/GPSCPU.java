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
        printByteCode();
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
}
