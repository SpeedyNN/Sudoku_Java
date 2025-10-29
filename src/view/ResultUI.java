package view;

import javax.swing.JOptionPane;

public class ResultUI 
{
	
	String[] Choice = {"Try Again", "Change Difficulty", "Exit"};
	public int selection = JOptionPane.showOptionDialog(null, 
			"You are done with the sudoku puzzle."+
			"\nYour result:"+" RESULT"
			+"\nYour Time: "+ model.GameTimer.TimeElapsed+ "Seconds",
			"Java Sudoku",0,3,null,Choice, Choice[0]);	
}
