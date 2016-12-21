/**
 * Created by murat on 15.12.16.
 */
public class VariableTupel {
    private Variable v1;
    private Variable v2;

    public VariableTupel(Variable variable1, Variable variable2)
    {
        v1 = variable1;
        v2 = variable2;
    }

    public Variable getV1() {
        return v1;
    }

    public Variable getV2() {
        return v2;
    }

    public boolean contains(Variable v) {
        if(v1.equals(v)) return true;
        if(v2.equals(v)) return true;
        return false;
    }
}
