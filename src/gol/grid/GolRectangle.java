package gol.grid;

import java.awt.Rectangle;

public class GolRectangle extends Rectangle {

	private static final long serialVersionUID = 7271143675275130512L;
	
	private int gridPositionX;
	private int gridPositionY;
	
	public GolRectangle(int gridPositionX, int gridPositionY, int x, int y, int width, int height) {
		super(x, y, width, height);
		this.gridPositionX = gridPositionX;
		this.gridPositionY = gridPositionY;
	}

	public int getGridPositionX() {
		return gridPositionX;
	}

	public void setGridPositionX(int gridPositionX) {
		this.gridPositionX = gridPositionX;
	}

	public int getGridPositionY() {
		return gridPositionY;
	}

	public void setGridPositionY(int gridPositionY) {
		this.gridPositionY = gridPositionY;
	}
	
	public boolean isNeighbourTo(int otherX, int otherY) {
		if (gridPositionX == otherX && gridPositionY == otherY) {
			return false;
		}
		
		return (gridPositionX + 1 == otherX || gridPositionX - 1 == otherX || gridPositionX == otherX) &&
				(gridPositionY + 1 == otherY || gridPositionY - 1 == otherY || gridPositionY == otherY);
	}
}
