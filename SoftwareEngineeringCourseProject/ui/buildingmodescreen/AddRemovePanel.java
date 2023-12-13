package ui.buildingmodescreen;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;


import java.awt.Dimension;
import java.awt.Graphics;


import domain.asteroids.Asteroid;
import domain.asteroids.AsteroidList;

public class AddRemovePanel extends JPanel implements ActionListener {

    public JButton startButton;
    
    private JLabel simpleLabel;
	private JLabel firmLabel;
	private JLabel explosiveLabel;
	private JLabel giftLabel;

    private JLabel simpleNumLabel;
	private JLabel firmNumLabel;
	private JLabel explosiveNumLabel;
	private JLabel giftNumLabel;

	private JButton simpleButton;
	private JButton firmButton;
	private JButton expButton;
	private JButton giftButton;

	AsteroidList aList = AsteroidList.getInstance();
	private JButton delButton;

    private Color backgroundColor = Color.blue;

    ActionListener l;


    public AddRemovePanel(Dimension d) {

        this.setBackground(backgroundColor);
		this.setLayout(null);
        this.setSize(d);
		this.setLayout(null);
		Dimension dimen = new Dimension( (int)d.getWidth() - (200 * 3/2), 20);
       simpleLabel = new JLabel();
	   firmLabel = new JLabel();
	   explosiveLabel = new JLabel();
	   giftLabel = new JLabel();

       simpleNumLabel = new JLabel();
       firmNumLabel = new JLabel();
       explosiveNumLabel = new JLabel();
       giftNumLabel = new JLabel();
	   

        this.add(simpleLabel);
		simpleLabel.setText("Simple Asteroids:");
        // simpleLabel.setSize((int)d.getWidth()/10, 20);
		simpleLabel.setBounds(((int)dimen.getWidth())*108/100, (int)dimen.getWidth()/80, 200, 20);
		simpleLabel.setVisible(true);

		

        this.add(simpleNumLabel);
		simpleNumLabel.setText("12");
        // simpleNumLabel.setSize((int)d.getWidth()/10, 20);
		simpleNumLabel.setBounds((((int)dimen.getWidth() + simpleLabel.getWidth()))*101/100 , (int)dimen.getWidth()/80, 200, 20);
		simpleNumLabel.setVisible(true);


        this.add(firmLabel);
		firmLabel.setText("Firm Asteroids:");
		// firmLabel.setSize((int)d.getWidth()/10, 20);
		firmLabel.setBounds(((int)dimen.getWidth()*108/100), 30, 200, 20);
		firmLabel.setVisible(true);

        this.add(firmNumLabel);
		firmNumLabel.setText("100");
        // firmNumLabel.setSize((int)d.getWidth()/10, 20);
		firmNumLabel.setBounds(((int)dimen.getWidth() + firmLabel.getWidth())*101/100, 30, 200, 20);
		firmNumLabel.setVisible(true);



        this.add(explosiveLabel);
		explosiveLabel.setText("Explosive Asteroids:");
		// explosiveLabel.setSize((int)d.getWidth()/10, 20);
		explosiveLabel.setVisible(true);
		explosiveLabel.setBounds(((int)dimen.getWidth()*108/100), 50, 200, 20);

        this.add(explosiveNumLabel);
		explosiveNumLabel.setText("100");
        // explosiveNumLabel.setSize((int)d.getWidth()/10, 20);
		explosiveNumLabel.setBounds((((int)dimen.getWidth() + explosiveLabel.getWidth()))*102/100, 50, 200, 20);
		explosiveNumLabel.setVisible(true);



        this.add(giftLabel);
		giftLabel.setText("Gift Asteroids:");
		// giftLabel.setSize((int)d.getWidth()/10, 20);
		giftLabel.setVisible(true);
		giftLabel.setBounds(((int)dimen.getWidth()*108/100) , 70, 200, 20);

        this.add(giftNumLabel);
		giftNumLabel.setText("100");
		// giftNumLabel.setSize((int)d.getWidth()/10, 20);
        giftNumLabel.setBounds(((int)dimen.getWidth() + giftLabel.getWidth())*101/100, 70, 200, 20);
		giftNumLabel.setVisible(true);

		startButton = new JButton();
        this.add(startButton);
        startButton.setText("Start");
		startButton.setLayout(null);
		startButton.setVisible(true);
		startButton.setBounds((int)d.getWidth() - startButton.getWidth() - 200, 240, 150, 20);
        startButton.addActionListener(l);

		simpleButton = new JButton();
        this.add(simpleButton);
        simpleButton.setText("add simple");
		simpleButton.setLayout(null);
		simpleButton.setVisible(true);
		simpleButton.setBounds((int)d.getWidth() - giftNumLabel.getWidth() * 3/2 + 100, 100, 150, 20);
        simpleButton.addActionListener(l);

		firmButton = new JButton();
        this.add(firmButton);
        firmButton.setText("add firm");
		firmButton.setLayout(null);
		firmButton.setVisible(true);
		firmButton.setBounds((int)d.getWidth() - giftNumLabel.getWidth() * 3/2 + 100, 130, 150, 20);
        firmButton.addActionListener(l);

		expButton = new JButton();
        this.add(expButton);
        expButton.setText("add explosive");
		expButton.setLayout(null);
		expButton.setVisible(true);
		expButton.setBounds((int)d.getWidth() - giftNumLabel.getWidth() * 3/2 + 100, 160, 150, 20);
        expButton.addActionListener(l);

		giftButton = new JButton();
        this.add(giftButton);
        giftButton.setText("add gift");
		giftButton.setLayout(null);
		giftButton.setVisible(true);
		giftButton.setBounds((int)d.getWidth() - giftNumLabel.getWidth() * 3/2 + 100, 190, 150, 20);
        giftButton.addActionListener(l);


    }
	


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Asteroid element : aList.getAsteroids()) {
			if (element.name.equals("Simple")) {
				g.setColor(Color.green);
				g.fillRect(element.getX(), element.getY(), element.width, element.len);
			} else if (element.name.equals("Firm")) {
				g.setColor(Color.gray);
				g.fillOval(element.getX(), element.getY(), element.width, element.len);
			} else if(element.name.equals("Explosive"))  {
				g.setColor(Color.orange);
				g.fillOval(element.getX(), element.getY(), element.width, element.len);
			} else if(element.name.equals("Gift"))  {
				g.setColor(Color.red);
				g.fillRect(element.getX(), element.getY(), element.width, element.len);
		}
		repaint();
	}
	}
    

	public JLabel getSimpleNumLabel() {
		return simpleNumLabel;
	}

	public JLabel getFirmNumLabel() {
		return firmNumLabel;
	}

	public JLabel getExplosiveNumLabel() {
		return explosiveNumLabel;
	}

	public JLabel getGiftNumLabel() {
		return giftNumLabel;
	}


	public JButton getstartButton() {
		return startButton;
	}

	public JButton getSimpleButton() {
		return simpleButton;
	}

	public JButton getFirmButton() {
		return firmButton;
	}

	public JButton getExpButton() {
		return expButton;
	}

	public JButton getGiftButton() {
		return giftButton;
	}

	public JButton getDelButton() {
		return delButton;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}