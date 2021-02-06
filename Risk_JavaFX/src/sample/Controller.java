package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button ontario, quebec, nwTerritory, alberta, greenland, eastUS, westUS, centralAmerica, alaska,
            greatBritain, westEU, southEU, ukraine, northEU, iceland, scandinavia, afghanistan, india,
            middleEast, japan, ural, yakutsk, kamchatka, siam, irkutsk, siberia, mongolia, china,
            eastAustralia, newGuinea, westAustralia, indonesia, venezuela, peru, brazil, argentina,
            congo, northAfrica, southAfrica, egypt, eastAfrica, madagascar;

    public final int[] continentIDs = Constants.getContinentIds();

    //global variables for io.
    @FXML private TextArea outputText;
    @FXML private TextField inputText;
    private String strInput;
    private String player1, player2;
    Allocate allocate;
    private String listOutput = "";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final Button[] countryButtons = {
                ontario, quebec, nwTerritory, alberta, greenland, eastUS, westUS, centralAmerica, alaska,
                greatBritain, westEU, southEU, ukraine, northEU, iceland, scandinavia, afghanistan, india,
                middleEast, japan, ural, yakutsk, kamchatka, siam, irkutsk, siberia, mongolia, china,
                eastAustralia, newGuinea, westAustralia, indonesia, venezuela, peru, brazil, argentina,
                congo, northAfrica, southAfrica, egypt, eastAfrica, madagascar};

        for (int index = 0; index < countryButtons.length; index++) {
            switch (continentIDs[index]) {
                case 0 -> countryButtons[index].setStyle("-fx-background-color: #f2ff00; -fx-text-fill: #000000");  //N. America
                case 1 -> countryButtons[index].setStyle("-fx-background-color: #000FFF");                          //Europe
                case 2 -> countryButtons[index].setStyle("-fx-background-color: #00cf22");                          //Asia
                case 3 -> countryButtons[index].setStyle("-fx-background-color: #f562c1");                          //Australia
                case 4 -> countryButtons[index].setStyle("-fx-background-color: #cf0000");                          //S. America
                case 5 -> countryButtons[index].setStyle("-fx-background-color: #ffbb00; -fx-text-fill: #000000");  //Africa
            }
        }

        listOutput += "Please enter name for Player 1\n";
        outputText.setText(listOutput);
        inputText.setOnAction(e ->{
            strInput = inputText.getText();
            parseInput();
        });

    }

    //integer that decides what to do with input (idk how to do this in better way)
    private int i = 0;
    private void parseInput() {
        switch (i) {
            case 0 -> {
                //gets player 1 name from input
                player1 = strInput;
                listOutput += "Please enter name for Player 2\n";
                outputText.setText(listOutput);
                inputText.setText("");
            }
            case 1 -> {
                //assigns countries to each player after getting player 2 name
                player2 = strInput;
                listOutput += "player1: " + player1 + "\nplayer2: " + player2 + "\n";
                inputText.setText("");
                allocate = new Allocation(player1, player2);
                allocate.assignCountries();
                outputText.setText(listOutput);

            }
        }
        i++;
    }

}
