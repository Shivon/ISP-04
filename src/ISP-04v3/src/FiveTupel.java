/**
 * Created by murat on 15.12.16.
 */
public class FiveTupel {
    private String nation;
    private String farbe;
    private String zigarette;
    private String getrank;
    private String tier;


    public FiveTupel(String nation, String farbe, String zigarette, String getrank, String tier)
    {
        this.nation = nation;
        this.farbe = farbe;
        this.zigarette = zigarette;
        this.getrank = getrank;
        this.tier = tier;
    }

    public boolean contains(String part) {
        if(part.equals(nation)) return true;
        if(part.equals(farbe)) return true;
        if(part.equals(zigarette)) return true;
        if(part.equals(getrank)) return true;
        if(part.equals(tier)) return true;

        return false;
    }

    public void showTupel() {
        System.out.println("(" + nation + "," + farbe + "," + zigarette + "," + getrank + "," + tier + ")");
    }

    public String getNation() {
        return nation;
    }

    public String getFarbe() {
        return farbe;
    }

    public String getZigarette() {
        return zigarette;
    }

    public String getGetrank() {
        return getrank;
    }

    public String getTier() {
        return tier;
    }

    public boolean constraint(SixTupel tupel) {
        if(!nation.equals(tupel.getNation())) return false;
        if(!farbe.equals(tupel.getFarbe())) return false;
        if(!zigarette.equals(tupel.getZigarette())) return false;
        if(!getrank.equals(tupel.getGetrank())) return false;
        if(!tier.equals(tupel.getTier())) return false;
        return true;

    }
}
