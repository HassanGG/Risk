package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

//player class that contains all information on each player
//colour needs to be added first before we assign colour same with dice
public class Player {
    private String name;
    private String colour;
    private ArrayList<String> countries = new ArrayList<>();
    private Dice dice;
//    private HashMap<String, Integer> armies;

    public Player(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return colour;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

    public Dice getDice() {
        return dice;
    }

    //    public HashMap<String, Integer> getArmies() {
//        return armies;
//    }


}
