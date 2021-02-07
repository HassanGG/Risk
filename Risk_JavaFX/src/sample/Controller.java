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

    public Button ontario, quebec, nwTerritory, alberta, greenland, eastUS, westUS, centralAmerica, alaska,
            greatBritain, westEU, southEU, ukraine, northEU, iceland, scandinavia, afghanistan, india,
            middleEast, japan, ural, yakutsk, kamchatka, siam, irkutsk, siberia, mongolia, china,
            eastAustralia, newGuinea, westAustralia, indonesia, venezuela, peru, brazil, argentina,
            congo, northAfrica, southAfrica, egypt, eastAfrica, madagascar;

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
            switch (Constants.CONTINENT_IDS[index]) {
                case 0: countryButtons[index].setStyle("-fx-background-color: #f2ff00; -fx-text-fill: #000000");  //N. America
                    break;
                case 1: countryButtons[index].setStyle("-fx-background-color: #000FFF");                          //Europe
                    break;
                case 2: countryButtons[index].setStyle("-fx-background-color: #00cf22");                          //Asia
                    break;
                case 3: countryButtons[index].setStyle("-fx-background-color: #f562c1");                          //Australia
                    break;
                case 4: countryButtons[index].setStyle("-fx-background-color: #cf0000");                          //S. America
                    break;
                case 5: countryButtons[index].setStyle("-fx-background-color: #ffbb00; -fx-text-fill: #000000");  //Africa
                    break;
            }
        }

        outputText.appendText("Please enter name for Player 1\n");
        inputText.setOnAction(e ->{
            strInput = inputText.getText();
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
