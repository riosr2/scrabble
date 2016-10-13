import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.*;
import java.io.*;


public class Scrabble {
	static String specialCode; //String to set special tiles.
	static ImageIcon icon; //Variable for the icons of each button
	static Image img; //Img that will be turned into the icon
	static int boxSize = 25; //How big each icon should be.
	static boolean valid = true; //Boolean to see if a move is valid
	static Set<String> dictionary; //A set of words in the Oxford Dictionary.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		initHashSet(); //This initializes the dictionary I use.

		JFrame scrabble = new JFrame();
		scrabble.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scrabble.setSize(900, 700);
		scrabble.setTitle("Scrabble");
		

		JPanel panelBoard = new JPanel();
		scrabble.add(panelBoard);
		
		JPanel panelControl = new JPanel();
		scrabble.add(panelControl);
		
		//Sets a couple of dimensional attributes than can change easily.
		int x = 10;
		int y = 75;
		Square[][] board = new Square[15][15];
		int controlStart = 410;
		int yControl = 80;
		panelBoard.setBounds(0, 0, 400, 700);
		panelBoard.setLayout(null);
		panelControl.setBounds(controlStart, 0, 320, 700);
		panelControl.setLayout(null);
		
		//The next couple of things add different fields to display information or make a move.
		JLabel move = new JLabel();
		move.setBounds(controlStart + 20, yControl, 280, 20);
		move.setText("PLAYER 1 MOVE");
		
		JLabel word = new JLabel();
		word.setBounds(controlStart + 50, yControl + 20, 40, 20);
		word.setText("WORD:");
		
		JLabel word2 = new JLabel();
		word2.setBounds(controlStart + 55, yControl + 50, 400, 40);
		word2.setText("Click Board where First Letter of Word Should Appear");
		
		JLabel row = new JLabel();
		row.setBounds(controlStart + 50, yControl + 100, 100, 20);
		row.setText("SELECTED ROW:");
		
		JLabel col = new JLabel();
		col.setBounds(controlStart + 90, yControl + 120, 55, 20);
		col.setText("COLUMN:");
		
		JLabel direction = new JLabel();
		direction.setBounds(controlStart + 50, yControl + 150, 120, 20);
		direction.setText("DIRECTION OF WORD");
		
		JTextField rowText = new JTextField();
		rowText.setBounds(controlStart + 150, yControl + 100, 30, 20);
		
		JTextField colText = new JTextField();
		colText.setBounds(controlStart + 150, yControl + 120, 30, 20);
		
		JTextField wordText = new JTextField();
		wordText.setBounds(controlStart + 100, yControl + 20, 50, 20);
		
		String[] data = {"Horizontal", "Vertical"};
		JList<String> directionList = new JList<String>(data);
		directionList.setBounds(controlStart + 70, yControl + 170, 70, 40);
		
		JButton makeMove = new JButton("MAKE MOVE");
		makeMove.setBounds(controlStart + 55, yControl + 215, 105, 35);
		
