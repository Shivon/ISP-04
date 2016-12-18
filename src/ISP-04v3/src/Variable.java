import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by murat on 15.12.16.
 */
public class Variable {
    List<SixTupel> tupels;
    private int nummer;
    private int nCount, fCount, zCount, gCount, tCount;

    public Variable(Component nation, Component farbe, Component zigarette, Component getrank, Component tier)
    {
        this.nummer = -1;
        tupels = new ArrayList<>();

        this.nCount = 5;
        fCount = 5;
        zCount = 5;
        gCount = 5;
        tCount = 5;

        for(String n : nation.values())
        {

            for(String f : farbe.values())
            {

                for(String z : zigarette.values())
                {

                    for(String g : getrank.values())
                    {

                        for(String t : tier.values())
                        {

                            for(int i = 1; i <= 5; i++){
                                tupels.add(new SixTupel(i, n, f, z, g, t));
                            }
                        }
                    }
                }
            }
        }
    }
    public int getNummer() {
        return nummer;
    }

    public boolean containsTupelWith(String part) {
        for(SixTupel tupel : tupels)
        {
            if(tupel.contains(part)) return true;
        }
        return false;
    }

    public boolean deleteAllTupelsWithout(String part) {
        List<SixTupel> t = new ArrayList<>(tupels);
        if(Constants.getComponentName(part).equals(Constants.NATION)) nCount = 1;
        if(Constants.getComponentName(part).equals(Constants.FARBE)) fCount = 1;
        if(Constants.getComponentName(part).equals(Constants.ZIGARETTE)) zCount = 1;
        if(Constants.getComponentName(part).equals(Constants.GETRAENK)) gCount = 1;
        if(Constants.getComponentName(part).equals(Constants.TIER)) tCount = 1;
        boolean deleted = false;

        for(SixTupel tupel : t)
        {
            if(!tupel.contains(part))
            {

                tupels.remove(tupel);
                deleted = true;
            }
        }
        return deleted;
    }

    public boolean deleteAllTupelsWith(String part) {

        boolean deleted = false;

        if(containsTupelWith(part)){
            if(Constants.getComponentName(part).equals(Constants.NATION)) nCount--;
            if(Constants.getComponentName(part).equals(Constants.FARBE))  fCount--;
            if(Constants.getComponentName(part).equals(Constants.ZIGARETTE)) zCount--;
            if(Constants.getComponentName(part).equals(Constants.GETRAENK)) gCount--;
            if(Constants.getComponentName(part).equals(Constants.TIER)) tCount--;
        }
        List<SixTupel> t = new ArrayList<>(tupels);
        for(SixTupel tupel : t)
        {
            if(tupel.contains(part))
            {
                tupels.remove(tupel);
                deleted = true;
            }
        }
        return deleted;
    }

    public Variable(int nummer, Component nation, Component farbe, Component zigarette, Component getrank, Component tier)
    {
        this.nummer = nummer;
        this.nCount = 5;
        fCount = 5;
        zCount = 5;
        gCount = 5;
        tCount = 5;
        tupels = new ArrayList<>();
        for(String n : nation.values())
        {
            for(String f : farbe.values())
            {
                for(String z : zigarette.values())
                {
                    for(String g : getrank.values())
                    {
                        for(String t : tier.values())
                        {
                            tupels.add(new SixTupel(nummer, n, f, z, g, t));
                        }
                    }
                }
            }
        }
    }


