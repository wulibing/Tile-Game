package code;

import java.util.*; 					// List, Linked List

import javax.swing.*; 					// JFrame, JLabel, JPanel

import java.awt.image.BufferedImage;
import support.HelpfulImageMethods;


public class FifteenPuzzle {		
	
	private JFrame 				_window;		// game window
	private JPanel 				_panel;			// game board
	private JPanel 				_buttonPanel;	// JPanel for New Game / Quit buttons
	private int 				_boardSize;		// length of game board
	private LinkedList<Tile> 	_imageList;		// list of images
	private PlayGame			_game;			
	
	public FifteenPuzzle(String filePath) {
		// create a LinkedList of images
		_imageList = getImageList(filePath);
		
		// build window
		_window = new JFrame("CSE115 - Lab 8 - Allison Phillips");
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// build the game board and button JPanels
		_panel = drawPanel(filePath); 	// game board JPanel
		_buttonPanel = new JPanel();	// JPanel for buttons
		_buttonPanel.setBackground(java.awt.Color.LIGHT_GRAY);
		_buttonPanel.setPreferredSize(new java.awt.Dimension(_boardSize, 35));
		
		
		_window.getContentPane().add(_buttonPanel);
		_window.getContentPane().add(_panel);
		
		_window.setLayout(new java.awt.FlowLayout(_boardSize));
		_window.setBackground(java.awt.Color.DARK_GRAY);
		_window.setPreferredSize(new java.awt.Dimension(getBoardSize() + 10, (getBoardSize() + 70)));
	
		// output images from list to the JPanel
		drawBoard(_imageList, _panel);

		JButton startNewGame 	= new JButton("Start New Game"),
				quitGame 		= new JButton("Quit");
		
		_game = new PlayGame(_imageList, _panel, squaresPerSide());						// create an instance of PlayGame
		startNewGame.addActionListener(new StartNewGame(_imageList, _panel, _game));	// start game button
		quitGame.addActionListener(new QuitGame());										// quit game button

		// add buttons to the button panel
		_buttonPanel.add(startNewGame);
		_buttonPanel.add(quitGame);
		
		// size window to fit content and make it visible
		_window.pack();
		_window.setVisible(true);
		
	}


	public void drawBoard(List<Tile> imageList, JPanel _panel) {
	// this maps the initial game board image to the panel... is only used once	
		
		// Variables:
		int		subImageSize 	= imageList.get(0).getWidth(),	// size of an edge of a subImage square (index 0)
				xCoordinate 	= 2,							// starting coordinate for X (2 pixel buffer)
				yCoordinate 	= 2;							// starting coordinate for Y (2 pixel buffer)
		
		for(int row = 0, labelCounter = 0; row < squaresPerSide(); row = row + 1){
			
			for(int col = 0; col < squaresPerSide(); col = col + 1, labelCounter++){ 					
				
				if(labelCounter < imageList.size()){
								
					_panel.add(imageList.get(labelCounter).getLabel()).setBounds(xCoordinate, yCoordinate, subImageSize, subImageSize);
				
				}
				
				if( col == ( squaresPerSide() - 1 )){
					
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


	public int squaresPerSide() {
		
		return 4;
	}
	
	
	public int boardSize(){
		
		return _boardSize;
		
	}
	
	
	public void setBoardSize(int boardSize) {
		
		_boardSize = boardSize; 
	}
	
	
	public int getBoardSize(){
		
		return _boardSize;
	}

	
	public LinkedList <Tile> getImageList(String filePath){
	
		// load master image into a variable wholeImage
		BufferedImage wholeImage = HelpfulImageMethods.loadImage(filePath);
				
		// Other Variables
					_imageList 	= new LinkedList<Tile>();
		int 		imageLength = (wholeImage.getWidth())/squaresPerSide();
											// it is a square... I don't need two variables for width/height
		int 	counter = 0;
		
		for(int row = 0, xCoordinate = 0, yCoordinate = 0; row < squaresPerSide(); row = row + 1){
			int col; // I want col to have the life span of the loop, but to also be accessible to the outer loop
			
			for(col = 0; col < squaresPerSide(); col = col + 1, counter++){ 
				
				// create a subImage
				BufferedImage singleSubImage = 	HelpfulImageMethods.createSubImage(wholeImage, 
												xCoordinate, yCoordinate, imageLength, imageLength); 
			
				JLabel label = new JLabel(new ImageIcon(singleSubImage));
				label.setBounds(xCoordinate, yCoordinate, imageLength, imageLength);
				
				// add the subImage to the imageList
				_imageList.add(new Tile(counter, xCoordinate, yCoordinate, label, singleSubImage, false));
				
				if(col == (squaresPerSide() - 1)){
				
					// if the loop is in the last column, reset x to the initial value before the row (yCoordinate) is incremented
					xCoordinate = 0;
				
				}
				else{
					
					// else increment x to next section
					xCoordinate = xCoordinate + imageLength;
					
				}		
			}
			
			// increment y by the length of the subImage
			yCoordinate = yCoordinate + imageLength;
			
		}
		//System.out.println("_imageList size: " + _imageList.size());
		
		// get the last tile in the list and overwite it
		Tile blankTile = _imageList.get(_imageList.size()-1); 
		BufferedImage tmp = blankTile.getImage();
		blankTile.setImage(tmp);
		blankTile.setBlank(true);
		
		return _imageList;	// return a reference to the created list

	}

	public JPanel drawPanel(String filePath){

		// _boardSize is length of an edge of the reassembled image, or game board
		// = length of the master image plus the two-pixel buffer around each square
		setBoardSize(HelpfulImageMethods.loadImage(filePath).getHeight() + 2*(squaresPerSide()+1));
		
		// create a JPanel, remove layout (null), and set the panel size based on _boardSize
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(java.awt.Color.DARK_GRAY);
		panel.setPreferredSize(new java.awt.Dimension(getBoardSize(),getBoardSize()));
		
		return panel;
		
	}
}
