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
                case 0: game.getPlayer1().getCountries().add(randomize.get(i));
                    break;
                case 1: game.getPlayer2().getCountries().add(randomize.get(i));
                    break;
                case 2: game.getNeutral1().getCountries().add(randomize.get(i));
                    break;
                case 3: game.getNeutral2().getCountries().add(randomize.get(i));
                    break;
                case 4: game.getNeutral3().getCountries().add(randomize.get(i));
                    break;
                case 5: game.getNeutral4().getCountries().add(randomize.get(i));
                    break;
            }
        }

        assignArmies(game);
    }

    private void assignArmies(Game game) {
        //armies per country is equal to 4
        ArrayList<ArrayList<String>> playerCountries = new ArrayList<>();
        playerCountries.add(game.getPlayer1().getCountries());
        playerCountries.add(game.getPlayer2().getCountries());
        playerCountries.add(game.getNeutral1().getCountries());
        playerCountries.add(game.getNeutral2().getCountries());
        playerCountries.add(game.getNeutral3().getCountries());
        playerCountries.add(game.getNeutral4().getCountries());

        for(ArrayList<String> i : playerCountries) {
            for(String j : i) {
                allArmies.put(j, 4);
            }
        }

    }

}
