package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;

public class GameUI extends JPanel
{
	private JFrame gameFrame;
	private JPanel gameBoardPanel;
	private JPanel controlPanel;
	private JPanel numberDeckPanel;
	
	private static final int WINDOW_SIZE_X = 800;
	private static final int WINDOW_SIZE_Y = 800;
	
	private static final int CELL_COUNT = 9;
	
	private static final int CELL_GAP = 9;
	
	private static final Color BG = Color.BLACK;
	private static final Color CELL_COLOR = Color.WHITE;
	
	public GameUI(){
		initializeUI();
		
	}
	
	private void initializeUI(){
		
		gameFrame = new JFrame("Sudoku");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setLayout(new BorderLayout());
		
		
		gameBoardPanel = new JPanel();
		gameBoardPanel.setLayout(new GridLayout(CELL_COUNT, CELL_COUNT));
		
		for(int i = 0; i < CELL_COUNT; i++){
			for(int j = 0; j < CELL_COUNT;j++) {
				
			JPanel cell = new JPanel();
			cell.setBackground(CELL_COLOR);
			
			int top = (i % 3 == 0) ? 5 : 1;
        	int left = (j % 3 == 0) ? 5 : 1;
        	int bottom = (i == 8) ? 5 : 1;
        	int right = (j == 8) ? 5 : 1;

			
			cell.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
			gameBoardPanel.add(cell);
			}
		}
		
		numberDeckPanel = new JPanel();
		numberDeckPanel.setLayout(new GridLayout(1, 9));	
		numberDeckPanel.setBackground(Color.gray);
		
		for (int i = 1; i <= 9;  i++){
			
			JButton numberButton = new JButton(String.valueOf(i));
			numberButton.setFont(new Font("Broadway", Font.BOLD, 20));
			numberButton.setBackground(Color.GRAY);
			numberButton.setPreferredSize(new Dimension(WINDOW_SIZE_X / CELL_COUNT, WINDOW_SIZE_X / CELL_COUNT));
			numberButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			numberButton.setFocusPainted(false);
			numberButton.setContentAreaFilled(false);
			numberButton.setOpaque(true);
			numberDeckPanel.add(numberButton);
			
			numberButton.addMouseListener(new java.awt.event.MouseAdapter()
			{
				
				public void mousePressed(java.awt.event.MouseEvent evt) {
				numberButton.setBackground(Color.DARK_GRAY);
				}
				
				public void mouseReleased(java.awt.event.MouseEvent evt){
				numberButton.setBackground(Color.GRAY);
				}
				
			});	
		gameFrame.add(gameBoardPanel, BorderLayout.CENTER);
		gameFrame.add(numberDeckPanel, BorderLayout.SOUTH);
		gameFrame.setVisible(true);
		}
}
}












