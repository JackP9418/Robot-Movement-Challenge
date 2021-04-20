package com.robot.main;

import com.robot.app.RobotMovementApp;
import com.robot.factory.RobotFactory;
import com.robot.factory.impl.GridMovementRobotFactoryImpl;
import com.robot.robot.service.RobotCommandService;

public class Main {

	public static void main(String agrs[]) {
		RobotCommandService service = getRobotCommandService(agrs);
		RobotMovementApp app = new RobotMovementApp(service);
		app.startApp();
	}

	private static RobotCommandService getRobotCommandService(String agrs[]) {
		// can based on args values to create service for robot in the future
		// for now just based on the args to capture the table dimension.
		
		RobotFactory factory = new GridMovementRobotFactoryImpl();
		return factory.buildRobotCommandService(new Object[] {5,5});
	}
}
