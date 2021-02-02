import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener{

    JButton enter;
    JTextField textField;

    GUI(){
        this.setTitle("Risk");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1300,850);


        Map map = new Map();
        JPanel updateBox = new JPanel();
        UserInput inputBox = new UserInput();
        enter = new JButton("Enter");
        textField = new JTextField();
        enter.addActionListener(this);
        enter.setBounds(1000,600,300,250);


        updateBox.setBackground(Color.LIGHT_GRAY);

        textField.setPreferredSize(new Dimension(250,50));
        textField.setBounds(0,600,1000,250);
        this.add(enter);
        this.add(textField);

        map.setBounds(0,0,1000,600);
        updateBox.setBounds(1000,0,300,600);

        this.add(map);
        this.add(updateBox);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == enter) {
            System.out.println("Welcome" + textField.getText());

        }
    }
}
