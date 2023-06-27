import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Save {
    public static void savefile(Map m){

        Player n = m.getPlayer();
        ArrayList<Items> attackItemList = m.getArme();
        ArrayList<Items> healItemList = m.getPotion();
        Monster monster = m.getMonster();

        String fileName = "savefile.txt";
        String playerData = n.toString();
        
        String mapData = "Map:\n" + m.toString();

        String monsterData = "Monsters:\n";
        monsterData += monster.toString() + "\n";

        String attackItemData = "AttackItem:\n";
        for (int i = 0; i < attackItemList.size(); i++){
            attackItemData += attackItemList.get(i).toString() + "\n";
        }

        String healItemData = "HealItem:\n";
        for (int i = 0; i < healItemList.size(); i++){
            healItemData += healItemList.get(i).toString() + "\n";
        }

        String data = playerData + "\n\n" + mapData + "\n\n" + monsterData + "\n\n" + attackItemData + "\n\n" + healItemData;

        try {
            // Create a FileWriter object with the specified file name
            FileWriter fileWriter = new FileWriter(fileName);

            // Wrap the FileWriter object in a BufferedWriter
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the data to the file
            bufferedWriter.write(data);

            // Close the BufferedWriter
            bufferedWriter.close();

            System.out.println("Save file created successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while creating the save file.");
            e.printStackTrace();
        }
    }

    public static Map fetchSaveFile(){
        Map savedMap = new Map(new View());
        String filePath = "savefile.txt";
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            return new Map(new View());
        }

        try {
            List<String> playerLines = new ArrayList<>();
            List<String> enemyLines = new ArrayList<>();
            List<String> attackItemLines = new ArrayList<>();
            List<String> healItemLines = new ArrayList<>();
            List<String> mapLines = new ArrayList<>();

            List<String> lines = Files.readAllLines(path);
            int i = 0;

            for(String line : lines){
                switch(line) {
                    case "Player:":
                        i = 0;
                        break;
                    case "Map:":
                        i = 1;
                        break;
                    case "Monsters:":
                        i = 2;
                        break;
                    case "AttackItems:":
                        i = 3;
                        break;
                    case "HealItems:":
                        i = 4;
                        break;
                    default:
                        switch(i){
                            case 0:
                                playerLines.add(line);
                                break;
                            case 1:
                                mapLines.add(line);
                                break;
                            case 2:
                                enemyLines.add(line);
                                break;
                            case 3:
                                attackItemLines.add(line);
                                break;
                            case 4:
                                healItemLines.add(line);
                                break;
                        }
                    }
            }
            //Items
            for(String line : attackItemLines){
                if(!line.equals("Item:") && !line.equals("")){
                    String[] item = line.split(",");
                    int value = Integer.parseInt(item[0].trim());
                    String name = item[1].trim();
                    Items attackItem = new Items(value, name, false);
                    savedMap.addArme(attackItem);
                }
            }

            for(String line : healItemLines){
                if(!line.equals("Item:") && !line.equals("")){
                    String[] item = line.split(",");
                    int value = Integer.parseInt(item[0].trim());
                    String name = item[1].trim();
                    Items healItem = new Items(value, name, true);
                    savedMap.addPotion(healItem);
                }
            }

            // Player
            int playerHp = Integer.parseInt(playerLines.get(0).trim());
            int playerMaxHp = Integer.parseInt(playerLines.get(1).trim());
            int playerXp = Integer.parseInt(playerLines.get(2).trim());
            String playerName = playerLines.get(3).trim();
            String[] playerItem = playerLines.get(4).split(" ");
            String playerSelectedItem = playerLines.get(5).trim();
            String[] playerPosition = playerLines.get(6).split(",");
            int playerX = Integer.parseInt(playerPosition[0].trim());
            int playerY = Integer.parseInt(playerPosition[1].trim());
            int playerLevel = Integer.parseInt(playerLines.get(7).trim());

            Player player = new Player(playerMaxHp, playerName);
            player.setHp(playerHp);
            player.setExperience(playerXp);
            player.setLevel(playerLevel);

            List<Items> playerItems = new ArrayList<>();
            player.setInventory(playerItems); //vide l'inventaire avant la récup de la sauvegarde au cas où
            for(String item : playerItem){
                if(!item.equals("")){
                    for(Items attackItem : savedMap.getArme()){
                        if(attackItem.getName().equals(item)){
                            player.addItem(attackItem);
                        }
                    }
                    for(Items healItem : savedMap.getPotion()){
                        if(healItem.getName().equals(item)){
                            player.addItem(healItem);
                        }
                    }
                }
            }

            for(Items item : player.getInventory()){
                if(playerSelectedItem == "null")
                    player.setSelectedItem(null);
                else if(item.getName().equals(playerSelectedItem)){
                    player.setSelectedItem(item);
                }
            }

            Point playerPoint = new Point(playerX, playerY);
            player.setPosition(playerPoint);
            savedMap.setPlayer(player);

            List<String> mon1 = new ArrayList<>();
            List<String> mon2 = new ArrayList<>();
            List<String> mon3 = new ArrayList<>();
            //Monsters
            int y = 0;
            for(String line : enemyLines){
                if (line.equals("Monster:")){;
                    y+=1;
                    break;
                }
                switch(y){
                    case 0:
                        break;
                    case 1:
                        mon1.add(line);
                        break;
                    case 2:
                        mon2.add(line);
                        break;
                    case 3:
                        mon3.add(line);
                        break;
                }
            }
            int monsterHp = Integer.parseInt(mon1.get(0).trim());
            int monsterMaxHp = Integer.parseInt(mon1.get(1).trim());
            String monsterName = mon1.get(2).trim();
            int monsterLevel = Integer.parseInt(mon1.get(3).trim());
            String[] monsterPosition = mon1.get(4).split(",");
            int monsterX = Integer.parseInt(monsterPosition[0].trim());
            int monsterY = Integer.parseInt(monsterPosition[1].trim());
            int monsterDamage = Integer.parseInt(mon1.get(5).trim());

            Monster monster1 = new Monster(monsterMaxHp, monsterLevel, monsterDamage, monsterName);
            monster1.setHp(monsterHp);
            Point monsterPoint1 = new Point(monsterX, monsterY);
            monster1.setPosition(monsterPoint1);

            monsterHp = Integer.parseInt(mon2.get(0).trim());
            monsterMaxHp = Integer.parseInt(mon2.get(1).trim());
            monsterName = mon2.get(2).trim();
            monsterLevel = Integer.parseInt(mon2.get(3).trim());
            monsterPosition = mon2.get(4).split(",");
            monsterX = Integer.parseInt(monsterPosition[0].trim());
            monsterY = Integer.parseInt(monsterPosition[1].trim());
            monsterDamage = Integer.parseInt(mon2.get(5).trim());

            Monster monster2 = new Monster(monsterMaxHp, monsterLevel, monsterDamage, monsterName);
            monster2.setHp(monsterHp);
            Point monsterPoint2 = new Point(monsterX, monsterY);
            monster2.setPosition(monsterPoint2);

            monsterHp = Integer.parseInt(mon3.get(0).trim());
            monsterMaxHp = Integer.parseInt(mon3.get(1).trim());
            monsterName = mon3.get(2).trim();
            monsterLevel = Integer.parseInt(mon3.get(3).trim());
            monsterPosition = mon3.get(4).split(",");
            monsterX = Integer.parseInt(monsterPosition[0].trim());
            monsterY = Integer.parseInt(monsterPosition[1].trim());
            monsterDamage = Integer.parseInt(mon3.get(5).trim());

            Monster monster3 = new Monster(monsterMaxHp, monsterLevel, monsterDamage, monsterName);
            monster3.setHp(monsterHp);
            Point monsterPoint3 = new Point(monsterX, monsterY);
            monster3.setPosition(monsterPoint3);

            savedMap.setMonster(monster1);

            View.monsterList.add(monster1);
            View.monsterList.add(monster2);
            View.monsterList.add(monster3);

            //Map
            char[][] mapChar = new char[22][80];
            for(String l : mapLines){
                for(char c : l.toCharArray()){
                    mapChar[mapLines.indexOf(l)][l.indexOf(c)] = c;
                }
            }
            savedMap.setRoom(mapChar);

            return savedMap;

        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier");
            return new Map(new View());
        }
    }
}
