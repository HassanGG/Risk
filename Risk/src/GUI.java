import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    GUI(){
        this.setTitle("Risk");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1300,850);


        Map map = new Map();
        JPanel updateBox = new JPanel();
        JPanel inputBox = new JPanel();

        updateBox.setBackground(Color.LIGHT_GRAY);
        inputBox.setBackground(Color.BLACK);

        map.setBounds(0,0,1000,600);
        updateBox.setBounds(1000,0,300,600);
        inputBox.setBounds(0,600,1300,250);

        this.add(map);
        this.add(updateBox);
        this.add(inputBox);

    }

}
