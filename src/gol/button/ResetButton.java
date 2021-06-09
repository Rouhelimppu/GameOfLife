package gol.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gol.gridselector.CellComboBox;
import gol.panels.GamePanel;

public class ResetButton extends JButton implements ActionListener {

	private static final long serialVersionUID = -4356871317080703618L;
	
	CellComboBox comboBox;
	GamePanel panel;
	
	public ResetButton() {
		addActionListener(this);
	}
	
	public void setGetGridFrom(CellComboBox box) {
		this.comboBox = box;
	}
	
	public void setListeningPanel(GamePanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.resetGridAndFillGrid(comboBox.getGrid().getRows(), comboBox.getGrid().getColumns());
	}

}
