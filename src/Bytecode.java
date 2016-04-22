import java.util.Map;

/**
 * Created by Satyam on 20-04-2016.
 */
public class Bytecode {
    private LinesOfCode fullcode;
    private Map<String, LinesOfCode> functions;

    Bytecode(LinesOfCode fullcode, Map<String, LinesOfCode> functions){
        this.fullcode = fullcode;
        this.functions = functions;
    }

    public  LinesOfCode getFullcode(){
        return this.fullcode;
    }

    public  Map<String, LinesOfCode> getFunctions(){
        return this.functions;
    }
}
