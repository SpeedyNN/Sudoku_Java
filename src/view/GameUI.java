package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GameUI extends JPanel
{
	private JFrame gameFrame;
	private JPanel gameBoardContainer;
	private JPanel gameBoardPanel;
	private JPanel numberDeckContainer;
	private JPanel numberDeckPanel;
	private JLabel cursorItem;
	
	private static final int WINDOW_SIZE_X = 800;
	private static final int WINDOW_SIZE_Y = 800;
	
	private static final int CELL_COUNT = 9;
	private static final int CELL_SIZE = 70;
	
	private static final Color BG = Color.GRAY;
	private static final Color CELL_COLOR = Color.WHITE;
	private static final Color HOVERED_CELL_COLOR = Color.LIGHT_GRAY;
	private static final Color NUMBER_DECK_COLOR = Color.DARK_GRAY;

	private JLabel currentCursorItem = null; 
	private JPanel currentHoveredCell = null;
	private List<JLabel> numberLabels = new ArrayList<>();

	
	public GameUI(){
		initializeUI();
		displayGame();
	}
	
	private void initializeUI(){
		
		gameFrame = new JFrame("Sudoku");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setMinimumSize(new Dimension(WINDOW_SIZE_X, WINDOW_SIZE_Y));
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(true); 
		gameFrame.setLayout(new BorderLayout());
		
		gameBoardContainer = new JPanel(new GridBagLayout());
		gameBoardContainer.setBackground(BG);
		
		int boardSize = CELL_SIZE * CELL_COUNT;
		
		gameBoardPanel = new JPanel();
		gameBoardPanel.setLayout(new GridLayout(CELL_COUNT, CELL_COUNT));
		gameBoardPanel.setPreferredSize(new Dimension(boardSize, boardSize));
		
		gameBoardContainer.add(gameBoardPanel);
		
		for(int i = 0; i < CELL_COUNT; i++){
			for(int j = 0; j < CELL_COUNT;j++) {
				
			JPanel cell = new JPanel(new BorderLayout());
			cell.setBackground(CELL_COLOR);
			cell.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
			
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
		
		cursorItem = new JLabel("", SwingConstants.CENTER);
		cursorItem.setSize(CELL_SIZE, CELL_SIZE);
		cursorItem.setBackground(CELL_COLOR);
		cursorItem.setOpaque(true);
		cursorItem.setFont(new Font("Broadway", Font.BOLD, 45));
		cursorItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		cursorItem.setVisible(false);
		
		gameFrame.setGlassPane(new JComponent() {
			{
				setLayout(null);
				add(cursorItem);
				setOpaque(false);
			}
		});
		gameFrame.getGlassPane().setVisible(true);
		
		numberDeckContainer = new JPanel(new GridBagLayout()); 
		numberDeckContainer.setBackground(NUMBER_DECK_COLOR);
		numberDeckContainer.setPreferredSize(new Dimension(WINDOW_SIZE_X, CELL_SIZE));
		
		numberDeckPanel = new JPanel();
		numberDeckPanel.setLayout(new GridLayout()); 
		int deckWidth = CELL_SIZE * CELL_COUNT;
		numberDeckPanel.setPreferredSize(new Dimension(deckWidth, CELL_SIZE));
		
		numberDeckContainer.add(numberDeckPanel);

		for (int i = 1; i <= 9;  i++){
			
			JLabel numberLabel = new JLabel(String.valueOf(i), SwingConstants.CENTER);
			numberLabel.setFont(new Font("Broadway", Font.BOLD, 45));
			numberLabel.setBackground(CELL_COLOR);
			numberLabel.setForeground(Color.BLACK);
			numberLabel.setOpaque(true);
			numberLabel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
			numberLabel.setMinimumSize(new Dimension(CELL_SIZE, CELL_SIZE));
			numberLabel.setMaximumSize(new Dimension(CELL_SIZE, CELL_SIZE));
			numberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			
			numberLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					pickUpNumber(numberLabel);
					updateCursorPosition(e);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					dropNumberIntoCell(currentHoveredCell);
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					numberLabel.setBackground(HOVERED_CELL_COLOR);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					numberLabel.setBackground(CELL_COLOR);
				}
			});
			
			numberLabel.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					updateCursorPosition(e);
				}
			});
			
			numberDeckPanel.add(numberLabel);
			numberLabels.add(numberLabel);
		}
		
		gameFrame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				updateCursorPosition(e);
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				updateCursorPosition(e);
			}
		});

		gameFrame.add(gameBoardContainer, BorderLayout.CENTER);
		gameFrame.add(numberDeckContainer, BorderLayout.SOUTH);
		gameFrame.setVisible(true);
	}
	
	private void updateCursorPosition(MouseEvent e) {
		if (currentCursorItem != null && cursorItem.isVisible()) {
			Point glassPanePoint = e.getPoint();
			SwingUtilities.convertPointToScreen(glassPanePoint, e.getComponent());
			SwingUtilities.convertPointFromScreen(glassPanePoint, gameFrame.getGlassPane());
			
			int x = glassPanePoint.x - CELL_SIZE / 2;
			int y = glassPanePoint.y - CELL_SIZE / 2;
			
			cursorItem.setLocation(x, y);
			gameFrame.getGlassPane().repaint();
		}
	}
	
	private void pickUpNumber(JLabel numberLabel) {
		if(currentCursorItem != null) return;
		System.out.println("picking up number: " + numberLabel.getText());
		currentCursorItem = numberLabel;
		
		cursorItem.setText(numberLabel.getText());
		cursorItem.setVisible(true);
		
		Point mousePos = numberLabel.getMousePosition();
		if (mousePos != null) {
			SwingUtilities.convertPointToScreen(mousePos, numberLabel);
			SwingUtilities.convertPointFromScreen(mousePos, gameFrame.getGlassPane());
			cursorItem.setLocation(mousePos.x - CELL_SIZE / 2, mousePos.y - CELL_SIZE / 2);
		}
	}
	
	private void dropNumberIntoCell(JPanel targetCell) {
		if(targetCell == null || currentCursorItem == null) {
			currentCursorItem = null;
			cursorItem.setVisible(false);
			System.err.println("no cell detected"); 
			return;
		}
		
		System.out.println("dropping number: " + currentCursorItem.getText() + " into cell");
		targetCell.removeAll();
		
		JLabel numberLabel = new JLabel(currentCursorItem.getText(), SwingConstants.CENTER);
		numberLabel.setFont(new Font("Broadway", Font.BOLD, 45));
		targetCell.add(numberLabel, BorderLayout.CENTER);
		
		targetCell.revalidate();
		targetCell.repaint();
		currentCursorItem = null;
		cursorItem.setVisible(false);
	}
	
	private void displayGame() {
		// implementation for displaying the game
	}
}