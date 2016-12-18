import com.sun.org.apache.bcel.internal.generic.ALOAD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by murat on 15.12.16.
 */
public class Main {
    public static void main (String[] args) {
        Constants.initNameMapping();

        Component nation = new Component(Constants.NATION);
        Component getrank = new Component(Constants.GETRAENK);
        Component tier = new Component(Constants.TIER);
        Component farbe = new Component(Constants.FARBE);
        Component zigarette = new Component(Constants.ZIGARETTE);

        List<Component> components = new ArrayList<>();
        components.add(nation);
        components.add(getrank);
        components.add(tier);
        components.add(farbe);
        components.add(zigarette);

        try {
            initComponents(nation, getrank, tier, farbe, zigarette);

            Variable house1 = new Variable(1, nation, farbe, zigarette, getrank, tier);
            Variable house2 = new Variable(2, nation, farbe, zigarette, getrank, tier);
            Variable house3 = new Variable(3, nation, farbe, zigarette, getrank, tier);
            Variable house4 = new Variable(4, nation, farbe, zigarette, getrank, tier);
            Variable house5 = new Variable(5, nation, farbe, zigarette, getrank, tier);
            List<Variable> houses = new ArrayList<>();
            houses.add(house1);
            houses.add(house2);
            houses.add(house3);
            houses.add(house4);
            houses.add(house5);

            Variable variable = new Variable(nation, farbe, zigarette, getrank, tier);
            Algorithm al = new Algorithm();

            boolean run = true;
            while(run)
            {



                run = false;
                boolean x = false;
                x = knownOrder(1, "Norweger", houses);
                if(x) run = true;

                x = variable.knownPart("Brite", "Rot");
                if(x) run = true;

                x = variable.knownPartWithout("Norweger", "Blau");
                if(x) run = true;
                x = knownOrder("Norweger", Constants.NEIGHBOR, "Blau", houses);
                if(x) run = true;

                x = knownOrder("Grün", Constants.LEFT, "Weiß", houses);
                if(x) run = true;

                x = variable.knownPart("Gelb", "Dunhill");
                if(x) run = true;

                al.flat(houses);
                al.ac1(variable, houses);
                al.flat(houses);

                x = variable.knownPartWithout("Pferd", "Dunhill");
                if(x) run = true;
                x = knownOrder("Pferd", Constants.NEIGHBOR, "Dunhill", houses);
                if(x) run = true;

                x = knownOrder(3, "Milch", houses);
                if(x) run = true;

                x = variable.knownPart("Grün", "Kaffee");
                if(x) run = true;

                x = knownOrder("Grün", Constants.LEFT, "Weiß", houses);
                if(x) run = true;

                //al.ac1(variable, houses);


                x = variable.knownPart("Schwede", "Hund");
                if(x) run = true;

                x = variable.knownPart("Däne", "Tee");
                if(x) run = true;



                x = variable.knownPart("Pall-Mall", "Vogel");
                if(x) run = true;



                x = variable.knownPartWithout("Malboro", "Katze");
                if(x) run = true;
                x = knownOrder("Malboro", Constants.NEIGHBOR, "Katze", houses);
                if(x) run = true;

                x = variable.knownPart("Winfield", "Bier");
                if(x) run = true;

                x = variable.knownPart("Deutscher", "Rothmann");
                if(x) run = true;

                x = variable.knownPartWithout("Malboro", "Wasser");
                if(x) run = true;
                x = knownOrder("Malboro", Constants.NEIGHBOR, "Wasser", houses);
                if(x) run = true;

                x = al.ac1(variable, houses);
                if(x) run = true;

                x = al.flat(houses);
                if(x) run = true;
            }


            house4.showTupels();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean knownOrder(int i, String part, List<Variable> houses) {
        boolean deleted = false;
        for(Variable house : houses)
        {
            if(house.getNummer() == i)
            {
                deleted = house.deleteAllTupelsWithout(part);
            }else{
                deleted = house.deleteAllTupelsWith(part);
            }
        }
        return deleted;
    }

    private static boolean knownOrder(String part1, String order, String part2, List<Variable> houses)
    {
        boolean deleted = false;
        if(order.equals(Constants.LEFT))
        {
            Variable h1 = houses.get(0);
            Variable h2 = houses.get(4);

            if(h1.containsTupelWith(part2)) {
                deleted = h1.deleteAllTupelsWith(part2);
            }
            if(h2.containsTupelWith(part1)){
                deleted =  h2.deleteAllTupelsWith(part1);
            }

             h2 = houses.get(1);

            if(!h2.containsTupelWith(part2)){
                deleted = h1.deleteAllTupelsWith(part1);
            }
            if(!h1.containsTupelWith(part1)){
                deleted = h2.deleteAllTupelsWith(part2);
            }

             h1 = houses.get(1);
             h2 = houses.get(2);

            if(!h2.containsTupelWith(part2)){
                deleted = h1.deleteAllTupelsWith(part1);
            }
            if(!h1.containsTupelWith(part1)){
                deleted = h2.deleteAllTupelsWith(part2);
            }

             h1 = houses.get(2);
             h2 = houses.get(3);

            if(!h2.containsTupelWith(part2)){
                deleted = h1.deleteAllTupelsWith(part1);
            }
            if(!h1.containsTupelWith(part1)){
                deleted = h2.deleteAllTupelsWith(part2);
            }

             h1 = houses.get(3);
             h2 = houses.get(4);

            if(!h2.containsTupelWith(part2)){
                deleted = h1.deleteAllTupelsWith(part1);
            }
            if(!h1.containsTupelWith(part1)){
                deleted = h2.deleteAllTupelsWith(part2);
            }




        }else if (order.equals(Constants.NEIGHBOR))
        {
            Variable h1 = houses.get(0);
            Variable h2 = houses.get(1);
            Variable h3;

            if(!h2.containsTupelWith(part1)) {
                deleted = h1.deleteAllTupelsWith(part2);
            }
            if(!h2.containsTupelWith(part2)){
                deleted = h1.deleteAllTupelsWith(part1);
            }

            h1 = houses.get(0);
            h2 = houses.get(1);
            h3 = houses.get(2);

            int part1Deleted = 0;
            int part2Deleted = 0;

            if(!h1.containsTupelWith(part1) && !h3.containsTupelWith(part1)){
                deleted = h2.deleteAllTupelsWith(part2);
                part2Deleted++;
            }
            if(!h1.containsTupelWith(part2) && !h3.containsTupelWith(part2)) {
                deleted = h2.deleteAllTupelsWith(part1);
                part1Deleted++;
            }

            h1 = houses.get(1);
            h2 = houses.get(2);
            h3 = houses.get(3);

            if(!h1.containsTupelWith(part1) && !h3.containsTupelWith(part1)){
                deleted = h2.deleteAllTupelsWith(part2);
                part2Deleted++;
            }
            if(!h1.containsTupelWith(part2) && !h3.containsTupelWith(part2)){
                deleted = h2.deleteAllTupelsWith(part1);
                part1Deleted++;
            }

            h1 = houses.get(2);
            h2 = houses.get(3);
            h3 = houses.get(4);

            if(!h1.containsTupelWith(part1) && !h3.containsTupelWith(part1)) {
                deleted = h2.deleteAllTupelsWith(part2);
                part2Deleted++;
            }
            if(!h1.containsTupelWith(part2) && !h3.containsTupelWith(part2)){
                deleted = h2.deleteAllTupelsWith(part1);
                part1Deleted++;
            }

            h1 = houses.get(3);
            h2 = houses.get(4);

            if(!h1.containsTupelWith(part1)){
                deleted = h2.deleteAllTupelsWith(part2);
                part2Deleted++;
            }
            if(!h1.containsTupelWith(part2)){
                deleted = h2.deleteAllTupelsWith(part1);
                part1Deleted++;
            }

            if(part1Deleted == 3)
            {
                for(Variable house : houses)
                {
                    if(house.containsTupelWith(part1))
                    {
                        deleted = house.deleteAllTupelsWithout(part1);
                    }else{
                        deleted = house.deleteAllTupelsWith(part1);
                    }
                }
            }

            if(part2Deleted == 3)
            {
                for(Variable house : houses)
                {
                    if(house.containsTupelWith(part2))
                    {
                        deleted = house.deleteAllTupelsWithout(part2);
                    }else{
                        deleted = house.deleteAllTupelsWith(part2);
                    }

                }
            }

        }

        return deleted;
    }

    private static void initComponents(Component nation, Component getrank, Component tier, Component farbe, Component zigarette) throws Exception
    {
        nation.insertContent("Brite");
        nation.insertContent("Schwede");
        nation.insertContent("Däne");
        nation.insertContent("Norweger");
        nation.insertContent("Deutscher");

        getrank.insertContent("Tee");
        getrank.insertContent("Kaffee");
        getrank.insertContent("Milch");
        getrank.insertContent("Bier");
        getrank.insertContent("Wasser");

        tier.insertContent("Hund");
        tier.insertContent("Vogel");
        tier.insertContent("Katze");
        tier.insertContent("Pferd");
        tier.insertContent("Fisch");

        farbe.insertContent("Rot");
        farbe.insertContent("Grün");
        farbe.insertContent("Weiß");
        farbe.insertContent("Gelb");
        farbe.insertContent("Blau");

        zigarette.insertContent("Pall-Mall");
        zigarette.insertContent("Dunhill");
        zigarette.insertContent("Malboro");
        zigarette.insertContent("Winfield");
        zigarette.insertContent("Rothmann");
    }
}