    public boolean knownPart(String part1, String part2) {

        if(containsTupelWith(part1)){
            if(Constants.getComponentName(part1).equals(Constants.NATION)) nCount--;
            if(Constants.getComponentName(part1).equals(Constants.FARBE))  fCount--;
            if(Constants.getComponentName(part1).equals(Constants.ZIGARETTE)) zCount--;
            if(Constants.getComponentName(part1).equals(Constants.GETRAENK)) gCount--;
            if(Constants.getComponentName(part1).equals(Constants.TIER)) tCount--;
        }

        if(containsTupelWith(part2)){
            if(Constants.getComponentName(part2).equals(Constants.NATION)) nCount--;
            if(Constants.getComponentName(part2).equals(Constants.FARBE))  fCount--;
            if(Constants.getComponentName(part2).equals(Constants.ZIGARETTE)) zCount--;
            if(Constants.getComponentName(part2).equals(Constants.GETRAENK)) gCount--;
            if(Constants.getComponentName(part2).equals(Constants.TIER)) tCount--;
        }


        boolean deleted = false;
        List<SixTupel> t = new ArrayList<>(tupels);
        for(SixTupel tupel : t)
        {
            if(tupel.contains(part1) && !tupel.contains(part2))
            {

                tupels.remove(tupel);
                deleted = true;
            }else if(!tupel.contains(part1) && tupel.contains(part2))
            {

                tupels.remove(tupel);
                deleted = true;
            }
        }
        return deleted;
    }

    public boolean knownPartWithout(String part1, String part2) {

        if(Constants.getComponentName(part1).equals(Constants.NATION)) nCount = 1;
        if(Constants.getComponentName(part1).equals(Constants.FARBE)) fCount = 1;
        if(Constants.getComponentName(part1).equals(Constants.ZIGARETTE)) zCount = 1;
        if(Constants.getComponentName(part1).equals(Constants.GETRAENK)) gCount = 1;
        if(Constants.getComponentName(part1).equals(Constants.TIER)) tCount = 1;

        if(Constants.getComponentName(part2).equals(Constants.NATION)) nCount = 1;
        if(Constants.getComponentName(part2).equals(Constants.FARBE)) fCount = 1;
        if(Constants.getComponentName(part2).equals(Constants.ZIGARETTE)) zCount = 1;
        if(Constants.getComponentName(part2).equals(Constants.GETRAENK)) gCount = 1;
        if(Constants.getComponentName(part2).equals(Constants.TIER)) tCount = 1;
        boolean deleted = false;
        List<SixTupel> t = new ArrayList<>(tupels);
        for(SixTupel tupel : t)
        {
            if(tupel.contains(part1) && tupel.contains(part2))
            {
                tupels.remove(tupel);
                deleted = true;
            }
        }
        return deleted;
    }

    public void showTupels() {
        int i = 0;
        for(SixTupel tupel : tupels)
        {
            tupel.showTupel();
            i++;
        }

        System.out.println("Anzahl: " + i);
    }

    public Iterator iterator() {
        List<SixTupel> t = new ArrayList<>(tupels);
        return t.iterator();
    }

    public void deleteTupel(SixTupel v1DirectTupel) {

        tupels.remove(v1DirectTupel);

        recalcCounts();
    }

    private void recalcCounts() {

        String n = "",f = "",z = "",g = "",t = "";
        nCount = 0;
        fCount = 0;
        zCount = 0;
        gCount = 0;
        tCount = 0;
        for(SixTupel tupel : tupels)
        {
            if(!n.equals(tupel.getNation()))
            {
                n = tupel.getNation();
                nCount++;
            }
            if(!f.equals(tupel.getFarbe()))
            {
                f = tupel.getFarbe();
                fCount++;
            }
            if(!z.equals(tupel.getZigarette()))
            {
                z = tupel.getZigarette();
                zCount++;
            }
            if(!g.equals(tupel.getGetrank()))
            {
                g = tupel.getGetrank();
                gCount++;
            }
            if(!t.equals(tupel.getTier()))
            {
                t = tupel.getTier();
                tCount++;
            }
        }
    }

    public int getnCount() {
        return nCount;
    }

    public int getfCount() {
        return fCount;
    }

    public int getzCount() {
        return zCount;
    }

    public int getgCount() {
        return gCount;
    }

    public int gettCount() {
        return tCount;
    }

    public String getFirstFarbe() {
        return tupels.get(0).getFarbe();
    }
    public String getFirstNation() {
        return tupels.get(0).getFarbe();
    }
    public String getFirstZigarette() {
        return tupels.get(0).getFarbe();
    }
    public String getFirstGetrank() {
        return tupels.get(0).getFarbe();
    }
    public String getFirstTier() {
        return tupels.get(0).getFarbe();
    }
}
