package code;

import java.awt.image.BufferedImage;
import javax.swing.JLabel;

public class Tile {
	
	private int				_tileIndex,		// index of that tile within the image list
							_x,				// x coordinate
							_y;				// y coordinate
	private JLabel 			_label;			// label being added to _panel, contains _image
	private BufferedImage 	_image;			// image, pre-label
	private boolean			_isBlank;		// flag to denote whether or not this is the blank tile
	
	
	public Tile(int index, int xCoordinate, int yCoordinate, JLabel label, BufferedImage image, boolean isBlank){
		
		_tileIndex 	= index; 	
		_label 		= label;	
		_image		= image;
		_x			= xCoordinate;
		_y			= yCoordinate;
		_isBlank	= isBlank;
		
	}
	
	
	public void setImage(BufferedImage image){
		
		_image = image;
		_image.getGraphics().clearRect(0, 0, getWidth(), getWidth());
		
	}
	

	public int getIndex(){
		
		return _tileIndex;
	
	}
	

	public JLabel getLabel(){
		
		return _label;
		
	}
	
	
	public BufferedImage getImage(){
		
		return _image;
	}

	
	public int getWidth() {

		return _label.getWidth();
	
	}
	
	public void setBlank(boolean trueFalse){
		
		_isBlank = trueFalse;
		
	}
	
	public boolean getBlank(){
		
		return _isBlank;
		
	}
	
	
	public boolean checkBounds(int xClick, int yClick){
		
		if (	(( xClick >= _x ) && ( xClick <= ( _x + getWidth() ))	) 			// is within x coordinates
				&& 
				(( yClick >= _y ) && ( yClick <= ( _y + getWidth() ))	))	 		// is within y coordinates
		{	
			return true;
		}
		else	
			return false;
		
		
	}
}

