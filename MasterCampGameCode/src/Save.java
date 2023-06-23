import java.io.*;
import java.util.ArrayList;

public class Save {
    public void savefile(Player n, Map m, ArrayList<Monster> monsters, ArrayList<Items> attackItemList, ArrayList<Items> healItemList){
        String fileName = "savefile.txt";
        String playerData = n.toString();
        
        String mapData = "Map:\n" + m.toString();

        String monsterData =
        "Monsters:\n" + 
        monsters.get(0).toString() +
        "\n" +
        monsters.get(1).toString() +
        "\n" +
        monsters.get(2).toString();

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
}
