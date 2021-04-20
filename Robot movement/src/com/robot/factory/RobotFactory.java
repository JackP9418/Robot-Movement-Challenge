package com.robot.factory;

import com.robot.robot.service.RobotCommandService;

/**
 * Robot factory interface, can be implemented to have different types of robot
 * factory
 * 
 * @author Jack version 1.0 - created.
 */
public interface RobotFactory {
	public RobotCommandService buildRobotCommandService(Object[] params);

}
