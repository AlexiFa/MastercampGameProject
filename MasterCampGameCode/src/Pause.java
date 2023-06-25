import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Pause extends JFrame{
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private int selected;

    public Pause(){
        initComponents();
        setTitle("Interface Jeu");
        setLocationRelativeTo(null);
        jTextArea1.setText("[Resume]\nQuit");
        selected = 0;
    }

    private void initComponents() {
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
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 725, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
    }

    private void jTextArea1KeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            if (selected == 0) {
                selected = 1;
                jTextArea1.setText("Resume\n[Quit]");
            } else {
                selected = 0;
                jTextArea1.setText("[Resume]\nQuit");
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (selected == 0) {
                selected = 1;
                jTextArea1.setText("Resume\n[Quit]");
            } else {
                selected = 0;
                jTextArea1.setText("[Resume]\nQuit");
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (selected == 0) {
                this.dispose();
            } else {
                System.exit(0);
            }
        }
    }
}
