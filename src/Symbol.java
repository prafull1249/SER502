/**
 * Created by Satyam on 20-04-2016.
 */
public class Symbol {
    // type = 0 for int
    // type = 1 for bool
    private int type;
    private int value;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type){
        this.type = type;
    }

    Symbol(int type, int value){
        this.type = type;
        this.value = value;
    }

    Symbol(int type){
        this.type = type;
    }

    Symbol(){
        type = -1;
        value = 0;
    }
}
