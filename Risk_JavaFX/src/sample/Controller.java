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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private Boolean gotPlayerNames = false;

    Allocation allocate = new Allocation();
    Game game = new Game();

    /*Runs piece of code upon application start up*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Button[] countryButtons = {ontario, quebec, nwTerritory, alberta, greenland, eastUS, westUS, centralAmerica, alaska,
                greatBritain, westEU, southEU, ukraine, northEU, iceland, scandinavia, afghanistan, india,
                middleEast, japan, ural, yakutsk, kamchatka, siam, irkutsk, siberia, mongolia, china,
                eastAustralia, newGuinea, westAustralia, indonesia, venezuela, peru, brazil, argentina,
                congo, northAfrica, southAfrica, egypt, eastAfrica, madagascar};

        outputText.appendText("Please enter name for Player 1\n");
        inputText.setOnAction(this);    //calls handle method

        game.setCountryButtons(countryButtons);
    }

    private int i = 0;
    private void parseInput() {
        switch (i) {
            case 0:
                //gets player 1 name from input
                game.getPlayer1().setName(inputText.getText());
                outputText.appendText("Please enter name for Player 2\n");
                inputText.setText("");
                i++;
                break;

            case 1: {
                game.getPlayer2().setName(inputText.getText());
                outputText.appendText("\nPlayer 1: " + game.getPlayer1().getName() +"\tColour: RED\n"
                        + "Player 2: " + game.getPlayer2().getName() + "\tColour: BLUE\n\n");
                inputText.setText("");

                allocate.assignPlayerValues(game);
                printAllocation();

                outputText.appendText(game.getCurrent().getName() + ", it is your turn. Enter the country you would like to assign armies to.\nYou have " + numToAssign + "\n");

                initialiseButtonColours();
                state = gameStates.CHOOSE_COUNTRY;
//                gotPlayerNames = true;
                break;
            }
        }
    }

    //currently move is not being used

//    private void move() {
//        int index = CountryHashMap.getIndexOfCountry(inputText.getText());
//
//        if(index == -1){
//            outputText.appendText("Enter valid country.\n");
//            inputText.setText("");
//        }else{
//            //prints the adjacent countries
//            outputText.appendText("Country Name: " + Constants.COUNTRY_NAMES[index] + "\n");
//            outputText.appendText("Continent: " + Constants.CONTINENT_NAMES[Constants.CONTINENT_IDS[index]] + "\n");
//            outputText.appendText("Adjacent Countries: \n");
//            for(int i = 0; i < Constants.ADJACENT[index].length; i++){
//                outputText.appendText("\t" + Constants.COUNTRY_NAMES[Constants.ADJACENT[index][i]] + "\n");
//            }
//
//            outputText.appendText("\n");
//            inputHistory.appendText(inputText.getText() + "\n");
//
//            //handles armies per turn
//            outputText.appendText(game.getCurrent().getName() + ", it is your turn. Enter the country you would like to assign armies to.\nYou have " + numToAssign + "\n");
//            assignArmies(game.getCurrent());
//            inputText.setText("");
//            //here
//        }
//    }

    /* This method assigns armies to the player each turn to the country of their choice
        TODO: ask what country they would like to assign armies to
        TODO: ask how many of their armies they would like to assign
        TODO: if they assigned less than they have ask again what country and so on**/

    private final int DEFAULT_NUM_ARMIES = 3;
    int numToAssign = DEFAULT_NUM_ARMIES;
    private void assignArmies(Player player) {
        Boolean[] ownedContinent = player.ownedContinents().clone();
        //adds value of continent owned to value of turn
        for(int i = 0; i < ownedContinent.length; i++) {
            if(ownedContinent[i]) {
                numToAssign += Constants.CONTINENT_VALUES[i];
            }
        }

        outputText.appendText(game.getCurrent().getName() + ", you have " + numToAssign + " armies to assign this round, what country would you like to assign armies to?\n");
        state = gameStates.CHOOSE_COUNTRY;

    }

    String chosenCountry;
    private void pickCountry(Player player) {

        int index = CountryHashMap.getIndexOfCountry(inputText.getText());

        if(index == -1){
            outputText.appendText("Enter valid country.\n");
            inputText.setText("");
        }else{
            //if the player does not have the country they inputted be mad
            if(!player.getCountries().contains(Constants.COUNTRY_NAMES[index])) {
                outputText.appendText("Enter a country that you own please.\n");
                inputText.setText("");
                return;
            }

            //if country is valid change chosen country and change game state
            chosenCountry = Constants.COUNTRY_NAMES[index];
            state = gameStates.SELECT_AMOUNT_ARMIES;
            inputHistory.appendText(inputText.getText()  +"\n");
            inputText.setText("");
            outputText.appendText("Enter the amount of armies you would like to assign to this country. Current number left: " + numToAssign + "\n");
        }

    }

    private void askAmount(Player player) {
        int amount = Integer.parseInt(inputText.getText());
        int currAmount = allocate.allArmies.get(chosenCountry);
        if(!inputText.getText().matches(".*\\d.*")) {
            outputText.appendText("Enter an integer value \n");
            return;
        }

        if(amount > numToAssign) {
            outputText.appendText("You entered a value too big.\n");
            inputText.setText("");
            return;
        }

        allocate.allArmies.put(chosenCountry, currAmount + amount);
        numToAssign -= amount;
        if(numToAssign <= 0) {
            numToAssign = DEFAULT_NUM_ARMIES;
            inputHistory.appendText(inputText.getText() + "\n");
            inputText.setText("");
            chosenCountry = "";
            game.switchTurn();
            state = gameStates.CHOOSE_COUNTRY;
//            outputText.appendText(game.getCurrent().getName() + " it is your turn, Enter the country you would like to assign armies to.\n");
            assignArmies(game.getCurrent());
            return;
        }

        if(numToAssign > 0) {
            state = gameStates.CHOOSE_COUNTRY;
            outputText.appendText("What country would you like to assign the rest of your armies to?\n");
            inputHistory.appendText(inputText.getText() + "\n");
            inputText.setText("");
        }

    }


    private void printAllocation() {
        outputText.appendText(game.getPlayer1().getName() + "'s countries: " + game.getPlayer1().getCountries().toString() + "\n");
        outputText.appendText(game.getPlayer2().getName() + "'s countries: " + game.getPlayer2().getCountries().toString() + "\n");
        outputText.appendText("Neutral Countries: \n" + game.getNeutral1().getCountries().toString() + "\n");
        outputText.appendText(game.getNeutral2().getCountries().toString() + "\n");
        outputText.appendText(game.getNeutral3().getCountries().toString() + "\n");
        outputText.appendText(game.getNeutral4().getCountries().toString() + "\n\n");
    }

    //this enum holds the functions that the player is able to initiate
    enum gameStates {PLAYER_NAMES, MOVE_SETUP,CHOOSE_COUNTRY, SELECT_AMOUNT_ARMIES}
    gameStates state = gameStates.PLAYER_NAMES;
    @Override
    public void handle(ActionEvent actionEvent) {
        //checks if invalid input first
        if(inputText.getText().isBlank()) {
            outputText.appendText("Enter valid input\n");
            inputText.setText("");
        } else {
            switch (state) {
                case PLAYER_NAMES:
                    inputHistory.appendText(inputText.getText() + "\n");
                    parseInput();
                    inputText.setText("");
                    break;
//                case MOVE_SETUP:
//                    move();
//                    break;
                case CHOOSE_COUNTRY:
                    pickCountry(game.getCurrent());
                    break;
                case SELECT_AMOUNT_ARMIES:
                    askAmount(game.getCurrent());
                    break;
            }
        }
//        if(!gotPlayerNames){
//            inputHistory.appendText(inputText.getText() + "\n");
//            parseInput();
//            inputText.setText("");
//        }else{
//            move();
//        }
    }


    private void changeButtonColour(String countryInput, String playerColour){
        Pattern pattern = Pattern.compile(countryInput,Pattern.CASE_INSENSITIVE);
        boolean countryFound = false;

        for (Button button : game.getCountryButtons()){
            Matcher m = pattern.matcher(button.getId());
            if(m.find()){
                button.setStyle("-fx-background-color: " + playerColour);
                countryFound = true;
                break;
            }
        }
        if(!countryFound){
            String buttonName = CountryHashMap.COUNTRY_BUTTON_ALT_NAMES.get(countryInput);

            pattern = Pattern.compile(buttonName,Pattern.CASE_INSENSITIVE);
            for (Button button : game.getCountryButtons()){
                Matcher m = pattern.matcher(button.getId());
                if(m.find()){
                    button.setStyle("-fx-background-color: " + playerColour);
                    break;
                }
            }
        }
    }

    private void initialiseButtonColours(){
        for(String country: game.getPlayer1().getCountries()){
            changeButtonColour(country, game.getPlayer1().getColour());
        }
        for(String country: game.getPlayer2().getCountries()){
            changeButtonColour(country, game.getPlayer2().getColour());
        }
        for(String country: game.getNeutral1().getCountries()){
            changeButtonColour(country, game.getNeutral1().getColour());
        }
        for(String country: game.getNeutral2().getCountries()){
            changeButtonColour(country, game.getNeutral2().getColour());
        }
        for(String country: game.getNeutral3().getCountries()){
            changeButtonColour(country, game.getNeutral3().getColour());
        }
        for(String country: game.getNeutral4().getCountries()){
            changeButtonColour(country, game.getNeutral4().getColour());
        }
    }
}
