package domain.saveLoad;

import java.io.FileWriter;
import java.io.IOException;

import domain.objects.ObjectList;


public class FileSave implements Saver{

    LoadSaveHandler lSaveHandler = LoadSaveHandler.getInstance();

    public FileSave fileSave() {
        return new FileSave();
    }

    @Override
    public void save() {
        
        
        
        try {

            if (lSaveHandler.getPlayer().getUserName().equals("dganioglu19")) {

                FileWriter myWriter = new FileWriter("domain/saveLoad/usertextfiles/dganioglu19.txt");
                myWriter.append(ObjectList.getInstance().toString());

                myWriter.close();

            } else if (lSaveHandler.getPlayer().getUserName().equals("tonal19")) {
                FileWriter myWriter = new FileWriter("domain/saveLoad/usertextfiles/tonal19.txt");
                myWriter.append(ObjectList.getInstance().toString());

                myWriter.close();

            } else if (lSaveHandler.getPlayer().getUserName().equals("yhizir19")) {
                FileWriter myWriter = new FileWriter("domain/saveLoad/usertextfiles/yhizir19.txt");
                myWriter.append(ObjectList.getInstance().toString());

                myWriter.close();

            } else if (lSaveHandler.getPlayer().getUserName().equals("bkoken19")) {
                FileWriter myWriter = new FileWriter("domain/saveLoad/usertextfiles/bkoken19.txt");
                myWriter.append(ObjectList.getInstance().toString());

                myWriter.close();

            } else if (lSaveHandler.getPlayer().getUserName().equals("kdai19")) {
                FileWriter myWriter = new FileWriter("domain/saveLoad/usertextfiles/kdai19.txt");
                myWriter.append(ObjectList.getInstance().toString());

                myWriter.close();

            } 
            
            // if (lSaveHandler.getPlayer().getUserName().equals("dganioglu19")) {
                


            //     FileWriter myWriter = new FileWriter("domain/saveLoad/usertextfiles/dganioglu19.txt");

                    
            //     for (Asteroid a : GameHandler.getInstance().getAsteroidList()) {
            //         myWriter.append(a.name + "\n"+ a.life + "\n" + a.x + "\n" + a.y + "\n");
            //     }
            //     myWriter.close();
               
            // } else if (lSaveHandler.getPlayer().getUserName().equals("tonal19")) {

            //     FileWriter myWriter = new FileWriter("domain/saveLoad/usertextfiles/tonal19.txt");
            //     for (Asteroid a : GameHandler.getInstance().getAsteroidList()) {
            //         myWriter.append(a.name + "\n"+ a.life + "\n" + a.x + "\n" + a.y + "\n");
            //     }
            //     myWriter.close();

            // } else if (lSaveHandler.getPlayer().getUserName().equals("bkoken19")) {

            //     FileWriter myWriter = new FileWriter("domain/saveLoad/usertextfiles/bkoken19.txt");
            //     for (Asteroid a : GameHandler.getInstance().getAsteroidList()) {
            //         myWriter.append(a.name + "\n"+ a.life + "\n" + a.x + "\n" + a.y + "\n");
            //     }
            //     myWriter.close();

            // } else if (lSaveHandler.getPlayer().getUserName().equals("yhizir19")) {

            //     FileWriter myWriter = new FileWriter("domain/saveLoad/usertextfiles/yhizir19.txt");
            //     for (Asteroid a : GameHandler.getInstance().getAsteroidList()) {
            //         myWriter.append(a.name + "\n"+ a.life + "\n" + a.x + "\n" + a.y + "\n");
            //     }
            //     myWriter.close();
                
            // } else if (lSaveHandler.getPlayer().getUserName().equals("kdai19")) {

            //     FileWriter myWriter = new FileWriter("domain/saveLoad/usertextfiles/kdai19.txt");
            //     for (Asteroid a : GameHandler.getInstance().getAsteroidList()) {
            //         myWriter.append(a.name + "\n"+ a.life + "\n" + a.x + "\n" + a.y + "\n");
            //     }
            //     myWriter.close();
                
            // }

            
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        

        
    }
    
}
