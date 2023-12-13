package domain.saveLoad;

import java.util.ArrayList;

import domain.aliens.AlienFactory;
import domain.asteroids.AsteroidFactory;
import domain.handlers.RunHandler;
import domain.objects.Player;
import domain.powerups.PowerUpFactory;


public class LoadSaveHandler {

    SaveAdapter saveAdapter = new SaveAdapter();
    LoadAdapter loadAdapter = new LoadAdapter();
  

    private static LoadSaveHandler instance;
    Player player = Player.getInstance();
    
    AsteroidFactory asteroidFactory = AsteroidFactory.getInstance();
    AlienFactory alienFactory = AlienFactory.getInstance();
    PowerUpFactory powerUpFactory = PowerUpFactory.getInstance();
   




    public static LoadSaveHandler getInstance() {
        if (instance == null) {
            
			instance = new LoadSaveHandler();
		}
		return instance;
	}

    public void save(Saver s) {
        this.saveAdapter.setAdapter(s);
        saveAdapter.save();
    }

    public void loadGame(Loader l) {
        this.loadAdapter.setAdapter(l);
        loadAdapter.load();
    }



    public Player getPlayer() {
        return this.player;
    }

    public void createFromLoad(ArrayList<String> coordinates) {

        for (int i = 0; i < coordinates.size(); i++) {
                
            if (coordinates.get(i).equals("Simple")) {
                asteroidFactory.createSimpleFromLoad(Boolean.parseBoolean(coordinates.get(i + 1)), coordinates.get(i + 2), Integer.parseInt(coordinates.get(i + 3)), Integer.parseInt(coordinates.get(i + 4)), Integer.parseInt(coordinates.get(i + 5)));
                i += 5;

            } else if (coordinates.get(i).equals("Firm")) {
                asteroidFactory.createFirmFromLoad(Boolean.parseBoolean(coordinates.get(i + 1)), coordinates.get(i + 2), Integer.parseInt(coordinates.get(i + 3)), Integer.parseInt(coordinates.get(i + 4)), Integer.parseInt(coordinates.get(i + 5)));
                i += 5;

            } else if (coordinates.get(i).equals("Explosive")) {
                asteroidFactory.createExplosiveFromLoad(Boolean.parseBoolean(coordinates.get(i + 1)), coordinates.get(i + 2), Integer.parseInt(coordinates.get(i + 3)), Integer.parseInt(coordinates.get(i + 4)), Integer.parseInt(coordinates.get(i + 5)));
                i += 5;

            } else if (coordinates.get(i).equals("Gift")) {
                asteroidFactory.createGiftFromLoad(Boolean.parseBoolean(coordinates.get(i + 1)), coordinates.get(i + 2), Integer.parseInt(coordinates.get(i + 3)), Integer.parseInt(coordinates.get(i + 4)), Integer.parseInt(coordinates.get(i + 5)));
                i += 5;

            } else if (coordinates.get(i).equals("Repairing")) {
                alienFactory.createRepairingFromLoad(Integer.parseInt(coordinates.get(i+1)), Integer.parseInt(coordinates.get(i + 2)));
                i +=2; 

            } else if (coordinates.get(i).equals("Protecting")) {
                alienFactory.createProtectingFromLoad(Integer.parseInt(coordinates.get(i+1)), Integer.parseInt(coordinates.get(i + 2)));
                i +=2; 
            }else if (coordinates.get(i).equals("Surprising")) {
                alienFactory.createSurprisingFromLoad(Integer.parseInt(coordinates.get(i+1)), Integer.parseInt(coordinates.get(i + 2)));
                i +=2; 
            }else if (coordinates.get(i).equals("Time Wasting")) {
                alienFactory.createTimeWastingFromLoad(Integer.parseInt(coordinates.get(i+1)), Integer.parseInt(coordinates.get(i + 2)));
                i +=2; 
            }else if (coordinates.get(i).equals("Cooperative")) {
                alienFactory.createCooperativeFromLoad(Integer.parseInt(coordinates.get(i+1)), Integer.parseInt(coordinates.get(i + 2)));
                i +=2; 
            } else if (coordinates.get(i).equals("Chance") || coordinates.get(i).equals("InBagChance")) {
                powerUpFactory.createChanceFromLoad(Integer.parseInt(coordinates.get(i + 1)), Integer.parseInt(coordinates.get(i + 2)), coordinates.get(i));
                i += 2;
            } else if (coordinates.get(i).equals("Wrap") || coordinates.get(i).equals("InBagWrap")) {
                powerUpFactory.createWrapFromLoad(Integer.parseInt(coordinates.get(i + 1)), Integer.parseInt(coordinates.get(i + 2)), coordinates.get(i));
                i += 2;
            }else if (coordinates.get(i).equals("Magnet") || coordinates.get(i).equals("InBagMagnet")) {
                powerUpFactory.createMagnetFromLoad(Integer.parseInt(coordinates.get(i + 1)), Integer.parseInt(coordinates.get(i + 2)), coordinates.get(i));
                i += 2;
            }else if (coordinates.get(i).equals("TallerP") || coordinates.get(i).equals("InBagTallerP")) {
                powerUpFactory.createTallerPaddleFromLoad(Integer.parseInt(coordinates.get(i + 1)), Integer.parseInt(coordinates.get(i + 2)), coordinates.get(i));
                i += 2;
            }else if (coordinates.get(i).equals("GOB") || coordinates.get(i).equals("InBagGOB")) {
                powerUpFactory.createGangOfBallsFromLoad(Integer.parseInt(coordinates.get(i + 1)), Integer.parseInt(coordinates.get(i + 2)), coordinates.get(i));
                i += 2;
            }else if (coordinates.get(i).equals("DLG") || coordinates.get(i).equals("InBagDLG")) {
                powerUpFactory.createDestructiveLaserGunFromLoad(Integer.parseInt(coordinates.get(i + 1)), Integer.parseInt(coordinates.get(i + 2)), coordinates.get(i));
                i += 2;
            } else if (coordinates.get(i).equals("Ball")) {
                RunHandler.getInstance().setBallsFromLoad(Double.parseDouble(coordinates.get(i + 1)), Double.parseDouble(coordinates.get(i + 2)));
                i += 2;
            }
            
                

        }

    }

    
}
