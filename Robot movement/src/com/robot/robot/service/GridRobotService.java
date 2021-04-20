package com.robot.robot.service;

import com.robot.exception.InvalidCommandException;
import com.robot.exception.RobotExecutionException;
import com.robot.robot.BaseRobot;
import com.robot.robot.command.BaseCommand;
import com.robot.robot.command.ClearStateCommand;
import com.robot.robot.command.LeftCommand;
import com.robot.robot.command.MoveCommand;
import com.robot.robot.command.PlaceCommand;
import com.robot.robot.command.ReportCommand;
import com.robot.robot.command.RightCommand;
import com.robot.util.AppConstants;
import com.robot.util.AppConstants.Robot.CommandType;
import com.robot.util.AppConstants.Robot.Direction;
import com.robot.util.IntUtil;

public class GridRobotService implements RobotCommandService {
	protected BaseRobot robot;
	private int maxCol, maxRow;

	public GridRobotService(int col, int row, BaseRobot robot) {
		this.robot = robot;
		this.setMaxCol(col);
		this.setMaxRow(row);
	}

	@Override
	public String executeCommand(String command) {
		BaseCommand result = this.robot.executeCommand(buildRobotCommand(command));
		if (result.getException() == null) {
			return (String) result.getCommandResult();
		} else {
			throw result.getException();
		}
	}

	public BaseCommand buildRobotCommand(String commandStr) {
		// at this point the view ensure us with no empty input
		// now check for the input if it is matches any command below.
		String sanitisedCommand = commandStr.trim().replace(" ", "").toUpperCase();
		BaseCommand command = null;
		CommandType type;
		try {
			type = CommandType.valueOf(sanitisedCommand);
			switch (type) {
			case MOVE:
				command = new MoveCommand();
				break;
			case LEFT:
				command = new LeftCommand();
				break;
			case RIGHT:
				command = new RightCommand();
				break;
			case REPORT:
				command = new ReportCommand();
				break;
			case CLEAR:
				command = new ClearStateCommand();
				break;
			default: // do nothing
				break;
			}
		} catch (IllegalArgumentException e) {
			// do nothing here just yet
			type = CommandType.UNKNOWN;
		}

		// if command still null then process further.
		if (command == null) {
			// try to extract PLACE command
			if (sanitisedCommand.contains(AppConstants.FixedUserInput.PLACE_INPUT)) {
				// first convert original to upper case and remove the word place
				String coordinates = commandStr.toUpperCase().replace(AppConstants.FixedUserInput.PLACE_INPUT, "");
				// then remove the empty space just in case
				coordinates = coordinates.replace(" ", "");
				// then split it into array
				String[] coorArray = coordinates.split(",");
				if (coorArray.length < 3 || coorArray.length > 3) {
					throw new InvalidCommandException(commandStr);
				}
				// extract x and y coordinates
				int xCoor = IntUtil.parse(coorArray[0], -1);
				int yCoor = IntUtil.parse(coorArray[1], -1);

				if (xCoor == -1 || yCoor == -1) {
					throw new InvalidCommandException(commandStr);
				}

				if (xCoor > maxCol || xCoor < 0 || yCoor > maxRow || yCoor < 0) {
					throw new RobotExecutionException(
							String.format("X,Y coordinates cannot be less than 0 or > %d,%d", maxCol, maxRow));
				}

				// extract direction
				Direction direction = Direction.UNKNOWN;
				try {
					direction = Direction.valueOf(coorArray[2]);
				} catch (IllegalArgumentException e) {
					throw new InvalidCommandException(commandStr);
				}

				command = new PlaceCommand(maxCol, maxRow, xCoor, yCoor, direction);

			} else {
				throw new InvalidCommandException(commandStr);
			}
		}

		command.setOriginalInputCommand(commandStr);
		return command;
	}

	public void setMaxCol(int maxCol) {
		this.maxCol = maxCol - 1;
	}

	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow - 1;
	}

}
