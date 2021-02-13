package sample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryHashMap {
    public static final HashMap<String,Integer> COUNTRY_INDEX = new HashMap<>() {{
//        put("ontario", 0);
//        put("quebec", 1);
        put("nw Territory", 2);
//        put("alberta", 3);
//        put("greenland", 4);
        put("east US", 5);
        put("west US", 6);
        put("c America", 7);
//        put("alaska", 8);
        put("gb", 9);
        put("west eu", 10);
        put("south eu", 11);
//        put("ukraine", 12);
        put("north eu", 13);
//        put("iceland", 14);
//        put("scandinavia", 15);
//        put("afghanistan", 16);
//        put("india", 17);
        put("mid East", 18);
//        put("japan", 19);
//        put("ural", 20);
//        put("yakutsk", 21);
//        put("kamchatka", 22);
//        put("siam", 23);
//        put("irkutsk", 24);
//        put("siberia", 25);
//        put("mongolia", 26);
//        put("china", 27);
        put("east Australia", 28);
//        put("new Guinea", 29);
        put("west Australia", 30);
//        put("indonesia", 31);
//        put("venezuela", 32);
//        put("peru", 33);
//        put("brazil", 34);
//        put("argentina", 35);
//        put("congo", 36);
        put("north Africa", 37);
        put("south Africa", 38);
//        put("egypt", 39);
        put("east Africa", 40);
//        put("madagascar", 41);
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
