package sample;

import java.util.ArrayList;
import java.util.HashMap;

//allocate players like so
//Allocate allocation = new Allocation("player1", "player2");
//allocation.assignCountries();
public interface Allocate {
    //gets the name of the first player
    public Player getPlayer1();

    //gets the name of the second player
    public Player getPlayer2();

    public Player getNeutral1();
    public Player getNeutral2();
    public Player getNeutral3();
    public Player getNeutral4();

    //get each player
//    public ArrayList<String> getNeutral1Countries();
//    public ArrayList<String> getNeutral2Countries();
//    public ArrayList<String> getNeutral3Countries();
//    public ArrayList<String> getNeutral4Countries();
//    public ArrayList<String> getPlayer1Countries();
//    public ArrayList<String> getPlayer2Countries();

//    public HashMap<String, Integer> getPlayer1Armies();
//    public HashMap<String, Integer> getPlayer1Armies();
//    public HashMap<String, Integer> getPlayer1Armies();
//    public HashMap<String, Integer> getPlayer1Armies();
//    public HashMap<String, Integer> getPlayer1Armies();
//    public HashMap<String, Integer> getPlayer1Armies();
//    public HashMap<String, Integer> getPlayer1Armies();

    //assigns random countries based on continent to 2 players and 4 neutrals
    public void assignPlayerValues();

}
