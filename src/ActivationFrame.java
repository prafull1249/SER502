import java.util.Stack;

/**
 * Created by Satyam on 21-04-2016.
 */
public class ActivationFrame {
    int returnAddress;
    Symbol returnValue;
    Stack<Block> blockStack;
    Stack<Symbol> operandStack;
    String functionName;

    ActivationFrame(String name){
        blockStack = new Stack<>();
        blockStack.push(new Block());
        operandStack = new Stack<>();
        returnValue = new Symbol();
        functionName = name;
    }
}
