package gol.algorithm;

import java.util.ArrayList;
import java.util.List;

import gol.grid.GolRectangle;

public class NeighbourAlgorithm {

	public static List<GolRectangle> getDeadAndLiving(List<GolRectangle> livingCell, List<GolRectangle> grid) {	
		List<GolRectangle> newFillGridList = new ArrayList<>();
		
		for (GolRectangle cell : grid) {
			int aliveNeighbours = 0;
			for (GolRectangle neighbour : livingCell) {
				if (cell.isNeighbourTo(neighbour.getGridPositionX(), neighbour.getGridPositionY())) {
					aliveNeighbours++;
				}
			}
			
			if (aliveNeighbours == 3) {
				if (!livingCell.contains(cell)) {
					newFillGridList.add(cell);	
				}
			}
			
			if (aliveNeighbours >= 2 && aliveNeighbours <= 3) {
				if (livingCell.contains(cell)) {
					newFillGridList.add(cell);	
				}
			}
		}
		
		return newFillGridList;
	}
}
