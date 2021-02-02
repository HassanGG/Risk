//allocate players like so
//Allocate allocation = new Allocation("player1", "player2");
//allocation.assignCountries();
public interface Allocate {
    //gets the name of the first player
    public String getPlayer1();

    //gets the name of the second player
    public String getPlayer2();

    //assigns random countries based on continent to 2 players
    public void assignCountries();

}
