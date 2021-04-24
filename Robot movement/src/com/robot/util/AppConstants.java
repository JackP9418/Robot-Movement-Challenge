package com.robot.util;

/**
 * Developer's Repository of constants.
 * @author Jack
 * @version 1.0
 */
public class AppConstants {
	
	public class FixedUserInput {
		public static final String EXIT_INPUT = "EXIT";
		public static final String PLACE_INPUT = "PLACE";
		public static final String CLEAR_STATE_INPUT = "CLEAR";
	}
	
	public class MenuItem{
		// choices
		public static final int INSTRUCTION_CHOICE = 1;
		public static final int UPLOAD_CHOICE = 2;
		public static final int CLEAR_STATE_CHOICE = 3;
		public static final int EXIT_APP_CHOICE = 4;
		
		// choice description
		public static final String INSTRUCTION_DESCRIPTION = INSTRUCTION_CHOICE + " - Send command to Robot";
		public static final String INSTRUCTION_MSG = "Please enter command to send to robot or type EXIT to return to main menu";
		
		public static final String UPLOAD_CHOICE_DESCRIPTION = UPLOAD_CHOICE + " - Import robot commands";
		public static final String UPLOAD_CHOICE_MSG = "Please specify full command file path, only .TXT file is supported. Type EXIT to return to main menu";
		
		public static final String CLEAR_STATE_DESCRIPTION = CLEAR_STATE_CHOICE + " - Reset robot state";
		
		public static final String EXIT_APP_DESCRIPTION= EXIT_APP_CHOICE +" - Exit app";
		public static final String EXIT_APP_MSG = "**** GOODBYE *******";
	}
	
	public class Message {
		public static final String SELECT_MENU_MSG = "Enter number to select from choices from the menu above ";
		public static final String INVALID_INPUT_ERR_MSG = "%s is not a valid choice. Please try again.";
		public static final String SERVICE_ABSENT_ERR = "Robot service is absent";
		public static final String INVALID_FILE_ERR = "The file either does not exist, empty or not supported.";
	}
	
	public static class Robot{
		public enum Direction{
			UNKNOWN,
			SOUTH,
			WEST,
			NORTH,
			EAST
		}
		public enum CommandType{
			UNKNOWN,
			CLEAR,
			PLACE,
			MOVE,
			LEFT,
			RIGHT,
			REPORT
		}
	}
}
