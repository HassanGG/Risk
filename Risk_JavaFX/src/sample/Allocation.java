package sample;

import javafx.scene.paint.Color;

import java.util.*;

//!!!!!!In order to access player names this class needs to be extended!!!!!!!
public class Allocation implements Allocate {

    private final String PLAYER1, PLAYER2;
    private Player player1;
    private Player player2;
    private Player neutral1;
    private Player neutral2;
    private Player neutral3;
    private Player neutral4;

    private ArrayList<String> player1Countries = new ArrayList<String>();
    private ArrayList<String> player2Countries = new ArrayList<String>();
    private ArrayList<String> neutral1Countries = new ArrayList<String>();
    private ArrayList<String> neutral2Countries = new ArrayList<String>();
    private ArrayList<String> neutral3Countries = new ArrayList<String>();
    private ArrayList<String> neutral4Countries = new ArrayList<String>();

    //hashmap containing all countries and their army values
    private HashMap<String, Integer> allArmies = new HashMap<>();



    //player names will decided in another class or in main
    public Allocation(String player1, String player2) {
        this.PLAYER1 = player1;
        this.PLAYER2 = player2;
    }

    public HashMap<String, Integer> getAllArmies() {
        return allArmies;
    }
//    public String getPLAYER1() {
//        return PLAYER1;
//    }
//
//    public String getPLAYER2() {
//        return PLAYER2;
//    }
//
//    public ArrayList<String> getPlayer1Countries() {
//        return player1Countries;
//    }
//
//    public ArrayList<String> getPlayer2Countries() {
//        return player2Countries;
//    }
//
//    public ArrayList<String> getNeutral1Countries() {
//        return neutral1Countries;
//    }
//
//    public ArrayList<String> getNeutral2Countries() {
//        return neutral2Countries;
//    }
//
//    public ArrayList<String> getNeutral3Countries() {
//        return neutral3Countries;
//    }
//
//    public ArrayList<String> getNeutral4Countries() {return neutral4Countries; }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getNeutral1() {
        return neutral1;
    }

    public Player getNeutral2() {
        return neutral2;
    }

    public Player getNeutral3() {
        return neutral3;
    }

    public Player getNeutral4() {
        return neutral4;
    }

    public void assignPlayerValues() {
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
                case 0: player1Countries.add(randomize.get(i));
                    break;
                case 1: player2Countries.add(randomize.get(i));
                    break;
                case 2: neutral1Countries.add(randomize.get(i));
                    break;
                case 3: neutral2Countries.add(randomize.get(i));
                    break;
                case 4: neutral3Countries.add(randomize.get(i));
                    break;
                case 5: neutral4Countries.add(randomize.get(i));
                    break;
            }
        }

        //all temporarily set to blue for colour.
        player1 = new Player(PLAYER1, Color.BLUE, player1Countries);
        player2 = new Player(PLAYER2, Color.RED, player2Countries);
        neutral1 = new Player("neutral 1", Color.WHITE, neutral1Countries);
        neutral2 = new Player("neutral 2", Color.WHITE, neutral2Countries);
        neutral3 = new Player("neutral 3", Color.WHITE, neutral3Countries);
        neutral4 = new Player("neutral 4", Color.WHITE, neutral4Countries);
        assignArmies();
    }

    private void assignArmies() {
        //armies per country is equal to 4
        ArrayList<ArrayList<String>> playerCountries = new ArrayList<>();
        playerCountries.add(player1.getCountries());
        playerCountries.add(player2.getCountries());
        playerCountries.add(neutral1.getCountries());
        playerCountries.add(neutral2.getCountries());
        playerCountries.add(neutral3.getCountries());
        playerCountries.add(neutral4.getCountries());

        for(ArrayList<String> i : playerCountries) {
            for(String j : i) {
                allArmies.put(j, 4);
            }
        }

    }

}
