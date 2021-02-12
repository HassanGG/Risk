package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    //Player instances
    Player objPlayer1, objPlayer2, objNeutral1, objNeutral2, objNeutral3, objNeutral4;

    /*Runs piece of code upon application start up*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        outputText.appendText("Please enter name for Player 1\n");
        inputText.setOnAction(e ->{
            strInput = inputText.getText();
            inputHistory.appendText(strInput + "\n");
            parseInput();
            inputText.setText("");
        });

    }

    //integer that decides what to do with input (idk how to do this in better way)
    private Boolean goPlayer1 = true;
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
                allocate.assignPlayerValues();
                objPlayer1 = allocate.getPlayer1();
                objPlayer2 = allocate.getPlayer2();
                objNeutral1 = allocate.getNeutral1();
                objNeutral2 = allocate.getNeutral2();
                objNeutral3 = allocate.getNeutral3();
                objNeutral4 = allocate.getNeutral4();
                printAllocation(allocate);
                i++;
                outputText.appendText("Player 1 it's your turn, press enter \"finish\" to finish\n");
                parseInput();
                break;
            }

            case 2: {
                if(goPlayer1) {
                    move(objPlayer1, inputText.getText());
                } else {
                     move(objPlayer2, inputText.getText());
                }

                inputText.setText("");
            }


        }

        if(i != 2) {
            i++;
        }
    }

    public void move(Player player, String input) {

        if(input.equals("finish")) {
            goPlayer1 = !goPlayer1;
            if(goPlayer1) {
                outputText.appendText("Player 1 it's your turn, press enter \"finish\" to finish\n");
            }

            if(!goPlayer1) {
                outputText.appendText("Player 2 it's your turn, press enter \"finish\" to finish\n");
            }
        }

        //if input is equal to other things do other things
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
        outputText.appendText(objPlayer1.getName() + "'s countries: " + objPlayer1.getCountries().toString() + "\n");
        outputText.appendText(objPlayer2.getName() + "'s countries: " + objPlayer2.getCountries().toString() + "\n");
        outputText.appendText("Neutral Countries: \n" + objNeutral1.getCountries().toString() + "\n");
        outputText.appendText(objNeutral2.getCountries().toString() + "\n");
        outputText.appendText(objNeutral3.getCountries().toString() + "\n");
        outputText.appendText(objNeutral4.getCountries().toString() + "\n");
    }
}
