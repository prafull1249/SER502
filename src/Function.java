/**
 * Created by Giridhar on 4/21/2016.
 */
import java.util.*;
public class Function {
    public String name;
    private List<String> codeLines;
    private Map<String, Object> params;
    private Map<String, Object> variables;

    Function(String name){
        this.name = name;
    }

    public void addCodeLine(String newLine){
        codeLines.add(newLine);
    }

    public void addParam(String param, Object obj){
        params.put(param, obj);
    }

    public void addVar(String var, Object obj){
        variables.put(var, obj);
    }
    
}
