import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    GUI(){
        this.setTitle("Risk");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setSize(1300,850);
        this.setVisible(true);

        JPanel playBox = new JPanel();
        JPanel updateBox = new JPanel();
        JPanel inputBox = new JPanel();

        playBox.setBackground(Color.CYAN);
        updateBox.setBackground(Color.LIGHT_GRAY);
        inputBox.setBackground(Color.WHITE);

        playBox.setPreferredSize(new Dimension(1000,600));
        updateBox.setPreferredSize(new Dimension(300,600));
        inputBox.setPreferredSize(new Dimension(1250,250));

        this.add(playBox,BorderLayout.WEST);
        this.add(updateBox,BorderLayout.EAST);
        this.add(inputBox,BorderLayout.SOUTH);
    }

}
