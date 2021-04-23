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

}
