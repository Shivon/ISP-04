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
        List<Variabletuple> variabletuples = new ArrayList<>();
        for(Variable v1 : variables.values())
        {
            for(Variable v2 : variables.values())
            {
                variabletuples.add(new Variabletuple(v1,v2));
                variabletuples.add(new Variabletuple(v2,v1));
            }
        }

        boolean change = false;

        do{

            change = false;

            for(Variabletuple vtuple : variabletuples){
                Variable vk = vtuple.getV1();
                Variable vm = vtuple.getV2();

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
        List<Variabletuple> variabletuples = new ArrayList<>();

        variabletuples.add(new Variabletuple(variables.get("Farbe"), variables.get("Nation")));
        variabletuples.add(new Variabletuple(variables.get("Nation"), variables.get("Farbe")));
        variabletuples.add(new Variabletuple(variables.get("Farbe"), variables.get("Drink")));
        variabletuples.add(new Variabletuple(variables.get("Drink"), variables.get("Farbe")));
        variabletuples.add(new Variabletuple(variables.get("Farbe"), variables.get("Zigarette")));
        variabletuples.add(new Variabletuple(variables.get("Zigarette"), variables.get("Farbe")));
        variabletuples.add(new Variabletuple(variables.get("Nation"), variables.get("Drink")));
        variabletuples.add(new Variabletuple(variables.get("Drink"), variables.get("Nation")));
        variabletuples.add(new Variabletuple(variables.get("Nation"), variables.get("Tier")));
        variabletuples.add(new Variabletuple(variables.get("Tier"), variables.get("Nation")));
        variabletuples.add(new Variabletuple(variables.get("Nation"), variables.get("Zigarette")));
        variabletuples.add(new Variabletuple(variables.get("Zigarette"), variables.get("Nation")));
        variabletuples.add(new Variabletuple(variables.get("Drink"), variables.get("Zigarette")));
        variabletuples.add(new Variabletuple(variables.get("Zigarette"), variables.get("Drink")));
        variabletuples.add(new Variabletuple(variables.get("Zigarette"), variables.get("Tier")));
        variabletuples.add(new Variabletuple(variables.get("Tier"), variables.get("Zigarette")));
        variabletuples.add(new Variabletuple(variables.get("Farbe"), variables.get("Farbe")));

        List<Variabletuple>variabletuples1 = new ArrayList<>(variabletuples);

        boolean consistent = true;

        while(!variabletuples.isEmpty() && consistent)
        {

            Variabletuple variabletuple = variabletuples.get(0);
            variabletuples.remove(variabletuple);
            Variable vk = variabletuple.getV1();
            Variable vm = variabletuple.getV2();

            if(revise(vk, vm))
            {
                //union(variabletuple, variabletuples, variabletuples1);

                for(Variabletuple vt : variabletuples1)
                {
                    if(vt.contains(vk))
                    {
                        if(!variabletuples.contains(vt))
                        {
                            variabletuples.add(vt);
                        }
                    }
                }
                consistent = !variabletuple.getV1().isEmpty();
            }
        }


        return consistent;

    }

    private void union(Variabletuple variabletuple, List<Variabletuple> variabletuples, List<Variabletuple> variabletuples1) {
        Variable vk = variabletuple.getV1();
        Variable vm = variabletuple.getV2();

        for(Variabletuple vt : variabletuples1)
        {
            if(vt.getV2().equals(vk))
            {
                if(!variabletuples.contains(vt))
                    variabletuples.add(vt);
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
            tuple tupleV1 = (tuple) iteratorV1.next();
            Iterator iteratorV2 = v2.iterator();


            //1. Der Brite lebt im roten Haus
            if(((tupleV1.contains("Brite") && v2.getName().equals("Farbe")) || (tupleV1.contains("Rot") && v2.getName().equals("Nation"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Brite") && tupleV2.contains("Rot")) || tupleV1.contains("Rot") && tupleV2.contains("Brite"))
                        if(directConstraint(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //2. Der Schwede hält sich einen Hund.
            if(((tupleV1.contains("Schwede") && v2.getName().equals("Tier")) || (tupleV1.contains("Hund") && v2.getName().equals("Nation"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Schwede") && tupleV2.contains("Hund")) || tupleV1.contains("Hund") && tupleV2.contains("Schwede"))
                        if(directConstraint(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //3. Der Daene trinkt gern Tee.
            if(((tupleV1.contains("Daene") && v2.getName().equals("Drink")) || (tupleV1.contains("Tee") && v2.getName().equals("Nation"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Daene") && tupleV2.contains("Tee")) || tupleV1.contains("Tee") && tupleV2.contains("Daene"))
                        if(directConstraint(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //4. Das gruene Haus steht links neben dem wei�en Haus.
            if((tupleV1.contains("Gruen") || tupleV1.contains("Weiss")) && v2.getName().equals("Farbe"))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if(gruen_links_weiss(tupleV1,tupleV2))
                    {
                        found = true;
                        break;
                    }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //5. Der Besitzer des gruenen Hauses trinkt Kaffee
            if(((tupleV1.contains("Gruen") && v2.getName().equals("Drink")) || (tupleV1.contains("Kaffee") && v2.getName().equals("Farbe"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Gruen") && tupleV2.contains("Kaffee")) || tupleV1.contains("Kaffee") && tupleV2.contains("Gruen"))
                    if(directConstraint(tupleV1, tupleV2))
                    {
                        found = true;
                        break;
                    }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //6. Die Person, die Pall Mall raucht, hat einen Vogel.
            if(((tupleV1.contains("Pall-Mall") && v2.getName().equals("Tier")) || (tupleV1.contains("Vogel") && v2.getName().equals("Zigarette"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Pall-Mall") && tupleV2.contains("Vogel")) || tupleV1.contains("Vogel") && tupleV2.contains("Pall-Mall"))
                        if(directConstraint(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //8. Der Bewohner des gelben Hauses raucht Dunhill.
            if(((tupleV1.contains("Gelb") && v2.getName().equals("Zigarette")) || (tupleV1.contains("Dunhill") && v2.getName().equals("Farbe"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Gelb") && tupleV2.contains("Dunhill")) || tupleV1.contains("Dunhill") && tupleV2.contains("Gelb"))
                        if(directConstraint(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //10. Der Malboro-Raucher wohnt neben der Person mit der Katze
            if(((tupleV1.contains("Malboro") && v2.getName().equals("Tier")) || (tupleV1.contains("Katze") && v2.getName().equals("Zigarette"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Malboro") && tupleV2.contains("Katze")) || tupleV1.contains("Katze") && tupleV2.contains("Malboro"))
                        if(neighbor(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //11. Der Mann mit dem Pferd lebt neben der Person, die Dunhill raucht.
            if(((tupleV1.contains("Dunhill") && v2.getName().equals("Tier")) || (tupleV1.contains("Pferd") && v2.getName().equals("Zigarette"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Dunhill") && tupleV2.contains("Pferd")) || tupleV1.contains("Pferd") && tupleV2.contains("Dunhill"))
                        if(neighbor(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //12. Der Winfield-Raucher trinkt gern Bier.
            if(((tupleV1.contains("Bier") && v2.getName().equals("Zigarette")) || (tupleV1.contains("Winfield") && v2.getName().equals("Drink"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Bier") && tupleV2.contains("Winfield")) || tupleV1.contains("Winfield") && tupleV2.contains("Bier"))
                        if(directConstraint(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //13. Der Norweger wohnt neben dem blauen Haus
            if(((tupleV1.contains("Norweger") && v2.getName().equals("Farbe")) || (tupleV1.contains("Blau") && v2.getName().equals("Nation"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Norweger") && tupleV2.contains("Blau")) || tupleV1.contains("Blau") && tupleV2.contains("Norweger"))
                        if(neighbor(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //14. Der Deutsche raucht Rothmanns.
            if(((tupleV1.contains("Deutscher") && v2.getName().equals("Zigarette")) || (tupleV1.contains("Rothmann") && v2.getName().equals("Nation"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Deutscher") && tupleV2.contains("Rothmann")) || tupleV1.contains("Rothmann") && tupleV2.contains("Deutscher"))
                        if(directConstraint(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
                    if(!delete)
                        delete = true;
                    break;
                }
            }

            //15. Der Malboro-Raucher hat einen Nachbarn, der Wasser trinkt.
            if(((tupleV1.contains("Malboro") && v2.getName().equals("Drink")) || (tupleV1.contains("Wasser") && v2.getName().equals("Zigarette"))))
            {
                boolean found = false;
                while(iteratorV2.hasNext())
                {
                    tuple tupleV2 = (tuple) iteratorV2.next();
                    if((tupleV1.contains("Malboro") && tupleV2.contains("Wasser")) || tupleV1.contains("Wasser") && tupleV2.contains("Malboro"))
                        if(neighbor(tupleV1, tupleV2))
                        {
                            found = true;
                            break;
                        }

                }

                if(!found)
                {
                    v1.deletetuple(tupleV1);
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

    private boolean neighbor(tuple tupleV1, tuple tupleV2) {
        int difference = tupleV1.getHouseNo() - tupleV2.getHouseNo();

        if(difference == 1) return true;
        if(difference == -1) return true;

        return false;
    }

    private boolean directConstraint(tuple tupleV1, tuple tupleV2) {
        int houseNo = tupleV1.getHouseNo();
        if(tupleV2.equals(houseNo, tupleV2.getPart())) return true;
        return false;
    }

    private boolean gruen_links_weiss(tuple tupleV1, tuple tupleV2) {

        tuple gruen, weiss;
        if(tupleV1.getPart().equals("Gruen") && tupleV2.getPart().equals("Weiss"))
        {
            gruen = tupleV1;
            weiss = tupleV2;
        }else if(tupleV2.getPart().equals("Gruen") && tupleV1.getPart().equals("Weiss"))
        {
            gruen = tupleV2;
            weiss = tupleV1;
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
