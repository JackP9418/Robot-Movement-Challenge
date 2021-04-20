package com.robot.robot.command;

import com.robot.util.AppConstants.Robot.CommandType;
import com.robot.util.AppConstants.Robot.Direction;

public class PlaceCommand extends BaseCommand{
	private int maxCol, maxRow;
	private int placedCol, placedRow;
	private Direction placedDirection;
	
	public PlaceCommand(int maxCol, int maxRow, int placedCol, int placedRow, Direction placedDirection) {
		super(CommandType.PLACE);
		this.maxCol = maxCol;
		this.maxRow = maxRow;
		this.placedCol = placedCol;
		this.placedRow = placedRow;
		this.placedDirection = placedDirection;
	}
	
	public int getMaxCol() {
		return maxCol;
	}
	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol;
	}
	public int getMaxRow() {
		return maxRow;
	}
	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}
	public int getPlacedCol() {
		return placedCol;
	}
	public void setPlacedCol(int placedCol) {
		this.placedCol = placedCol;
	}
	public int getPlacedRow() {
		return placedRow;
	}
	public void setPlacedRow(int placedRow) {
		this.placedRow = placedRow;
	}
	public Direction getPlacedDirection() {
		return placedDirection;
	}
	public void setPlacedDirection(Direction placedDirection) {
		this.placedDirection = placedDirection;
	}
	
	
}
