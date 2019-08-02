
Inspiration for the minesweeper AI taken from here:

https://magnushoff.com/minesweeper/


Problem: Find out safe places to click or locations of mines given a minesweeper game state.

Solution: Check all possible combinations and see if there's anything in common. However this needs to be optimized.


Input will be an integer array of the minefield.

Returns an array where safe spots(1) and mines(2) are marked. For example:

`000001000022000100100`

Generating all the possible combinations of mine locations is very time and memory consuming.