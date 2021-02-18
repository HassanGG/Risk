package sample;

import java.util.Random;

public class Dice {
    private int diceValue;

    public Dice() {
    }

    public int getDiceValue() {
        return diceValue;
    }

    private void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public int rollDice(){
        Random rand = new Random();
        setDiceValue(rand.nextInt(6) + 1);
        return getDiceValue();
    }
}
