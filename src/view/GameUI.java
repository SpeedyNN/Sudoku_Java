package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

import init.run;
import model.SudokuBoard;
import model.GameTimer;

public class GameUI extends JPanel
{
	private JFrame gameFrame;
	private JPanel topPanel;
	private JPanel finishedButtonPanel;
	private JLabel finishedButtonLabel;
	private JPanel gameBoardContainer;
	private JPanel gameBoardPanel;
	private JPanel numberDeckContainer;
	private JPanel numberDeckPanel;
	private JLabel cursorItem;
	
	private static final int WINDOW_SIZE_X = 800;
	private static final int WINDOW_SIZE_Y = 900;
	
	private static final int CELL_COUNT = 9;
	private static final int CELL_SIZE = 70;
	
	private static final Color BG = Color.GRAY;
	private static final Color CELL_COLOR = Color.WHITE;
	private static final Color PERM_CELL_FG = Color.BLACK;
	private static final Color CUSTOM_CELL_FG = new Color(20,127,126);
	private static final Color HOVERED_CELL_COLOR = Color.LIGHT_GRAY;
	private static final Color NUMBER_DECK_COLOR = Color.DARK_GRAY;
	
	private static final Color FINISHED_BUTTON_COLOR = new Color(177, 249, 164);
	private static final Color FINISHED_BUTTON_HOVER = new Color(201, 251, 192);
	private static final Color FINISHED_BUTTON_PRESSED = new Color(159, 200, 152);

	
	private int[][] sudokuBoard;

	private JLabel currentCursorItem = null; 
	private JPanel currentHoveredCell = null;
	
	private JPanel[][] cellGrid = new JPanel[CELL_COUNT][CELL_COUNT];	
	
	private List<JLabel> numberLabels = new ArrayList<>();

	
	public GameUI(SudokuBoard sudokuBoard){
		initializeUI();
		System.out.println(cellGrid);
		this.sudokuBoard = sudokuBoard.getGrid();
		displayGame();
	}
	
	private void initializeUI(){
		
		gameFrame = new JFrame("Sudoku");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setMinimumSize(new Dimension(WINDOW_SIZE_X, WINDOW_SIZE_Y));
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(true); 
		gameFrame.setLayout(new BorderLayout());
		
		topPanel = new JPanel(new GridBagLayout());
		topPanel.setBackground(BG);
		topPanel.setPreferredSize(new Dimension(WINDOW_SIZE_X, 60));
		
		finishedButtonLabel = new JLabel("Done", SwingConstants.CENTER);
		finishedButtonLabel.setFont(new Font("Broadway", Font.BOLD, 45));
		
		finishedButtonPanel = new JPanel();
		finishedButtonPanel.setBackground(FINISHED_BUTTON_COLOR);
		finishedButtonPanel.setForeground(Color.WHITE);
		finishedButtonPanel.setVisible(true);
		
		finishedButtonPanel.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				finishedButtonPanel.setBackground(FINISHED_BUTTON_HOVER);
			}
			public void mouseExited(MouseEvent arg0) {
				finishedButtonPanel.setBackground(FINISHED_BUTTON_COLOR);
			}
			public void mousePressed(MouseEvent arg0) {
				
				finishedButtonPanel.setBackground(FINISHED_BUTTON_PRESSED);
				
				//Calculate answer
				// IF BOARD IS FILLED MOVE TO THIS
				
				gameFrame.dispose();
				ResultUI resultUI = new ResultUI();
				
				switch (resultUI.selection)
				{
				// Try again
				case 0:
				
				break;
				// change difficulty
				case 1:
				run Run = new run();
				Run.main(null);
				break;
				
				// Exit
				case 2:
				break;
				
				default:
				System.err.println("Nothing was clicked so the game closed");
				break;
				}
			}

