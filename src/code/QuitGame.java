package code;

import java.awt.event.*;

public class QuitGame implements ActionListener{

	public QuitGame(){
		
	}
	
	public void actionPerformed (ActionEvent e){
		
		System.out.println("You have chosen to quit the game.");
		
		// quit game
		System.exit(0);
			
	}
}
