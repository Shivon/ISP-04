import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Murat on 20.12.2016.
 */
public class Algorithm {


    public void acOne(HashMap<String,Variable> variables)
    {
        List<VariableTupel> variableTupels = new ArrayList<>();
        for(Variable v1 : variables.values())
        {
            for(Variable v2 : variables.values())
            {
                variableTupels.add(new VariableTupel(v1,v2));
                variableTupels.add(new VariableTupel(v2,v1));
            }
        }

        boolean change = false;

        do{

            change = false;

            for(VariableTupel vTupel : variableTupels){
                Variable vk = vTupel.getV1();
                Variable vm = vTupel.getV2();

                change = revise(vk, vm);
                if(change) break;
            }

            boolean x = false;


            for(Variable v : variables.values())
            {
                x = v.flat();
                if(x) change = x;
            }

        }while(change);
    }

    HashMap<String,Variable> variables;
    public boolean acThreeForLookahead(HashMap<String,Variable> variables)
    {
        this.variables = variables;
        List<VariableTupel> variableTupels = new ArrayList<>();

        variableTupels.add(new VariableTupel(variables.get("Farbe"), variables.get("Nation")));
        variableTupels.add(new VariableTupel(variables.get("Nation"), variables.get("Farbe")));
        variableTupels.add(new VariableTupel(variables.get("Farbe"), variables.get("Drink")));
        variableTupels.add(new VariableTupel(variables.get("Drink"), variables.get("Farbe")));
        variableTupels.add(new VariableTupel(variables.get("Farbe"), variables.get("Zigarette")));
        variableTupels.add(new VariableTupel(variables.get("Zigarette"), variables.get("Farbe")));
        variableTupels.add(new VariableTupel(variables.get("Nation"), variables.get("Drink")));
        variableTupels.add(new VariableTupel(variables.get("Drink"), variables.get("Nation")));
        variableTupels.add(new VariableTupel(variables.get("Nation"), variables.get("Tier")));
        variableTupels.add(new VariableTupel(variables.get("Tier"), variables.get("Nation")));
        variableTupels.add(new VariableTupel(variables.get("Nation"), variables.get("Zigarette")));
        variableTupels.add(new VariableTupel(variables.get("Zigarette"), variables.get("Nation")));
        variableTupels.add(new VariableTupel(variables.get("Drink"), variables.get("Zigarette")));
        variableTupels.add(new VariableTupel(variables.get("Zigarette"), variables.get("Drink")));
        variableTupels.add(new VariableTupel(variables.get("Zigarette"), variables.get("Tier")));
        variableTupels.add(new VariableTupel(variables.get("Tier"), variables.get("Zigarette")));
        variableTupels.add(new VariableTupel(variables.get("Farbe"), variables.get("Farbe")));

        List<VariableTupel>variableTupels1 = new ArrayList<>(variableTupels);

        boolean consistent = true;

        while(!variableTupels.isEmpty() && consistent)
        {

            VariableTupel variableTupel = variableTupels.get(0);
            variableTupels.remove(variableTupel);
            Variable vk = variableTupel.getV1();
            Variable vm = variableTupel.getV2();

            if(revise(vk, vm))
            {
                //union(variableTupel, variableTupels, variableTupels1);

                for(VariableTupel vt : variableTupels1)
                {
                    if(vt.contains(vk))
                    {
                        if(!variableTupels.contains(vt))
                        {
                            variableTupels.add(vt);
                        }
                    }
                }
                consistent = !variableTupel.getV1().isEmpty();
            }
        }


        return consistent;

    }

    private void union(VariableTupel variableTupel, List<VariableTupel> variableTupels, List<VariableTupel> variableTupels1) {
        Variable vk = variableTupel.getV1();
        Variable vm = variableTupel.getV2();

        for(VariableTupel vt : variableTupels1)
        {
            if(vt.getV2().equals(vk))
            {
                if(!variableTupels.contains(vt))
                    variableTupels.add(vt);
            }
        }
    }


