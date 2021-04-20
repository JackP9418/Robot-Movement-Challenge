package com.robot.robot.command;

import com.robot.util.AppConstants.Robot.CommandType;

public abstract class BaseCommand {
	
	private RuntimeException exception;
	private Object commandResult;
	private String originalInputCommand;
	private CommandType type;

	public BaseCommand(CommandType type) {
		this.type = type;
	}
	
	public RuntimeException getException() {
		return exception;
	}

	public void setException(RuntimeException exception) {
		this.exception = exception;
	}

	public Object getCommandResult() {
		return commandResult;
	}

	public void setCommandResult(Object commandResult) {
		this.commandResult = commandResult;
	}

	public String getOriginalInputCommand() {
		return originalInputCommand;
	}

	public void setOriginalInputCommand(String originalInputCommand) {
		this.originalInputCommand = originalInputCommand;
	}

	public CommandType getType() {
		return type;
	}

	public void setType(CommandType type) {
		this.type = type;
	}
	
	

}
