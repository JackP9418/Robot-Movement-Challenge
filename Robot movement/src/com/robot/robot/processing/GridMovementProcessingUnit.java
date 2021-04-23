package com.robot.robot.processing;

public class GridMovementProcessingUnit implements RobotProcessingUnit {

	private int maxCol, maxRow;

	public GridMovementProcessingUnit(int col, int row) {
		setMaxCol(col);
		setMaxRow(row);
	}

	private void setMaxCol(int col) {
		this.maxCol = col - 1;
	}

	private void setMaxRow(int row) {
		this.maxRow = row - 1;
	}

}
