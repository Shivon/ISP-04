import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Murat on 20.12.2016.
 */
public class Main {

    static HashMap<String,Variable> component;
    public static void main(String[] args)
    {
        initComponent();

        Algorithm algorithm = new Algorithm();


        algorithm.acThreeForLookahead(component);

        showAllVariables();

    }

    private static void showAllVariables() {
        String house1 = "Haus 1: ";
        String house2 = "Haus 2: ";
        String house3 = "Haus 3: ";
        String house4 = "Haus 4: ";
        String house5 = "Haus 5: ";

        for(Variable v : component.values())
        {
            Iterator iterator = v.iterator();
            while(iterator.hasNext())
            {
                Tupel t = (Tupel) iterator.next();

                if(t.getHouseNo() == 1)
                {
                    house1 += " - "  + t.getPart();
                }
                if(t.getHouseNo() == 2)
                {
                    house2 += " - "  + t.getPart();
                }
                if(t.getHouseNo() == 3)
                {
                    house3 += " - "  + t.getPart();
                }
                if(t.getHouseNo() == 4)
                {
                    house4 += " - "  + t.getPart();
                }
                if(t.getHouseNo() == 5)
                {
                    house5 += " - "  + t.getPart();
                }
            }
        }

        System.out.println(house1);
        System.out.println(house2);
        System.out.println(house3);
        System.out.println(house4);
        System.out.println(house5);

    }


    private static void initComponent()
    {
        component = new HashMap<>();

        Variable nation = new Variable("Nation");

        for(int i = 1; i <= 5; i++)
        {

            if(i == 1){
                nation.putTupel(new Tupel("Nation",i, "Norweger"));
            }else
            {
                nation.putTupel(new Tupel("Nation", i, "Brite"));
                nation.putTupel(new Tupel("Nation",i, "Schwede"));
                nation.putTupel(new Tupel("Nation",i, "Däne"));
                nation.putTupel(new Tupel("Nation",i, "Deutscher"));
            }

        }

        Variable drink = new Variable("Drink");

        for(int i = 1; i <= 5; i++)
        {


            if(i == 3) {
                drink.putTupel(new Tupel("Drink", i, "Milch"));
            }else{
                drink.putTupel(new Tupel("Drink", i, "Tee"));
                drink.putTupel(new Tupel("Drink",i, "Kaffee"));
                drink.putTupel(new Tupel("Drink",i, "Bier"));
                drink.putTupel(new Tupel("Drink",i, "Wasser"));
            }


        }

        Variable tier = new Variable("Tier");

        for(int i = 1; i <= 5; i++)
        {
            tier.putTupel(new Tupel("Tier", i, "Hund"));
            tier.putTupel(new Tupel("Tier",i, "Vogel"));
            tier.putTupel(new Tupel("Tier",i, "Katze"));
            tier.putTupel(new Tupel("Tier",i, "Pferd"));
            tier.putTupel(new Tupel("Tier",i, "Fisch"));
        }

        Variable farbe = new Variable("Farbe");

        for(int i = 1; i <= 5; i++)
        {
            farbe.putTupel(new Tupel("Farbe",i, "Rot"));
            farbe.putTupel(new Tupel("Farbe",i, "Grün"));
            farbe.putTupel(new Tupel("Farbe",i, "Weiß"));
            farbe.putTupel(new Tupel("Farbe",i, "Gelb"));
            farbe.putTupel(new Tupel("Farbe",i, "Blau"));
        }

        Variable zigarette = new Variable("Zigarette");

        for(int i = 1; i <= 5; i++)
        {
            zigarette.putTupel(new Tupel("Zigarette",i, "Pall-Mall"));
            zigarette.putTupel(new Tupel("Zigarette",i, "Dunhill"));
            zigarette.putTupel(new Tupel("Zigarette",i, "Malboro"));
            zigarette.putTupel(new Tupel("Zigarette",i, "Winfield"));
            zigarette.putTupel(new Tupel("Zigarette",i, "Rothmann"));
        }

        component.put("Nation", nation);
        component.put("Farbe", farbe);
        component.put("Tier", tier);
        component.put("Zigarette", zigarette);
        component.put("Drink", drink);
    }

}