		for(int i = 0; i < 15; i++){//Sets the special spaces on the board to their appropriate icon.
			for(int j = 0; j < 15; j++){
				Square tile = new Square(i, j);
				TileListener btnListener = new TileListener(rowText, colText);
				tile.addActionListener(btnListener);
				tile.setBounds(x, y, boxSize, boxSize);
				
				tile.setBorder(BorderFactory.createLineBorder(Color.black));
				tile.setOpaque(true);
				if((i == 7) && (j == 7)){
					specialCode = "ST";
				}
				else if(((i==1) && (j==5)) || ((i==1) && (j==9)) || ((i==5) && (j==1)) || ((i==5) && (j==5)) || ((i==5) && (j==9)) || ((i==5) && (j==13)) || ((i==9) && (j==1)) || ((i==9) && (j==5)) || ((i==9) && (j==9)) || ((i==9) && (j==13)) || ((i==13) && (j==5)) || ((i==13) && (j==9))){
						specialCode = "TL";
				}
				else if(((i==0) && (j==0)) || ((i==0) && (j==7)) || ((i==0) && (j==14)) || ((i==7) && (j==0)) || ((i==7) && (j==14)) || ((i==14) && (j==0)) || ((i==14) && (j==7)) || ((i==14) && (j==14))){
					specialCode = "TW";
				}
				else if(((i==0) && (j==3)) || ((i==0) && (j==11)) || ((i==2) && (j==6)) || ((i==2) && (j==8)) || ((i==3) && (j==0)) || ((i==3) && (j==7)) || ((i==3) && (j==14)) || ((i==6) && (j==2)) || ((i==6) && (j==6)) || ((i==6) && (j==8)) || ((i==6) && (j==12)) || ((i==7) && (j==3)) || ((i==7) && (j==11)) || ((i==14) && (j==3)) || ((i==14) && (j==11)) || ((i==12) && (j==6)) || ((i==12) && (j==8)) || ((i==11) && (j==0)) || ((i==11) && (j==7)) || ((i==11) && (j==14)) || ((i==8) && (j==2)) || ((i==8) && (j==6)) || ((i==8) && (j==8)) || ((i==8) && (j==12))){
					specialCode = "DL";
				}
				else if(((i==1) && (j==1)) || ((i==1) && (j==13)) || ((i==2) && (j==2)) || ((i==2) && (j==12)) || ((i==3) && (j==3)) || ((i==3) && (j==11)) || ((i==4) && (j==4)) || ((i==4) && (j==10)) || ((i==13) && (j==1)) || ((i==13) && (j==13)) || ((i==12) && (j==2)) || ((i==12) && (j==12)) || ((i==11) && (j==3)) || ((i==11) && (j==11)) || ((i==10) && (j==4)) || ((i==10) && (j==10))){
					specialCode = "DW";
				}
				else{
					specialCode = "a";
				}
				switch(specialCode){
				case "DL":
					icon = new ImageIcon("images/DoubleLetter.jpg");break;
				case "DW":
				 	icon = new ImageIcon("images/DoubleWord.jpg");break;
				case "TL":
					icon = new ImageIcon("images/TripleLetter.jpg");break;
				case "TW":
					icon = new ImageIcon("images/TripleWord.jpg");break;
				case "ST":
					icon = new ImageIcon("images/Star.jpg");break;
				default:
					icon = new ImageIcon();
					tile.setOpaque(false);
				}
				if(icon.getDescription() != null){
				img = icon.getImage();
				Image newImg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
				icon= new ImageIcon(newImg);
				tile.setIcon(icon);
				}
				panelBoard.setLayout(null);
				x+=boxSize;
				panelBoard.add(tile);
				board[i][j] = tile;
			}
			x = 10;
			y+=boxSize;
		}
	
		//Adds all of the various fields to the control panel
		panelControl.add(move);
		panelControl.add(word);
		panelControl.add(word2);
		panelControl.add(row);
		panelControl.add(col);
		panelControl.add(direction);
		panelControl.add(rowText);
		panelControl.add(colText);
		panelControl.add(wordText);
		panelControl.add(directionList);
		panelControl.add(makeMove);

