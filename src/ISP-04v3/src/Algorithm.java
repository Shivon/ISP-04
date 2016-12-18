import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by murat on 15.12.16.
 */
public class Algorithm {

    public boolean revice(Variable v1, List<Variable> houses)
    {

        boolean delete = false;
        Iterator v1Iterator = v1.iterator();


        while(v1Iterator.hasNext())
        {
            flat(houses);
            SixTupel fiveTupel = (SixTupel) v1Iterator.next();

            Iterator houseIterator = houses.iterator();

            boolean found = false;

            while(houseIterator.hasNext())
            {
                House house = (House) houseIterator.next();

                Iterator tupelIterator = house.iterator();

                while(tupelIterator.hasNext())
                {
                    SixTupel sixTupel = (SixTupel) tupelIterator.next();
                    if(fiveTupel.constraint(sixTupel))
                    {
                        found = true;
                        break;
                    }
                }


            }

            if(!found)
            {
                v1.deleteTupel(fiveTupel);
                delete = true;
            }
        }

        return delete;
    }



    public boolean revice( House v1,Variable v2)
    {

        boolean delete = false;
        Iterator v1Iterator = v1.iterator();


        while(v1Iterator.hasNext())
        {
            SixTupel sixTupel = (SixTupel) v1Iterator.next();

            Iterator v2Iterator = v2.iterator();

            boolean found = false;


            while(v2Iterator.hasNext())
            {
                SixTupel fiveTupel = (SixTupel) v2Iterator.next();
                if(sixTupel.constraint(fiveTupel))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
            {
                v1.deleteTupel(sixTupel);
                delete = true;
            }

        }

        return delete;
    }

    public boolean revice(House h1, House h2) {

        boolean delete = false;
        Iterator v1Iterator = h1.iterator();


        while(v1Iterator.hasNext())
        {
            SixTupel sixTupel = (SixTupel) v1Iterator.next();

            Iterator v2Iterator = h2.iterator();

            boolean found = false;


            while(v2Iterator.hasNext())
            {
                SixTupel sixTupel1 = (SixTupel) v2Iterator.next();
                if(sixTupel.constraint(sixTupel1))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
            {
                h1.deleteTupel(sixTupel);
                delete = true;
            }

        }

        return delete;
    }

    public boolean ac1(Variable variable, List<Variable> houses) {
        boolean deleted = false;
        List<VariableTupel> q = new ArrayList<>();
        for(Variable v : houses)
        {
            q.add(new VariableTupel(variable,v));
            q.add(new VariableTupel(v,variable));
        }


        boolean change = false;

        do{

            change = false;

            for(VariableTupel vTupel : q){
                Variable vk = vTupel.getV1();
                Variable vm = vTupel.getV2();

                change = revice(vk,vm);
                if(change) deleted = true;
                //if(change) {break;
            }
            //q.remove(0);


        }while(change);
        return deleted;
    }

    private boolean revice(Variable vk, Variable vm) {


        boolean delete = false;
        Iterator vkIterator = vk.iterator();


        while(vkIterator.hasNext())
        {
            SixTupel sixTupel = (SixTupel) vkIterator.next();

            Iterator vmIterator = vm.iterator();

            boolean found = false;


            while(vmIterator.hasNext())
            {
                SixTupel sixTupel1 = (SixTupel) vmIterator.next();
                if(vk.getNummer() == -1){
                    if(sixTupel.getNummer() != sixTupel1.getNummer()){
                        found = true;
                    }
                }
                if(sixTupel.constraint(sixTupel1))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
            {
                vk.deleteTupel(sixTupel);
                delete = true;
            }

        }

        return delete;
    }

    public boolean flat(List<Variable> houses) {
        boolean deleted = false;
       for(Variable house : houses)
       {
           if(house.getnCount() == 1)
           {
               String part = ((SixTupel)house.iterator().next()).getNation();
               for(Variable house1 : houses)
               {
                   if(house.getNummer() != house1.getNummer())
                       deleted = house1.deleteAllTupelsWith(part);
               }
           }
           if(house.getfCount() == 1)
           {

               String part = ((SixTupel)house.iterator().next()).getFarbe();
               for(Variable house1 : houses)
               {
                   if(house.getNummer() != house1.getNummer())
                       deleted = house1.deleteAllTupelsWith(part);
               }
           }
           if(house.getzCount() == 1)
           {
               String part = ((SixTupel)house.iterator().next()).getZigarette();
               for(Variable house1 : houses)
               {
                   if(house.getNummer() != house1.getNummer())
                       deleted = house1.deleteAllTupelsWith(part);
               }
           }
           if(house.getgCount() == 1)
           {
               String part = ((SixTupel)house.iterator().next()).getGetrank();
               for(Variable house1 : houses)
               {
                   if(house.getNummer() != house1.getNummer())
                       deleted = house1.deleteAllTupelsWith(part);
               }
           }
           if(house.gettCount() == 1)
           {
               String part = ((SixTupel)house.iterator().next()).getTier();
               for(Variable house1 : houses)
               {
                   if(house.getNummer() != house1.getNummer())
                       deleted = house1.deleteAllTupelsWith(part);
               }
           }


       }
        return deleted;
    }

}
