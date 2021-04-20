# Introduction

This project made for interview purposes.

# Robot movement

This application takes specified user input and compiled into specific commands and send to the robot to be moving within 5 x 5 dimension table.

# Getting started

To get the program running, navigate to **/Robot movement/build** folder and execute the command below

```bash
java -jar RobotMovement.jar
```

## Menu and commands

After executed the command above. The following texts will display:

```bash
***** ROBOT TRACKING APP *****
1 - Send command to Robot
2 - Import robot commands
3 - Reset robot state
4 - Exit app
Enter number to select from choices from the menu above
```

### Menu

You can select the menu option by entering number which correspond to the item display. Below is the explaination of each menu item

- **Send command to robot** - this menu is to get to interact with robot by sending a single command to the robot.
- **Import robot commands** - import the text file contains list of commands. See Sample Test files within **/Robot movement/test** for example.
- **Reset robot state** - this will reset the current state of the robot including removing the robot from the table.

### Type of Commands

The following commands are available to the robot:

- **PLACE X,Y,F** - place robot on the table at X , Y coordinates and facing F direction. this must be the initial comand before subsequence comands can be executed.
- **MOVE** - move forward 1 value to which direction the robot is facing.
- **RIGHT** or **LEFT** - turn the robot 90 degree to specified side.

- **REPORT** - print the current robot's position of X, Y coordinates and the direction its facing.