		//The method to actually make a move
		makeMove.addActionListener(new MoveListener() {
			@Override
			public void actionPerformed(ActionEvent e){ //If the button is clicked
				if(directionList.getSelectedValue() == null){ //If no direction is selected, the user is prompted to select an appropriate direction
					JOptionPane.showMessageDialog(null, "Please select a direction!");
				}
				else{ //Else we check to see if we can make the move.
					//Set variables to keep track of various elements.
					String choice = directionList.getSelectedValue();
					int row = Integer.parseInt(rowText.getText()) - 1;
					int col = Integer.parseInt(colText.getText()) - 1;
					String word = wordText.getText().toLowerCase();
					int colLength = word.length() + col;
					int rowLength = word.length() + row;
					ImageIcon icon;
					boolean found = false;

					if(dictionary.contains(word)){//Check to see if the word is even a word
						if((choice.equals("Horizontal")) && (colLength < 16)){//Check to see what type of move it is and whether it will go out of bounds
							if((!((row == 7) && (col == 7))) && (board[7][7].getValue().equals(""))) valid = false; //If the center doesn't have a tile yet, first move has not been made
							for(int k = 0; k < word.length(); k++){
								if(!board[row][col+k].getValue().equals("")){//Check to see if we find an existing tile.
									found = true;
								}
							}
							if((row == 7) && (col == 7)){//WIP: There is a bug where no matter what move it is, the middle piece can change.
							}
							else if(found){//If we found another tile, compare the letter you want to place with the letter on the board.
								for(int k = 0; k < word.length(); k++){
									if(!board[row][col+k].getValue().equals("")){
										if(!board[row][col+k].getValue().equals(word.substring(k,k+1).toLowerCase())){ //If they are different letters it is not a valid move
											JOptionPane.showMessageDialog(null, "Move not valid! Letters don't match.");
											valid = false;
											break;
										}
									}
								}
							}
							else{//If the move does not pass through the center (source of the bug) or another tile, move is not valid.
								valid = false;
								JOptionPane.showMessageDialog(null,"Move not valid! Must start at center or pass through another tile");
							}
							if(valid){//If it's valid loop through horizontally and place a tile for each letter.
								int x = 0;
								for(int i = col; i < colLength; i++){
									switch(word.substring(x,x+1).toLowerCase()){//Switch case block to find the correct icon to place
										case "a": icon = new ImageIcon("tiles/Tile00.jpg");break;
										case "b": icon = new ImageIcon("tiles/Tile01.jpg");break;
										case "c": icon = new ImageIcon("tiles/Tile02.jpg");break;
										case "d": icon = new ImageIcon("tiles/Tile03.jpg");break;
										case "e": icon = new ImageIcon("tiles/Tile04.jpg");break;
										case "f": icon = new ImageIcon("tiles/Tile05.jpg");break;
										case "g": icon = new ImageIcon("tiles/Tile06.jpg");break;
										case "h": icon = new ImageIcon("tiles/Tile07.jpg");break;
										case "i": icon = new ImageIcon("tiles/Tile08.jpg");break;
										case "j": icon = new ImageIcon("tiles/Tile09.jpg");break;
										case "k": icon = new ImageIcon("tiles/Tile10.jpg");break;
										case "l": icon = new ImageIcon("tiles/Tile11.jpg");break;
										case "m": icon = new ImageIcon("tiles/Tile12.jpg");break;
										case "n": icon = new ImageIcon("tiles/Tile13.jpg");break;
										case "o": icon = new ImageIcon("tiles/Tile14.jpg");break;
										case "p": icon = new ImageIcon("tiles/Tile15.jpg");break;
										case "q": icon = new ImageIcon("tiles/Tile16.jpg");break;
										case "r": icon = new ImageIcon("tiles/Tile17.jpg");break;
										case "s": icon = new ImageIcon("tiles/Tile18.jpg");break;
										case "t": icon = new ImageIcon("tiles/Tile19.jpg");break;
										case "u": icon = new ImageIcon("tiles/Tile20.jpg");break;
										case "v": icon = new ImageIcon("tiles/Tile21.jpg");break;
										case "w": icon = new ImageIcon("tiles/Tile22.jpg");break;
										case "x": icon = new ImageIcon("tiles/Tile23.jpg");break;
										case "y": icon = new ImageIcon("tiles/Tile24.jpg");break;
										case "z": icon = new ImageIcon("tiles/Tile25.jpg");break;
										default: icon = new ImageIcon("tiles/Tile26.jpg");break;
									}
									if(icon.getDescription() != null){//Actually sets the icon to that letter
										img = icon.getImage();
										Image newImg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
										icon= new ImageIcon(newImg);
										board[row][i].setIcon(icon);
										board[row][i].setValue(word.substring(x,x+1).toLowerCase());
									}
									x+=1;
								}
							}
							valid = true;//Resets the valid variable so the next move starts out as a valid move.
						}
						else if((choice.equals("Vertical")) && (rowLength < 16)){//Same thing as horizontal but updates rows instead of columns.
							if(!((row == 7) && (col == 7)) && (board[7][7].getValue().equals(""))) valid = false;
							for(int k = 0; k < word.length(); k++){
								if(!board[row+k][col].getValue().equals("")){
									found = true;
								}
							}
							if((row == 7) && (col == 7)){
							}
							else if(found){
								for(int k = 0; k < word.length(); k++){
									if(!board[row+k][col].getValue().equals("")){
										if(!board[row+k][col].getValue().equals(word.substring(k,k+1).toLowerCase())){
											JOptionPane.showMessageDialog(null, "Move not valid! Letters don't match.");
											valid = false;
											break;
										}
									}
								}
							}
							else{
								valid = false;
								JOptionPane.showMessageDialog(null,"Move not valid! Must start at center or pass through another tile");
							}
							if(valid){
								int x = 0;
								for(int i = row; i < rowLength; i++){
									switch(word.substring(x,x+1).toLowerCase()){
										case "a": icon = new ImageIcon("tiles/Tile00.jpg");break;
										case "b": icon = new ImageIcon("tiles/Tile01.jpg");break;
										case "c": icon = new ImageIcon("tiles/Tile02.jpg");break;
										case "d": icon = new ImageIcon("tiles/Tile03.jpg");break;
										case "e": icon = new ImageIcon("tiles/Tile04.jpg");break;
										case "f": icon = new ImageIcon("tiles/Tile05.jpg");break;
										case "g": icon = new ImageIcon("tiles/Tile06.jpg");break;
										case "h": icon = new ImageIcon("tiles/Tile07.jpg");break;
										case "i": icon = new ImageIcon("tiles/Tile08.jpg");break;
										case "j": icon = new ImageIcon("tiles/Tile09.jpg");break;
										case "k": icon = new ImageIcon("tiles/Tile10.jpg");break;
										case "l": icon = new ImageIcon("tiles/Tile11.jpg");break;
										case "m": icon = new ImageIcon("tiles/Tile12.jpg");break;
										case "n": icon = new ImageIcon("tiles/Tile13.jpg");break;
										case "o": icon = new ImageIcon("tiles/Tile14.jpg");break;
										case "p": icon = new ImageIcon("tiles/Tile15.jpg");break;
										case "q": icon = new ImageIcon("tiles/Tile16.jpg");break;
										case "r": icon = new ImageIcon("tiles/Tile17.jpg");break;
										case "s": icon = new ImageIcon("tiles/Tile18.jpg");break;
										case "t": icon = new ImageIcon("tiles/Tile19.jpg");break;
										case "u": icon = new ImageIcon("tiles/Tile20.jpg");break;
										case "v": icon = new ImageIcon("tiles/Tile21.jpg");break;
										case "w": icon = new ImageIcon("tiles/Tile22.jpg");break;
										case "x": icon = new ImageIcon("tiles/Tile23.jpg");break;
										case "y": icon = new ImageIcon("tiles/Tile24.jpg");break;
										case "z": icon = new ImageIcon("tiles/Tile25.jpg");break;
										default: icon = new ImageIcon("tiles/Tile26.jpg");break;
									}
									if(icon.getDescription() != null){
										img = icon.getImage();
										Image newImg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
										icon= new ImageIcon(newImg);
										board[i][col].setIcon(icon);
										board[i][col].setValue(word.substring(x,x+1).toLowerCase());
									}
									x+=1;
								}
							}
							valid = true;
						}
						else{
							JOptionPane.showMessageDialog(null, "Move is not valid! Word must fit in the board!");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Move is not valid! Input is not a word!");
					}
				}
			}
		});
		
		panelBoard.setVisible(true);
		panelControl.setVisible(true);
		scrabble.setVisible(true);
	}

	public static void initHashSet(){
		File file = new File("data/words.txt");
		try{
			Scanner scan = new Scanner(file);
			dictionary = new HashSet<String>();
			while(scan.hasNext()){
				dictionary.add(scan.next());//Reads in and adds all words in the text file to the hashset.
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
