package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;

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
    @FXML private Label player1Colour, player2Colour, neutral1Colour, neutral2Colour, neutral3Colour, neutral4Colour;

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

    //this enum holds the functions that the player is able to initiate
    enum gameStates {PLAYER_NAMES, CHOOSE_COUNTRY, SELECT_AMOUNT_ARMIES}
    gameStates state = gameStates.PLAYER_NAMES;
    @Override
    public void handle(ActionEvent actionEvent) {
        //checks if invalid input first
        inputHistory.appendText(inputText.getText() + "\n");
        if(inputText.getText().isBlank()) {
            outputText.appendText("Enter valid input\n");
            inputText.setText("");
        } else {
            switch (state) {
                case PLAYER_NAMES:
                    assignNames();
                    break;
                case CHOOSE_COUNTRY:
                    pickCountry(game.getCurrent());
                    break;
                case SELECT_AMOUNT_ARMIES:
                    askAmount();
                    break;
            }
        }
    }

    private int i = 0;
    private void assignNames() {
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
                initButtonText();

                decideFirstPlayer();
                outputText.appendText(game.getCurrent().getName() + ", it is your turn. Enter the country you would like to assign armies to.\nYou have " + numToAssign + "\n");

                initialiseButtonColours();

                state = gameStates.CHOOSE_COUNTRY;
                break;
            }
        }
    }


    private final int DEFAULT_NUM_ARMIES = 3;
    int numToAssign = DEFAULT_NUM_ARMIES;

    //this checks if the player owns any continents and then assigns the correct number of armies this turn.
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
    //takes what country the player typed and validates and stores it for askAmount (which is next called :) )
    private void pickCountry(Player player) {

        int index = CountryHashMap.getIndexOfCountry(inputText.getText());

        if(index == -1){
            outputText.appendText("Enter valid country.\n");
            inputText.setText("");
        }else{
            //if the player does not have the country they inputted be mad
            if(!player.getCountries().contains(Constants.COUNTRY_NAMES[index])) {
                System.out.println(Constants.COUNTRY_NAMES[index]);
                outputText.appendText("Enter a country that you own please.\n");
                inputText.setText("");
                return;
            }

            //if country is valid change chosen country and change game state
            chosenCountry = Constants.COUNTRY_NAMES[index];
            state = gameStates.SELECT_AMOUNT_ARMIES;
            inputText.setText("");
            outputText.appendText("Enter the amount of armies you would like to assign to this country. Current number left: " + numToAssign + "\n");
        }

    }

    //pickCountry already asked player so it just gets the amount and validates it and then changes the amount of armies in the hashmap
    private void askAmount() {
        int amount;
        int currAmount = allocate.allArmies.get(chosenCountry);

        if(!inputText.getText().matches("[0-9]+") ) {
            outputText.appendText("Enter an integer value \n");
            inputText.setText("");
            return;
        }

        amount = Integer.parseInt(inputText.getText());

        if(amount > numToAssign) {
            outputText.appendText("You entered a value too big.\n");
            inputText.setText("");
            return;
        }

        allocate.allArmies.put(chosenCountry, currAmount + amount);
        updateButtonText();
        numToAssign -= amount;
        if(numToAssign <= 0) {
            numToAssign = DEFAULT_NUM_ARMIES;
            outputText.appendText("You have assigned all of your armies.\n\n");
            inputText.setText("");
            chosenCountry = "";
            game.switchTurn();
            state = gameStates.CHOOSE_COUNTRY;
            assignArmies(game.getCurrent());
            return;
        }

        if(numToAssign > 0) {
            state = gameStates.CHOOSE_COUNTRY;
            outputText.appendText("You assigned " + amount + " armies you have " + numToAssign + " left.\n");
            outputText.appendText("What country would you like to assign the rest of your armies to?\n");
            inputText.setText("");
        }

    }

    //changes colours of buttons to suit what the player's colours are
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

    //This just changes the button colours to players colour.
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


    //Whoeveer rolls higher goes first, sets game.current to winner.
    private void decideFirstPlayer(){
        int player1Roll = game.getPlayer1().getDice().rollDice();
        int player2Roll = game.getPlayer2().getDice().rollDice();


        if(player1Roll > player2Roll){
            game.setCurrent(game.getPlayer1());
            outputText.appendText(game.getPlayer1().getName() + " rolled: " + player1Roll + "\n");
            outputText.appendText(game.getPlayer2().getName() + " rolled: " + player2Roll + "\n");
            outputText.appendText(game.getPlayer1().getName() + " rolled higher than " + game.getPlayer2().getName() + ", " +
                    game.getPlayer1().getName() + " goes first.\n\n");
        }else if(player1Roll < player2Roll){
            game.setCurrent(game.getPlayer2());
            outputText.appendText(game.getPlayer1().getName() + " rolled: " + player1Roll + "\n");
            outputText.appendText(game.getPlayer2().getName() + " rolled: " + player2Roll + "\n");
            outputText.appendText(game.getPlayer2().getName() + " rolled higher than " + game.getPlayer1().getName() + ", " +
                    game.getPlayer2().getName() + " goes first.\n\n");
        }else{
            outputText.appendText(game.getPlayer1().getName() + " rolled: " + player1Roll + "\n");
            outputText.appendText(game.getPlayer2().getName() + " rolled: " + player2Roll + "\n");
            outputText.appendText(game.getPlayer1().getName() + " tied with " + game.getPlayer2().getName() + ", " +
                    " roll again.\n\n");
            decideFirstPlayer();
        }
    }

    //Sets the text of each button to the value of armies on that country
    private void initButtonText(){
        for(int index = 0 ; index < Constants.COUNTRY_NAMES.length; index++){
            game.countryButtons[index].setText(String.valueOf(allocate.allArmies.get(Constants.COUNTRY_NAMES[index])));
        }
    }

    // same as initButtonText but for one country during a turn.
    private void updateButtonText(){
        int index = CountryHashMap.getIndexOfCountry(chosenCountry);
        game.countryButtons[index].setText(String.valueOf(allocate.allArmies.get(Constants.COUNTRY_NAMES[index])));
    }
}
