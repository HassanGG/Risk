package sample;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class UnitTest {
    Player objPlayer1, objPlayer2, objNeutral1, objNeutral2, objNeutral3, objNeutral4;
    Allocation allocate;

    @Before
    public void setup() {
        allocate = new Allocation("","");
        allocate.assignPlayerValues();
        objPlayer1 = allocate.getPlayer1();
        objPlayer2 = allocate.getPlayer2();
        objNeutral1 = allocate.getNeutral1();
        objNeutral2 = allocate.getNeutral2();
        objNeutral3 = allocate.getNeutral3();
        objNeutral4 = allocate.getNeutral4();
    }
    @Test
    //To check player1 and player2 is allocated 9 territories
    public void test1() {
        allocate.assignPlayerValues();
        int actual1 = objPlayer1.getCountries().size();
        int actual2 = objPlayer2.getCountries().size();
        assertEquals(9, actual1);
        assertEquals(9, actual2);

    }

    @Test
    //To check neutralplauer1 is allocated 6 territories
    public void test2() {
        allocate.assignPlayerValues();
        int actual = objNeutral1.getCountries().size();
        assertEquals(6, actual);

    }

    @Test
    //To check neutralplauer2 is allocated 6 territories
    public void test3() {

        allocate.assignPlayerValues();
        int actual = objNeutral2.getCountries().size();
        assertEquals(6, actual);

    }

    @Test
    //To check neutralplauer3 is allocated 6 territories
    public void test4() {

        allocate.assignPlayerValues();
        int actual = objNeutral3.getCountries().size();
        assertEquals(6, actual);

    }

    @Test
    //To check neutralplauer4 is allocated 6 territories
    public void test5() {

        allocate.assignPlayerValues();
        int actual = objNeutral4.getCountries().size();
        assertEquals(6, actual);

    }

    /*@Test
    //To check if player1 and player2's have been randomly allocated
    public void test6(){

        Allocation p1countries = new Allocation("", "");
        p1countries.assignCountries();
    }*/
}

