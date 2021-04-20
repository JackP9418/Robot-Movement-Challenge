package com.robot.app;

import com.robot.exception.InvalidCommandException;
import com.robot.exception.RobotExecutionException;
import com.robot.exception.UserExitException;
import com.robot.robot.service.RobotCommandService;
import com.robot.util.AppConstants;
import com.robot.util.FileUtil;
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
	private UIMenu view;
	private RobotCommandService robotService;

	public RobotMovementApp(RobotCommandService service) {
		this.view = new UIMenu();
		this.robotService = service;
	}

	public void startApp() {
		if (robotService != null)
			commenceUserInteracttion();
		else {
			System.out.println(AppConstants.Message.SERVICE_ABSENT_ERR);
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

			userFinished = (selectedItem.getItemChoice() == AppConstants.MenuItem.EXIT_APP_CHOICE);
			if (userFinished) {
				view.printMessage(selectedItem.getInstructions());
				continue;
			}

			handleSelectedMenuItem(selectedItem);
		}
	}

	private void handleSelectedMenuItem(MenuItem item) {
		boolean loopUntilExit = item.isLoopingInstruction();
		do {
			String command = "";
			if (item.isRequireUserInput()) {
				command = view.captureUserInput(item.getInstructions());
			}
			if (command.trim().toUpperCase().equalsIgnoreCase(AppConstants.FixedUserInput.EXIT_INPUT)) {
				loopUntilExit = false;
				continue;
			}

			switch (item.getItemChoice()) {
			case AppConstants.MenuItem.INSTRUCTION_CHOICE:
				loopUntilExit = sendRobotCommand(command) && loopUntilExit;
				break;
			case AppConstants.MenuItem.UPLOAD_CHOICE:
				String[] instructions = FileUtil.readFileContent(command, "TXT");
				if (instructions != null)
					performBulkInstructions(instructions);
				else
					view.printErrorMessage(AppConstants.Message.INVALID_FILE_ERR);
				break;
			case AppConstants.MenuItem.CLEAR_STATE_CHOICE:
				loopUntilExit = sendRobotCommand(AppConstants.FixedUserInput.CLEAR_STATE_INPUT) && loopUntilExit;
				break;
			}
		} while (loopUntilExit);
	}

	private boolean sendRobotCommand(String command) {
		boolean shouldLoop = true;
		try {
			String message = this.robotService.executeCommand(command);
			if (message != null && !message.isEmpty() && message.trim().length() > 0)
				view.printMessage(message);
		} catch (RuntimeException e) {
			if (e instanceof UserExitException) {
				shouldLoop = false;
			} else if (e instanceof RobotExecutionException) {
				view.printErrorMessage(e.getMessage());
			} else if (e instanceof InvalidCommandException) {
				view.printErrorMessage(e.getMessage());
			} else {
				shouldLoop = false;
				view.printErrorMessage(e.getMessage());
			}
		}
		return shouldLoop;
	}

	public void performBulkInstructions(String[] instructions) {
		for (String instruction : instructions)
			sendRobotCommand(instruction);
	}
}
