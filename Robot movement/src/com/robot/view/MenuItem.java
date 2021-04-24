package com.robot.view;

/**
 * Menu item class, contains information about the item
 * and instruction how to interact with the item.
 * 
 * @author Jack
 * @version 1.0 - created
 * 
 */
public class MenuItem {
	private int itemChoice;
	private String itemDescription;
	private String instruction;
	private boolean isLoopingInstruction;
	private boolean requireUserInput;
	
	public MenuItem(int choice, String description, String instruction) {
		this.itemChoice = choice;
		this.itemDescription = description;
		this.instruction = instruction;
	}
	
	public int getItemChoice() {
		return itemChoice;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public String getInstructions() {
		return instruction;
	}

	public boolean isLoopingInstruction() {
		return isLoopingInstruction;
	}

	public void setLoopingInstruction(boolean isLoopingInstruction) {
		this.isLoopingInstruction = isLoopingInstruction;
	}

	public boolean isRequireUserInput() {
		return requireUserInput;
	}

	public void setRequireUserInput(boolean requireUserInput) {
		this.requireUserInput = requireUserInput;
	}
	
	
	
	

}
