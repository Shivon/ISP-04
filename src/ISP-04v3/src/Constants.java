import java.util.*;

/**
 * Created by murat on 14.12.16.
 */
public class Constants {
    public static final int AMOUNT_OF_OPTIONS = 5;
    public static final String LEFT = "LEFT";
    public static final String NEIGHBOR = "NEIGHBOR";

    public static final String NATION = "Nation";
    public static final String ZIGARETTE = "Zigarette";
    public static final String GETRAENK = "Getränk";
    public static final String FARBE = "Farbe";
    public static final String TIER = "Tier";
    public static final String REIHENFOLGE = "Reihenfolge";

    public static HashMap<String, String> optionComponentNameMapping = new HashMap<>();

    public static List<String> componentNames = Arrays.asList(NATION, ZIGARETTE, GETRAENK, FARBE, TIER, REIHENFOLGE);

    public static String generate_id(Component component1, Component component2, Component component3) {
        String result = "";
        char[] chars = new char[3];
        chars[0] = component1.getName().charAt(0);
        chars[1] = component2.getName().charAt(0);
        chars[2] = component3.getName().charAt(0);
        Arrays.sort(chars);

        result = String.copyValueOf(chars);
        return result;
    }


    public static boolean idIsValid(String id) {
        char[] chars = new char[3];
        chars[0] = id.charAt(0);
        chars[1] = id.charAt(1);
        chars[2] = id.charAt(2);

        if(chars[0] == chars[1]) return false;
        if(chars[0] == chars[2]) return false;
        if(chars[1] == chars[2]) return false;
        return true;
    }

    public static void initNameMapping()
    {
        optionComponentNameMapping.put("Brite", NATION);
        optionComponentNameMapping.put("Schwede", NATION);
        optionComponentNameMapping.put("Däne", NATION);
        optionComponentNameMapping.put("Norweger", NATION);
        optionComponentNameMapping.put("Deutscher", NATION);

        optionComponentNameMapping.put("Tee", GETRAENK);
        optionComponentNameMapping.put("Kaffee", GETRAENK);
        optionComponentNameMapping.put("Milch", GETRAENK);
        optionComponentNameMapping.put("Bier", GETRAENK);
        optionComponentNameMapping.put("Wasser", GETRAENK);

        optionComponentNameMapping.put("Hund", TIER);
        optionComponentNameMapping.put("Vogel", TIER);
        optionComponentNameMapping.put("Katze", TIER);
        optionComponentNameMapping.put("Pferd", TIER);
        optionComponentNameMapping.put("Fisch", TIER);

        optionComponentNameMapping.put("1", REIHENFOLGE);
        optionComponentNameMapping.put("2", REIHENFOLGE);
        optionComponentNameMapping.put("3", REIHENFOLGE);
        optionComponentNameMapping.put("4", REIHENFOLGE);
        optionComponentNameMapping.put("5", REIHENFOLGE);

        optionComponentNameMapping.put("Rot", FARBE);
        optionComponentNameMapping.put("Grün", FARBE);
        optionComponentNameMapping.put("Weiß", FARBE);
        optionComponentNameMapping.put("Gelb", FARBE);
        optionComponentNameMapping.put("Blau", FARBE);

        optionComponentNameMapping.put("Pall-Mall", ZIGARETTE);
        optionComponentNameMapping.put("Dunhill", ZIGARETTE);
        optionComponentNameMapping.put("Malboro", ZIGARETTE);
        optionComponentNameMapping.put("Winfield", ZIGARETTE);
        optionComponentNameMapping.put("Rothmann", ZIGARETTE);
    }
    public static String getComponentName(String option)
    {
        return optionComponentNameMapping.get(option);
    }
}
