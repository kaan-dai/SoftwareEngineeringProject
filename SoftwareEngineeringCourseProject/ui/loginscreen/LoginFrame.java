package ui.loginscreen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.buildingmodescreen.BuildingFrame;
import ui.gamescreen.GameFrame;
import domain.controllers.LoginController;
import domain.saveLoad.DataBaseSave;
import domain.saveLoad.FileLoad;
import domain.saveLoad.FileSave;
import domain.saveLoad.LoadSaveHandler;

public class LoginFrame extends Frame{
	
	
	Dimension d;
	LoginPanel logP;
	Selection loadP;
	BuildingFrame bgF;

	FileSave filesave = new FileSave();
	FileLoad fileLoad = new FileLoad();
	DataBaseSave databasesave = new DataBaseSave();
	LoginController loginController = new LoginController();


	LoadSaveHandler loadSaveHandler = LoadSaveHandler.getInstance();

	ActionListener l;

	GameFrame gameFrame;
	

	public LoginFrame(Dimension d, String name) {
		super(d, name);
		this.d = d;
		this.validate();
		this.setVisible(true);

		logP = new LoginPanel(d);
		this.add(logP);

		loadP = new Selection(d);
		

		
	}
	
	/*public void addFirstPanel() {
		
		logP = new LoginPanel(d);
		this.add(logP);
		
	}*/
	
	public void addSecondPanel() {
		this.add(loadP);
	}
	
	public void act(LoginFrame p) {
		ActionListener buttonListener = new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj == loadP.getLoadGame()) {
					p.setVisible(false);

					
					
					loadSaveHandler.loadGame(fileLoad);
					gameFrame = new GameFrame();
                    gameFrame.Start();


				} else if (obj == loadP.getBuildGame()) {
					p.setVisible(false);
					bgF = new BuildingFrame(d, "Building Mode");
					bgF.setVisible(true);
					bgF.addPanel();
					
					bgF.act(bgF);

					//-----> adapter trial
					
					
					

				} else  if (obj == logP.getLoginButton()) {
					//System.out.println(logP.getUsernamTextField().getText());
					if (loginController.loginCheck(logP.getUsernamTextField().getText(),logP.getPasswordTextField().getText())) {
						logP.setVisible(false);
						addSecondPanel();
						loadSaveHandler.getPlayer().setUserName(logP.getUsernamTextField().getText());
						
					} 
					
					
				} else  if (obj == loadP.exitGameButton()) {
					System.exit(0);
				}
			}
			
		};
		
		logP.getLoginButton().addActionListener(buttonListener);
		loadP.getLoadGame().addActionListener(buttonListener);
		loadP.getBuildGame().addActionListener(buttonListener);
		loadP.exitGameButton().addActionListener(buttonListener);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
