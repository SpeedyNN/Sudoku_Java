package init;

import view.StartUI;
import model.GameTimer;
import model.SudokuBoard;
import view.GameUI;

public class run {
	
			


	public static void main(String[] args) 
	{
			// TODO Auto-generated method stub
			
		
		
		StartUI startUI = new StartUI();
		
		SudokuBoard mainBoard = null;
		
		switch (startUI.selection){
			
			case 0:
			mainBoard = new SudokuBoard(0);
			System.out.println("you picked the EASY difficulty!");
			break;
			
			case 1:
			mainBoard = new SudokuBoard(1);
			System.out.println("you picked the MEDIUM difficulty!");
			break;
			case 2:
			mainBoard = new SudokuBoard(2);
			System.out.println("you picked the HARD difficulty!");
			break;
			
			default:
			System.err.println("No difficulty picked!!!");
			break;
		}

		GameUI gameUI = new GameUI(mainBoard);		
		gameUI.timer = new GameTimer();
		gameUI.timer.start();
	}
}
