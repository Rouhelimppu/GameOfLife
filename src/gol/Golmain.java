package gol;

import java.awt.Canvas;
import java.awt.EventQueue;

import javax.swing.JFrame;

import gol.run.RunCanvas;

public class Golmain extends Canvas {

	private static final long serialVersionUID = -8187410920660501074L;

	public static void main(String[] args) {
		new Golmain("GoL");
	}
	
	public Golmain(String name) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame mainFrame = new JFrame(name);
				RunCanvas canvas = new RunCanvas(mainFrame);
				canvas.drawMainCanvas();
			}
		});
	}
	
}
