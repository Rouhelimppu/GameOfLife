package gol.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import gol.globals.Globals;
import gol.panels.GamePanel;

public class StartButton extends JButton implements ActionListener, Runnable {

	private static final long serialVersionUID = -4650018960617322689L;
	
	private Thread gameThread;
	private GamePanel panel;
	private static String START = "Start";
	private static String STOP = "Stop";
	
	public StartButton() {
		addActionListener(this);
	}
	
	public void setListeningPanel(GamePanel panel) {
		this.panel = panel;
	}
	
	private void setButtonText(String text) {
		this.setText(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gameThread = new Thread(this);
		if (this.getText().equals(START)) {
			setButtonText(STOP);
			panel.setGameStarted(true);
			gameThread.start();
		} else {
			setButtonText(START);
			panel.setGameStarted(false);
			gameThread.interrupt();
		}
	}

	@Override
	public void run() {
		panel.resetEvolution();
		while(panel.getGameStarted()) {
			try {
				panel.runGol();
				Thread.sleep(Globals.THREAD_SLEEP_TIME);
			} catch (InterruptedException e) {
				panel.setGameStarted(false);
				setButtonText(START);
				return;
			}
		}
	}

}
