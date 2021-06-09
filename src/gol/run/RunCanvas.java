package gol.run;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gol.button.ResetButton;
import gol.button.StartButton;
import gol.globals.Globals;
import gol.grid.Grid;
import gol.gridselector.CellComboBox;
import gol.label.StatisticLabel;
import gol.panels.GamePanel;

public class RunCanvas {
	private JFrame mainFrame;
	GridBagLayout gridBagLayout = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	
	private GamePanel gamePanel = new GamePanel();
	private JPanel controlPanel = new JPanel();
	private List<Grid> grid = new ArrayList<>();
	
	@SuppressWarnings({ })
	private DefaultComboBoxModel<Grid[]> model;
	private StartButton startButton = new StartButton();
	private ResetButton resetButton = new ResetButton();
	private StatisticLabel statisticLabel = new StatisticLabel();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RunCanvas(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		grid.add(new Grid(4, 4));
		grid.add(new Grid(8, 10));
		grid.add(new Grid(20, 20));
		grid.add(new Grid(40, 40));
		grid.add(new Grid(80, 70));
		grid.add(new Grid(100, 100));
		grid.add(new Grid(120, 120));
		grid.add(new Grid(200, 200));
		
		Grid[] grids = new Grid[grid.size()];
		model = new DefaultComboBoxModel(grid.toArray(grids));
	}
	
	public void drawMainCanvas() {
		setGamePanel();
		setControlPanel();
		mainFrame.add(gamePanel);
		mainFrame.add(controlPanel);
		mainFrame.setSize(gamePanel.getWidth(), controlPanel.getHeight() + gamePanel.getHeight());
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setGamePanel() {
		gamePanel.setBackground(Color.white);
		gamePanel.setBounds(0, Globals.CONTROL_PANEL_HEIGHT, Globals.FRAME_WIDTH, Globals.GAME_PANEL_HEIGHT);
		gamePanel.resetGridAndFillGrid(grid.get(0).getRows(), grid.get(0).getColumns());
		gamePanel.setStatsReader(statisticLabel);
	}
	
	private void setControlPanel() {
		controlPanel.setLayout(gridBagLayout);
		controlPanel.setBackground(Color.LIGHT_GRAY);
		controlPanel.setBounds(0, 0, Globals.FRAME_WIDTH, Globals.CONTROL_PANEL_HEIGHT);
		
		CellComboBox comboBox = new CellComboBox(model);
		startButton.setListeningPanel(gamePanel);
		resetButton.setListeningPanel(gamePanel);
		resetButton.setGetGridFrom(comboBox);
		
		startButton.setText("Start");
		resetButton.setText("Reset");
		statisticLabel.setStatistics();
		
		comboBox.setListeningPanel(gamePanel);
		setConstraints(0, 0, comboBox);
		setConstraints(1, 0, startButton);
		setConstraints(2, 0, resetButton);
		setConstraints(0, 1, statisticLabel);
		
		controlPanel.add(comboBox);
		controlPanel.add(startButton);
		controlPanel.add(resetButton);
		controlPanel.add(statisticLabel);
	}
	
	private void setConstraints(int x, int y, Component t) {
		gbc.gridx = x;
		gbc.gridy = y;
		gridBagLayout.setConstraints(t, gbc);
	}
}
