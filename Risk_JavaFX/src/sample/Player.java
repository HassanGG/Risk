package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

//player class that contains all information on each player
//colour needs to be added first before we assign colour same with dice
public class Player {
    private String name;
    private Color colour;
    private ArrayList<String> countries;
//    private HashMap<String, Integer> armies;
    //dice instance here

    public Player(String name, Color colour, ArrayList<String> countries) {
        this.name = name;
        this.colour = colour;
        this.countries = (ArrayList<String>) countries.clone();
    }

    public Player() {}

    public String getName() {
        return name;
    }

    public Color getColour() {
        return colour;
    }

    public ArrayList<String> getCountries() {
        return countries;
    }

//    public HashMap<String, Integer> getArmies() {
//        return armies;
//    }


}
