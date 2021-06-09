package gol.gridselector;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import gol.grid.Grid;
import gol.panels.GamePanel;

@SuppressWarnings("rawtypes")
public class CellComboBox extends JComboBox implements ActionListener {

	private static final long serialVersionUID = 2690756797676988196L;
	
	private GamePanel panel;
	
	@SuppressWarnings({ "unchecked" })
	public CellComboBox(DefaultComboBoxModel model) {
		super(model);
		addActionListener(this);
	}
	
	public Grid getGrid() {
		return (Grid) this.getSelectedItem();
	}
	
	public void setListeningPanel(GamePanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Grid grid = (Grid) this.getSelectedItem();
		panel.resetGridAndFillGrid(grid.getRows(), grid.getColumns());
	}
	
}
