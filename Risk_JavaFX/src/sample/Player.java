package sample;

import java.util.ArrayList;

//player class that contains all information on each player
//colour needs to be added first before we assign colour same with dice
public class Player {

    public Player(String name, String colour, ArrayList<String> countries) {
        this.name = name;
        this.colour = colour;
        this.countries = (ArrayList<String>) countries.clone();
    }


    private String name;
    private String colour;
    private ArrayList<String> countries;
    //dice instance here

}
