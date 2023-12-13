package domain.timer;

import java.util.TimerTask;

import domain.handlers.RunHandler;

public class BallMoveTimer extends TimerTask {


    private RunHandler rhand;
    private boolean timerStart = false;
  
    public BallMoveTimer(RunHandler rHandler) {
    	 this.rhand= rHandler;
    }

    @Override
    public void run() {
        timerStart = true;
        rhand.moveBall();
    }

    public boolean timerStarted(){
        return this.timerStart;
    }
}
