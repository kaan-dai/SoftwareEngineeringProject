package ui.buildingmodescreen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import java.awt.Toolkit;

import domain.asteroids.Asteroid;
import domain.asteroids.AsteroidFactory;
import domain.asteroids.AsteroidList;
import domain.controllers.BuildingController;
import domain.handlers.BuildingHandler;
import ui.gamescreen.GameFrame;
import ui.loginscreen.Frame;


public class BuildingFrame extends Frame implements MouseInputListener {

    Dimension d;
    BuildGamePanel bgP;
    ActionListener l;
    AddRemovePanel addRemoveP;
    AsteroidList aList = AsteroidList.getInstance();
    GameFrame gameF;
    BuildingHandler buildingHandler = BuildingHandler.getInstance();
    AsteroidFactory astFactory = AsteroidFactory.getInstance();
    BuildingController buildingController = BuildingController.getInstance();

    int mousePhase = 0;
    private int delete = 0;

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = (int)screenSize.getWidth();    
	public static int height = (int)screenSize.getHeight();
    

    public BuildingFrame(Dimension d, String name) {
        super(d, name);
        this.d = d;
        this.validate();
        this.setVisible(true);
        
        addRemoveP = new AddRemovePanel(d);
        addRemoveP.addMouseListener(this);
    }
    
    public void addPanel() {
        bgP = new BuildGamePanel(d);
        this.add(bgP);
    }

    /*public void addAddRemovePanel() {
        addRemoveP = new AddRemovePanel(d);
        this.add(addRemoveP);
    }*/

    

