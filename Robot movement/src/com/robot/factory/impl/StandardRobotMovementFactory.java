package com.robot.factory.impl;

import com.robot.factory.RobotFactory;
import com.robot.robot.BaseRobot;
import com.robot.robot.GridMovementRobot;
import com.robot.robot.processing.RobotMovementProcessingUnit;
import com.robot.robot.processing.RobotProcessingUnit;

/**
 * Robot movement factory, responsible for building the robot and their
 * commands.
 * 
 * between Robot and User.
 * 
 * @author Jack
 * @version 1.0 - created
 * 
 */

public class StandardRobotMovementFactory implements RobotFactory {

	@Override
	public BaseRobot buildRobot(Object[] params) {
		if (params == null || params.length < 2)
			return null;

		int maxCol = (int) params[0];
		int maxRow = (int) params[1];

		BaseRobot gridMovementRobot = new GridMovementRobot();
		gridMovementRobot.setProcessingUnit(buidlGridProcessingUnit(maxCol, maxRow));

		return gridMovementRobot;
	}

	private RobotProcessingUnit buidlGridProcessingUnit(int maxCol, int maxRow) {
		RobotProcessingUnit processingUnit = new RobotMovementProcessingUnit(maxCol, maxRow);
		return processingUnit;
	}

}
