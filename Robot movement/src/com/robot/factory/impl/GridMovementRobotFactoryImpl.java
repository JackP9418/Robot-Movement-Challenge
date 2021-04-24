package com.robot.factory.impl;

import com.robot.factory.RobotFactory;
import com.robot.robot.BaseRobot;
import com.robot.robot.GridMovementRobot;
import com.robot.robot.service.GridRobotService;
import com.robot.robot.service.RobotCommandService;

/**
 * Grid robot movement factory. Used to build and return the service for robot
 * 
 * between Robot and User.
 * 
 * @author Jack
 * @version 1.0 - created
 * 
 */

public class GridMovementRobotFactoryImpl implements RobotFactory {

	@Override
	public RobotCommandService buildRobotCommandService(Object[] params) {
		if (params == null || params.length < 2)
			return null;

		int maxCol = (int) params[0];
		int maxRow = (int) params[1];

		BaseRobot gridMovementRobot = new GridMovementRobot();
		RobotCommandService service = new GridRobotService(maxCol, maxRow, gridMovementRobot);
		return service;
	}

}
