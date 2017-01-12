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
                tuple t = (tuple) iterator.next();

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
                nation.puttuple(new tuple("Nation",i, "Norweger"));
            }else
            {
                nation.puttuple(new tuple("Nation", i, "Brite"));
                nation.puttuple(new tuple("Nation",i, "Schwede"));
                nation.puttuple(new tuple("Nation",i, "Daene"));
                nation.puttuple(new tuple("Nation",i, "Deutscher"));
            }

        }

        Variable drink = new Variable("Drink");

        for(int i = 1; i <= 5; i++)
        {


            if(i == 3) {
                drink.puttuple(new tuple("Drink", i, "Milch"));
            }else{
                drink.puttuple(new tuple("Drink", i, "Tee"));
                drink.puttuple(new tuple("Drink",i, "Kaffee"));
                drink.puttuple(new tuple("Drink",i, "Bier"));
                drink.puttuple(new tuple("Drink",i, "Wasser"));
            }


        }

        Variable tier = new Variable("Tier");

        for(int i = 1; i <= 5; i++)
        {
            tier.puttuple(new tuple("Tier", i, "Hund"));
            tier.puttuple(new tuple("Tier",i, "Vogel"));
            tier.puttuple(new tuple("Tier",i, "Katze"));
            tier.puttuple(new tuple("Tier",i, "Pferd"));
            tier.puttuple(new tuple("Tier",i, "Fisch"));
        }

        Variable farbe = new Variable("Farbe");

        for(int i = 1; i <= 5; i++)
        {
            farbe.puttuple(new tuple("Farbe",i, "Rot"));
            farbe.puttuple(new tuple("Farbe",i, "Gruen"));
            farbe.puttuple(new tuple("Farbe",i, "Weiss"));
            farbe.puttuple(new tuple("Farbe",i, "Gelb"));
            farbe.puttuple(new tuple("Farbe",i, "Blau"));
        }

        Variable zigarette = new Variable("Zigarette");

        for(int i = 1; i <= 5; i++)
        {
            zigarette.puttuple(new tuple("Zigarette",i, "Pall-Mall"));
            zigarette.puttuple(new tuple("Zigarette",i, "Dunhill"));
            zigarette.puttuple(new tuple("Zigarette",i, "Malboro"));
            zigarette.puttuple(new tuple("Zigarette",i, "Winfield"));
            zigarette.puttuple(new tuple("Zigarette",i, "Rothmann"));
        }

        component.put("Nation", nation);
        component.put("Farbe", farbe);
        component.put("Tier", tier);
        component.put("Zigarette", zigarette);
        component.put("Drink", drink);
    }

}
