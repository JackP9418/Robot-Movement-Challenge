package com.robot.robot;

import com.robot.exception.RobotExecutionException;
import com.robot.robot.command.BaseCommand;
import com.robot.robot.command.PlaceCommand;
import com.robot.robot.command.ReportCommand;
import com.robot.util.AppConstants.Robot.CommandType;
import com.robot.util.AppConstants.Robot.Direction;

public class GridMovementRobot extends BaseRobot {
	private int currRow, currCol;
	private int maxRow, maxCol;
	private Direction currDirection = Direction.UNKNOWN;

	@Override
	protected boolean isCommandSupported(BaseCommand command) {
		return command.getType() != CommandType.UNKNOWN;
	}

	@Override
	protected BaseCommand innerExecuteCommand(BaseCommand command) {
		if (command.getType() != CommandType.PLACE) {
			if (!isPlaced()) {
				return buildErrorCommand("Robot has not been placed on table yet");
			}
			switch (command.getType()) {
			case MOVE:
				switch (this.currDirection) {
				case WEST:
				case EAST:
					return moveXCoordinate();
				case NORTH:
				case SOUTH:
					return moveYCoodinate();
				default:
					return buildErrorCommand("Unkown Direction");
				}
			case LEFT:
			case RIGHT:
				return turnRobot(command.getType());
			case REPORT:
				return reportCurrentPosition();
			case CLEAR:
				return clearCurrentState();
			default:
				return buildErrorCommand("Unknown Command Type");
			}
		} else {
			return initialiseRobotPosition(command);
		}
	}

	private BaseCommand moveXCoordinate() {
		int nextCol = this.currCol;
		if (this.currDirection == Direction.EAST) {
			nextCol++;
		} else {
			nextCol--;
		}
		if (nextCol < 0 || nextCol > maxCol) {
			return buildErrorCommand("Cannot move to specified X coordinate");
		}
		this.currCol = nextCol;
		return new ReportCommand();
	}

	private BaseCommand moveYCoodinate() {
		int nextRow = this.currRow;
		if (this.currDirection == Direction.NORTH) {
			nextRow++;
		} else {
			nextRow--;
		}
		if (nextRow < 0 || nextRow > maxRow) {
			return buildErrorCommand("Cannot move to specified Y coordinate");
		}
		this.currRow = nextRow;
		return new ReportCommand();
	}

	private BaseCommand reportCurrentPosition() {
		String currPos = String.format("OUTPUT: %d, %d, %s", this.currCol, this.currRow, this.currDirection.name());
		BaseCommand command = new ReportCommand();
		command.setCommandResult(currPos);
		return command;
	}

	private BaseCommand turnRobot(CommandType type) {
		switch (this.currDirection) {
		case SOUTH:
			switch (type) {
			case LEFT:
				this.currDirection = Direction.EAST;
				break;
			case RIGHT:
				this.currDirection = Direction.WEST;
				break;
			default: // do nothing
			}
			break;
		case WEST:
			switch (type) {
			case LEFT:
				this.currDirection = Direction.SOUTH;
				break;
			case RIGHT:
				this.currDirection = Direction.NORTH;
				break;
			default: // do nothing
			}
			break;
		case NORTH:
			switch (type) {
			case LEFT:
				this.currDirection = Direction.WEST;
				break;
			case RIGHT:
				this.currDirection = Direction.EAST;
				break;
			default: // do nothing
			}
			break;
		case EAST:
			switch (type) {
			case LEFT:
				this.currDirection = Direction.NORTH;
				break;
			case RIGHT:
				this.currDirection = Direction.SOUTH;
				break;
				
			default: // do nothing
			}
			break;
		default:
			break;
		}

		return new ReportCommand();
	}

	private BaseCommand initialiseRobotPosition(BaseCommand command) {
		PlaceCommand placeCommand = (PlaceCommand) command;

		this.currCol = placeCommand.getPlacedCol();
		this.currRow = placeCommand.getPlacedRow();
		this.maxRow = placeCommand.getMaxRow();
		this.maxCol = placeCommand.getMaxCol();

		this.currDirection = placeCommand.getPlacedDirection();

		ReportCommand resultCommand = new ReportCommand();
		resultCommand.setCommandResult(String.format(" Robot is placed at %d,%d and is facing %s", this.currCol,
				this.currRow, this.currDirection.name()));
		return resultCommand;
	}

	private boolean isPlaced() {
		return this.currCol > -1 && this.currRow > -1 && this.currDirection != Direction.UNKNOWN;
	}

	private BaseCommand buildErrorCommand(String message) {
		BaseCommand errorCommand = new ReportCommand();
		errorCommand.setException(new RobotExecutionException(message));
		return errorCommand;
	}
	
	private BaseCommand clearCurrentState() {
		this.currCol = -1;
		this.currRow = -1;
		this.maxCol = -1;
		this.maxRow = -1;
		this.currDirection = Direction.UNKNOWN;
		return new ReportCommand();
	}
	
	// only for testing
	public Object[] getCurPositions() {
		return new Object[]{this.currCol, this.currRow, this.currDirection};
	}

}
