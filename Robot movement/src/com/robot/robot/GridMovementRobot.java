package com.robot.robot;

import com.robot.util.AppConstants.Robot.Direction;

public class GridMovementRobot extends BaseRobot {

	private int currRow, currCol;
	private Direction currDirection;

	public Object[] getCurrentPosition() {
		return new Object[] { this.currCol, this.currRow, this.currDirection };
	}

	public void executeCommand() {
		// TODO: implement
	}
}
