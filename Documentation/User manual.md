# User manual
This is a guide to using this application.

Note: The size and mines of the minefield can be changed
in the class Board with the following variables:\
`N_MINES`\
`N_COLS`\
`N_ROWS`

## Features
This application has very simplistic features.
 - Normal minesweeper game mechanics
 - AI helper to solve the game

### How to play
Left click to reveal the contents of a tile. If it's a mine, you lose.

Right click to flag a tile.

Left click on a numbered tile to use chording.\
--> Chording will automatically reveal tiles, if all adjacent mines have been found.

Mouse wheel click to use AI.\
--> AI will solve one step of the game for you.\
--> If the AI cannot find a safe move, it will not guess.\
--> If a guess is required, it will do nothing until the player makes a move.

Note: The game will not end until all mines have been flagged. Remember to flag all mines.

### Auto clicker tool
Since repeatedly clicking to invoke the AI can be annoying,
there is an included Auto Clicker made with AutoHotkey.
To use it, run the AutoClicker.exe and scroll the mouse wheel
in the minesweeper game. To exit script press Escape or Ctrl + R.\
Uncompiled version is also available.

## How to launch the application
### Running the application
You can run the application by using the command\
`gradle run`\
in a command prompt or a terminal.

### Building the jar
You can build a jar file from the command line
with the following command:\
`gradle jar`

It does not produce a working version of the application however.
The application builds the interface with image resources, and
getting those to work with the built jar file has been unsuccesful
so far.
