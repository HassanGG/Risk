import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//!!!!!!In order to access player names this class needs to be extended!!!!!!!
public class Allocation implements Data{

    private final String player1, player2;
    private ArrayList<String> player1Countries = new ArrayList<String>();
    private ArrayList<String> player2Countries = new ArrayList<String>();

    //player names will decided in another class or in main
    public Allocation(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public ArrayList<String> getPlayer1Countries() {
        return player1Countries;
    }

    public ArrayList<String> getPlayer2Countries() {
        return player2Countries;
    }

    private void assign(List randomize) {
        //this if statement makes sure that there are an equal number of assigned countries in total. This is because there are two odd continent sizes.
        if(randomize.size() == Europe.length) {
            for(int i = 0; i < randomize.size(); i++) {
                if(i % 2 == 0) {
                    player2Countries.add((String) randomize.get(i));
                } else {
                    player1Countries.add((String) randomize.get(i));
                }
            }

        } else {
            for(int i = 0; i < randomize.size(); i++) {
                if(i % 2 == 0) {
                    player1Countries.add((String) randomize.get(i));
                } else {
                    player2Countries.add((String) randomize.get(i));
                }
            }
        }
    }

    public void assignCountries() {
        //first copy array of countries by continent into list, shuffle and then assign half to each. Two odd continents so one player gets extra for one continent.
        String[][] continents = {NorthAmerica, Europe, Asia, Africa, Australia, SouthAmerica};
        for(String[] i : continents) {
            List<String> randomize = Arrays.asList(i);
            Collections.shuffle(randomize);
            assign(randomize);
        }
    }

    public static void main(String[] args) {
        Allocation yo = new Allocation("Hassan", "Denzel");
        yo.assignCountries();
        System.out.println(yo.player1Countries);
        System.out.println(yo.player1Countries.size());
        System.out.println(yo.player2Countries.size());
    }
}
