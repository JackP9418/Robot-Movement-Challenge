package com.robot.exception;

public class InvalidCommandException extends RuntimeException {
	public InvalidCommandException(String command) {
		super(String.format("%s is unknown command", command));
	}
}
