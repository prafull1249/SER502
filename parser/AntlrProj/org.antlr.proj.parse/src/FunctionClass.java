import java.util.HashMap;

/**
 * Created by Prafull on 4/21/2016.
 */
public class FunctionClass {

    enum typeData  {
        INT,
        BOOL,
        VOID
    }
    private typeData returnType = typeData.VOID;
    private HashMap<String,typeData> paramMap = null;
    private HashMap<String,typeData> localMap = null;
    private String name = null;

    FunctionClass(String Funcname){
        name = Funcname;
        paramMap = new HashMap<String, typeData>();
        localMap = new HashMap<String, typeData>();

    }

    public void populateParamMap(String name, int type) {
        typeData i;
        if (type==0)
            i = typeData.INT;
        else if (type==1)
            i = typeData.BOOL;
        else
            i = typeData.VOID;
        paramMap.put(name,i);

    }

    public void populateLocalMap(String name, int type) {
        typeData i;
        if (type==0)
            i = typeData.INT;
        else if (type==1)
            i = typeData.BOOL;
        else
            i = typeData.VOID;
        paramMap.put(name,i);

    }

    public boolean ifDefined(String name){
        return paramMap.containsKey(name);
    }

    public void setParamMap(HashMap<String, typeData> paramMap) {
        this.paramMap = paramMap;
    }

    public void setReturnType(int returnType) {
        typeData i;
        if (returnType ==0)
            i = typeData.INT;
        else if (returnType == 1)
            i = typeData.BOOL;
        else
            i = typeData.VOID;
        this.returnType = i;
    }

    public HashMap<String, typeData> getLocalMap() {
        return localMap;
    }

    public HashMap<String, typeData> getParamMap() {
        return paramMap;
    }

    public typeData getReturnType() {
        return returnType;
    }
}
