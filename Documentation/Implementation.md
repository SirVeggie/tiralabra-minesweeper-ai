# Implementation
At first the plan was to semi- brute force all possible
combinations for the current situation. This however is
a very time consuming process, so I searched for a better
alternative.

## Algorithm
### Using linear algebra
From a minefield, we can extract series of linear equations.
These equations consist of a numbered tile and its adjacent
unsolved tiles. The unsolved tiles are the variables, and
the number is the equation's answer.\
When we have multiple of these equations, we can solve for it
in a system of linear equations.

### Matrices
One way to solve a system of linear equations is to use a matrix.
We can assign the linear equations into the matrix like so:

`x1 + x2 = 1`\
`x1 + x2 + x3 = 2`\
`x2 + x3 + x4 = 2`

1 1 0 0 | 1\
1 1 1 0 | 2\
0 1 1 1 | 2

### Interpretation
If we look at the matrix from before, we can make some conclusions.\
Let's subtract row 1 from row 2:

1 1 0 0 | 1\
0 0 1 0 | 1\
0 1 1 1 | 2

Now we can see that the value of `x3` is 1. This means `x3` is a mine.
As long as we know the real location of `x3` on the minefield, we can
mark it with a flag. Conversely we can see if a spot is safe:

1 1 0 0 | 1\
0 0 1 0 | 0\
0 1 1 1 | 2

In this situation the value of `x3` is 0. So `x3` is safe.

## Flow of the AI's functions
![flowchart](/Documentation/Images/MinesweeperAI_flow.png)

## O-Analysis
`n = size of the minefield`\
`w = matrix width`\
`h = matrix height`

The maximum width or height for the matrix is maybe about 2/3*n.

### Time complexity
90% if not more of a normal difficulty game is solved by the
Local Solver.\
Local solver's time complexity is O(8n)

Complex solver solves the other situations that Local cannot solve.\
Its time complexity is roughly O(n + w * h<sup>2</sup>)

### Space complexity
Local Solver: O(n)

Complex Solver roughly: O(n + w * h<sup>2</sup>)

## Improvements
### Mine awareness
Currently the algorithm can solve most situations, but there are
some that it cannot solve. For example if a tile hidden behind
flagged tiles, the algorithm will never come in contact with it.
However, for example if that tile is the last one remaining,
a human player can deduce that it is a mine.\
Essentially the algorithm is unable to draw any conclusions
based on the remaining amount of mines unlike humans.

### Dealing with less than ideal situations
The algorithm only makes moves that are 100% safe. Meaning it
will never guess based on chance. In a future version of the
algorithm, a guessing feature could be implemented. The AI
would calculate the odds of being a mine for the tiles, and then
click the tile that is most likely not a mine. This way the AI
could play a game all by itself, unlike currently when it
still needs human help.

### AI mechanic implementation
If a user wants to only use the AI, it is very cumbersome.
Despite several tries, I was unable to implement the AI in
a more flexible way due to the base game not being made by me.\
In the future the base minesweeper functionality should be
rewritten from scratch with a better way to implement the AI.

## Sources
The method of solving a mineweeper field was taken from here:\
[Solving Minesweeper with Matrices](https://massaioli.wordpress.com/2013/01/12/solving-minesweeper-with-matricies/)

