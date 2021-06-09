package gol.label;

import javax.swing.JLabel;

public class StatisticLabel extends JLabel {
	
	private static final long serialVersionUID = 4926258335130317160L;

	private static final String STATISTIC = "Statistics:";
	private String status;
	private String aliveCount;
	private String evolutions;
	
	public StatisticLabel() {
		this.status = "";
		this.aliveCount = "";
		this.evolutions = "";
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setAliveCount(int aliveCount) {
		this.aliveCount = "Alive: " + aliveCount;
	}
	
	public void setEvolutions(int evolutions) {
		this.evolutions = "Evolution: " + evolutions;
	}
	
	public void setStatistics() {
		this.setText(toString());
	}
	
	public String toString() {
		return "<html><body>" + STATISTIC + "<br>" + status + "<br>" + aliveCount + "<br>" + evolutions + "<body></html>";
	}
}
