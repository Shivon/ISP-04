/**
 * Created by Murat on 20.12.2016.
 */
public class Tupel {
    private int houseNo;
    private String part;
    private String componentName;

    public Tupel(String componentName, int houseNo, String part)
    {
        this.componentName = componentName;
        this.houseNo = houseNo;
        this.part = part;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public String getPart() {
        return part;
    }

    public boolean equals(int no, String p)
    {
        if(houseNo != no) return false;
        if(!part.equals(p)) return false;
        return true;
    }

    public void showTupel()
    {
        System.out.println("(" + houseNo + "," + part + ")");
    }

    public boolean contains(String p)
    {
        if(part.equals(p)) return true;
        return false;
    }

    public String getComponentName() {
        return componentName;
    }

    public String toString() {
      return "House no: " + houseNo + ", part: " + part + ", componentName: " + componentName;
    }
}