    public boolean revise(Variable v1, Variable v2)
    {
        // System.out.println("in REVISE: v1 = " + v1.toString() + ", v2 = " + v2.toString());

        boolean delete = false;
        Iterator iteratorV1 = v1.iterator();


        while(iteratorV1.hasNext())
        {
            Tupel tupelV1 = (Tupel) iteratorV1.next();
            Iterator iteratorV2 = v2.iterator();


            //1. Der Brite lebt im roten Haus
            if(((tupelV1.contains("Brite") && v2.getName().equals("Farbe")) || (tupelV1.contains("Rot") && v2.getName().equals("Nation"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Brite") && tupelV2.contains("Rot")) || tupelV1.contains("Rot") && tupelV2.contains("Brite"))
                        if(directConstraint(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //2. Der Schwede hält sich einen Hund.
            if(((tupelV1.contains("Schwede") && v2.getName().equals("Tier")) || (tupelV1.contains("Hund") && v2.getName().equals("Nation"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Schwede") && tupelV2.contains("Hund")) || tupelV1.contains("Hund") && tupelV2.contains("Schwede"))
                        if(directConstraint(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //3. Der Daene trinkt gern Tee.
            if(((tupelV1.contains("Daene") && v2.getName().equals("Drink")) || (tupelV1.contains("Tee") && v2.getName().equals("Nation"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Daene") && tupelV2.contains("Tee")) || tupelV1.contains("Tee") && tupelV2.contains("Daene"))
                        if(directConstraint(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //4. Das gruene Haus steht links neben dem wei�en Haus.
            if((tupelV1.contains("Gruen") || tupelV1.contains("Weiss")) && v2.getName().equals("Farbe"))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if(gruen_links_weiss(tupelV1,tupelV2))
                    {
                        found = true;
                        break;
                    }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //5. Der Besitzer des gruenen Hauses trinkt Kaffee
            if(((tupelV1.contains("Gruen") && v2.getName().equals("Drink")) || (tupelV1.contains("Kaffee") && v2.getName().equals("Farbe"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Gruen") && tupelV2.contains("Kaffee")) || tupelV1.contains("Kaffee") && tupelV2.contains("Gruen"))
                    if(directConstraint(tupelV1, tupelV2))
                    {
                        found = true;
                        break;
                    }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //6. Die Person, die Pall Mall raucht, hat einen Vogel.
            if(((tupelV1.contains("Pall-Mall") && v2.getName().equals("Tier")) || (tupelV1.contains("Vogel") && v2.getName().equals("Zigarette"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Pall-Mall") && tupelV2.contains("Vogel")) || tupelV1.contains("Vogel") && tupelV2.contains("Pall-Mall"))
                        if(directConstraint(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //8. Der Bewohner des gelben Hauses raucht Dunhill.
            if(((tupelV1.contains("Gelb") && v2.getName().equals("Zigarette")) || (tupelV1.contains("Dunhill") && v2.getName().equals("Farbe"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Gelb") && tupelV2.contains("Dunhill")) || tupelV1.contains("Dunhill") && tupelV2.contains("Gelb"))
                        if(directConstraint(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //10. Der Malboro-Raucher wohnt neben der Person mit der Katze
            if(((tupelV1.contains("Malboro") && v2.getName().equals("Tier")) || (tupelV1.contains("Katze") && v2.getName().equals("Zigarette"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Malboro") && tupelV2.contains("Katze")) || tupelV1.contains("Katze") && tupelV2.contains("Malboro"))
                        if(neighbor(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //11. Der Mann mit dem Pferd lebt neben der Person, die Dunhill raucht.
            if(((tupelV1.contains("Dunhill") && v2.getName().equals("Tier")) || (tupelV1.contains("Pferd") && v2.getName().equals("Zigarette"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Dunhill") && tupelV2.contains("Pferd")) || tupelV1.contains("Pferd") && tupelV2.contains("Dunhill"))
                        if(neighbor(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //12. Der Winfield-Raucher trinkt gern Bier.
            if(((tupelV1.contains("Bier") && v2.getName().equals("Zigarette")) || (tupelV1.contains("Winfield") && v2.getName().equals("Drink"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Bier") && tupelV2.contains("Winfield")) || tupelV1.contains("Winfield") && tupelV2.contains("Bier"))
                        if(directConstraint(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //13. Der Norweger wohnt neben dem blauen Haus
            if(((tupelV1.contains("Norweger") && v2.getName().equals("Farbe")) || (tupelV1.contains("Blau") && v2.getName().equals("Nation"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Norweger") && tupelV2.contains("Blau")) || tupelV1.contains("Blau") && tupelV2.contains("Norweger"))
                        if(neighbor(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //14. Der Deutsche raucht Rothmanns.
            if(((tupelV1.contains("Deutscher") && v2.getName().equals("Zigarette")) || (tupelV1.contains("Rothmann") && v2.getName().equals("Nation"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Deutscher") && tupelV2.contains("Rothmann")) || tupelV1.contains("Rothmann") && tupelV2.contains("Deutscher"))
                        if(directConstraint(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //15. Der Malboro-Raucher hat einen Nachbarn, der Wasser trinkt.
            if(((tupelV1.contains("Malboro") && v2.getName().equals("Drink")) || (tupelV1.contains("Wasser") && v2.getName().equals("Zigarette"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    Tupel tupelV2 = (Tupel) iteratorV2.next();
                    if((tupelV1.contains("Malboro") && tupelV2.contains("Wasser")) || tupelV1.contains("Wasser") && tupelV2.contains("Malboro"))
                        if(neighbor(tupelV1, tupelV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deleteTupel(tupelV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }




        }


        boolean x = false;
        x = v1.flat();
        if(x) delete = true;

        return delete;
    }

    private boolean neighbor(Tupel tupelV1, Tupel tupelV2) {
        int difference = tupelV1.getHouseNo() - tupelV2.getHouseNo();

        if(difference == 1) return true;
        if(difference == -1) return true;

        return false;
    }

    private boolean directConstraint(Tupel tupelV1, Tupel tupelV2) {
        int houseNo = tupelV1.getHouseNo();
        if(tupelV2.equals(houseNo, tupelV2.getPart())) return true;
        return false;
    }

    private boolean gruen_links_weiss(Tupel tupelV1, Tupel tupelV2) {

        Tupel gruen, weiss;
        if(tupelV1.getPart().equals("Gruen") && tupelV2.getPart().equals("Weiss"))
        {
            gruen = tupelV1;
            weiss = tupelV2;
        }else if(tupelV2.getPart().equals("Gruen") && tupelV1.getPart().equals("Weiss"))
        {
            gruen = tupelV2;
            weiss = tupelV1;
        }else
        {
            return false;
        }

        int difference = weiss.getHouseNo() - gruen.getHouseNo();

        if(difference == 1)
        {
            return true;
        }
        return false;
    }

}
