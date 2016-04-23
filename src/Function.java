/**
 * Created by Giridhar on 4/21/2016.
 */
import java.util.*;
public class Function {
    public String name;
    private List<String> codeLines;
    private LinkedHashMap<String, Symbol> params;
    private Map<String, Object> variables;

    Function(String name){
        this.name = name;
        this.params = new LinkedHashMap<>();
        this.variables = new HashMap<>();
    }

    public void addCodeLine(String newLine){
        codeLines.add(newLine);
    }

    public void addParam(String param, Symbol sym){
        params.put(param, sym);
    }

    public void addVar(String var, Symbol sym){
        variables.put(var, sym);
    }
    
}
