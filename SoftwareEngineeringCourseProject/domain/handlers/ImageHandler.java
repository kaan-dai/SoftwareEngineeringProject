package domain.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImageHandler {
    private static List<String> imageFileList = new ArrayList<>();

    public static List<String> getImageFile() {
        try {
            File imagePaths = new File("images/imagePaths.txt");
            Scanner scanner = new Scanner(imagePaths);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                imageFileList.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("file not found");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("image not found");
        }
        
        return imageFileList;
    }   
}
