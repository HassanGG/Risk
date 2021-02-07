package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    /* Connects Controller class to fxml file*/
    public Button ontario, quebec, nwTerritory, alberta, greenland, eastUS, westUS, centralAmerica, alaska,
            greatBritain, westEU, southEU, ukraine, northEU, iceland, scandinavia, afghanistan, india,
            middleEast, japan, ural, yakutsk, kamchatka, siam, irkutsk, siberia, mongolia, china,
            eastAustralia, newGuinea, westAustralia, indonesia, venezuela, peru, brazil, argentina,
            congo, northAfrica, southAfrica, egypt, eastAfrica, madagascar;

    //global variables for io.
    @FXML private TextArea outputText;
    @FXML private TextField inputText;
    @FXML private TextArea inputHistory;
    private String strInput;
    private String player1, player2;
    Allocate allocate;

    /*Runs piece of code upon application start up*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputText.appendText("Please enter name for Player 1\n");
        inputText.setOnAction(e ->{
            strInput = inputText.getText();
            inputHistory.appendText(strInput + "\n");
            inputText.setText("");
            parseInput();
        });

    }

    //integer that decides what to do with input (idk how to do this in better way)
    private int i = 0;
    private void parseInput() {
        switch (i) {
            case 0:
                //gets player 1 name from input
                player1 = strInput;
                outputText.appendText("Please enter name for Player 2\n");
                inputText.setText("");
                break;

            case 1: {
                player2 = strInput;
                outputText.appendText("player 1: " + player1 + "\nplayer 2: " + player2 + "\n");
                inputText.setText("");
                allocate = new Allocation(player1, player2);
                allocate.assignCountries();
                printAllocation(allocate);
                break;
            }
        }
        i++;
    }

    /*
    * ((Button)event.getSource()).getId() --->  gets the id of the event source, e.g. when "Ontario" button
    *       is pressed, its fx:id "ontario" is return as a string.
    * CountryHashMap.COUNTRY_INDEX.get(((Button)event.getSource()).getId()) ---> returns the index of the id to
    *       be used in other arrays. e.g. Constants.COUNTRY_NAMES, Constants.ADJACENT
    * */
    public void getAdjacentCountries(ActionEvent event){
        int countryIndex = CountryHashMap.COUNTRY_INDEX.get(((Button)event.getSource()).getId());

        String string = Constants.COUNTRY_NAMES[countryIndex] + "'s Adjacent Countries:\n";

        for (int index = 0; index < Constants.ADJACENT[countryIndex].length; index++){
            int i = Constants.ADJACENT[countryIndex][index];
            string += Constants.COUNTRY_NAMES[i] + "\n";
        }
        string += "\n";

        outputText.appendText(string);
    }

    private void printAllocation(Allocate allocate) {
        outputText.appendText(allocate.getPLAYER1() + "'s countries: " + allocate.getPlayer1Countries().toString() + "\n");
        outputText.appendText(allocate.getPLAYER2() + "'s countries: " + allocate.getPlayer2Countries().toString() + "\n");
        outputText.appendText("Neutral Countries: \n" + allocate.getNeutral1Countries().toString() + "\n");
        outputText.appendText(allocate.getNeutral2Countries().toString() + "\n");
        outputText.appendText(allocate.getNeutral3Countries().toString() + "\n");
        outputText.appendText(allocate.getNeutral4Countries().toString() + "\n");
    }
}
