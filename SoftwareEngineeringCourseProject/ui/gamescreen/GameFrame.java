package ui.gamescreen;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JButton;

public class GameFrame extends JFrame {

    Dimension gamesize = Toolkit.getDefaultToolkit().getScreenSize();
    public GamePanel gamepanel = new GamePanel(gamesize,this);
    public PausePanel pausepanel = new PausePanel(gamesize, this);
    JButton pauseButton;
    JButton resumeButton;
    JButton savegameButton;
    JButton helpButton;

    
    public void Start() {
        this.setTitle("Outer Space");
        this.setSize(gamesize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(gamepanel);
        this.addKeyListener(gamepanel);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void removePanel(GamePanel p) {
         p.setVisible(false);
         this.pausepanel.setBounds(100, 100, 1000, 1000);
         this.add(pausepanel);
    }
    public void addPanel() {
         this.remove(pausepanel);
         gamepanel.resumeGame();
         gamepanel.setVisible(true);
    }

}