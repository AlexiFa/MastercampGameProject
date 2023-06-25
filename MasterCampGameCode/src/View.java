import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class View extends JFrame{

    private Map map = new Map();

    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    public View(){
        initComponents();
        setTitle("Interface Jeu");
        setLocationRelativeTo(null);
        jTextArea1.setText(map.toString());

        startMonsterTimer(); // Start the monster timer
    }


    private void initComponents(){

        //Zone défilement
        jScrollPane1 = new JScrollPane();


        //Zone de texte
        jTextArea1 = new JTextArea();


        //Lorsque l'on ferme la fenêtre
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
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 725, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }


    private void jTextArea1KeyPressed(KeyEvent evt) {
        int keyCode = evt.getKeyCode();
        // si une des fleches est appuyée
        if(keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT){
            map.move(evt.getKeyCode());
            jTextArea1.setText(map.toString());
        }
        // la touche echap est appuyée
        else if(keyCode == KeyEvent.VK_ESCAPE){
            Pause pause = new Pause();
            pause.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }


    /*public boolean fetchSaveFile(){
        String filePath = "savefile.txt";
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
           return false; 
        }

        try {
            for (String line : Files.readAllLines(path)) {
                System.out.println(line);
            }

        } catch (IOException e) {
            return false;
        }
    }*/

    private void startMonsterTimer(){
        java.util.Timer timer = new java.util.Timer();
        int delay = 500; // milliseconds

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Random rand = new Random();
                int n = rand.nextInt(4);
                map.moveMonster(n);
                jTextArea1.setText(map.toString());
            }
        }, delay, delay);
    }
}

