
package ui;
import java.awt.Dimension;
import java.awt.Toolkit;

import domain.handlers.BuildingHandler;
import ui.loginscreen.LoginFrame;

public class GameLoop {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int width = (int)screenSize.getWidth();    
	static int height = (int)screenSize.getHeight();
    static Dimension size = new Dimension();

    public static void main(String[] args) {
        size.setSize(width,height);
        BuildingHandler.getInstance().setWidthAndHeight(width, height);
    	LoginFrame logf = new LoginFrame(size , "Login");
    	logf.act(logf);
    
    }
    
}
