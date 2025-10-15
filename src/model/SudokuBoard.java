package model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;


public class SudokuBoard {
	
	private int[][] grid;
	private static final int SIZE = 9;
	private final int difficulty;
	private static final Random rand = new Random();
	
	public SudokuBoard(int difficulty){
		this.difficulty = difficulty;
		grid = new int[SIZE][SIZE];
		initializeWithValidBoard();
	}

	public int[][] getGrid() {
		return grid;
	}
	
	public void printBoard() {
		
		for(int i = 0; i < grid.length; i++){ 	// i = col
			
			for(int j = 0; j < grid[i].length; j++)	// j = row
			{
				System.out.print(grid[i][j]+ " ");
			
			}
			System.out.println();
		}
		
	}
	
	
	//AI SLOP VVVVV
	private void initializeWithValidBoard() {
	    // A valid Sudoku board with some zeros (empty cells)
	    int[][] validBoard = {
	        {5, 3, 0, 0, 7, 0, 0, 0, 0},
	        {6, 0, 0, 1, 9, 5, 0, 0, 0},
	        {0, 9, 8, 0, 0, 0, 0, 6, 0},
	        {8, 0, 0, 0, 6, 0, 0, 0, 3},
	        {4, 0, 0, 8, 0, 3, 0, 0, 1},
	        {7, 0, 0, 0, 2, 0, 0, 0, 6},
	        {0, 6, 0, 0, 0, 0, 2, 8, 0},
	        {0, 0, 0, 4, 1, 9, 0, 0, 5},
	        {0, 0, 0, 0, 8, 0, 0, 7, 9}
	    };
	    
	    // Copy the valid board to our grid
	    for (int i = 0; i < SIZE; i++) {
	        for (int j = 0; j < SIZE; j++) {
	            grid[i][j] = validBoard[i][j];
	        }
	    }
	}
	

	
	
		
	
	
	

}


