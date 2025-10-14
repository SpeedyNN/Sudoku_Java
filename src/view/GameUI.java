package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
	private static final Color HOVERED_CELL_COLOR = Color.LIGHT_GRAY;

	private JButton currentCursorItem = null; 
	private JPanel currentHoveredCell = null;
	private List<JButton> numberButtons = new ArrayList<>();

	
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
			cell.setFont(new Font("Broadway", Font.BOLD, 45));
			
			int top = (i % 3 == 0) ? 5 : 1;
        	int left = (j % 3 == 0) ? 5 : 1;
        	int bottom = (i == 8) ? 5 : 1;
        	int right = (j == 8) ? 5 : 1;
			
			cell.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
			gameBoardPanel.add(cell);

			cell.addMouseListener(new MouseAdapter() {
				
				public void mouseEntered(MouseEvent arg0) {
					currentHoveredCell = cell;
					cell.setBackground(HOVERED_CELL_COLOR);
				}
				
				public void mouseExited(MouseEvent arg0) {
					currentHoveredCell = null;
					cell.setBackground(CELL_COLOR);
				}
				
			});
			
			}
		}
		
		numberDeckPanel = new JPanel();
		numberDeckPanel.setLayout(new GridLayout(1, 9));	
		numberDeckPanel.setBackground(Color.gray);
		

		// Create the number buttons, add to numberdeckpanel, and add them to a list for future reference
		for (int i = 1; i <= 9;  i++){
			
			JButton numberButton = new JButton(String.valueOf(i));
			numberButton.setFont(new Font("Broadway", Font.BOLD, 45));
			numberButton.setBackground(Color.GRAY);
			numberButton.setPreferredSize(new Dimension(WINDOW_SIZE_X / CELL_COUNT, WINDOW_SIZE_X / CELL_COUNT));
			numberButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			numberButton.setFocusPainted(false);
			numberButton.setContentAreaFilled(false);
			numberButton.setOpaque(true);
			numberDeckPanel.add(numberButton);
			
			numberButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					pickUpNumber(numberButton);
				}
				public void mouseReleased(MouseEvent arg0) {
					dropNumberIntoCell(currentHoveredCell);
				}
			});
		} 

		gameFrame.add(gameBoardPanel, BorderLayout.CENTER);
		gameFrame.add(numberDeckPanel, BorderLayout.SOUTH);
		gameFrame.setVisible(true);
}
	
	
	
	
	private void pickUpNumber(JButton numberButton) {
		
		if(currentCursorItem != null) return;
		System.out.println("picking up number: " + numberButton.getText());
		currentCursorItem = numberButton;
		
	}
	
	private void dropNumberIntoCell(JPanel targetCell) {
		
		if(targetCell == null || currentCursorItem == null) {
			currentCursorItem = null;
			System.err.println("no cell detected"); 
			return;
			}
		
		System.out.println("dropping number: " + currentCursorItem.getText() + " into cell");
		targetCell.removeAll();
		targetCell.add(new JLabel(currentCursorItem.getText()));
		targetCell.revalidate();
		targetCell.repaint();
		currentCursorItem = null;
		
	}

}











