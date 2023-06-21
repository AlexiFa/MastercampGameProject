import java.awt.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class View extends javax.swing.JFrame{

    private Map map = new Map();

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    public View(){
        initComponents();
        setTitle("Interface Jeu");
        setLocationRelativeTo(null);
        jTextArea1.setText(map.toString());

    }




    private void initComponents(){

        //Zone défilement
        jScrollPane1 = new javax.swing.JScrollPane();


        //Zone de texte
        jTextArea1 = new javax.swing.JTextArea();


        //Lorsque l'on ferme la fenêtre
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(9);
        jTextArea1.setRows(9);
        jTextArea1.setFont(new java.awt.Font("Courier New", 1, 15));
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }


    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {
        map.move(evt.getKeyCode());
        jTextArea1.setText(map.toString());
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new View().setVisible(true);
            }
        });
    }


    public boolean fetchSaveFile(){
        String filePath = "savefile.txt";
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
           return false; 
        }

        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            // Loop over all lines
            //create all Items
            int indexAtt = 0, indexDef = 0;
            for(String line : lines){
                if(line == "AttackItem"){
                    indexAtt = lines.indexOf(line);
                }
                if(line == "HealItem"){
                    indexDef = lines.indexOf(line);
                }
            }
            while(indexAtt < indexDef){
                Items newItem = new Items(Integer.parseInt(lines.get(indexAtt+1)), lines.get(indexAtt), false);
                indexAtt += 2;
                Main.attackItemList.add(newItem);
            }
            while(indexDef < lines.size()){
                Items newItem = new Items(Integer.parseInt(lines.get(indexDef+1)), lines.get(indexDef), true);
                indexDef += 2;
                Main.healItemList.add(newItem);
            }

            //create player
            Player player = new Player(Integer.parseInt(lines.get(2)), lines.get(4));
            player.setExperience(Integer.parseInt(lines.get(3)));
            player.setHp(Integer.parseInt(lines.get(1)));
            //créer une liste inventaire à partir de ce que l'on a sur la ligne 5
            List<Items> inventory = new ArrayList<>();
            String[] parts = lines.get(5).split(" ");
            for (int i = 0; i < parts.length; i++){
                for(Items item : Main.attackItemList){
                    if(item.getName() == parts[i]){
                        inventory.add(item);
                    }
                }
                for(Items item : Main.healItemList){
                    if(item.getName() == parts[i]){
                        inventory.add(item);
                    }
                }
            }
            player.setInventory(inventory);
            for(Items item : inventory){
                if 
            }

        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

}

