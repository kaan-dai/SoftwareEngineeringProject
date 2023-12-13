package ui.gamescreen;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;

import domain.handlers.RunHandler;
import domain.saveLoad.DataBaseSave;
import domain.saveLoad.FileSave;
import domain.saveLoad.LoadSaveHandler;

public class PausePanel extends JPanel implements ActionListener{

    GameFrame frame;

    int width, height;

    JButton quitButton;
    JButton texfileSaveButton;
    JButton databaseSaveButton;
    JButton resumeButton;

    JButton helpButton;

    ActionListener l;

    LoadSaveHandler loadSaveHandler = LoadSaveHandler.getInstance();
    FileSave filesave = new FileSave();
	DataBaseSave databasesave = new DataBaseSave();

    JPanel emptyPanel;


    public PausePanel(Dimension d, GameFrame gameFrame) {
       
        this.setBackground(Color.BLUE);
        this.width = (int)d.getWidth()/3;
        this.height = (int)d.getHeight()/3;
        this.setSize(width, height);
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gb = new GridBagConstraints();
        gb.fill = GridBagConstraints.HORIZONTAL;

       

        this.quitButton = new JButton();
        this.quitButton.setText("Quit");
        this.quitButton.setFocusable(false);

        this.texfileSaveButton = new JButton();
        this.texfileSaveButton.setText("SaveToTextFile");
        this.texfileSaveButton.setFocusable(false);

        this.databaseSaveButton = new JButton();
        this.databaseSaveButton.setText("SaveToDatabase");
        this.databaseSaveButton.setFocusable(false);

        this.resumeButton = new JButton();
        this.resumeButton.setText("Resume");
        this.resumeButton.setFocusable(false);

        this.helpButton = new JButton();
        this.helpButton.setText("Help");
        this.helpButton.setFocusable(false);
        
        this.quitButton.addActionListener(this);
        this.texfileSaveButton.addActionListener(this);
        this.databaseSaveButton.addActionListener(this);
        this.resumeButton.addActionListener(this);
        this.helpButton.addActionListener(this);

        gb.insets = new Insets(5, 5, 5, 5);
        gb.gridx = 0;
        gb.gridy = 0;
        this.add(resumeButton,gb);
        
        gb.gridx = 0;
        gb.gridy = 1;
        this.add(databaseSaveButton,gb);
        
        gb.gridx = 0;
        gb.gridy = 2;
        this.add(texfileSaveButton,gb);
        
        gb.gridx = 0;
        gb.gridy = 4;
        this.add(helpButton,gb);
        
        gb.gridx = 0;
        gb.gridy = 6;
        this.add(quitButton,gb);

        this.frame = gameFrame;       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == this.quitButton) {
            this.frame.dispose();
            System.exit(0);

        } else if (e.getSource() == this.texfileSaveButton) {
            loadSaveHandler.save(filesave);

        } else if (e.getSource() == this.databaseSaveButton) {
            loadSaveHandler.save(databasesave);

        } else if(e.getSource() == this.resumeButton) {
            this.frame.addPanel();
            RunHandler.getInstance().StartTimer();

        } else if(e.getSource() == this.helpButton) {
            HelpPanel.openHelpScreen();  
        }
    }   
}
