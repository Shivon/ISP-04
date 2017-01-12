import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Murat on 20.12.2016.
 */
public class Variable {

    List<tuple> tuples;
    private String name;

    public Variable(String name)
    {
        tuples = new ArrayList<>();
        this.name = name;
    }

    public void puttuple(tuple t)
    {
        tuples.add(t);
    }

    public void showVariable()
    {
        for (tuple t : tuples)
        {
            t.showtuple();
        }
    }

    public boolean isEmpty()
    {
        return tuples.isEmpty();
    }
    public boolean equals(Variable v)
    {
        if(!name.equals(v.getName())) return false;
        return true;
    }

    public boolean contains(int houseNo, String p)
    {
        for(tuple tuple : tuples)
        {
            if(tuple.equals(houseNo, p)) return true;
        }
        return false;
    }

    public List<tuple> GetAlltuplesWithPart(String part)
    {
        List<tuple> alltuplesWithPart = new ArrayList<>();
        for(tuple t : tuples)
        {
            if(t.contains(part))
            {
                alltuplesWithPart.add(t);
            }
        }

        return alltuplesWithPart;
    }

    public int GetHouseNo(String part) {
        for(tuple t : tuples)
        {
            if(t.contains(part)) return t.getHouseNo();
        }

        return -1;
    }

    public Iterator iterator()
    {
        List<tuple> t = new ArrayList<>(tuples);
        return t.iterator();
    }

    public void deletetuple(tuple tuple) {
        tuples.remove(tuple);



    }

    public boolean flat()
    {
        List<tuple> tuplesToDelete = new ArrayList<>();
        boolean found = false;

        //Ueberpruefen, ob tuple im anderen Haus vorhanden ist, wenn nicht koennen alle andere geloescht werden
          for(tuple t1 : tuples)
          {
              found = false;
              for(tuple t2 : tuples)
              {
                  if((t1.getHouseNo() != t2.getHouseNo()) && t1.getPart().equals(t2.getPart()))
                  {
                      found = true;
                      break;
                  }
              }
              if(!found)
              {
                  tuplesToDelete.add(t1);
              }
          }

        //Ueberpruefen, ob tuple als einziger fue ein Haus ist, wenn ja, koennen tuple in anderen Haeuser geloescht werden
        for(tuple t1 : tuples)
        {
            int count = 0;
            for(tuple t2 : tuples)
            {
                if(t1.getHouseNo() == t2.getHouseNo())
                {
                        count++;
                }
            }
            if(count == 1)
            {
                if(!tuplesToDelete.contains(t1))
                {
                    tuplesToDelete.add(t1);
                }
            }
        }

        boolean delete = false;


        List<tuple> tu = new ArrayList<>(tuples);

        for(tuple t : tuplesToDelete)
        {
            for(tuple t1 : tu)
            {
                if(t.getHouseNo() == t1.getHouseNo())
                {
                    if(!t.getPart().equals(t1.getPart()))
                    {
                        delete = true;
                        tuples.remove(t1);
                    }
                }else
                {
                    if(t.getPart().equals(t1.getPart()))
                    {
                        delete = true;
                        tuples.remove(t1);
                    }
                }
            }
        }

       return  delete;
    }

    @Override
    public String toString() {
      return "Name: " + name + ", tuples: " + tuples.toString();
    }


    private String gettupleName(int n1) {
        for(tuple t : tuples)
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
