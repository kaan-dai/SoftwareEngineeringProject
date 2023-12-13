package domain.aliens;

import java.util.ArrayList;


public class AlienList {
 
    private static ArrayList<Alien> aliens = new ArrayList<Alien>();

    private static AlienList instance; 

    private AlienList() {}
	
    public static AlienList getInstance() {
        if (instance == null) {
            instance = new AlienList();
            return instance;
        }
        return instance;
    }

    public void removeAll() {
        aliens.removeAll(aliens);
    }

    public void addAlien(Alien a) {
        aliens.add(a);
    }

    public  ArrayList<Alien> getAliens() {
        return aliens;
    }

    public void remove(Alien a) {
        aliens.remove(a);
    }

    public int getListSize() {
        return aliens.size();
    }

    public boolean hasRepairing() {
        if(aliens.size() == 0) {
            return false;
        } else {
            for(Alien ali : aliens) {
                if(ali.getType().equals("Repairing")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasProtecting() {
        if(aliens.size() == 0) {
            return false;
        } else {
            for(Alien ali : aliens) {
                if(ali.getType().equals("Protecting")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCooperative() {
        if(aliens.size() == 0) {
            return false;
        } else {
            for(Alien ali : aliens) {
                if(ali.getType().equals("Cooperative")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasTimeWasting() {
        if(aliens.size() == 0) {
            return false;
        } else {
            for(Alien ali : aliens) {
                if(ali.getType().equals("Time Wasting")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasSurprising() {
        if(aliens.size() == 0) {
            return false;
        } else {
            for(Alien ali : aliens) {
                if(ali.getType().equals("Surprising")) {
                    return true;
                }
            }
        }
        return false;
    }
}
