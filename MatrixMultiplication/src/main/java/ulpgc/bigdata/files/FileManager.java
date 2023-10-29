package ulpgc.bigdata.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public void createFile(String filename) {
        try {
            File myObj = new File(filename);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void write(String filepath, String content) {
        try {
            FileWriter myWriter = new FileWriter(filepath, true);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
