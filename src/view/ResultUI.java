package view;

import javax.swing.JOptionPane;

import init.run;
public class ResultUI  
{
	
	String[] Choice = {"Try Again", "Change Difficulty", "Exit"};
	public int selection =  JOptionPane.showOptionDialog(null, 
	"You are done with the sudoku puzzle."+
	"\nYour result:"+ run.globalGameUi.getFinalResult()
	+"\nYour Time:"+ model.GameTimer.TimeElapsed+" seconds",
	"Java Sudoku",0,3,null,Choice, Choice[0]);;
					
}	


