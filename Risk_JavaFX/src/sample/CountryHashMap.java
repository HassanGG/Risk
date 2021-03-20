package sample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryHashMap {
    public static final HashMap<String,Integer> COUNTRY_INDEX = new HashMap<>() {{
        put("eastUS", 5);
        put("eastunitedstates", 5);
        put("westUS", 6);
        put("westunitedstates", 6);
        put("cAmerica", 7);
        put("britain", 9);
        put("westeu", 10);
        put("southeu", 11);
        put("northeu", 13);
        put("midEast", 18);
        put("eastAustralia", 28);
        put("westAustralia", 30);
        put("northAfrica", 37);
        put("southAfrica", 38);
        put("eastAfrica", 40);
    }};

    public static final HashMap<String, String> COUNTRY_BUTTON_ALT_NAMES = new HashMap<>() {{
        put("NW Territory", "nwTerritory");
        put("W United States", "westUS");
        put("West US", "westUS");
        put("E United States", "eastUS");
        put("East US", "eastUS");
        put("Central America", "centralAmerica");
        put("C America", "centralAmerica");
        put("Great Britain", "greatBritain");
        put("Britain", "greatBritain");
        put("N Europe", "northEU");
        put("North EU", "northEU");
        put("W Europe", "westEU");
        put("West EU", "westEU");
        put("S Europe", "southEU");
        put("South EU", "southEU");
        put("N Africa", "northAfrica");
        put("North Africa", "northAfrica");
        put("E Africa", "eastAfrica");
        put("East Africa", "eastAfrica");
        put("S Africa", "southAfrica");
        put("South Africa", "southAfrica");
        put("Middle East", "middleEast");
        put("Mid East", "middleEast");
        put("New Guinea", "newGuinea");
        put("W Australia", "westAustralia");
        put("West Australia", "westAustralia");
        put("E Australia", "eastAustralia");
        put("East Australia", "eastAustralia");
    }};


    public static int getIndexOfCountry(String regex){
        int index = -1;
        boolean foundIndex = false;
        regex = regex.replaceAll("\\s","");
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        String[] countries = new String[Constants.NUM_COUNTRIES];

        String name;

        for (int i=0; i<Constants.NUM_COUNTRIES; i++) {
            name = Constants.COUNTRY_NAMES[i];
            name = name.toLowerCase();
            name = name.replaceAll("\\s","");

            countries[i] = name;
        }

        for(String country : countries){
            Matcher m = pattern.matcher(country);
            if (m.find()) {
                index = Arrays.asList(countries).indexOf(country);
                foundIndex = true;
            }
        }

        if(!foundIndex){
            Set<String> keys = CountryHashMap.COUNTRY_INDEX.keySet();

            for (String key : keys) {
                Matcher m = pattern.matcher(key);
                if (m.find()) {
                    index = CountryHashMap.COUNTRY_INDEX.get(key);
                }
            }
        }

        return index;
    }
}
