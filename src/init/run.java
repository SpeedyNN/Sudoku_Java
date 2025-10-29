package init;

import view.StartUI;


import model.SudokuBoard;
import view.GameUI;
import model.GameTimer;

public class run {
	
	public static GameUI globalGameUi;
	private static SudokuBoard mainBoard = null;


	public static void main(String[] args) 
	{		
		StartUI startUI = new StartUI();
		
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
		//GameTimer timer = new GameTimer();
		//timer.start();
	/*	globalGameUI ui = new GameUI(timer);
		ui.show(); */

		globalGameUi = new GameUI(mainBoard);			
	}

	public static SudokuBoard getMainBoard(){

		return mainBoard;

	}
}
