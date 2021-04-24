package com.robot.test;

import com.robot.robot.BaseRobot;
import com.robot.robot.GridMovementRobot;
import com.robot.robot.service.GridRobotService;

public class GridRobotServiceTestHelper extends GridRobotService {

	public GridRobotServiceTestHelper(int col, int row, BaseRobot robot) {
		super(col, row, robot);
	}
	
	public Object[] getCurrRobotPos() {
		return ((GridMovementRobot)this.robot).getCurPositions();
	}

}
