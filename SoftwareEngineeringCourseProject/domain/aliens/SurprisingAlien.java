package domain.aliens;

import domain.asteroids.AsteroidList;
import domain.controllers.BuildingController;

public class SurprisingAlien extends Alien {

    AlienState currentAlienState;

    AlienState cooperative;
    AlienState challenging;
    AlienState confused;
    AlienState protecting;
    AlienState repairing;

    //player's progress in the game will determine surprising aliens's behavior
    int initialAsteroidNumber = BuildingController.getInstance().getInitialAsteroidNum();
    int currentAsteroidNumber = AsteroidList.getInstance().getListSize();
    double playersProgress; //percantage of the remaining asteroids
    
    public SurprisingAlien(){
        this.type = "Surprising";
        this.isMoving = false;
        this.isHit = false;
        
        cooperative = new Cooperative(this);
        challenging = new Challenging(this);
        confused = new Confused(this);
        protecting = new Protecting(this);
        repairing = new Repairing(this);

        //calculate progress of the player:
        playersProgress = (currentAsteroidNumber * 100) / initialAsteroidNumber;

        //set currentAlienState according to progress:
        if(playersProgress >= 70) {
            currentAlienState = cooperative;
            this.surprisingAlienState = "cooperative";
        } else if(playersProgress <= 30) {
            currentAlienState = challenging;
            this.surprisingAlienState = "challenging";
        } else if (playersProgress > 40 && playersProgress < 50) {
            currentAlienState = protecting;
            this.surprisingAlienState = "protecting";
        } else if (playersProgress > 50 && playersProgress < 60) {
            currentAlienState = repairing;
            this.surprisingAlienState = "repairing";
        } else if ((playersProgress > 30 && playersProgress < 40) || (playersProgress > 60 && playersProgress < 70)) {
            currentAlienState = confused;
            this.surprisingAlienState = "confused";
        }

    }
    
    @Override
    public void applyFunction() {
        currentAlienState.perform(this);
    }
    
    public AlienState getCurrentAlienState() {
        return currentAlienState;
    }

    public void setCurrentAlienState(AlienState newAlienState) {
        currentAlienState = newAlienState;
    }

    public AlienState getCooperative() {
        return cooperative;
}

    public AlienState getChallenging() {
        return challenging;
    }

    public AlienState getConfused() {
        return confused;
    }

    public AlienState getProtecting() {
        return protecting;
    }

    public AlienState getRepairing() {
        return repairing;
    }

}
