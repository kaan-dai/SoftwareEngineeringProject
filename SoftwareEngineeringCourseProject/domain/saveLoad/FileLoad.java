package domain.saveLoad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import domain.controllers.BuildingController;
import domain.handlers.RunHandler;
import domain.objects.Paddle;
import domain.objects.Player;
// import domain.objects.Time;


public class FileLoad implements Loader{

    LoadSaveHandler lSaveHandler = LoadSaveHandler.getInstance();

    int numberSimple = 0;
    int numberFirm = 0;
    int numberExplosive = 0;
    int numberGift = 0;

    int numberRepairing;
    int numberProtecting;
    int numberTimeWasting;
    int numberCoopearitve;
    int numberSurprising;

    int numPowerUp = 0;

    ArrayList<String> coordinates = new ArrayList<String>();

    int i = 1;

    public FileSave fileSave() {
        return new FileSave();
    }

    @Override
    public void load() {
       
        try {
            if (lSaveHandler.getPlayer().getUserName().equals("dganioglu19")) {
    
                File file = new File("domain/saveLoad/usertextfiles/dganioglu19.txt");
         
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                BuildingController.getInstance().setInitialAsteroidNum(Integer.parseInt(br.readLine()));
                RunHandler.getInstance().setTime(Integer.valueOf(br.readLine()));
                Player.getInstance().setScoreFromLoad(Integer.valueOf(br.readLine()));
                Player.getInstance().setHealth(Integer.valueOf(br.readLine()));
                Paddle.getInstance().setPaddleX(Integer.valueOf(br.readLine()));
                Paddle.getInstance().setPaddleY(Integer.valueOf(br.readLine()));

                while ((st = br.readLine()) != null) {

                    this.coordinates.add((st.toString()));
               }

               System.out.println(coordinates);
               lSaveHandler.createFromLoad(coordinates);
                br.close();
                

            }
            if (lSaveHandler.getPlayer().getUserName().equals("tonal19")) {
    
                File file = new File("domain/saveLoad/usertextfiles/tonal19.txt");
         
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                BuildingController.getInstance().setInitialAsteroidNum(Integer.parseInt(br.readLine()));
                RunHandler.getInstance().setTime(Integer.valueOf(br.readLine()));
                Player.getInstance().setScoreFromLoad(Integer.valueOf(br.readLine()));
                Player.getInstance().setHealth(Integer.valueOf(br.readLine()));
                Paddle.getInstance().setPaddleX(Integer.valueOf(br.readLine()));
                Paddle.getInstance().setPaddleY(Integer.valueOf(br.readLine()));

                while ((st = br.readLine()) != null) {

                    this.coordinates.add((st.toString()));
               }

              
               lSaveHandler.createFromLoad(coordinates);
                br.close();

            }
            if (lSaveHandler.getPlayer().getUserName().equals("bkoken19")) {
    
                File file = new File("domain/saveLoad/usertextfiles/bkoken19.txt");
         
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                BuildingController.getInstance().setInitialAsteroidNum(Integer.parseInt(br.readLine()));
                RunHandler.getInstance().setTime(Integer.valueOf(br.readLine()));
                Player.getInstance().setScoreFromLoad(Integer.valueOf(br.readLine()));
                Player.getInstance().setHealth(Integer.valueOf(br.readLine()));
                Paddle.getInstance().setPaddleX(Integer.valueOf(br.readLine()));
                Paddle.getInstance().setPaddleY(Integer.valueOf(br.readLine()));

                while ((st = br.readLine()) != null) {

                    this.coordinates.add((st.toString()));
               }

              
               lSaveHandler.createFromLoad(coordinates);
                br.close();

            }
            if (lSaveHandler.getPlayer().getUserName().equals("yhizir19")) {
    
                File file = new File("domain/saveLoad/usertextfiles/yhizir19.txt");
         
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                BuildingController.getInstance().setInitialAsteroidNum(Integer.parseInt(br.readLine()));
                RunHandler.getInstance().setTime(Integer.valueOf(br.readLine()));
                Player.getInstance().setScoreFromLoad(Integer.valueOf(br.readLine()));
                Player.getInstance().setHealth(Integer.valueOf(br.readLine()));
                Paddle.getInstance().setPaddleX(Integer.valueOf(br.readLine()));
                Paddle.getInstance().setPaddleY(Integer.valueOf(br.readLine()));

                while ((st = br.readLine()) != null) {

                    this.coordinates.add((st.toString()));
               }

              
               lSaveHandler.createFromLoad(coordinates);
                br.close();

            }
            if (lSaveHandler.getPlayer().getUserName().equals("kdai19")) {
    
                File file = new File("domain/saveLoad/usertextfiles/kdai19.txt");
         
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st;
                BuildingController.getInstance().setInitialAsteroidNum(Integer.parseInt(br.readLine()));
                RunHandler.getInstance().setTime(Integer.valueOf(br.readLine()));
                Player.getInstance().setScoreFromLoad(Integer.valueOf(br.readLine()));
                Player.getInstance().setHealth(Integer.valueOf(br.readLine()));
                Paddle.getInstance().setPaddleX(Integer.valueOf(br.readLine()));
                Paddle.getInstance().setPaddleY(Integer.valueOf(br.readLine()));

                while ((st = br.readLine()) != null) {

                    this.coordinates.add((st.toString()));
               }

              
               lSaveHandler.createFromLoad(coordinates);
                br.close();

            }


        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        
       
        }
    
    }
}
