import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Murat on 20.12.2016.
 */
public class Variable {

    List<Tupel> tupels;
    private String name;
    public Variable(String name)
    {
        tupels = new ArrayList<>();
        this.name = name;
    }

    public void putTupel(Tupel t)
    {
        tupels.add(t);
    }

    public void showVariable()
    {
        for (Tupel t : tupels)
        {
            t.showTupel();
        }
    }

    public boolean isEmpty()
    {
        return tupels.isEmpty();
    }
    public boolean equals(Variable v)
    {
        if(!name.equals(v.getName())) return false;
        return true;
    }

    public boolean contains(int houseNo, String p)
    {
        for(Tupel tupel : tupels)
        {
            if(tupel.equals(houseNo, p)) return true;
        }
        return false;
    }

    public List<Tupel> GetAllTupelsWithPart(String part)
    {
        List<Tupel> allTupelsWithPart = new ArrayList<>();
        for(Tupel t : tupels)
        {
            if(t.contains(part))
            {
                allTupelsWithPart.add(t);
            }
        }

        return allTupelsWithPart;
    }

    public int GetHouseNo(String part) {
        for(Tupel t : tupels)
        {
            if(t.contains(part)) return t.getHouseNo();
        }

        return -1;
    }

    public Iterator iterator()
    {
        List<Tupel> t = new ArrayList<>(tupels);
        return t.iterator();
    }

    public void deleteTupel(Tupel tupel) {
        tupels.remove(tupel);



    }

    public boolean flat()
    {
        List<Tupel> tupelsToDelete = new ArrayList<>();
        boolean found = false;

        //Überprüfung ob Tupel im anderen Haus vorhanden ist, wenn nicht können alle andere gelöscht werden
          for(Tupel t1 : tupels)
          {
              found = false;
              for(Tupel t2 : tupels)
              {
                  if((t1.getHouseNo() != t2.getHouseNo()) && t1.getPart().equals(t2.getPart()))
                  {
                      found = true;
                      break;
                  }
              }
              if(!found)
              {
                  tupelsToDelete.add(t1);
              }
          }

        //Überprüfung ob Tupel als einziger für ein Haus ist, wenn ja, können Tupel in anderen Häuser gelöscht werden
        for(Tupel t1 : tupels)
        {
            int count = 0;
            for(Tupel t2 : tupels)
            {
                if(t1.getHouseNo() == t2.getHouseNo())
                {
                        count++;
                }
            }
            if(count == 1)
            {
                if(!tupelsToDelete.contains(t1))
                {
                    tupelsToDelete.add(t1);
                }
            }
        }

        boolean delete = false;


        List<Tupel> tu = new ArrayList<>(tupels);

        for(Tupel t : tupelsToDelete)
        {
            for(Tupel t1 : tu)
            {
                if(t.getHouseNo() == t1.getHouseNo())
                {
                    if(!t.getPart().equals(t1.getPart()))
                    {
                        delete = true;
                        tupels.remove(t1);
                    }
                }else
                {
                    if(t.getPart().equals(t1.getPart()))
                    {
                        delete = true;
                        tupels.remove(t1);
                    }
                }
            }
        }

       return  delete;
    }




    private String getTupelName(int n1) {
        for(Tupel t : tupels)
        {
            if(t.getHouseNo() == n1)
            {
                return t.getPart();
            }
        }
        return "";
    }

    public String getName() {
        return name;
    }
}
