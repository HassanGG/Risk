package sample;

import javafx.scene.control.Button;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryHashMap {
    public static final HashMap<String,Integer> COUNTRY_INDEX = new HashMap<>() {{
        put("east US", 5);
        put("east united states", 5);
        put("west US", 6);
        put("west united states", 6);
        put("c America", 7);
        put("britain", 9);
        put("west eu", 10);
        put("south eu", 11);
        put("north eu", 13);
        put("mid East", 18);
        put("east Australia", 28);
        put("west Australia", 30);
        put("north Africa", 37);
        put("south Africa", 38);
        put("east Africa", 40);
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
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        String[] countries = Constants.COUNTRY_NAMES;

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
