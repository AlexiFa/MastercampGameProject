import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.List;

public class View extends JFrame{
    static boolean paused = false;
    static ArrayList<Items> attackItemList = new ArrayList<Items>();
    static ArrayList<Items> healItemList = new ArrayList<Items>();
    static ArrayList<Monster> monsterList = new ArrayList<Monster>();
    private Map map = new Map(View.this);
    private Inventory inventory = new Inventory(5, 5, 5);
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private Items selectedItem;
    private JTextArea jTextArea2;
    private JTextArea jTextArea3;
    private int result;
    public View(){
        initComponents();
        setTitle("Javenture");
        setLocationRelativeTo(null);
        jTextArea1.setText(map.toString());
        updateInventory();

        jTextArea3.setText("Player -- HP : " + map.getPlayer().getHp() + "\nMonster -- HP : " + map.getMonster().getHp());

        startMonsterTimer(); // Start the monster timer
    }


    private void initComponents(){

        //Zone défilement
        jScrollPane1 = new JScrollPane();

        //Zone de texte
        jTextArea1 = new JTextArea();
        jTextArea2 = new JTextArea();
        jTextArea3 = new JTextArea();

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

        jTextArea2.setColumns(9);
        jTextArea2.setRows(9);
        jTextArea2.setFont(new Font("Courier New", 1, 15));
        jTextArea2.setForeground(Color.WHITE);
        jTextArea2.setBackground(Color.BLACK);
        jTextArea2.setEditable(false);

        jTextArea3.setColumns(9);
        jTextArea3.setRows(9);
        jTextArea3.setFont(new Font("Courier New", 1, 15));
        jTextArea3.setForeground(Color.WHITE);
        jTextArea3.setBackground(Color.BLACK);
        jTextArea3.setEditable(false);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextArea3, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                                        .addComponent(jTextArea2, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                                )
                                .addContainerGap()
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextArea3, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextArea2, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        )
                                )
                                .addContainerGap()
                        )
        );


        pack();
    }

    public void showMessageBox(String message) {
        UIManager.put("OptionPane.background", Color.WHITE);
        UIManager.put("OptionPane.messageForeground", Color.BLACK);
        UIManager.put("OptionPane.messageFont", new Font(Font.MONOSPACED, Font.PLAIN, 12));
        UIManager.put("Button.background", Color.BLACK);

        result = JOptionPane.showConfirmDialog(this, message, "ScreenPreview", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

        if(result == JOptionPane.YES_OPTION){

            boolean added = inventory.addItem(map.getItem());
            if (added) {
                updateInventory(); // Mettre à jour l'affichage de l'inventaire
                jTextArea1.setText(map.toString());
            } else {
                JOptionPane.showMessageDialog(this, "L'inventaire est plein.", "Inventaire plein", JOptionPane.ERROR_MESSAGE);
            }
        }

    }



    public void showMessage(String message) {
        jTextArea2.setText(message );
        StringBuilder inventoryText = new StringBuilder();
        inventoryText.append(message).append("\n\n");
        inventoryText.append("Votre inventaire :\n");

        List<Items> inventoryItems = inventory.getItems();

        for (int i = 0; i < inventoryItems.size(); i++) {
            Items item = inventoryItems.get(i);
            inventoryText.append(i + 1).append(". ").append(item.getName()).append(" - Valeur : ").append(item.getValue()).append("\n");
        }

        jTextArea2.setText(inventoryText.toString());
        paused = true;
        int choice = askForChoice("Choisissez un élément de votre inventaire : " + "\n Entrez 0 pour quitter.", inventoryItems);
        if (choice >= 1 && choice <= inventoryItems.size()) {
            selectedItem = inventoryItems.get(choice - 1);
            inventoryItems.remove(choice - 1);
            updateInventory();
        }
        paused = false;
    }

    public Items getSelectedItems() {
        return selectedItem;
    }
    public void setSelectedItem(Items selectedItem) {
        this.selectedItem = selectedItem;
    }



     private int askForChoice(String message, List<Items> options) {
        String input = JOptionPane.showInputDialog(this, message);

        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= options.size()) {
                return choice;
            } //fermer la fenêtre
            else if (choice == 0) {
                return 0;
            } else  {
                showMessage("Choix invalide. Veuillez réessayer.");
                return askForChoice(message, options);
            }
        } catch (NumberFormatException e) {
            showMessage("Choix invalide. Veuillez réessayer.");
            return askForChoice(message, options);
        }


    }

    public void showMessage2(String Message){
        jTextArea3.setText("Player -- HP : " + map.getPlayer().getHp());
        jTextArea3.append(Message);
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
            Save.savefile(map);
            paused = true;
            pause.setVisible(true);
            // this.dispose();
        }// si la touche s est appuyée
        else if(evt.getKeyCode() == 83){
            Save.savefile(map);
        } else if(evt.getKeyCode() == 76){
            map = Save.fetchSaveFile();
            jTextArea1.setText(map.toString());
        }

        else if(keyCode == KeyEvent.VK_R){
            map = new Map(View.this);
            jTextArea1.setText(map.toString());
        }



    }

/*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                new View().setVisible(true);
            }
        });
    }*/

    private void startMonsterTimer(){
        java.util.Timer timer = new java.util.Timer();
        int delay = 500; // milliseconds
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Random rand = new Random();
                int n = rand.nextInt(4);
                if(!paused) {
                    map.moveMonster(n);
                    jTextArea1.setText(map.toString());
                }
            }
        }, delay, delay);
    }




    private void updateInventory() {
        StringBuilder inventoryText = new StringBuilder();
        inventoryText.append("Inventory:\n");


        int itemCount = Math.min(inventory.getCapacity(), inventory.getRows() * inventory.getCols());

        for (int i = 0; i < itemCount; i++) {
            Items item = inventory.getItem(i);
            if (item != null) {
                inventoryText.append(item.getName()).append(" - Valeur : ").append(item.getValue()).append("\n");
            } else {
                inventoryText.append("-\n");
            }
        }

        jTextArea2.setText(inventoryText.toString());
    }


}
