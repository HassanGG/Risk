package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    enum gameStates {PLAYER_NAMES, CHOOSE_COUNTRY, SELECT_AMOUNT_ARMIES, ASSIGN_NEUTRAL, CHOOSE_ATTACKER,
        CHOOSE_DEFENDER, ATTACK_PHASE,CHOOSE_FORTIFY}
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
                case ASSIGN_NEUTRAL:
                    assignNeutral();
                    break;
                case CHOOSE_ATTACKER:
//                    playerAttack();
                    chooseAttackingCountry();
                    break;
                case CHOOSE_DEFENDER:
                    chooseDefendingCountry();
                    break;
                case ATTACK_PHASE:
                    playerAttack();
                    break;
                case CHOOSE_FORTIFY:


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
    private Boolean pickCountry(Player player) {

        int index = CountryHashMap.getIndexOfCountry(inputText.getText());

        if(index == -1){
            outputText.appendText("Enter valid country.\n");
            inputText.setText("");
            return false;
        }else{
            //if the player does not have the country they inputted be mad
            if(!player.getCountries().contains(Constants.COUNTRY_NAMES[index])) {
                outputText.appendText("Enter a country that " + player.getName() + " owns please.\n");
                inputText.setText("");
                return false;
            }

            //if country is valid change chosen country and change game state
            chosenCountry = Constants.COUNTRY_NAMES[index];
            inputText.setText("");

            if(player == game.getPlayer1() || player == game.getPlayer2()){
                outputText.appendText("Enter the amount of armies you would like to assign to this country. Current number left: " + numToAssign + "\n");
                state = gameStates.SELECT_AMOUNT_ARMIES;
            }
            return true;
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

        allocate.allArmies.replace(chosenCountry, currAmount + amount);
        updateButtonText(chosenCountry);
        numToAssign -= amount;
        if(numToAssign <= 0) {
            numToAssign = DEFAULT_NUM_ARMIES;
            outputText.appendText("You have assigned all of your armies.\n\n");
            inputText.setText("");
            chosenCountry = "";
            outputText.appendText(game.getCurrent().getName() + ", reinforce a country owned by " + game.getCurrentNeutral().getName() + "\n");
            state = gameStates.ASSIGN_NEUTRAL;
//            outputText.appendText(game.getCurrent().getName() + " pick your attacking country.\n");
//            state = gameStates.ATTACK_PHASE;
            return;
        }

        if(numToAssign > 0) {
            state = gameStates.CHOOSE_COUNTRY;
            outputText.appendText("You assigned " + amount + " armies you have " + numToAssign + " left.\n");
            outputText.appendText("What country would you like to assign the rest of your armies to?\n");
            inputText.setText("");
        }

    }

    private void assignNeutral(){
        Boolean noError = neutralArmyAssign();
        if(noError) {
            game.switchNeutral();
            if(!game.n1Turn()) {
                outputText.appendText(game.getCurrent().getName() + ", reinforce a country owned by " + game.getCurrentNeutral().getName() + "\n");
            }
        }
    }

    private final int NEUTRAL_ARMY_PER_TURN = 1;
    private Boolean neutralArmyAssign(){
        //This sets chosenCountry to whatever neutral wants
        Boolean noError = pickCountry(game.getCurrentNeutral());

        if(noError) {
            int currAmount = allocate.allArmies.get(chosenCountry);
            allocate.allArmies.replace(chosenCountry, currAmount + NEUTRAL_ARMY_PER_TURN);
            updateButtonText(chosenCountry);

            if(game.n4Turn()) {
//                game.switchTurn();
//                state = gameStates.CHOOSE_COUNTRY;
                state = gameStates.CHOOSE_ATTACKER;
//                if(game.n4Turn()) outputText.appendText("\n");
                if(game.n4Turn()) outputText.appendText(game.getCurrent().getName() + " pick your attacking country.\n");
//                assignArmies(game.getCurrent());
            }
        }
        return noError;
    }

    private int attackCountryIndex = -1;
    private int defendingCountryIndex = -1;

    private void playerAttack(){

        int attackArmies = allocate.allArmies.get(Constants.COUNTRY_NAMES[attackCountryIndex]);
        int defendArmies = allocate.allArmies.get(Constants.COUNTRY_NAMES[defendingCountryIndex]);
        int max_dice = 0;

        if(attackArmies == 1){
            outputText.appendText("Attacking country cannot attack if it only has 1 army.\n");
            return;
        }else if(attackArmies == 2){
            max_dice = 1;
        }else if(attackArmies == 3){
            max_dice = 2;
        }else if(attackArmies >= 4){
            max_dice = 3;
        }

        if(!inputText.getText().matches("[0-9]+") ) {
            outputText.appendText("Enter an integer value \n");
            inputText.setText("");
            return;
        }

        int dice_num = Integer.parseInt(inputText.getText());

        System.out.println(dice_num);
        if(!(dice_num >= 0 && dice_num <= max_dice)){
            outputText.appendText("Enter valid dice count.\n");
            inputText.setText("");
            return;
        }

        int dice_used;
        for(dice_used = 0; dice_used < dice_num; dice_used++){

            if(game.attack(game.getCurrent(),outputText) == 1){
                outputText.appendText("Defender lost.\n\n");
                defendArmies--;
            }else {
                outputText.appendText(game.getCurrent().getName() + " lost.\n\n");
                attackArmies--;
            }
            if(defendArmies == 0 || attackArmies == 0){
                break;
            }
        }

        if(defendArmies == 0){
            attackArmies -= dice_num - dice_used;
            allocate.allArmies.replace(Constants.COUNTRY_NAMES[attackCountryIndex], attackArmies);
            allocate.allArmies.replace(Constants.COUNTRY_NAMES[defendingCountryIndex], dice_num - dice_used);
            changeButtonColour(Constants.COUNTRY_NAMES[defendingCountryIndex], game.getCurrent().getColour());
        }else if(attackArmies == 0){
            defendArmies -= dice_num - dice_used;
            allocate.allArmies.replace(Constants.COUNTRY_NAMES[attackCountryIndex], dice_num - dice_used);
            allocate.allArmies.replace(Constants.COUNTRY_NAMES[defendingCountryIndex], defendArmies);
            changeButtonColour(Constants.COUNTRY_NAMES[attackCountryIndex], game.getCurrent().getColour());
        }else{
            allocate.allArmies.replace(Constants.COUNTRY_NAMES[attackCountryIndex], attackArmies);
            allocate.allArmies.replace(Constants.COUNTRY_NAMES[defendingCountryIndex], defendArmies);
        }


        updateButtonText(Constants.COUNTRY_NAMES[attackCountryIndex]);
        updateButtonText(Constants.COUNTRY_NAMES[defendingCountryIndex]);

        inputText.setText("");

        outputText.appendText(game.getCurrent().getName() + " pick your attacking country.\n");
        state = gameStates.CHOOSE_ATTACKER;
    }

    private void chooseAttackingCountry(){
        if(skipPhase()){
            return;
        }

        int index = CountryHashMap.getIndexOfCountry(inputText.getText());

        if(index == -1){
            outputText.appendText("Enter valid country.\n");
            inputText.setText("");
        }else {
            if (!game.getCurrent().getCountries().contains(Constants.COUNTRY_NAMES[index])) {
                outputText.appendText("Enter a country that " + game.getCurrent().getName() + " owns please.\n");
                inputText.setText("");
            }else{
                attackCountryIndex = index;
                outputText.appendText(game.getCurrent().getName() + ", choose a country you want to attack.\n");
                inputText.setText("");

                state = gameStates.CHOOSE_DEFENDER;
            }
        }
    }

    private void chooseDefendingCountry(){
        if(skipPhase()){
            return;
        }

        int index = CountryHashMap.getIndexOfCountry(inputText.getText());

        if(index == -1){
            outputText.appendText("Enter valid country.\n");
        }else {

            if (game.getCurrent().getCountries().contains(Constants.COUNTRY_NAMES[index])) {
                outputText.appendText("Enter a country that " + game.getCurrent().getName() + " does not own please.\n");
                inputText.setText("");
            }else{
                defendingCountryIndex = index;
                if(!game.isAdjacent(attackCountryIndex,defendingCountryIndex)){
                    outputText.appendText("Please choose countries that are next to each other.\n\n");
                    outputText.appendText(game.getCurrent().getName() + " pick your attacking country.\n");
                    inputText.setText("");
                    state = gameStates.CHOOSE_ATTACKER;
                }else{
                    outputText.appendText("Enter number of dice you want to use.\n\n");
                    state = gameStates.ATTACK_PHASE;
                    inputText.setText("");
                }
            }
        }
    }

    private boolean skipPhase(){
        String inputString = inputText.getText();
        inputString = inputString.replaceAll("\\s","");
        inputString = inputString.toLowerCase();
        if(inputString.equals("skip")){
            outputText.appendText(game.getCurrent().getName() + " choose a country fortify.\n");
            inputText.setText("");
            state = gameStates.CHOOSE_FORTIFY;
            return true;
        }
        return false;
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
    private void updateButtonText(String chosenCountry){
        int index = CountryHashMap.getIndexOfCountry(chosenCountry);
        game.countryButtons[index].setText(String.valueOf(allocate.allArmies.get(Constants.COUNTRY_NAMES[index])));
    }
}
