/**
 * Created by Satyam on 20-04-2016.
 */
public enum Opcode {
    ISTORE,
    BSTORE,
    STORE,
    ASSIGN,
    ADD,
    SUB,
    MUL,
    EQ,
    GT,
    LT,
    OR,
    LOOP_START,
    LOOP_END,
    FUNC_START,
    FUNC_END,
    TRETURN,
    PARAM,
    RETURN,
    CALL,
    PRINT,
    IDEC,
    BDEC,
    IF_START,
    IF_END,
    IF_BLOCK_START,
    IF_BLOCK_END,
    ELSE_BLOCK_START,
    ELSE_BLOCK_END;

    public boolean isValidOpcode(String str){
        boolean valid = false;

        for(Opcode o: Opcode.values()){
            if(o.equals(str.toUpperCase()))
                valid = true;
        }

        return valid;
    }
}
