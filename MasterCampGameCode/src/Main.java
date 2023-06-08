import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
public class Main {
    static ArrayList<Items> itemsList = new ArrayList<Items>();
    public static void main(String[] args) {
        //create items
        //ajouter des items dedans par la suite...
        // create map grid with swing
        JFrame frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(22, 22));
        JLabel[][] buttons = new JLabel[22][22];
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 22; j++) {
                JLabel label = new JLabel();
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                buttons[i][j] = label;
                buttons[i][j].setBackground(Color.WHITE);
                frame.add(buttons[i][j]);
            }
        }
        frame.setSize(1000, 800);

    }
}