    public void act(BuildingFrame p) {
		ActionListener buttonListener = new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
                
				if(obj == bgP.getEnterButton()) {

					if (BuildingController.getInstance().check(bgP.getSimpleAstField().getText(), bgP.getFirmAstField().getText(), bgP.getExplosiveAstField().getText(), bgP.getGiftAstField().getText()))
                     { 
                        bgP.setVisible(false);
                        p.add(addRemoveP);

                        buildingHandler = BuildingHandler.getInstance();
                        buildingHandler.setNumbers(bgP.getSimpleAstField().getText(),bgP.getFirmAstField().getText(),bgP.getExplosiveAstField().getText(),bgP.getGiftAstField().getText());
                        // buildingHandler.act();

                        addRemoveP.getSimpleNumLabel().setText(buildingHandler.getnumSimple());
                        addRemoveP.getFirmNumLabel().setText(buildingHandler.getnumFirm());
                        addRemoveP.getExplosiveNumLabel().setText(buildingHandler.getnumExp());
                        addRemoveP.getGiftNumLabel().setText(buildingHandler.getnumGif());

                        buildingHandler.createSimpleH();
                        buildingHandler.createFirmH();
                        buildingHandler.createExplosiveH();
                        buildingHandler.createGiftH();
                        
                                           
                     } else {
                         bgP.getMessage().setVisible(true);
                     }  
                    
				} else if (obj == addRemoveP.getstartButton()) {
                    p.setVisible(false);
                    BuildingController.getInstance().setInitialAsteroidNum(aList.getListSize());
                    gameF = new GameFrame();
                    gameF.Start();

                } else if (obj == addRemoveP.getSimpleButton() && !(aList.getListSize() >= 200) ) {
                    buildingHandler.createOneAsteroid("simple");
                    //AsteroidFactory.getInstance().createSimple();
                    addRemoveP.getSimpleNumLabel().setText(String.valueOf(Integer.parseInt(addRemoveP.getSimpleNumLabel().getText())+1));
                } else if (obj == addRemoveP.getFirmButton() && !(aList.getListSize() >= 200)) {
                    buildingHandler.createOneAsteroid("firm");
                    //AsteroidFactory.getInstance().createFirm();
                    addRemoveP.getFirmNumLabel().setText(String.valueOf(Integer.parseInt(addRemoveP.getFirmNumLabel().getText())+1));
                } else if (obj == addRemoveP.getExpButton() && !(aList.getListSize() >= 200)) {
                    buildingHandler.createOneAsteroid("explosive");
                   // AsteroidFactory.getInstance().createExplosive();
                    addRemoveP.getExplosiveNumLabel().setText(String.valueOf(Integer.parseInt(addRemoveP.getExplosiveNumLabel().getText())+1));
                } else if (obj == addRemoveP.getGiftButton() && !(aList.getListSize() >= 200)) {
                    buildingHandler.createOneAsteroid("gift");
                    //AsteroidFactory.getInstance().createGift();
                    addRemoveP.getGiftNumLabel().setText(String.valueOf(Integer.parseInt(addRemoveP.getGiftNumLabel().getText())+1));
                }
            
			
		}
    };
    
		
		bgP.getEnterButton().addActionListener(buttonListener);
        addRemoveP.getstartButton().addActionListener(buttonListener);
        addRemoveP.getSimpleButton().addActionListener(buttonListener);
        addRemoveP.getFirmButton().addActionListener(buttonListener);
        addRemoveP.getExpButton().addActionListener(buttonListener);
        addRemoveP.getGiftButton().addActionListener(buttonListener);
        	
	}


    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
      //  for(int i = 0; i < GameHandler.asteroids.size(); i++){
          // Asteroid element = GameHandler.asteroids.get(i); 
        for(Asteroid element : aList.getAsteroids()) {
            if (SwingUtilities.isRightMouseButton(e)){
                if(buildingHandler.checkMouseOverlap(element, mouseX, mouseY)){
                    if(element.name.equals("Simple") && aList.getNumSimple() > 75){
                        addRemoveP.getSimpleNumLabel().setText(String.valueOf(Integer.parseInt(addRemoveP.getSimpleNumLabel().getText()) - 1));
                        delete = 1;
                       
                    } else if(element.name.equals("Firm")&& aList.getNumFirm() > 10) {
                        addRemoveP.getFirmNumLabel().setText(String.valueOf(Integer.parseInt(addRemoveP.getFirmNumLabel().getText()) - 1));
                        delete = 1;
                    } else if (element.name.equals("Explosive")&& aList.getNumExplosive() > 5) {
                        addRemoveP.getExplosiveNumLabel().setText(String.valueOf(Integer.parseInt(addRemoveP.getExplosiveNumLabel().getText()) - 1));
                        delete = 1;
                    } else if(element.name.equals("Gift")&& aList.getNumGift() > 10) {
                        addRemoveP.getGiftNumLabel().setText(String.valueOf(Integer.parseInt(addRemoveP.getGiftNumLabel().getText()) - 1));
                        delete = 1;
                    }
                    
                   if (delete == 1) {
                    aList.remove(element);
                    repaint();
                    delete = 0;
                   } 
                }
            } else if(SwingUtilities.isLeftMouseButton(e)){
                    if (buildingHandler.checkMouseOverlap(element, mouseX, mouseY) && buildingHandler.selectedCheck() && mousePhase == 0) {
                        element.setIsSelected(true);
                        mousePhase = 1;
                        
                    }else
                    if(element.getIsSelected() && mousePhase == 1 && (mouseX + element.width) < width - (180 * 3/2) && buildingHandler.asteroidIntersection(mouseX,mouseY,element)) {
                        mousePhase = 0;
                        // GameHandler.move(element, mouseX, mouseY);
                        element.setX(mouseX);
                        element.setY(mouseY);
                        element.setIsSelected(false);
                        repaint();
                        
                    }                
            }
        }   
    }



    @Override
    public void mousePressed(MouseEvent e) {
        // int mouseX = e.getX();
        // int mouseY = e.getY();
        // for(Asteroid ast : GameHandler.asteroids) {
        //     if (GameHandler.checkMouseOverlap(ast, mouseX, mouseY) && GameHandler.selectedCheck()) {
        //         ast.setIsSelected(true);
               
        //     }
        //     if(ast.getIsSelected()) {
        //         GameHandler.move(ast, mouseX, mouseY);
        //         repaint();
        //     }
        // }
        // System.out.println(mouseX + " " + mouseY);

    }


    @Override
    public void mouseReleased(MouseEvent e) {
    //     int mouseX = e.getX();
    //     int mouseY = e.getY();
    //    for(Asteroid ast : GameHandler.asteroids) {
    //         if (GameHandler.checkMouseOverlap(ast, mouseX, mouseY)) {
    //             ast.setX(mouseX);
    //             ast.setY(mouseY);
    //             ast.isSelected =false;
    //             repaint();
    //         }
    //    }
       
      
	}
        
    

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
	
   
	

	
	

}
