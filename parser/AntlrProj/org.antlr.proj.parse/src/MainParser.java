import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

/**
 * Created by Prafull on 4/19/2016.
 */
public class MainParser {

    public static void main(String args[]) throws IOException {
        ANTLRInputStream input  = new ANTLRFileStream("C:\\Users\\Prafull\\work\\SER502\\parser\\AntlrProj\\org.antlr.proj.parse\\intermediate_checkScope");
        System.out.println(input.toString());
        GrammarLexer lexer = new GrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        GrammarParser parser  = new GrammarParser(tokens);
        ParseTree tree  = parser.programBody();
        System.out.println(tree.toStringTree(parser));
        new visitMain().visit(tree);
    }
}
