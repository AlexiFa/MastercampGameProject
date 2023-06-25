import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class View extends JFrame{
    static ArrayList<Items> attackItemList = new ArrayList<Items>();
    static ArrayList<Items> healItemList = new ArrayList<Items>();
    static ArrayList<Monster> monsterList = new ArrayList<Monster>();

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
        }// si la touche s est appuyée
        else if(evt.getKeyCode() == 83){
            Save.savefile(map);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new View().setVisible(true);
            }
        });
    }


//    public boolean fetchSaveFile(){
//        String filePath = "savefile.txt";
//        Path path = Paths.get(filePath);
//
//        if (!Files.exists(path)) {
//           return false;
//        }
//
//        try {
//            List<String> playerLines = new ArrayList<>();
//            List<String> enemyLines = new ArrayList<>();
//            List<String> attackItemLines = new ArrayList<>();
//            List<String> healItemLines = new ArrayList<>();
//            List<String> mapLines = new ArrayList<>();
//
//            List<String> lines = Files.readAllLines(path);
//            int i = 0;
//
//            for(String line : lines){
//                switch(line){
//                    case "Player:":
//                        i = 0;
//                        break;
//                    case "Map:":
//                        i = 1;
//                        break;
//                    case "Monsters:":
//                        i = 2;
//                        break;
//                    case "AttackItems:":
//                        i = 3;
//                        break;
//                    case "HealItems:":
//                        i = 4;
//                        break;
//                    default:
//                        switch(i){
//                            case 0:
//                                playerLines.add(line);
//                                break;
//                            case 1:
//                                mapLines.add(line);
//                                break;
//                            case 2:
//                                enemyLines.add(line);
//                                break;
//                            case 3:
//                                attackItemLines.add(line);
//                                break;
//                            case 4:
//                                healItemLines.add(line);
//                                break;
//                    }
//                }
//            }
//            //Items
//            for(String line : attackItemLines){
//                String[] item = line.split(",");
//                int value = Integer.parseInt(item[0].trim());
//                String name = item[1].trim();
//                Items attackItem = new Items(value, name, false);
//                Main.attackItemList.add(attackItem);
//            }
//
//            for(String line : healItemLines){
//                String[] item = line.split(",");
//                int value = Integer.parseInt(item[0].trim());
//                String name = item[1].trim();
//                Items healItem = new Items(value, name, true);
//                Main.healItemList.add(healItem);
//            }
//
//            // Player
//            int playerHp = Integer.parseInt(playerLines.get(0).trim());
//            int playerMaxHp = Integer.parseInt(playerLines.get(1).trim());
//            int playerXp = Integer.parseInt(playerLines.get(2).trim());
//            String playerName = playerLines.get(3).trim();
//            String[] playerItem = playerLines.get(4).split(" ");
//            String playerSelectedItem = playerLines.get(5).trim();
//            String[] playerPosition = playerLines.get(6).split(",");
//            int playerX = Integer.parseInt(playerPosition[0].trim());
//            int playerY = Integer.parseInt(playerPosition[1].trim());
//            int playerLevel = Integer.parseInt(playerLines.get(7).trim());
//
//            Player player = new Player(playerMaxHp, playerName);
//            player.setHp(playerHp);
//            player.setExperience(playerXp);
//            player.setLevel(playerLevel);
//
//            List<Items> playerItems = new ArrayList<>();
//            player.setInventory(playerItems); //vide l'inventaire avant la récup de la sauvegarde au cas où
//            for(String item : playerItem){
//                for(Items attackItem : Main.attackItemList){
//                    if(attackItem.getName().equals(item)){
//                        player.addItem(attackItem);
//                    }
//                }
//                for(Items healItem : Main.healItemList){
//                    if(healItem.getName().equals(item)){
//                        player.addItem(healItem);
//                    }
//                }
//            }
//
//            for(Items item : player.getInventory()){
//                if(item.getName().equals(playerSelectedItem)){
//                    player.setSelectedItem(item);
//                }
//            }
//
//            Point playerPoint = new Point(playerX, playerY);
//            player.setPosition(playerPoint);
//            List<String> mon1 = new ArrayList<>();
//            List<String> mon2 = new ArrayList<>();
//            List<String> mon3 = new ArrayList<>();
//            //Monsters
//            int y = 0;
//            for(String line : enemyLines){
//                if (line.equals("Monster:")){;
//                    y+=1;
//                    break;
//                }
//                switch(i){
//                    case 0:
//                        break;
//                    case 1:
//                        mon1.add(line);
//                        break;
//                    case 2:
//                        mon2.add(line);
//                        break;
//                    case 3:
//                        mon3.add(line);
//                        break;
//                }
//            }
//            int monsterHp = Integer.parseInt(mon1.get(0).trim());
//            int monsterMaxHp = Integer.parseInt(mon1.get(1).trim());
//            String monsterName = mon1.get(2).trim();
//            int monsterLevel = Integer.parseInt(mon1.get(3).trim());
//            String[] monsterPosition = mon1.get(4).split(",");
//            int monsterX = Integer.parseInt(monsterPosition[0].trim());
//            int monsterY = Integer.parseInt(monsterPosition[1].trim());
//            int monsterDamage = Integer.parseInt(mon1.get(5).trim());
//
//            Monster monster1 = new Monster(monsterMaxHp, monsterLevel, monsterDamage, monsterName);
//            monster1.setHp(monsterHp);
//            Point monsterPoint1 = new Point(monsterX, monsterY);
//            monster1.setPosition(monsterPoint1);
//
//            monsterHp = Integer.parseInt(mon2.get(0).trim());
//            monsterMaxHp = Integer.parseInt(mon2.get(1).trim());
//            monsterName = mon2.get(2).trim();
//            monsterLevel = Integer.parseInt(mon2.get(3).trim());
//            monsterPosition = mon2.get(4).split(",");
//            monsterX = Integer.parseInt(monsterPosition[0].trim());
//            monsterY = Integer.parseInt(monsterPosition[1].trim());
//            monsterDamage = Integer.parseInt(mon2.get(5).trim());
//
//            Monster monster2 = new Monster(monsterMaxHp, monsterLevel, monsterDamage, monsterName);
//            monster2.setHp(monsterHp);
//            Point monsterPoint2 = new Point(monsterX, monsterY);
//            monster2.setPosition(monsterPoint2);
//
//            monsterHp = Integer.parseInt(mon3.get(0).trim());
//            monsterMaxHp = Integer.parseInt(mon3.get(1).trim());
//            monsterName = mon3.get(2).trim();
//            monsterLevel = Integer.parseInt(mon3.get(3).trim());
//            monsterPosition = mon3.get(4).split(",");
//            monsterX = Integer.parseInt(monsterPosition[0].trim());
//            monsterY = Integer.parseInt(monsterPosition[1].trim());
//            monsterDamage = Integer.parseInt(mon3.get(5).trim());
//
//            Monster monster3 = new Monster(monsterMaxHp, monsterLevel, monsterDamage, monsterName);
//            monster3.setHp(monsterHp);
//            Point monsterPoint3 = new Point(monsterX, monsterY);
//            monster3.setPosition(monsterPoint3);
//
//            Main.monsterList.add(monster1);
//            Main.monsterList.add(monster2);
//            Main.monsterList.add(monster3);
//
//            //Map
//            //Alexis ajoute le code pour recréer la map
//
//
//            return true;
//
//
//
//
//
//        } catch (IOException e) {
//            return false;
//        }
//    }

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

