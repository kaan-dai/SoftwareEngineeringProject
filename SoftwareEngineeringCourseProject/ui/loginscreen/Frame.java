package ui.loginscreen;

import javax.swing.*;
import java.awt.Dimension;

/**
 * frame
 */
public class Frame extends JFrame {

    Dimension d;

    public Frame(Dimension d, String name) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
		this.setSize(d);
        this.setTitle(name);
        this.d = d;
        this.setResizable(false);

    }    
}