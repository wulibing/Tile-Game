package code;

import java.awt.event.*;
import java.util.*;
import javax.swing.JPanel;
import code.PlayGame;

public class StartNewGame implements ActionListener{

	private LinkedList <Tile> _imageList;
	private JPanel 		_panel;
	private PlayGame	_game;
		
	public StartNewGame(LinkedList <Tile> imageList, JPanel panel, PlayGame game){
	
		_imageList = imageList;
		_panel = panel;
		_game = game;
		
	}
	
	public void actionPerformed (ActionEvent e){
		
		// shuffle the board/start a new game
		Collections.shuffle(_imageList);
		
		// redraw the game board to show the shuffled tiles
		_game.drawBoard();
		
		// add the mouse listener; this way, no tiles will move until a game is started
		_panel.addMouseListener(new code.MouseClick(_game));
		
		//System.out.println("You have chosen to start a new game");
	}

}
