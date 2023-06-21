import java.io.*;
import java.util.ArrayList;

public class Save {
    public void savefile(Player n, Map m, Monster mon1, Monster mon2, Monster mon3, ArrayList<Items> attackItemList, ArrayList<Items> healItemList){
        String fileName = "savefile.txt";
        String playerData = n.toString();
        

        String mapData = "";

        String monsterData =
        "Monster\n" +
        mon1.getHp() + "\n" +
        mon1.getMaxHp() + "\n" +
        mon1.getName() + "\n" +
        mon1.getLevel() + "\n" +
        mon1.getPosition() + "\n" +
        mon1.getDamage() + "\n" +
        "\n" +
        "Monster\n" +
        mon2.getHp() + "\n" +
        mon2.getMaxHp() + "\n" +
        mon2.getName() + "\n" +
        mon2.getLevel() + "\n" +
        mon2.getPosition() + "\n" +
        mon2.getDamage() + "\n" +
        "\n" +
        "Monster\n" +
        mon3.getHp() + "\n" +
        mon3.getMaxHp() + "\n" +
        mon3.getName() + "\n" +
        mon3.getLevel() + "\n" +
        mon3.getPosition() + "\n" +
        mon3.getDamage() + "\n";

        String attackItemData = "AttackItem\n";
        for (int i = 0; i < attackItemList.size(); i++){
            attackItemData += attackItemList.get(i).getName() + "\n" + attackItemList.get(i).getValue() + "\n";
        }

        String healItemData = "HealItem\n";
        for (int i = 0; i < healItemList.size(); i++){
            healItemData += healItemList.get(i).getName() + "\n" + healItemList.get(i).getValue() + "\n";
        }

        String data = playerData + "\n" + mapData + "\n" + monsterData + "\n" + attackItemData + "\n" + healItemData;

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
}
