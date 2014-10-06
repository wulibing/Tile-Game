package code;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import code.PlayGame;

public class MouseClick implements MouseListener{
	
	private PlayGame _game;
	
	public MouseClick(PlayGame game){
		
		_game = game;

	}
	
	public void mouseClicked(MouseEvent e){
		
		java.awt.Point p = e.getPoint();
		
		_game.moveTiles(p);	
	}
	
	public void mouseEntered(MouseEvent e){
		// empty
	}
	
	public void mouseExited(MouseEvent e){
		// empty
	}
	
	public void mousePressed(MouseEvent e){
		// empty
	}
	
	public void mouseReleased(MouseEvent e){
		// empty
	}


}
