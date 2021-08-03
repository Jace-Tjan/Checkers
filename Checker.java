package checkergame;
//Jace Tjan
//Status: Checkers can be played generally
//no king made yet

//if you click a white spot accidentally it still moves the last
//	checker you clicked if you select an empty black box within it's options next.
//might want to add an action listener for all the white ones which turns clicked once false.

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Checker implements ActionListener{
	
	private JFrame frame;
	private JButton btnA[];
	private String text;
	private Boolean clickedOnce;
	private int previousClick;
	private int nextClick1;
	private int nextClick2;
	private int nextClick3;
	private int nextClick4;
	private int kClick1;
	private int kClick2;
	private int kClick3;
	private int kClick4;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checker window = new Checker();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Checker() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setLayout(new GridLayout(8,8));
		frame.setSize(410,410);
		frame.setTitle("Checker Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		clickedOnce = true; //initializing
		
		btnA= new JButton[64];
		for(int i = 0; i < 64; i++) {
			btnA[i] = new JButton();
			frame.add(btnA[i]);
			
			if(i < 8) {
				if(i % 2 == 0) { //even
					btnA[i].setBackground(Color.black);
					btnA[i].setText("O");
					btnA[i].setForeground(Color.white);
					btnA[i].addActionListener(this);
				}
				else
					btnA[i].setBackground(Color.white);
			} 
			else if(i > 7 && i < 16) {
				if(i % 2 == 0) //even
					btnA[i].setBackground(Color.white);
				else
				{ 
					btnA[i].setBackground(Color.black);
					btnA[i].setText("O");
					btnA[i].setForeground(Color.white);
					btnA[i].addActionListener(this);
				}
			}
			else if(i > 15 && i < 24) {
				if(i % 2 == 0){ //even
					btnA[i].setBackground(Color.black);
					btnA[i].setText("O");
					btnA[i].setForeground(Color.white); 
					btnA[i].addActionListener(this);
				}
				else
					btnA[i].setBackground(Color.white);
			}
			else if(i > 23 && i < 32) {
				if(i % 2 == 0) //even
					btnA[i].setBackground(Color.white);
				else {
					btnA[i].setBackground(Color.black);
					btnA[i].addActionListener(this);
				}
			}
			else if(i > 31 && i < 40) {
				if(i % 2 == 0) { //even
					btnA[i].setBackground(Color.black);
					btnA[i].addActionListener(this);
				}else
					btnA[i].setBackground(Color.white);
			}
			else if(i > 39 && i < 48) {
				if(i % 2 == 0) //even
					btnA[i].setBackground(Color.white);
				else{ //even
					btnA[i].setBackground(Color.black);
					btnA[i].setText("O");
					btnA[i].setForeground(Color.red);
					btnA[i].addActionListener(this);
				}
			}
			else if(i > 47 && i < 56) {
				if(i % 2 == 0){ //even
					btnA[i].setBackground(Color.black);
					btnA[i].setText("O");
					btnA[i].setForeground(Color.red);
					btnA[i].addActionListener(this);
				}
				else
					btnA[i].setBackground(Color.white);
			}
			else if(i > 55 && i < 64) {
				if(i % 2 == 0) //even
					btnA[i].setBackground(Color.white);
				else{ 
					btnA[i].setBackground(Color.black);
					btnA[i].setText("O");
					btnA[i].setForeground(Color.red); 
					btnA[i].addActionListener(this);
				}
			}
		}//end of for
	}
	
	
	public void actionPerformed(ActionEvent e) {
		text = ((JButton)e.getSource()).getText();
		if (text == "O" && clickedOnce == true || text == "K" && clickedOnce == true) { //button with checker
			for(int i = 0; i < 64; i++) {
				if(e.getSource() == btnA[i]) {//finds the button index that was clicked
					
					//sets the potential options for next click of button
					previousClick = i;
					if(btnA[i].getForeground() == Color.white && text == "O") {
						nextClick1 = i + 7;
						nextClick2 = i + 9;
						nextClick3 = i + 14;
						nextClick4 = i + 18;
					} else if(btnA[i].getForeground() == Color.red && text == "O") {
						nextClick1 = i - 7;
						nextClick2 = i - 9;
						nextClick3 = i - 14;
						nextClick4 = i - 18;
					} else if(text == "K") { //is king
						nextClick1 = i + 7;
						nextClick2 = i + 9;
						nextClick3 = i + 14;
						nextClick4 = i + 18;
						kClick1 = i - 7;
						kClick2 = i - 9;
						kClick3 = i - 14;
						kClick4 = i - 18;
					}
					
				}
			}
			clickedOnce = false;
		}
		else if(text != "O" && clickedOnce == false && text != "K") {//second click if no checker on spot.
			for(int i = 0; i < 64; i++) {
				if(e.getSource() == btnA[i]) { //finds the button index that was clicked
					
					if(i == nextClick1 || i == nextClick2 || i == nextClick3 || i == nextClick4) {
						//if moving one space
						if(i == nextClick1 || i == nextClick2 ) {
							btnA[i].setText(btnA[previousClick].getText()); //making new checker
							btnA[i].setForeground(btnA[previousClick].getForeground());
							
							btnA[previousClick].setText(""); //erasing previous
							btnA[previousClick].setForeground(Color.black);
							
							//makes a king
							if(i > 55 && i < 64) {
								btnA[i].setText("K");
							} 
							else if (i < 8) {
								btnA[i].setText("K");
							}
							
						}
						else if(i == nextClick3 ) { //if checker is jumped
							
							if(btnA[nextClick1].getText() == "O" || btnA[kClick1].getText() == "K") {
								//if white jumps a red
								if(btnA[previousClick].getForeground() == Color.white 
									&& btnA[nextClick1].getForeground() == Color.red) {
								
									btnA[i].setText(btnA[previousClick].getText()); //making new checker
									btnA[i].setForeground(btnA[previousClick].getForeground());
									
									btnA[nextClick1].setText(""); //erasing jumped checker
									btnA[nextClick1].setForeground(Color.black);
								
									btnA[previousClick].setText(""); //erasing previous
									btnA[previousClick].setForeground(Color.black);
									
									//makes a king
									if(i > 55 && i < 64) {
										btnA[i].setText("K");
									} 
									else if (i < 8) {
										btnA[i].setText("K");
									}
								}
							//if red jumps a white
								else if(btnA[previousClick].getForeground() == Color.red 
									&& btnA[nextClick1].getForeground() == Color.white) {
								
									btnA[i].setText(btnA[previousClick].getText()); //making new checker
									btnA[i].setForeground(btnA[previousClick].getForeground());
								
									btnA[nextClick1].setText(""); //erasing jumped checker
									btnA[nextClick1].setForeground(Color.black);
								
									btnA[previousClick].setText(""); //erasing previous
									btnA[previousClick].setForeground(Color.black);
									
									//makes a king
									if(i > 55 && i < 64) {
										btnA[i].setText("K");
									} 
									else if (i < 8) {
										btnA[i].setText("K");
									}
									
								}
							} 
						}//end of nextClick3
						else if(i == nextClick4) { //if checker is jumped
							
							if(btnA[nextClick2].getText() == "O" || btnA[nextClick2].getText() == "K") {
								//if white jumps a red
								if(btnA[previousClick].getForeground() == Color.white 
									&& btnA[nextClick2].getForeground() == Color.red) {
								
									btnA[i].setText(btnA[previousClick].getText()); //making new checker
									btnA[i].setForeground(btnA[previousClick].getForeground());
								
									btnA[nextClick2].setText(""); //erasing jumped checker
									btnA[nextClick2].setForeground(Color.black);
								
									btnA[previousClick].setText(""); //erasing previous
									btnA[previousClick].setForeground(Color.black);
									
									//makes a king
									if(i > 55 && i < 64) {
										btnA[i].setText("K");
									} 
									else if (i < 8) {
										btnA[i].setText("K");
									}
								}
								//if red jumps a white
								else if(btnA[previousClick].getForeground() == Color.red 
									&& btnA[nextClick2].getForeground() == Color.white) {
								
									btnA[i].setText(btnA[previousClick].getText()); //making new checker
									btnA[i].setForeground(btnA[previousClick].getForeground());
								
									btnA[nextClick2].setText(""); //erasing jumped checker
									btnA[nextClick2].setForeground(Color.black);
								
									btnA[previousClick].setText(""); //erasing previous
									btnA[previousClick].setForeground(Color.black);
									
									//makes a king
									if(i > 55 && i < 64) {
										btnA[i].setText("K");
									} 
									else if (i < 8) {
										btnA[i].setText("K");
									}
									
								}
							} 
							
						}//end of nextClick4
					}//end of if you Click one of the 4 options
					
					//section for the extra King selections
					else if(btnA[previousClick].getText() == "K") {
						if(i == kClick1 || i == kClick2 || i == kClick3 || i == kClick4) {
							//if moving one space
							if(i == kClick1 || i == kClick2 ) {
								btnA[i].setText(btnA[previousClick].getText()); //making new checker
								btnA[i].setForeground(btnA[previousClick].getForeground());
								
								btnA[previousClick].setText(""); //erasing previous
								btnA[previousClick].setForeground(Color.black);
							}
							else if(i == kClick3 ) { //if checker is jumped
								
								if(btnA[kClick1].getText() == "O" || btnA[kClick1].getText() == "K") {
									//if white jumps a red
									if(btnA[previousClick].getForeground() == Color.white 
										&& btnA[kClick1].getForeground() == Color.red) {
									
										btnA[i].setText(btnA[previousClick].getText()); //making new checker
										btnA[i].setForeground(btnA[previousClick].getForeground());
										
										btnA[kClick1].setText(""); //erasing jumped checker
										btnA[kClick1].setForeground(Color.black);
									
										btnA[previousClick].setText(""); //erasing previous
										btnA[previousClick].setForeground(Color.black);
										
									}
								//if red jumps a white
									else if(btnA[previousClick].getForeground() == Color.red 
										&& btnA[kClick1].getForeground() == Color.white) {
									
										btnA[i].setText(btnA[previousClick].getText()); //making new checker
										btnA[i].setForeground(btnA[previousClick].getForeground());
									
										btnA[kClick1].setText(""); //erasing jumped checker
										btnA[kClick1].setForeground(Color.black);
									
										btnA[previousClick].setText(""); //erasing previous
										btnA[previousClick].setForeground(Color.black);
									}
								} 
							}//end of kClick3
							else if(i == kClick4) { //if checker is jumped
								
								if(btnA[kClick2].getText() == "O" || btnA[kClick2].getText() == "K") {
									//if white jumps a red
									if(btnA[previousClick].getForeground() == Color.white 
										&& btnA[kClick2].getForeground() == Color.red) {
									
										btnA[i].setText(btnA[previousClick].getText()); //making new checker
										btnA[i].setForeground(btnA[previousClick].getForeground());
									
										btnA[kClick2].setText(""); //erasing jumped checker
										btnA[kClick2].setForeground(Color.black);
									
										btnA[previousClick].setText(""); //erasing previous
										btnA[previousClick].setForeground(Color.black);
									}
									//if red jumps a white
									else if(btnA[previousClick].getForeground() == Color.red 
										&& btnA[kClick2].getForeground() == Color.white) {
									
										btnA[i].setText(btnA[previousClick].getText()); //making new checker
										btnA[i].setForeground(btnA[previousClick].getForeground());
									
										btnA[kClick2].setText(""); //erasing jumped checker
										btnA[kClick2].setForeground(Color.black);
									
										btnA[previousClick].setText(""); //erasing previous
										btnA[previousClick].setForeground(Color.black);
										
									}
								} 
								
							}//end of kClick4
						}//end of if you Click one of the 4 options, second options
					}//end of if king
				}//end of if get source
			} //end of for loop
			
			clickedOnce = true;
		}//end of second click
		//this section is to deal with a bug if you wanted to click one color checker
		//then click the other color checker, and then click an empty space.
		//it now makes the newest clicked checker the last checker clicked.
		else if (text == "O" && clickedOnce == false || text == "K" && clickedOnce == false) {
			for(int i = 0; i < 64; i++) {
				if(e.getSource() == btnA[i]) {//finds the button index that was clicked
					
					//sets the potential options for next click of button
					previousClick = i;
					if(btnA[i].getForeground() == Color.white && text == "O") {
						nextClick1 = i + 7;
						nextClick2 = i + 9;
						nextClick3 = i + 14;
						nextClick4 = i + 18;
					} else if(btnA[i].getForeground() == Color.red && text == "O") {
						nextClick1 = i - 7;
						nextClick2 = i - 9;
						nextClick3 = i - 14;
						nextClick4 = i - 18;
					} else if(text == "K") { //is king
						nextClick1 = i + 7;
						nextClick2 = i + 9;
						nextClick3 = i + 14;
						nextClick4 = i + 18;
						kClick1 = i - 7;
						kClick2 = i - 9;
						kClick3 = i - 14;
						kClick4 = i - 18;
					}
					
				}//end of get source
			}//end of for loop
		}//end of if second click is checker, bug fixing section
	}//end of action performed

}//end of class
