package sample;

import java.util.ArrayList;

//allocate players like so
//Allocate allocation = new Allocation("player1", "player2");
//allocation.assignCountries();
public interface Allocate {
    //gets the name of the first player
    public String getPLAYER1();

    //gets the name of the second player
    public String getPLAYER2();

    //get each player
    public ArrayList<String> getNeutral1Countries();

    public ArrayList<String> getNeutral2Countries();

    public ArrayList<String> getNeutral3Countries();

    public ArrayList<String> getNeutral4Countries();

    public ArrayList<String> getPlayer1Countries();

    public ArrayList<String> getPlayer2Countries();

    //assigns random countries based on continent to 2 players and 4 neutrals
    public void assignCountries();

}
