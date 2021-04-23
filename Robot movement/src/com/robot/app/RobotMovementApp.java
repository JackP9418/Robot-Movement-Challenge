package com.robot.app;

import com.robot.factory.RobotFactory;
import com.robot.factory.impl.StandardRobotMovementFactory;
import com.robot.robot.BaseRobot;
import com.robot.util.AppConstants;
import com.robot.util.IntUtil;
import com.robot.view.MenuItem;
import com.robot.view.UIMenu;

/**
 * Main application. the middle man responsible for interacting between Robot
 * and User.
 * 
 * @author Jack
 * @version 1.0 - created
 * 
 */
public class RobotMovementApp {
	private int col, row;

	private UIMenu view;
	private RobotFactory factory;
	private BaseRobot robot;

	public RobotMovementApp(int col, int row) {
		this.col = col;
		this.row = row;
		this.view = new UIMenu();
	}

	public void startApp() {
		if (initialiseRobot())
			commenceUserInteracttion();
		else {
			System.out.println(AppConstants.Message.INITIALISE_ROBOT_ERR);
		}
	}

	private void commenceUserInteracttion() {
		boolean userFinished = false;
		while (!userFinished) {
			view.printMainMenu();
			String input = view.captureUserInput(AppConstants.Message.SELECT_MENU_MSG);
			int inputChoice = IntUtil.parse(input, -1);

			if (inputChoice == -1) {
				view.printErrorMessage(String.format(AppConstants.Message.INVALID_INPUT_ERR_MSG, input));
				continue;
			}

			MenuItem selectedItem = view.getMenuItem(inputChoice);
			if (selectedItem == null) {
				view.printErrorMessage(String.format(AppConstants.Message.INVALID_INPUT_ERR_MSG, input));
				continue;
			}

			// tell view to print instruction first
			view.printMessage(selectedItem.getInstructions());

			userFinished = (selectedItem.getItemChoice() == AppConstants.MenuItem.EXIT_APP_CHOICE);
			if (userFinished)
				continue;

			handleSelectedMenuItem(selectedItem);
		}
	}

	private void handleSelectedMenuItem(MenuItem item) {
		try {
			// handle robot run time here
		} catch (RuntimeException e) {
			view.printErrorMessage(e.getMessage());
		}
	}

	private boolean initialiseRobot() {
		this.factory = new StandardRobotMovementFactory();
		this.robot = factory.buildRobot(new Object[]{this.col, this.row});
		return this.robot != null;
	}
}
