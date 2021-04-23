package com.robot.util;

public class AppConstants {
	public class MenuItem{
		// choices
		public static final int INSTRUCTION_CHOICE = 1;
		public static final int EXIT_APP_CHOICE = 2;
		
		// choice description
		public static final String INSTRUCTION_DESCRIPTION = "1 - Send command to Robot";
		public static final String INSTRUCTION_MSG = "Please enter command to send to robot";
		
		public static final String EXIT_APP_DESCRIPTION= "2 - Exit app";
		public static final String EXIT_APP_MSG = "Please enter command to send to robot";
	}
	
	public class Message {
		public static final String SELECT_MENU_MSG = "Enter number to select from choices from the menu above ";
		public static final String INVALID_INPUT_ERR_MSG = "%s is not a valid choice. Please try again.";
	}
}
