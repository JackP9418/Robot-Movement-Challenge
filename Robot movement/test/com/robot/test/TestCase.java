package com.robot.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.robot.robot.BaseRobot;
import com.robot.robot.GridMovementRobot;
import com.robot.robot.service.RobotCommandService;
import com.robot.util.AppConstants;
import com.robot.util.AppConstants.Robot.Direction;

class TestCase {

	@Test
	void testMovement() {
		RobotCommandService service = getRobotCommandService(null);

		// test 1
		testCase1(service);
		service.executeCommand(AppConstants.FixedUserInput.CLEAR_STATE_INPUT);
		assetResult(-1, -1, Direction.UNKNOWN, service);

		// test 2
		testCase2(service);
	}

	// OUTPUT: 3,3,SOUTH
	void testCase1(RobotCommandService service) {
		List<String> instructionList = new ArrayList<String>();
		instructionList.add("PLACE 3,3,EAST");
		instructionList.add("MOVE"); // 4,3,EAST
		instructionList.add("RIGHT");// 4,3,SOUTH
		instructionList.add("LEFT");// 4,3,EAST
		instructionList.add("LEFT");// 4,3,NORTH
		instructionList.add("MOVE");// 4,4,NORTH
		instructionList.add("MOVE");// INVALID
		instructionList.add("LEFT");// 4,4,WEST
		instructionList.add("MOVE");// 3,4,WEST
		instructionList.add("LEFT");// 3,4,SOUTH
		instructionList.add("MOVE");// 3,3,SOUTH
		instructionList.add("REPORT");

		for (String instruction : instructionList) {
			try {
				service.executeCommand(instruction);
			} catch (Exception e) {
				// nothing
			}

		}
		assetResult(3, 3, Direction.SOUTH, service);
	}

	// OUTPUT: 3,3 EAST
	void testCase2(RobotCommandService service) {
		List<String> instructionList = new ArrayList<String>();
		instructionList.add("PLACE 4,4,NORTH");
		instructionList.add("MOVE"); // INVALID
		instructionList.add("RIGHT");// 4,4,EAST
		instructionList.add("LEFT");// 4,4,NORTH
		instructionList.add("LEFT");// 4,4,WEST
		instructionList.add("MOVE");// 3,4,WEST
		instructionList.add("MOVE");// 2,4,WEST
		instructionList.add("LEFT");// 2,4,SOUTH
		instructionList.add("MOVE");// 2,3,SOUTH
		instructionList.add("LEFT");// 2,3,EAST
		instructionList.add("MOVE");// 3,3,EAST
		instructionList.add("REPORT");

		for (String instruction : instructionList) {
			try {
				service.executeCommand(instruction);
			} catch (Exception e) {
				// nothing
			}

		}
		assetResult(3, 3, Direction.EAST, service);
	}
    
	// goes along the outside and back to 0,0 facing west
	void testCase3(RobotCommandService service) {
		List<String> instructionList = new ArrayList<String>();
		instructionList.add("PLACE 0,0,NORTH");
		instructionList.add("MOVE"); // 0,1,NORTH
		instructionList.add("MOVE"); // 0,2,NORTH
		instructionList.add("MOVE"); // 0,3,NORTH
		instructionList.add("MOVE"); // 0,4,NORTH
		
		instructionList.add("RIGHT");// 0,4,EAST
		instructionList.add("MOVE"); // 1,4,EAST
		instructionList.add("MOVE"); // 2,4,EAST
		instructionList.add("MOVE"); // 3,4,EAST
		instructionList.add("MOVE"); // 4,4,EAST
		
		instructionList.add("RIGHT");// 4,4,SOUTH
		instructionList.add("MOVE"); // 4,3,SOUTH
		instructionList.add("MOVE"); // 4,2,SOUTH
		instructionList.add("MOVE"); // 4,1,SOUTH
		instructionList.add("MOVE"); // 4,0,SOUTH
		
		
		instructionList.add("RIGHT");// 4,0,WEST
		instructionList.add("MOVE"); // 3,0,WEST
		instructionList.add("MOVE"); // 2,2,WEST
		instructionList.add("MOVE"); // 1,1,WEST
		instructionList.add("MOVE"); // 0,0,WEST
		instructionList.add("REPORT");

		for (String instruction : instructionList) {
			try {
				service.executeCommand(instruction);
			} catch (Exception e) {
				// nothing
			}

		}
		assetResult(0, 0, Direction.WEST, service);
	}

	private void assetResult(int expectedCol, int expectedRow, Direction expectedDirection,
			RobotCommandService service) {
		Object[] currPos = ((GridRobotServiceTestHelper) service).getCurrRobotPos();
		int curCol = (int) currPos[0];
		int curRow = (int) currPos[1];
		Direction curDirection = (Direction) currPos[2];

		assertEquals(expectedCol, curCol, "Current Column Position is Different");
		assertEquals(expectedRow, curRow, "Current Row Position is Different");
		assertEquals(expectedDirection, curDirection, "Current Direction is Different");
	}

	private static RobotCommandService getRobotCommandService(String agrs[]) {
		// can based on args values to create service for robot in the future
		// for now just based on the args to capture the table dimension.

		BaseRobot gridMovementRobot = new GridMovementRobot();
		RobotCommandService service = new GridRobotServiceTestHelper(5, 5, gridMovementRobot);
		return service;
	}

}
