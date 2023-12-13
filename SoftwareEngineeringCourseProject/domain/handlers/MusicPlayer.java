package domain.handlers;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class MusicPlayer {
    static Clip clip;

    private static String soundName = "domain/maintheme.wav";
    private static File file;

    public static void play() { 

       
        try {
            file = new File(soundName);
            AudioInputStream audi = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audi);
            clip.start();
        } catch (Exception e) {
        }
        

    }

}

