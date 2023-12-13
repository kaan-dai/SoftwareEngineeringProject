package ui.loginscreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import domain.handlers.MusicPlayer;

import java.awt.*;
import java.awt.image.BufferedImage;
import ui.Images;;

public class Selection extends JPanel implements ActionListener {

	private Color backgroundColor = Color.LIGHT_GRAY;

	public JButton loadGameText;
	public JButton loadGameData;
	public JButton buildGame;
	public JButton exitGame;
	private JLabel gameName;

	Timer timer;

	ActionListener l;

	static BufferedImage back1;
    static BufferedImage back2;
    static BufferedImage back3;
    static BufferedImage back4;
	static BufferedImage outerSpace;

	long start = System.currentTimeMillis();
	long curtime;
	Dimension d;



	public Selection(Dimension d) {
		this.d = d;
		Images.setImages();
        back1 = Images.getImageFileList(5);
        back2 = Images.getImageFileList(6);
        back3 = Images.getImageFileList(7);
        back4 = Images.getImageFileList(8);
		outerSpace = Images.getImageFileList(9);
		// this.setBackground(backgroundColor);
		this.setSize(d);
		this.setLayout(new GridBagLayout());
		GridBagConstraints cont = new GridBagConstraints();

		int h = (int) d.getHeight()/3;

		cont.insets = new Insets(5,5,h,5);
        cont.gridx = 0;
        cont.gridy = 0;
		cont.gridwidth = 2;
		gameName = new JLabel();
		// gameName.setFont(new Font("Serif", Font.BOLD, 40));
		// gameName.setSize(200, 200);
		// gameName.setForeground(Color.GREEN);
		// gameName.setText("OUTER SPACE");
		this.add(gameName, cont);
		
        
        cont.insets = new Insets(5,5,20,5);
        cont.gridx = 0;
        cont.gridy = 1;
		cont.gridwidth = 2;
		buildGame = new JButton();
		buildGame.setText("Build a new Game");
		buildGame.setBackground(backgroundColor);
		this.add(buildGame, cont);
        
        cont.gridx = 0;
        cont.gridy = 2;
		cont.gridwidth = 2;
		loadGameText = new JButton();
		loadGameText.setText("Load From Save File");
		loadGameText.setBackground(backgroundColor);
		this.add(loadGameText, cont);

		cont.gridx = 0;
        cont.gridy = 3;
		cont.gridwidth = 2;
		loadGameData = new JButton();
		loadGameData.setText("Load From Database");
		loadGameData.setBackground(backgroundColor);
		this.add(loadGameData, cont);
		
		cont.gridx = 0;
        cont.gridy = 4;
		cont.gridwidth = 2;
		exitGame = new JButton();
		exitGame.setText("Exit the Game");
		exitGame.setBackground(backgroundColor);
		this.add(exitGame, cont);

		MusicPlayer.play();
		
		timer = new javax.swing.Timer(100,this);
		timer.addActionListener(this);
		timer.start();
	}

	@Override
 	protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
		if(curtime%2 ==0) g.drawImage(back1, 0, 0, this.getSize().width, this.getSize().height,  null);
		else if(curtime%3 ==0) g.drawImage(back2, 0, 0, this.getSize().width, this.getSize().height,  null);
        else if(curtime%5 == 0) g.drawImage(back3, 0, 0, this.getSize().width, this.getSize().height,  null);
		else g.drawImage(back4, 0, 0, this.getSize().width, this.getSize().height,  null);
		g.drawImage(outerSpace, (int) (d.getWidth()/2 - d.getWidth()/12), (int) (d.getHeight()/3 - d.getHeight()/12), (int) d.getWidth()/6, (int) d.getHeight()/6, null);
	}

	public JButton getLoadGame() {
		return loadGameText;
	}

	public JButton getBuildGame() {
		return buildGame;
	}
	
	public JButton exitGameButton(){
		return exitGame;
	}

	public void actionPerformed(ActionEvent e){
		long end = System.currentTimeMillis();
		curtime = end - start;
		// System.out.println(curtime);
		repaint();

	}

}
