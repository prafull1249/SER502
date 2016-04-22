import java.util.HashMap;
import java.util.Map;

/**
 * Created by Satyam on 21-04-2016.
 */
public class Block {
    int startIndex;
    int endIndex;
    Map<String, Symbol> symbolTable;

    public Block() {
        symbolTable = new HashMap<String, Symbol>();
    }
}
