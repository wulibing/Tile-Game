package code;

import java.util.*;
import javax.swing.JPanel;

public class PlayGame {

	private LinkedList <Tile> 	_imageList;
	private JPanel 				_panel;
	private int 				_blankIndex;
	private	int					_squaresPerSide;
	
	public PlayGame(LinkedList <Tile> imageList, JPanel panel, int squaresPerSide){
		
		_imageList = imageList;
		_panel = panel;
		_squaresPerSide = squaresPerSide;
		
		drawBoard();
		
	}
	
	public void drawBoard(){
		// Variables:
		int		subImageSize 	= _imageList.get(0).getWidth(),	// size of an edge of a subImage square (index 0)
				xCoordinate 	= 2,							// starting coordinate for X (2 pixel buffer)
				yCoordinate 	= 2;							// starting coordinate for Y (2 pixel buffer)
		
		for(int row = 0, labelCounter = 0; row < _squaresPerSide; row = row + 1){
			
			for(int col = 0; col < _squaresPerSide; col = col + 1, labelCounter++){ 
 
				if(labelCounter < _imageList.size()){
					
					_panel.add(_imageList.get(labelCounter).getLabel()).setBounds(xCoordinate, yCoordinate, subImageSize, subImageSize);
				
				}
				
				if( col == ( _squaresPerSide - 1 )){
					
					// if the loop is on the last column, reset x to initial value before the row (yCoordinate) is incremented
					xCoordinate = 2;
					
				}
				else{
					
					// else increment x by the length of a subImage plus a 2 pixel buffer
					xCoordinate = xCoordinate + subImageSize + 2 ;
					
				}
			}
			
			// increment y by the length of a subImage plus a 2 pixel buffer 
			yCoordinate = yCoordinate + subImageSize + 2;
			
		}
	}
	
	public int getBlankIndex(){
		
		for(int i = 0; i < _imageList.size(); i=i+1){
			
			if(_imageList.get(i).getBlank()){
				_blankIndex = i;
			//	System.out.println("gray tile set to " + i);
			}
		}
		//System.out.println(" ");
		return _blankIndex;
	}
	
	public void moveTiles(java.awt.Point p){

		int		xCoordinate,
				yCoordinate;

		xCoordinate = (int)p.getX();
		yCoordinate = (int)p.getY();
		
		
		
		for (int i = 0; i < _imageList.size(); i++) {
			
			getBlankIndex();
			
			// System.out.println("Flag for i value " + i + " is " + _imageList.get(i).getBlank());
			
			if (_imageList.get(i).checkBounds(xCoordinate, yCoordinate))
			{	//	System.out.println("Tile Index: " + _imageList.get(i).getIndex() + ", List Index: " + i + ", Blank Tile: " + _blankIndex);
				
				// if the tile above/below grayTile was clicked
				if (	((_imageList.get(i).getIndex()) == (_blankIndex + _squaresPerSide)) 
						||
						((_imageList.get(i).getIndex()) == (_blankIndex - _squaresPerSide))	)
				{
					
					Collections.swap(_imageList, _blankIndex, _imageList.get(i).getIndex());	// swap the two tiles
					//	System.out.println("swap tile up/down");
				}
				
				// else if the tile is to the left or the right of the gray tile, now that the tile being above/below is ruled out
				else if(	((_blankIndex) == (_imageList.get(i).getIndex() + 1)) 
						|| 
							(_blankIndex == (_imageList.get(i).getIndex() - 1)))
				{
					
					Collections.swap(_imageList, _blankIndex,_imageList.get(i).getIndex());		// swap the two tiles
					//	System.out.println("swap tile left/right");
					
				}
				
			}
			
			drawBoard();
			
		}
	}
}
