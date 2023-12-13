package ui.gamescreen;

import javax.swing.JOptionPane;

public class HelpPanel {
    private static String help = 
      "Welcome to the Outer Space game! In order to play this game, you have to login first. In this game, you have the full control of the paddle. Your main aim is to collect as many points as you can, \n"
    + "without dropping the ball to the floor by using the paddle. If you drop the ball to the floor, you will lost one of your three chances. After you used all of your chances, your final score will \n" 
    + "appear on the screen. In order to increase your score, you should destroy as many asteroids as you can. However, this won't be that easy because of the aliens you have to deal with. \n"
    + "\n"
    + "Asteroids: \n"
    + "Simple Asteroids: Grey colored. These asteroid can be broken in one hit. \n"
    + "Firm Asteroids: Purple colored. To destroy these asteroids, the player needs to hit more than one, depending on the radius of the asteroid. \n"
    + "Explosive Asteroids: Red colored. Once they get hit, they dissapear and destroy its neighbor asteroids. \n"
    + "Gift Asteroids: Yellow colored. They are hiding power-ups or aliens inside them.  Once they get hit, they dissapear and reveal what is inside them. \n"
    + "\n"
    + "Aliens: \n"
    + "Repairing Alien: These aliens create simple asteroids. If they got hit, they stop building and dissapear. \n"
    + "Protecting Alien: These aliens protects the asteroids by hiding behind them. An asteroid containing a protecting alien can not get destroyed unless the ball hits the upper part of the asteroid. \n"
    + "Cooparative Alien: These aliens pick a random row and destroy all asteroids in that row. If they got hit, no more of them will appear. \n"
    + "Time-wasting Alien: These aliens select 8 asteroids randomly (If less than 8 obstacles exist, all of them are chosen). These obstacles will be frozen can't get hit for 15 seconds. \n"
    + "\n"
    + "Power-Ups: As aforementioned, there are gift asteroids which may contain power-ups. Note that a player can collect multiple power ups but can only use one at a time. \n"
    + "Taller paddle: This power-up doubles the length of the paddle for 30 seconds. To activate this power-up, you should press the T button. \n"
    + "Magnet: This power-up allows the paddle to catch the ball by pressing the M button and then throwing it by pressing the Q button. \n" 
    + "Destructive Laser Gun: This power-up contains attached lasers to both ends of the paddle which can destroy a full column of asteroids (even the firm ones). \n"
    + "Once you activate it using L button, you will have five shots and you can use one by pressing E button. \n"
    + "Chance:  At the beginning of the game, the player has three lives. This power-up increases the player’s lives by 1. \n"
    + "Wrap: This power-up allows player’s paddle to go through the side of the screen and re-appear on the opposite side for two minutes. To activate this power-up you need to press V button. \n"
    + "Gang-of-balls: This power-up allows 10 more balls to appear and which have the same size as the original ball. To activate this power-up you need to press G button.  \n"
    + "\n"
    + "Once you login, you have four options.  \n" 
    + "If you want to start a new game, you should use build new game button and if you want to load your existing game, you can choose one of the load options provided. \n"
    + "You can also quit the game by pressing quit game button. \n"
    + "After pressing build game button, you can design the game however you want. \n"
    + "You can choose the number of asteroids and change their numbers afterwards. \n"
    + "You can add asteroids by the provided buttons on the upper right and delete asteroids by using right mouse click. \n"
    + "You can also catch asteroids by left clicking on them and relocate them by pressing any free space. \n"
    + "Once you are done designing, you can press start and use W button to start the game. \n"
    + "You can pause anytime you want by using the pause button on the upper right. \n"
    + "After pausing the game, you can resume the game or save and quit."
    ;

    
    
	public static void openHelpScreen() {
		JOptionPane.showMessageDialog(null, help, "Help Screen", JOptionPane.INFORMATION_MESSAGE);
	}
}

