package sample;

import java.util.*;

//!!!!!!In order to access player names this class needs to be extended!!!!!!!
public class Allocation {

    //hashmap containing all countries and their army values
    private HashMap<String, Integer> allArmies = new HashMap<>();

    public Allocation() {
    }

    public HashMap<String, Integer> getAllArmies() {
        return allArmies;
    }

    public void assignPlayerValues(Game game) {
        //copy array of countries strings to list so we can randomize
        String[] countries = Constants.COUNTRY_NAMES;
        List<String> randomize = new ArrayList<String>(Arrays.asList(countries));

        //randomize the list of countries
        Collections.shuffle(randomize);

        //assign the countries to each player
        int whichPlayer = 0;
        final int POINT_WHEN_PLAYERS_GET_MORE = 36;
        for(int i = 0; i < randomize.size(); i++) {
            if(i > POINT_WHEN_PLAYERS_GET_MORE) {
                whichPlayer = i % 2;
            } else {
                whichPlayer = i % 6;
            }

            switch (whichPlayer) {
                case 0: game.player1.countries.add(randomize.get(i));
                    break;
                case 1: game.player2.countries.add(randomize.get(i));
                    break;
                case 2: game.neutral1.countries.add(randomize.get(i));
                    break;
                case 3: game.neutral2.countries.add(randomize.get(i));
                    break;
                case 4: game.neutral3.countries.add(randomize.get(i));
                    break;
                case 5: game.neutral4.countries.add(randomize.get(i));
                    break;
            }
        }

//        assignArmies();
    }

//    private void assignArmies() {
//        //armies per country is equal to 4
//        ArrayList<ArrayList<String>> playerCountries = new ArrayList<>();
//        playerCountries.add(player1.getCountries());
//        playerCountries.add(player2.getCountries());
//        playerCountries.add(neutral1.getCountries());
//        playerCountries.add(neutral2.getCountries());
//        playerCountries.add(neutral3.getCountries());
//        playerCountries.add(neutral4.getCountries());
//
//        for(ArrayList<String> i : playerCountries) {
//            for(String j : i) {
//                allArmies.put(j, 4);
//            }
//        }
//
//    }

}
