package com.robot.robot;

import com.robot.exception.InvalidCommandException;
import com.robot.robot.command.BaseCommand;
import com.robot.robot.command.ReportCommand;

public abstract class BaseRobot {
	public BaseCommand executeCommand(BaseCommand command) {
		if(command == null || !isCommandSupported(command)) {
			BaseCommand errorCommand = new ReportCommand();
			String error = "Command is null,";
			
			if(command != null)
				error = command.getOriginalInputCommand();
				
			errorCommand.setException(new InvalidCommandException(error));
			return errorCommand;
		}
		
		return innerExecuteCommand(command);
	}

	protected abstract boolean isCommandSupported(BaseCommand command);

	protected abstract BaseCommand innerExecuteCommand(BaseCommand command);
}
