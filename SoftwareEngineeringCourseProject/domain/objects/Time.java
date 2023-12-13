package domain.objects;

public class Time {
    private long start;
    private long now;
    private long elapsedTime;
    private long stopTime = 0;

    private static Time instance;

    private Time() {}

    public static Time getInstance() {
        if(instance == null) {
            instance = new Time();
            return instance;
        }
        return instance;
    }

    public void start() {
        start = System.currentTimeMillis();
    }    
   
    public long getElapsedTime() {
        now = System.currentTimeMillis();
        elapsedTime = ((now - start) / 1000) + stopTime;
        return elapsedTime;
    }

    public void stopTime() {
        stopTime = elapsedTime;
    }

    public long getStopTime() {
        return this.stopTime;
    }

    public void setStopTime(int time) {
        this.stopTime = time;
    }
}
