import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    int[][] coords = Constants.getCountryCoord();

    public Map() {
        this.setLayout(null);
        this.setBackground(Color.CYAN);

        CountryNode button1 = new CountryNode(coords[0][0],coords[0][1]);
        CountryNode button2 = new CountryNode(coords[1][0],coords[1][1]);
        CountryNode button3 = new CountryNode(coords[2][0],coords[2][1]);
        CountryNode button4 = new CountryNode(coords[3][0],coords[3][1]);
        CountryNode button5 = new CountryNode(coords[4][0],coords[4][1]);
        CountryNode button6 = new CountryNode(coords[5][0],coords[5][1]);
        CountryNode button7 = new CountryNode(coords[6][0],coords[6][1]);
        CountryNode button8 = new CountryNode(coords[7][0],coords[7][1]);
        CountryNode button9 = new CountryNode(coords[8][0],coords[8][1]);
        CountryNode button10 = new CountryNode(coords[9][0],coords[9][1]);
        CountryNode button11 = new CountryNode(coords[10][0],coords[10][1]);
        CountryNode button12 = new CountryNode(coords[11][0],coords[11][1]);
        CountryNode button13 = new CountryNode(coords[12][0],coords[12][1]);
        CountryNode button14 = new CountryNode(coords[13][0],coords[13][1]);
        CountryNode button15 = new CountryNode(coords[14][0],coords[14][1]);
        CountryNode button16 = new CountryNode(coords[15][0],coords[15][1]);
        CountryNode button17 = new CountryNode(coords[16][0],coords[16][1]);
        CountryNode button18 = new CountryNode(coords[17][0],coords[17][1]);
        CountryNode button19 = new CountryNode(coords[18][0],coords[18][1]);
        CountryNode button20 = new CountryNode(coords[19][0],coords[19][1]);
        CountryNode button21 = new CountryNode(coords[20][0],coords[20][1]);
        CountryNode button22 = new CountryNode(coords[21][0],coords[21][1]);
        CountryNode button23 = new CountryNode(coords[22][0],coords[22][1]);
        CountryNode button24 = new CountryNode(coords[23][0],coords[23][1]);
        CountryNode button25 = new CountryNode(coords[24][0],coords[24][1]);
        CountryNode button26 = new CountryNode(coords[25][0],coords[25][1]);
        CountryNode button27 = new CountryNode(coords[26][0],coords[26][1]);
        CountryNode button28 = new CountryNode(coords[27][0],coords[27][1]);
        CountryNode button29 = new CountryNode(coords[28][0],coords[28][1]);
        CountryNode button30 = new CountryNode(coords[29][0],coords[29][1]);
        CountryNode button31 = new CountryNode(coords[30][0],coords[30][1]);
        CountryNode button32 = new CountryNode(coords[31][0],coords[31][1]);
        CountryNode button33 = new CountryNode(coords[32][0],coords[32][1]);
        CountryNode button34 = new CountryNode(coords[33][0],coords[33][1]);
        CountryNode button35 = new CountryNode(coords[34][0],coords[34][1]);
        CountryNode button36 = new CountryNode(coords[35][0],coords[35][1]);
        CountryNode button37 = new CountryNode(coords[36][0],coords[36][1]);
        CountryNode button38 = new CountryNode(coords[37][0],coords[37][1]);
        CountryNode button39 = new CountryNode(coords[38][0],coords[38][1]);
        CountryNode button40 = new CountryNode(coords[39][0],coords[39][1]);
        CountryNode button41 = new CountryNode(coords[40][0],coords[40][1]);
        CountryNode button42 = new CountryNode(coords[41][0],coords[41][1]);

        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(button6);
        this.add(button7);
        this.add(button8);
        this.add(button9);
        this.add(button10);
        this.add(button11);
        this.add(button12);
        this.add(button13);
        this.add(button14);
        this.add(button15);
        this.add(button16);
        this.add(button17);
        this.add(button18);
        this.add(button19);
        this.add(button20);
        this.add(button21);
        this.add(button22);
        this.add(button23);
        this.add(button24);
        this.add(button25);
        this.add(button26);
        this.add(button27);
        this.add(button28);
        this.add(button29);
        this.add(button30);
        this.add(button31);
        this.add(button32);
        this.add(button33);
        this.add(button34);
        this.add(button35);
        this.add(button36);
        this.add(button37);
        this.add(button38);
        this.add(button39);
        this.add(button40);
        this.add(button41);
        this.add(button42);
    }
}
