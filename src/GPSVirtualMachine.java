import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by Satyam on 20-04-2016.
 */
public class GPSVirtualMachine {
    public static void main(String[] args) {
        try {
            //String bytecodeFile = "FactorialIterative.gpsb";
            //String bytecodeFile = "FactorialRecursive.gspb";
            //String bytecodeFile = "StackDemo.gpsb";
            //String bytecodeFile = args[0];
            //String bytecodeFile = "NestedIfElse.gpsb";
            //String bytecodeFile = "multiple_declarations.gpsb";
            String bytecodeFile = "./parser/AntlrProj/org.antlr.proj.parse/bytecode_sumfirstOddNo.gpsb";
            if(new File(bytecodeFile).isFile()) {
                System.out.println("Bytecode file found...");
            }
            else{
                System.err.println("File " + bytecodeFile + " does not exist.");
            }

            //System.err.println("'" + args[0] + "' does not exist.");

            GPSVirtualMachine vm = new GPSVirtualMachine();
            Bytecode code = vm.readByteCode(bytecodeFile);
            //Bytecode code = vm.readByteCode(args[0]);

            GPSCPU cpu = GPSCPU.getInstance(code);
            cpu.execute();
        }
        catch (Exception e) {
            System.err.println("Error in reading file" + e.getMessage());
        }
    }

    public Bytecode readByteCode(String filepath){

        LinesOfCode fullcode = new LinesOfCode();
        Map<String, Function> functions = new HashMap<String, Function>();
        LinesOfCode globalDeclaration = new LinesOfCode();

        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line = "";

            while ((line = br.readLine()) != null) {
                if(!(line.isEmpty() || line.trim().equals("") || line.trim().equals("\n"))){
                    String[] tokens = line.split(" ");
                    if (tokens[0].equalsIgnoreCase(Opcode.FUNC_START.toString())){
                        //System.out.println("got a new func");

                        String name = tokens[1];

                        LinesOfCode functionCode = new LinesOfCode();
                        //System.out.println("Adding new line: " + line);
                        functionCode.code.add(line);
                        fullcode.code.add(line);
                        boolean addToFunction = true;
                        while(addToFunction){
                            line = br.readLine();
                            tokens = line.split(" ");
                            if(tokens[0].equalsIgnoreCase(Opcode.FUNC_END.toString())){
                                addToFunction = false;
                                //System.out.println("Adding new line: " + line);
                                functionCode.code.add(line);
                                fullcode.code.add(line);
                            }else{
                                //System.out.println("Adding new line: " + line);
                                functionCode.code.add(line);
                                fullcode.code.add(line);
                            }
                        }
                        functions.put(name, generateFunction(name, functionCode));
                        //functions.put(name, functionCode);
                    }else{
                        fullcode.code.add(line);
                        globalDeclaration.code.add(line);
                    }
                }
            }

            System.out.println("Bytecode file parsed successfully...");

            br.close();
        }catch (Exception e){
            System.err.println("Error in parsing the file: " + e.getMessage());
        }

        return new Bytecode(fullcode, globalDeclaration, functions);
    }

    // Method to parse the lines and generate function objects
    public Function generateFunction(String name, LinesOfCode functionCode){
        ArrayList<Symbol> params = new ArrayList<>();
        int returnType = -1;

        for (int i=1; i<functionCode.code.size(); i++){
            String[] tokens = functionCode.code.get(i).split(" ");
            if(i==1){
                if(tokens[0].equalsIgnoreCase(Opcode.TRETURN.toString())){
                    if(tokens[1].equalsIgnoreCase("INT")){
                        returnType = 0;
                    }else if(tokens[1].equalsIgnoreCase("BOOLEAN")){
                        returnType = 1;
                    }else if(tokens[1].equalsIgnoreCase("VOID")){
                        returnType = -1;
                    }else{
                        System.err.println("Error: Incorrect return-type for function: "+name);
                        System.exit(0);
                    }
                }else{
                    System.err.println("Error: Return-type not specified for function: "+name);
                    System.exit(0);
                }
            }else if(tokens[0].equalsIgnoreCase(Opcode.IPARAM.toString())){
                params.add(new Symbol(0));
            }else if(tokens[0].equalsIgnoreCase(Opcode.BPARAM.toString())){
                params.add(new Symbol(1));
            }else{
                // breaking out since function definition is over
                break;
            }
        }
        return new Function(name, params, functionCode, returnType);
    }
    /*
    public void generateFunctions(String filepath){
        Map<String, Function> functions = new HashMap<String, Function>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line = "";
            Function currFunc;
            while ((line = br.readLine()) != null) {
                if(!(line.isEmpty() || line.trim().equals("") || line.trim().equals("\n"))){
                    if (line.startsWith(Opcode.FUNC_START.toString())){
                        String[] tokens = line.split(" ");
                        String name = tokens[1];
                        currFunc = new Function(name);
                        boolean addToFunction = true;
                        while(addToFunction){
                            line = br.readLine();
                            if(line.startsWith(Opcode.FUNC_END.toString())){
                                // set the flag and add the function to the map
                                addToFunction = false;
                                functions.put(name, currFunc);
                            }else if(line.startsWith(Opcode.IPARAM.toString())){
                                // create symbol of integer type and put it into parameters map
                                Symbol param = new Symbol(0,0);
                                currFunc.addParam(tokens[1],param);
                            }else if(line.startsWith(Opcode.IPARAM.toString())){
                                // create symbol of boolean type and put it into parameters map
                                Symbol param = new Symbol(1,0);
                                currFunc.addParam(tokens[1],param);
                            }else{
                                currFunc.addCodeLine(line);
                            }
                        }
                    }
                }
            }
            br.close();
        }catch (Exception e){
            System.out.println("Error in parsing the file" + e.getMessage());
        }
    }
    */
}