			public void mouseReleased(MouseEvent arg0) {
				
				finishedButtonPanel.setBackground(FINISHED_BUTTON_COLOR);

				//Calculate answer
			}
		});
		
		finishedButtonPanel.add(finishedButtonLabel);
		topPanel.add(finishedButtonPanel);
		
		
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
			cellGrid[i][j] = cell;
			//System.out.println("adding cell: " + cell + " to grid position: "+ i + " " + j);
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
				public void mousePressed(MouseEvent e) {
			        if (SwingUtilities.isRightMouseButton(e)) {
			            eraseCell(cell);
			        }
			    }
			});
			}
		}
		
		cursorItem = new JLabel("", SwingConstants.CENTER);
		cursorItem.setSize(CELL_SIZE, CELL_SIZE);
		cursorItem.setBackground(CELL_COLOR);
		cursorItem.setForeground(CUSTOM_CELL_FG);
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
			numberLabel.setForeground(CUSTOM_CELL_FG);
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
		gameFrame.add(topPanel, BorderLayout.NORTH);
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
		
		Component[] components = targetCell.getComponents();
	    for (Component comp : components) {
	        if (comp instanceof JLabel) {
	            JLabel existingLabel = (JLabel) comp;
	            if (existingLabel.getForeground().equals(PERM_CELL_FG)) {
	                System.err.println("Cannot overwrite permanent cell");
	                currentCursorItem = null;
	                cursorItem.setVisible(false);
	                return;
	            }
	        }
	    }
		
		System.out.println("dropping number: " + currentCursorItem.getText() + " into cell");
		targetCell.removeAll();
		
		JLabel numberLabel = new JLabel(currentCursorItem.getText(), SwingConstants.CENTER);
		numberLabel.setFont(new Font("Broadway", Font.BOLD, 45));
		numberLabel.setForeground(CUSTOM_CELL_FG);
		targetCell.add(numberLabel, BorderLayout.CENTER);
		
		targetCell.revalidate();
		targetCell.repaint();
		currentCursorItem = null;
		cursorItem.setVisible(false);
		
	}
	
	private void eraseCell(JPanel cell) {
	    // check if cell has temporary content (blue text)
	    Component[] components = cell.getComponents();
	    for (Component comp : components) {
	        if (comp instanceof JLabel) {
	            JLabel existingLabel = (JLabel) comp;
	            if (existingLabel.getForeground().equals(CUSTOM_CELL_FG)) {
	                cell.removeAll();
	                cell.revalidate();
	                cell.repaint();
	                System.err.println("Erased temporary number from cell");
	                return;
	            }
	        }
	    }
	    System.out.println("No temporary number to erase");
	}
	
	
	private void displayGame() {
		
		for(int i = 0; i < CELL_COUNT; i++) {
			for(int j = 0; j < CELL_COUNT; j++) {
				if(sudokuBoard[i][j] != 0) {
					JPanel targetCell = cellGrid[i][j];
					targetCell.removeAll();
					JLabel numberLabel = new JLabel(String.valueOf(sudokuBoard[i][j]), SwingConstants.CENTER);
					numberLabel.setFont(new Font("Broadway", Font.BOLD, 45));
					numberLabel.setForeground(PERM_CELL_FG);
					targetCell.add(numberLabel, BorderLayout.CENTER);
					targetCell.revalidate();
					targetCell.repaint();
				} 
			}
		}
		
	}
	
	public int[][] getCellGridAs2DArray(){
	    
	    int[][] result = new int[CELL_COUNT][CELL_COUNT];
	    
	    for(int i = 0; i < CELL_COUNT; i++) {
	        for(int j = 0; j < CELL_COUNT; j++) {
	            JPanel cell = cellGrid[i][j];
	            int cellValue = 0;
	            
	            Component[] components = cell.getComponents();
	            for (Component comp : components) {
	                if (comp instanceof JLabel) {
	                    JLabel label = (JLabel) comp;
	                    String text = label.getText();
	                    try {
	                        cellValue = Integer.parseInt(text);
	                    } catch (NumberFormatException e) {
	                        cellValue = 0;
	                    }
	                    break; 
	                }
	            }
	            
	            result[i][j] = cellValue;
	        }
	    }
	    
	    return result;
	}
}