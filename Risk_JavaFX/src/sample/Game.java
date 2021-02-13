package sample;

import javafx.scene.paint.Color;

public class Game {
    Player player1;
    Player player2;
    Player current;
    Player neutral1, neutral2, neutral3, neutral4;

    public Game() {
        player1 = new Player("player1", Color.RED);
        player2 = new Player("player2", Color.BLACK);
        neutral1 = new Player("neutral 1", Color.WHITE);
        neutral2 = new Player("neutral 2", Color.WHITE);
        neutral3 = new Player("neutral 3", Color.WHITE);
        neutral4 = new Player("neutral 4", Color.WHITE);
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

    public boolean p1Turn(){
        return current == player1;
    }

    public void switchTurn(){
        if(p1Turn()){
            current = player2;
        }else{
            current = player1;
        }
    }

}
