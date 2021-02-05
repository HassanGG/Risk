package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Button ontario, quebec, nwTerritory, alberta, greenland, eastUS, westUS, centralAmerica, alaska,
            greatBritain, westEU, southEU, ukraine, northEU, iceland, scandinavia, afghanistan, india,
            middleEast, japan, ural, yakutsk, kamchatka, siam, irkutsk, siberia, mongolia, china,
            eastAustralia, newGuinea, westAustralia, indonesia, venezuela, peru, brazil, argentina,
            congo, northAfrica, southAfrica, egypt, eastAfrica, madagascar;


    public final int[] continentIDs = Constants.getContinentIds();

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
    }
}
