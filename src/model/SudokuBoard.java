package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class SudokuBoard {
	
	private int[][] grid;
	private static final int SIZE = 9;
	private static final int SUBGRID = 3;
	private final int difficulty;
	private static final Random rand = new Random();
	
	public SudokuBoard(int difficulty){
		this.difficulty = difficulty;
		grid = new int[SIZE][SIZE];
		generateFullBoard(grid);
		removeNumbers(grid,difficulty);
		printBoard(grid);
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
	
	public static boolean generateFullBoard(int[][] grid) {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if(grid[row][col] == 0) {
					List<Integer> numbers = getShuffledNumbers();
					for (int num : numbers) {
						if (isValid(grid, row, col, num)) {
							grid[row][col] = num;
							if(generateFullBoard(grid)) {
								return true;
							}
							grid[row][col] = 0;
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	private static List<Integer> getShuffledNumbers() {
		java.util.List<Integer> nums = new ArrayList<>();
		for (int i = 1; i <= SIZE; i++) nums.add(i);
		Collections.shuffle(nums, rand);
		return nums;
	}
	
	private static boolean isValid(int[][] grid, int row, int col, int num) {
		for (int i = 0; i < SIZE; i++) {
			if (grid[row][i] == num || grid[i][col] == num || 
				    grid[row - row % SUBGRID + i / SUBGRID][col - col % SUBGRID + i % SUBGRID] == num) {
				return false;
			}
		}
		return true;
	}
	
	public static void removeNumbers(int[][] grid, int difficulty) {
		int removed = 0;
		int holes = 0;
		
		switch(difficulty) {
		case 0: 
			holes = 30;
			break;
		case 1:
			holes = 40;
			break;
		case 2:
			holes = 50;
			break;
		default:
			break;
		}
		while (removed < holes) {
			int row = rand.nextInt(SIZE);
			int col = rand.nextInt(SIZE);
			if(grid[row][col] != 0) {
				int backup = grid[row][col];
				grid[row][col] = 0;
				
				if (!hasUniqueSol(copyBoard(grid))) {
					grid[row][col] = backup;
					
				} else {
					removed++;
				}
			}
		}
	}
	
	public static boolean hasUniqueSol(int[][] grid) {
		return countSolutions(grid, 0) == 1;
	}
	
	private static int countSolutions(int[][] grid, int count) {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if(grid[row][col] == 0) {
					for (int num = 1; num <= SIZE; num++) {
						if (isValid(grid, row, col, num)) {
							grid[row][col] = num;
							count = countSolutions(grid, count);
							if (count > 1) return count;
							grid[row][col] = 0;
						}
					}
					return count;
				}
			}
		}
		return count +1;
	}
	  private static int[][] copyBoard(int[][] board) {
	        int[][] copy = new int[SIZE][SIZE];
	        for (int i = 0; i < SIZE; i++) {
	            copy[i] = Arrays.copyOf(board[i], SIZE);
	        }
	        return copy;
	    }
	  private static void printBoard(int[][] board) {
	        for (int[] row : board) {
	            for (int num : row) {
	                System.out.print((num == 0 ? "." : num) + " ");
	            }
	            System.out.println();
	        }
	    }
	
	
	
	

}


