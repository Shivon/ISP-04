import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by murat on 15.12.16.
 */
public class House {

    private int nummer;
    List<SixTupel> tupels;

    public House(int nummer, Component nation, Component farbe, Component zigarette, Component getrank, Component tier)
    {
        tupels = new ArrayList<>();
        this.nummer = nummer;
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

    public void deleteAllTupelsWith(String part) {
        List<SixTupel> t = new ArrayList<>(tupels);
        for(SixTupel tupel : t)
        {
            if(tupel.contains(part))
            {
                tupels.remove(tupel);
            }
        }
    }

    public void deleteAllTupelsWithout(String part) {
        List<SixTupel> t = new ArrayList<>(tupels);
        for(SixTupel tupel : t)
        {
            if(!tupel.contains(part))
            {
                tupels.remove(tupel);
            }
        }
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
    }
}
