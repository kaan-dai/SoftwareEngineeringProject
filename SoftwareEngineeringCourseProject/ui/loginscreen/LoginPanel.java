package ui.loginscreen;

import javax.swing.*;

//import javafx.event.ActionEvent;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;


public class LoginPanel extends JPanel {

    // private Color backgroundColor = Color.LIGHT_GRAY;

    private JLabel username;
	private JLabel password;
	
	private JTextField usernameField;
	private JPasswordField passwordField;

    private JButton loginButton;	

    ActionListener l;

    
    Dimension d;

    public LoginPanel(Dimension d) {
        this.setLayout(null);
        this.d = d;
        this.setSize(d);
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();

        cont.insets = new Insets(5,5,5,5);
        
        cont.gridx = 0;
        cont.gridy = 0;
        username = new JLabel();
        this.add(username, cont);

        cont.gridwidth = 2;
        cont.gridx = 3;
        cont.gridy = 0;
        usernameField = new JTextField(15);
        this.add(usernameField, cont);

        cont.gridwidth = 1;
        cont.gridx = 0;
        cont.gridy = 1;
        password = new JLabel();
        this.add(password, cont);

        cont.gridwidth = 2;
        cont.gridx = 3;
        cont.gridy = 1;
        passwordField = new JPasswordField(15);
        this.add(passwordField,cont);

        cont.gridwidth = 3;
        cont.gridx = 2;
        cont.gridy = 2;
        loginButton = new JButton();
        loginButton.addActionListener(l);
        this.add(loginButton,cont);

        username.setText("Username:");        
        password.setText("Password:");
        // usernameField.setText("dganioglu19");
        // passwordField.setText("12345");
        loginButton.setText("Login");
    }

    public JTextField getUsernamTextField() {
        return this.usernameField;
    }

    public JTextField getPasswordTextField() {
        return this.passwordField;
    }

	public JButton getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(JButton loginButton) {
		this.loginButton = loginButton;
	}

   /* public void act(LoginPanel p) {
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
               p.setVisible(false);
               var SelectPanel = new Selection(d);
               SelectPanel.setVisible(true);
                
            }
            
        });
    }*/
                
    }