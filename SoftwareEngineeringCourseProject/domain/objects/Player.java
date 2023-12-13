package domain.objects;

import java.util.HashMap;

import domain.powerups.PowerUp;

public class Player {

    private String userName;
    private int score = 0;
    private int health = 3;
    // private ArrayList<PowerUp> powerUpBag = new ArrayList<>();
    private HashMap<String, PowerUp> powerUpBag = new HashMap<>();

    private static Player instance;

    private Player(){}

    public static Player getInstance() {
        if (instance == null) {
             instance = new Player();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(long elapsedTime) {
        double result = this.score + (300/elapsedTime);
        this.score = (int)result;
    }

    public void setScoreFromLoad(long score) {
        this.score = (int)score;
    }

    public HashMap<String,PowerUp> getPowerUpBag() {
        return powerUpBag;
    }

    public void setPowerUpBag(HashMap<String,PowerUp> powerUpBag) {
        this.powerUpBag = powerUpBag;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void increaseLife() {
        this.health += 1;
    }

    public void addToBag(PowerUp pow) {
        switch (pow.getType()) {
            case "TallerP":
                powerUpBag.put("T", pow);
                break;
            case "Chance":
                this.increaseLife();
                powerUpBag.put("C", pow);
                break;
            case "DLG":
                powerUpBag.put("L", pow);
                break;
            case "GOB":
                powerUpBag.put("G", pow);
                break;
            case "Wrap":
                powerUpBag.put("P", pow);
                break;
            case "Magnet":
                powerUpBag.put("M", pow);
                break;
            default:
                break;
        }
        // System.out.println(this.powerUpBag.toString());
    }
    

}
