package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, EventHandler<ActionEvent> {

    /* Connects Controller class to fxml file*/
    @FXML Button ontario, quebec, nwTerritory, alberta, greenland, eastUS, westUS, centralAmerica, alaska,
            greatBritain, westEU, southEU, ukraine, northEU, iceland, scandinavia, afghanistan, india,
            middleEast, japan, ural, yakutsk, kamchatka, siam, irkutsk, siberia, mongolia, china,
            eastAustralia, newGuinea, westAustralia, indonesia, venezuela, peru, brazil, argentina,
            congo, northAfrica, southAfrica, egypt, eastAfrica, madagascar;

    //global variables for io.
    @FXML private TextArea outputText;
    @FXML public TextField inputText;
    @FXML private TextArea inputHistory;
    private String strInput;
    private Boolean gotPlayerNames = false;

    Allocation allocate = new Allocation();
    Game game = new Game();

    /*Runs piece of code upon application start up*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputText.appendText("Please enter name for Player 1\n");
        inputText.setOnAction(this);

    }

    private int i = 0;
    private void parseInput() {
        switch (i) {
            case 0:
                //gets player 1 name from input
                game.player1.setName(inputText.getText());
                outputText.appendText("Please enter name for Player 2\n");
                inputText.setText("");
                i++;
                break;

            case 1: {
                game.player2.setName(inputText.getText());
                outputText.appendText("player 1: " + game.player1.getName() + "\nplayer 2: " + game.player2.getName() + "\n");
                inputText.setText("");
                allocate.assignPlayerValues(game);
                printAllocation();
                move(game.current);
                gotPlayerNames = true;
                break;
            }
        }
    }

    public void move(Player player) {
        outputText.appendText(game.current.getName() + ", it is your turn, press enter to finish\n");
        /* Code to implement move goes here
        *
        *
        *
        *
        * */

        inputHistory.appendText(inputText.getText() + "\n");
        inputText.setText("");
        game.switchTurn();
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

    private void printAllocation() {
        outputText.appendText(game.player1.getName() + "'s countries: " + game.player1.getCountries().toString() + "\n");
        outputText.appendText(game.player2.getName() + "'s countries: " + game.player2.getCountries().toString() + "\n");
        outputText.appendText("Neutral Countries: \n" + game.neutral1.getCountries().toString() + "\n");
        outputText.appendText(game.neutral2.getCountries().toString() + "\n");
        outputText.appendText(game.neutral3.getCountries().toString() + "\n");
        outputText.appendText(game.neutral4.getCountries().toString() + "\n\n");
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(!gotPlayerNames){
            inputHistory.appendText(inputText.getText() + "\n");
            parseInput();
            inputText.setText("");
        }else{
            move(game.current);
        }
    }
}
