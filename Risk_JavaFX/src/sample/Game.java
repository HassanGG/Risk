package sample;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private Player player1;
    private Player player2;
    private Player current;
    private Player neutral1, neutral2, neutral3, neutral4;
    private enum cardTypes {INFANTRY, CAVALRY, ARTILLERY}
    private ArrayList<String> deck = new ArrayList<>();
    private HashMap<String, cardTypes> cardValues = new HashMap<>();
    Button[] countryButtons;


    public Game() {
        player1 = new Player("player1", "#ff0000");     //RED
        player2 = new Player("player2", "#0000ff");     //BLUE
        neutral1 = new Player("neutral 1", "#ffffff");  //WHITE
        neutral2 = new Player("neutral 2", "#ffffff");  //WHITE
        neutral3 = new Player("neutral 3", "#ffffff");  //WHITE
        neutral4 = new Player("neutral 4", "#ffffff");  //WHITE

        setCards();
        current = player1;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrent() {
        return current;
    }

    public void setCurrent(Player current) {
        this.current = current;
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

    public boolean p1Turn(){
        return current == player1;
    }

    public Button[] getCountryButtons() {
        return countryButtons;
    }

    public void setCountryButtons(Button[] countryButtons) {
        this.countryButtons = countryButtons;
    }

    public void switchTurn(){
        if(p1Turn()){
            current = player2;
        }else{
            current = player1;
        }
    }


    private void setCards() {
        //I need help
        cardValues.put(Constants.COUNTRY_NAMES[0], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[1], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[2], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[3], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[4], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[5], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[6], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[7], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[8], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[9], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[10], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[11], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[12], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[13], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[14], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[15], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[16], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[17], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[18], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[19], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[20], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[21], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[22], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[23], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[24], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[25], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[26], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[27], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[28], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[29], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[30], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[31], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[32], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[33], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[34], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[35], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[36], cardTypes.CAVALRY);
        cardValues.put(Constants.COUNTRY_NAMES[37], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[38], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[39], cardTypes.INFANTRY);
        cardValues.put(Constants.COUNTRY_NAMES[40], cardTypes.ARTILLERY);
        cardValues.put(Constants.COUNTRY_NAMES[41], cardTypes.INFANTRY);
    }

}
