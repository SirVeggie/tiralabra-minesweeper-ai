# Requirements Specification

Inspiration for the minesweeper AI taken from here:
https://magnushoff.com/minesweeper/

### Explanation

Problem: Find out safe places to click or locations of mines given a minesweeper game state.

Solution: Check all possible combinations and see if there's anything in common. However this needs to be optimized.

Input will be an integer array of the minefield.

Returns an array where safe spots(1) and mines/flags(2) are marked. For example:

`000001000022000100100`

## Complexities

These are calculated for a single 'round' of solving.

### Local solving

Unlike Global, Local complexities are easier to estimate.

Local solving goes through the entire field while also checking all 8 adjacent tiles.

**Time complexity**

Time complexity for local solving should be about O(n), where n is the size of the field.

**Space complexity**

Algorithm holds the entire input and output fields in memory, so space complexity is O(2k), where k is the size of the field.

### Global solving

Generating all the possible combinations of mine locations is very time and memory consuming.

Worst case:

**Time complexity**

If calculating one combination is constant, then the time complexity is O(s), where:

![alt text](/Documentation/Images/Time%20complexity.png)

n = size of the field

k = number of mines
