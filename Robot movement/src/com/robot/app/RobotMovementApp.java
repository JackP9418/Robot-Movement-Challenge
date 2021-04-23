package com.robot.app;

import com.robot.util.AppConstants;
import com.robot.util.IntUtil;
import com.robot.view.MenuItem;
import com.robot.view.UIMenu;

public class RobotMovementApp {
	private int col, row;
	private UIMenu view;

	public RobotMovementApp(int col, int row) {
		this.col = col;
		this.row = row;
		this.view = new UIMenu();
	}

	public void startApp() {
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

			userFinished = (selectedItem.getItemChoice() == AppConstants.MenuItem.EXIT_APP_CHOICE);
			if (userFinished)
				continue;

			// Handle menu item here

		}
	}
}
