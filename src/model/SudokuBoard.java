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
	

	
	
		
	
	
	

}


