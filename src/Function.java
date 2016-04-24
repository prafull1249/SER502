/**
 * Created by Giridhar on 4/21/2016.
 */
import java.util.*;
public class Function {
    public String name;
    private LinesOfCode codeLines;
    private ArrayList<Symbol> params;
    private int returnType;

    Function(String name, ArrayList<Symbol> params, LinesOfCode codeLines, int returnType){
        this.name = name;
        this.params = params;
        this.codeLines = codeLines;
        this.returnType = returnType;
    }

    public int getReturnType() {
        return returnType;
    }

    public LinesOfCode getCodeLines() {
        return codeLines;
    }

    public ArrayList<Symbol> getParams() {
        return params;
    }

    public void addCodeLine(String newLine){
        codeLines.code.add(newLine);
    }

    public void addParam(Symbol sym){
        params.add(sym);
    }

}
