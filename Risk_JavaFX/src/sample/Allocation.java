package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//!!!!!!In order to access player names this class needs to be extended!!!!!!!
public class Allocation implements Allocate {

    private final String player1, player2;

    private ArrayList<String> player1Countries = new ArrayList<String>();
    private ArrayList<String> player2Countries = new ArrayList<String>();
    private ArrayList<String> neutral1Countries = new ArrayList<String>();
    private ArrayList<String> neutral2Countries = new ArrayList<String>();
    private ArrayList<String> neutral3Countries = new ArrayList<String>();
    private ArrayList<String> neutral4Countries = new ArrayList<String>();


    //player names will decided in another class or in main
    public Allocation(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public ArrayList<String> getPlayer1Countries() {
        return player1Countries;
    }

    public ArrayList<String> getPlayer2Countries() {
        return player2Countries;
    }

    public ArrayList<String> getNeutral1Countries() {
        return neutral1Countries;
    }

    public ArrayList<String> getNeutral2Countries() {
        return neutral2Countries;
    }

    public ArrayList<String> getNeutral3Countries() {
        return neutral3Countries;
    }

    public ArrayList<String> getNeutral4Countries() {return neutral4Countries; }


        public void assignCountries() {
        //copy array of countries strings to list so we can randomize
        String[] countries = Constants.COUNTRY_NAMES;
        List<String> randomize = new ArrayList<String>(Arrays.asList(countries));

        //randomize the list of countries
        Collections.shuffle(randomize);

        //assign the countries to each player
        int whichPlayer = 0;
        for(int i = 0; i < randomize.size(); i++) {
            whichPlayer = i % 2;
            switch (whichPlayer) {
                case 0 -> player1Countries.add(randomize.get(i));
                case 1 -> player2Countries.add(randomize.get(i));
                case 2 -> neutral1Countries.add(randomize.get(i));
                case 3 -> neutral2Countries.add(randomize.get(i));
                case 4 -> neutral3Countries.add(randomize.get(i));
                case 5 -> neutral4Countries.add(randomize.get(i));
            }
        }
    }

}
