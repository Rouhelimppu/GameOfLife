package gol.panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JPanel;

import gol.algorithm.NeighbourAlgorithm;
import gol.grid.GolRectangle;
import gol.label.StatisticLabel;

public class GamePanel extends JPanel implements MouseListener {

	private static final long serialVersionUID = 5108778839313765121L;
	private static final String DEAD_STATUS = "All dead";
	private static final String EQUILIBRIUM_STATUS = "Equilibrium";
	private static final String STAGNANT_STATUS = "Stagnant";
	private static final String PROGRESSING_STATUS = "Progressing";
	
	private List<GolRectangle> fillGrid;
	private List<GolRectangle> grid;
	private List<GolRectangle> previousList;
	private boolean gameStarted;
	private Integer rows;
	private Integer columns;
	private StatisticLabel statsReader;
	private int evolution;
	
	public GamePanel() {
		addMouseListener(this);
		this.gameStarted = false;
		this.grid = new ArrayList<>();
		this.fillGrid = new ArrayList<>();
		this.previousList = new ArrayList<>();
		this.evolution = 0;
	}
	
	public void resetEvolution() {
		evolution = 0;
	}
	
	public void setStatsReader(StatisticLabel statsReader) {
		this.statsReader = statsReader;
	}
	
	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}
	
	public boolean getGameStarted() {
		return gameStarted;
	}
	
	private Optional<Integer> getRows() {
		return Optional.ofNullable(rows);
	}
	
	private Optional<Integer> getColumns() {
		return Optional.ofNullable(columns);
	}
	
	public void resetGridAndFillGrid(int rows, int columns) {
		if (!gameStarted) {
			this.grid = new ArrayList<>();
			this.fillGrid = new ArrayList<>();
			setGrid(rows, columns, Optional.empty());
		}
	}
	
	private void setGrid(int rows, int columns, Optional<Point> pointer) {
		this.grid = new ArrayList<>();
		
		this.rows = rows;
		this.columns = columns;
		
		int rectWidth = getSize().width / columns;
		int rectHeight = getSize().height / rows;
		
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				GolRectangle shape = new GolRectangle(column, row, column * rectWidth, row * rectHeight, rectWidth, rectHeight);
				grid.add(shape);
				
				pointer.ifPresent(point -> {
					if (shape.contains(point)) {
						if (fillGrid.contains(shape)) {
							fillGrid.remove(shape);
						} else {
							fillGrid.add(shape);
						}
					}
				});
			}
		}
		
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.red);
		fillGrid.forEach(cell -> g2d.fill(cell));
		
		g2d.setColor(Color.black);
		grid.forEach(cell -> g2d.draw(cell));
	}
	
	public void runGol() throws InterruptedException {
		evolution++;
		List<GolRectangle> newFillGrid = new ArrayList<>();
		newFillGrid = NeighbourAlgorithm.getDeadAndLiving(fillGrid, grid);
		statsReader.setAliveCount(newFillGrid.size());
		statsReader.setEvolutions(evolution);
		
		if (newFillGrid.equals(previousList)) {
			statsReader.setStatus(STAGNANT_STATUS);
			statsReader.setStatistics();
			throw new InterruptedException(STAGNANT_STATUS);
		}
		
		if (newFillGrid.equals(fillGrid)) {
			statsReader.setStatus(EQUILIBRIUM_STATUS);
			statsReader.setStatistics();
			throw new InterruptedException(EQUILIBRIUM_STATUS);
		}
		
		statsReader.setStatus(PROGRESSING_STATUS);
		statsReader.setStatistics();
		previousList = fillGrid;
		this.fillGrid = newFillGrid;
		repaint();
		
		if (this.fillGrid.isEmpty()) {
			statsReader.setStatus(DEAD_STATUS);
			statsReader.setStatistics();
			throw new InterruptedException(DEAD_STATUS);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!gameStarted && getRows().isPresent() && getColumns().isPresent()) {
			setGrid(rows, columns, Optional.of(e.getPoint()));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
