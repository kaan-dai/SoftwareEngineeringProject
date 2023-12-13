package domain.timer;

import java.util.TimerTask;
import domain.handlers.RunHandler;

public class AsteroidMoveTimer extends TimerTask{

    RunHandler rhandler;

    public AsteroidMoveTimer(RunHandler runHandler) {
        this.rhandler = runHandler;
    }

    @Override
    public void run() {
        rhandler.moveAsteroid();
    }

}
