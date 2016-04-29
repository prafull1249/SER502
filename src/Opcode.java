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
    DIV,
    EQ,
    GT,
    GTE,
    LT,
    LTE,
    LOOP_START,
    LOOP_END,
    FUNC_START,
    FUNC_END,
    TRETURN,
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
    ELSE_BLOCK_END,
    IPARAM,
    BPARAM,
    OR,
    AND,
    STACK,
    PUSH,
    POP,
    PEEK,
    SIZE,
    ILLEGAL;
}
