package com.robot.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.robot.util.AppConstants;

/**
 * View class for user. Responsible for printing
 * menu items onto the console. It also contains
 * the list of menu item. Can be de-constructed further
 * to have a Menu factory to build but we only interact
 * with one type of menu for now
 * 
 * @author Jack
 * @version 1.0 - created
 */
public class UIMenu {
	private Map<Integer, MenuItem> menuMap;
	private BufferedReader reader;

	public void printMainMenu() {
		buildMainMenuItems();
		this.printHeader();
		this.printMenuItems();
		this.reader = new BufferedReader(new InputStreamReader(System.in));
	}

	public String captureUserInput(String message) {
		boolean finished = false;
		String input = null;

		System.out.println(message);
		while (!finished) {
			try {
				input = reader.readLine();
				finished = validateInput(input);

			} catch (IOException e) {
				System.err.println("Exception while trying to catch input");
				e.printStackTrace();
				finished = true;
			}
		}
		return input;
	}

	
	public MenuItem getMenuItem(int choice) {
		return this.menuMap.get(choice);
	}
	
	public void printMessage(String message) {
		System.out.println(message);
	}
	
	public void printErrorMessage(String message) {
		System.err.println(message);
		System.out.println();
	}	

	public void closeView() {
		try {
			this.reader.close();
			this.reader = null;
		} catch (IOException e) {
			// do nothing
		}
	}
	
	private void printMenuItems() {
		menuMap.values().forEach(item -> {
			System.out.println(item.getItemDescription());
		});
	}

	private void buildMainMenuItems() {
		if (this.menuMap == null)
			this.menuMap = new HashMap<Integer, MenuItem>();

		this.menuMap.clear();

		menuMap.put(AppConstants.MenuItem.INSTRUCTION_CHOICE,
				new MenuItem(AppConstants.MenuItem.INSTRUCTION_CHOICE, AppConstants.MenuItem.INSTRUCTION_DESCRIPTION, AppConstants.MenuItem.INSTRUCTION_MSG));
		menuMap.put(AppConstants.MenuItem.EXIT_APP_CHOICE,
				new MenuItem(AppConstants.MenuItem.EXIT_APP_CHOICE, AppConstants.MenuItem.EXIT_APP_DESCRIPTION, AppConstants.MenuItem.EXIT_APP_MSG));
	}

	private void printHeader() {
		System.out.println("***** ROBOT TRACKING APP *****");
	}

    private boolean validateInput(String input) {
		return (input != null && !input.isEmpty() && input.trim().length() > 0);
	}
}
