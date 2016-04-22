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
            String bytecodeFile = "GPSFirstBytecode.gpsb";
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
        Map<String, LinesOfCode> functions = new HashMap<String, LinesOfCode>();
        LinesOfCode globalDeclaration = new LinesOfCode();

        try{
            BufferedReader br = new BufferedReader(new FileReader(filepath));
            String line = "";

            while ((line = br.readLine()) != null) {
                if(!(line.isEmpty() || line.trim().equals("") || line.trim().equals("\n"))){
                    if (line.startsWith(Opcode.FUNC_START.toString())){
                        //System.out.println("got a new func");

                        String[] tokens = line.split(" ");
                        String name = tokens[1];

                        LinesOfCode functionCode = new LinesOfCode();
                        //System.out.println("Adding new line: " + line);
                        functionCode.code.add(line);
                        fullcode.code.add(line);
                        boolean addToFunction = true;
                        while(addToFunction){
                            line = br.readLine();
                            if(line.startsWith(Opcode.FUNC_END.toString())){
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

                        functions.put(name, functionCode);
                    }else{
                        fullcode.code.add(line);
                        globalDeclaration.code.add(line);
                    }
                }
            }

            System.out.println("Bytecode file parsed successfully...");

            br.close();
        }catch (Exception e){
            System.out.println("Error in parsing the file" + e.getMessage());
        }

        return new Bytecode(fullcode, globalDeclaration, functions);
    }
}
