package com.robot.view;

public class MenuItem {
	private int itemChoice;
	private String itemDescription;
	private String instructions;
	
	public MenuItem(int choice, String description, String instructions) {
		this.itemChoice = choice;
		this.itemDescription = description;
		this.instructions = instructions;
	}
	
	public int getItemChoice() {
		return itemChoice;
	}
	public void setItemChoice(int itemChoice) {
		this.itemChoice = itemChoice;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
}
