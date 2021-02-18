package sample;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Game {
    private Player player1;
    private Player player2;
    private Player current;
    private Player neutral1, neutral2, neutral3, neutral4;
    Button[] countryButtons;

    public Game() {
        player1 = new Player("player1", "#ff0000");     //RED
        player2 = new Player("player2", "#0000ff");     //BLUE
        neutral1 = new Player("neutral 1", "#ffffff");  //WHITE
        neutral2 = new Player("neutral 2", "#ffffff");  //WHITE
        neutral3 = new Player("neutral 3", "#ffffff");  //WHITE
        neutral4 = new Player("neutral 4", "#ffffff");  //WHITE
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

    public void setCountryButtons(Button[] countryButtonsss) {
        this.countryButtons = countryButtonsss;
    }

    public void switchTurn(){
        if(p1Turn()){
            current = player2;
        }else{
            current = player1;
        }
    }

}
