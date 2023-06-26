import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JFrame{
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private int selected;
    private String titre = "\n   ######   #####   ##       ## ###### ##     # ####### ##     ## ######  ######\n"
            + "       ##  #     #  ##       ## ##     ###    #    ##   ##     ## ##    # ##    \n"
            + "       ## #       #  ##     ##  #####  ## #   #    ##   ##     ## ##    # ##### \n"
            + "       ## #########   ##   ##   ##     ##  #  #    ##   ##     ## ######  ##    \n"
            + " #     ## #       #    ## ##    ##     ##   # #    ##   ##     ## ##   #  ##    \n"
            + "  ######  #       #     ###     ###### ##    ##    ##    #######  ##    # ######\n\n\n";

    public Menu(){
        initComponents();
        setTitle("Menu");
        setLocationRelativeTo(null);
        jTextArea1.setText(titre);
        // creation du menu avec start et quit
        jTextArea1.append("\t\t\t\t[Start]\n\n\t\t\t\tQuit");
        selected = 0;
    }

    private void initComponents(){
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(9);
        jTextArea1.setRows(9);
        jTextArea1.setFont(new Font("Courier New", 1, 15));
        jTextArea1.setForeground(Color.WHITE);
        jTextArea1.setBackground(Color.BLACK);
        jTextArea1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 715, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
    }

    private void jTextArea1KeyPressed(KeyEvent evt) {
        int keyCode = evt.getKeyCode();
        // si la fleche du bas est appuyée
        if(keyCode == KeyEvent.VK_DOWN){
            // si on est sur start
            if(selected == 0){
                // on passe sur quit
                jTextArea1.setText(titre);
                jTextArea1.append("\t\t\t\tStart\n\n\t\t\t\t[Quit]");
                selected = 1;
            }
        }
        // si la fleche du haut est appuyée
        else if(keyCode == KeyEvent.VK_UP){
            // si on est sur quit
            if(selected == 1){
                // on passe sur start
                jTextArea1.setText(titre);
                jTextArea1.append("\t\t\t\t[Start]\n\n\t\t\t\tQuit");
                selected = 0;
            }
        }
        // si la touche entrée est appuyée
        else if(keyCode == KeyEvent.VK_ENTER){
            // si on est sur start
            if(selected == 0){
                // on lance le jeu
                new View().setVisible(true);
                this.dispose();
            }
            // si on est sur quit
            else if(selected == 1){
                // on quitte le jeu
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        new Menu().setVisible(true);
    }
}
