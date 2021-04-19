package com.robot.robot;

import com.robot.robot.processing.RobotProcessingUnit;

public abstract class BaseRobot {
	private RobotProcessingUnit processingUnit;
	
	public void executeCommand() {
		
	}
	
	
	public void setProcessingUnit(RobotProcessingUnit unit) {
		this.processingUnit = unit;
	}
	
	public abstract Object[] getCurrentPosition();
}
