package ui.buildingmodescreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BuildGamePanel extends JPanel {
	private Color backgroundColor = Color.ORANGE;

	private JLabel infoLabel = new JLabel();
	private JLabel simpleAstLabel= new JLabel();
	private JLabel firmAstLabel = new JLabel();
	private JLabel explosiveAstLabel = new JLabel();
	private JLabel giftAstLabel = new JLabel();
	
	private JTextField simpleAstField = new JTextField(10);
	private JTextField firmAstField = new JTextField(10);
	private JTextField explosiveAstField = new JTextField(10);
	private JTextField giftAstField = new JTextField(10);
	
	private JLabel message = new JLabel();

	public JButton enterButton = new JButton();

	ActionListener l;

	public BuildGamePanel(Dimension d) {
		this.setBackground(backgroundColor);
		this.setSize(d);
		this.setLayout(null);
		this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.insets = new Insets(5,5,5,5);
        

        cont.gridx = 0;
        cont.gridy = 2;
		cont.gridwidth = 2;
		this.add(infoLabel,cont);
		infoLabel.setText("Specify the number of each asteroid type: ");
		infoLabel.setVisible(true);
		infoLabel.setBounds(50, 50, 500, 20);

		cont.gridwidth = 1;
		cont.gridx = 0;
        cont.gridy = 4;
		this.add(simpleAstLabel,cont);
		simpleAstLabel.setText("Number of Simple Asteroids: ");
		simpleAstLabel.setBounds(50, 100, 400, 20);
		simpleAstLabel.setVisible(true);

		cont.gridx = 1;
        cont.gridy = 4;
		this.add(simpleAstField,cont);
		simpleAstField.setText("75");
		// getSimpleAstField().setLayout(null);
		// getSimpleAstField().setBounds(270, 100, 100, 20);
		// getSimpleAstField().setVisible(true);

		cont.gridx = 0;
        cont.gridy = 5;
		this.add(firmAstLabel,cont);
		firmAstLabel.setText("Number of Firm Asteroids: ");
		
		cont.gridx = 1;
        cont.gridy = 5;
		this.add(firmAstField,cont);
		firmAstField.setText("10");
		
	
		cont.gridx = 0;
        cont.gridy = 6;
		this.add(explosiveAstLabel,cont);
		explosiveAstLabel.setText("Number of Explosive Asteroids: ");
		
		cont.gridx = 1;
        cont.gridy = 6;
		this.add(explosiveAstField,cont);
		explosiveAstField.setText("10");
		
		cont.gridx = 0;
        cont.gridy = 7;
		this.add(giftAstLabel,cont);
		giftAstLabel.setText("Number of Gift Asteroids: ");
		
		cont.gridx = 1;
        cont.gridy = 7;
		this.add(giftAstField,cont);
		giftAstField.setLayout(null);
		giftAstField.setText("10");

		cont.gridwidth = 2;
		cont.gridx = 0;
        cont.gridy = 8;
		this.add(enterButton,cont);
		enterButton.setText("Enter");
		enterButton.setBackground(Color.WHITE);
		enterButton.addActionListener(l);

		cont.gridx = 0;
        cont.gridy = 9;
		this.add(message,cont);
		message.setText("Please change the inputs");
		message.setVisible(false);
		// message.setBounds(200, 400, 400, 20);

		
		this.setVisible(true);

	}



	public JTextField getSimpleAstField() {
		return simpleAstField;
	}

	public void setSimpleAstField(JTextField simpleAstField) {
		this.simpleAstField = simpleAstField;
	}

	public JTextField getFirmAstField() {
		return firmAstField;
	}

	public void setFirmAstField(JTextField firmAstField) {
		this.firmAstField = firmAstField;
	}

	public JTextField getExplosiveAstField() {
		return explosiveAstField;
	}

	public void setExplosiveAstField(JTextField explosiveAstField) {
		this.explosiveAstField = explosiveAstField;
	}

	public JTextField getGiftAstField() {
		return giftAstField;
	}

	public void setGiftAstField(JTextField giftAstField) {
		this.giftAstField = giftAstField;
	}

	public JButton getEnterButton() {
		return enterButton;
	}

	public void setLoginButton(JButton enterButton) {
		this.enterButton = enterButton;
	}

	public JLabel getMessage() {
		return message;
	}

	public void setMessage(JLabel message) {
		this.message =  message;
	}
	
}
