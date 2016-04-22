import java.util.Map;

/**
 * Created by Satyam on 20-04-2016.
 */
public class Bytecode {
    private LinesOfCode fullcode;
    private Map<String, LinesOfCode> functions;
    private LinesOfCode globalDeclaration;

    Bytecode(LinesOfCode fullcode, LinesOfCode globalDeclaration, Map<String, LinesOfCode> functions){
        this.fullcode = fullcode;
        this.globalDeclaration = globalDeclaration;
        this.functions = functions;
    }

    public  LinesOfCode getFullcode(){
        return this.fullcode;
    }

    public  LinesOfCode getGlobalDeclaration(){
        return this.globalDeclaration;
    }

    public  Map<String, LinesOfCode> getFunctions(){
        return this.functions;
    }
}
