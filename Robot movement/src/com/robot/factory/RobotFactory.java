package com.robot.factory;

import com.robot.robot.BaseRobot;
/**
 * Robot factory interface, can be implemented to
 * have different types of robot factory
 * @author Jack
 * version 1.0 - created.
 */
public interface RobotFactory {
	public BaseRobot buildRobot(Object[] params);
	
}
