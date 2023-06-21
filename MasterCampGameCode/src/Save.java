import java.io.*;

public class Save {
    public static void main(String[] args) {
        String fileName = "savefile.txt";
        String data = "This is the data to be saved in the file."; //ajouter ici différentes variables à sauvegarder, ainsi que le terrain. Le but est que si une sauvegarde existe, on la load

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
