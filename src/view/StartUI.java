package view;

import javax.swing.JOptionPane;

public class StartUI 
{
	
	String[] difficulties = {"Easy", "Medium", "Hard"};
	public int selection = JOptionPane.showOptionDialog(null, 
			"Choose the difficulty you would like to play!",
			"Java Sudoku",0,3,null,difficulties, difficulties[0]);	
	
}
