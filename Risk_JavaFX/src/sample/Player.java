package sample;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

//player class that contains all information on each player
//colour needs to be added first before we assign colour same with dice
public class Player {
    private String name;
    private String colour;
    private ArrayList<String> countries = new ArrayList<>();
    public ArrayList<String> cardHand = new ArrayList<>();
    private Dice dice = new Dice();
    public int infantryNum = 0;
    public int cavalryNum = 0;
    public int artilleryNum = 0;

    public Player(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    //method that checks if player owns continent
    //TODO: thest thsi
    public Boolean[] ownedContinents() {
        //in order: N.America, Europe, Asia, Australia, S. America, Africa
        Boolean[] owns = new Boolean[6];
        //sets the initial values to be true;
        Arrays.fill(owns, true);

        //checks if player owns certain continents
        for(int i = 0; i < Constants.COUNTRY_NAMES.length; i++) {
            switch (Constants.CONTINENT_IDS[i]) {
                case 0:
                    if(!countries.contains(Constants.COUNTRY_NAMES[i])){
                        owns[0] = false;
                    }
                    break;
                case 1:
                    if(!countries.contains(Constants.COUNTRY_NAMES[i])){
                        owns[1] = false;
                    }
                    break;
                case 2:
                    if(!countries.contains(Constants.COUNTRY_NAMES[i])){
                        owns[2] = false;
                    }
                    break;
                case 3:
                    if(!countries.contains(Constants.COUNTRY_NAMES[i])){
                        owns[3] = false;
                    }
                    break;
                case 4:
                    if(!countries.contains(Constants.COUNTRY_NAMES[i])){
                        owns[4] = false;
                    }
                    break;
                case 5:
                    if(!countries.contains(Constants.COUNTRY_NAMES[i])){
                        owns[5] = false;
                    }
                    break;
            }
        }
        return owns;
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

    public void removeCards(Controller.removeType type, Game game, int removeNumTo) {
        int numtoRemove = removeNumTo;
        switch (type) {
            case infantries:
                for(Iterator<String> ite = cardHand.iterator(); ite.hasNext();){
                    String card = ite.next();
                    if(numtoRemove == 0) {
                        return;
                    }
                    if(game.getCardValues().get(card) == Game.cardTypes.INFANTRY) {
                        ite.remove();
                        game.getCurrent().infantryNum -= 1;
                        numtoRemove--;
                    }
                }
                break;
            case cavalries:
                for(Iterator<String> ite = cardHand.iterator(); ite.hasNext();){
                    String card = ite.next();
                    if(numtoRemove == 0) {
                        return;
                    }
                    if(game.getCardValues().get(card) == Game.cardTypes.CAVALRY) {
                        ite.remove();
                        game.getCurrent().infantryNum -= 1;
                        numtoRemove--;
                    }
                }
                break;
            case artilleries:
                for(Iterator<String> ite = cardHand.iterator(); ite.hasNext();){
                    String card = ite.next();
                    if(numtoRemove == 0) {
                        return;
                    }
                    if(game.getCardValues().get(card) == Game.cardTypes.ARTILLERY) {
                        ite.remove();
                        game.getCurrent().infantryNum -= 1;
                        numtoRemove--;
                    }
                }
                break;
        }
    }

    public static void printCards(TextArea outputText, Game game){
        for(String country : game.getCurrent().cardHand){
            outputText.appendText(country + ", " + game.getCardValues().get(country) + "\n");
        }
    }
}
