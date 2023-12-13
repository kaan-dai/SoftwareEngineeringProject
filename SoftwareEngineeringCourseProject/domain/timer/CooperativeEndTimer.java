package domain.timer;

import java.util.TimerTask;

import domain.aliens.Alien;
import domain.aliens.AlienList;
import domain.aliens.CooperativeAlien;
import domain.handlers.RunHandler;

public class CooperativeEndTimer extends TimerTask {

    AlienList alilist = AlienList.getInstance();
    CooperativeAlien aloe;

    private boolean isRunning = false;

    public CooperativeEndTimer(CooperativeAlien alie) {
        aloe = alie;
    }

    @Override
    public void run(){
        aloe.applyFunction();
        isRunning = true;
        for (Alien ali : alilist.getAliens()) {
            if(ali.getType().equals("Cooperative")) {
                RunHandler.getInstance().getRemovalListOfAlien().add(ali);
            } 
            try {
                if(ali.getSurprisingAlienState().equals("cooperative")){
                    RunHandler.getInstance().getRemovalListOfAlien().add(ali);
                } 
            } catch (NullPointerException e) {
                
            }
           
        }
    }
    
    public boolean isRunning() {
        return isRunning;
    }
}
