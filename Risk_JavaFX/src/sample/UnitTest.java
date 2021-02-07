package sample;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class UnitTest {
    @Test
    //To check player1 and player2 is allocated 9 territories
    public void test1() {
        Allocation p1countries = new Allocation("", "");
        p1countries.assignCountries();
        int actual1 = p1countries.getPlayer1Countries().size();
        int actual2 = p1countries.getPlayer2Countries().size();
        assertEquals(9, actual1);
        assertEquals(9, actual2);

    }

    @Test
    //To check neutralplauer1 is allocated 6 territories
    public void test2() {

        Allocation p1countries = new Allocation("", "");
        p1countries.assignCountries();
        int actual = p1countries.getNeutral1Countries().size();
        assertEquals(6, actual);

    }

    @Test
    //To check neutralplauer2 is allocated 6 territories
    public void test3() {

        Allocation p1countries = new Allocation("", "");
        p1countries.assignCountries();
        int actual = p1countries.getNeutral2Countries().size();
        assertEquals(6, actual);

    }

    @Test
    //To check neutralplauer3 is allocated 6 territories
    public void test4() {

        Allocation p1countries = new Allocation("", "");
        p1countries.assignCountries();
        int actual = p1countries.getNeutral3Countries().size();
        assertEquals(6, actual);

    }

    @Test
    //To check neutralplauer4 is allocated 6 territories
    public void test5() {

        Allocation p1countries = new Allocation("", "");
        p1countries.assignCountries();
        int actual = p1countries.getNeutral4Countries().size();
        assertEquals(6, actual);

    }

    @Test
    //To check if player1 and player2's have been randomly allocated
    public void test6(){

        Allocation p1countries = new Allocation("", "");
        p1countries.assignCountries();
    }
}

