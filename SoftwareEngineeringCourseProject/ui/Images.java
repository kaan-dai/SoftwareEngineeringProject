package ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import domain.handlers.ImageHandler;

import java.awt.image.BufferedImage;
public class Images {

    private static List<BufferedImage> imageFileList = new ArrayList<>();
    
    
    public static BufferedImage getImageFileList(int i) {
        return imageFileList.get(i);
    }

    ImageHandler ih = new ImageHandler();
    private static List<String> imagePaths = ImageHandler.getImageFile();
    
     
    public static List<BufferedImage> setImages() {
        BufferedImage img = null;
        try {
            for (String imagePath : imagePaths) {
                img = ImageIO.read(new File(imagePath));
                imageFileList.add(img);
            }
        } catch (IOException e) {
            
        } catch (IndexOutOfBoundsException e) {
            System.err.println("image not found");
        }
        return imageFileList;
    }
}
