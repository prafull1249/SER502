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
        ANTLRInputStream input  = new ANTLRFileStream("C:\\New folder\\AntlrProj\\org.antlr.proj.parse\\FactIterative.gps");
        //ANTLRInputStream input  = new ANTLRFileStream(args[0]);
        //System.out.println(input.toString());
        //ANTLRInputStream input  = new ANTLRFileStream(args[0]);

        //ANTLRInputStream input  = new ANTLRFileStream("C:\\Users\\Prafull\\work\\SER502\\parser\\AntlrProj\\org.antlr.proj.parse\\intermediate_sumfirstOddNo");
        //System.out.println(input.toString());
        GrammarLexer lexer_f = new GrammarLexer(input);
        System.out.println("Lexing done..");
        CommonTokenStream tokens_f = new CommonTokenStream(lexer_f);
        System.out.println("Token Generation done..");
        GrammarParser parser_f  = new GrammarParser(tokens_f);
        System.out.println("Parsing done..");
        ParseTree tree_f  = parser_f.programBody();
/*
        String filename = "C:\\Users\\Prafull\\work\\SER502\\parser\\AntlrProj\\org.antlr.proj.parse\\intermediate_sumfirstOddNo";
        final CharStream stream = new ANTLRFileStream(filename);
        final GrammarLexer lexer = new GrammarLexer(stream);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final GrammarParser parser = new GrammarParser(tokens);
        final ParseTree tree = parser.programBody();
        final List<String> ruleNames = Arrays.asList(GrammarParser.ruleNames);
        final TreeViewer view = new TreeViewer(ruleNames, tree);
        view.open();
*/
        //System.out.println(tree_f.toStringTree(parser_f));
        System.out.println("Visiting Tree..");
        new visitMain().visit(tree_f);
    }
}
