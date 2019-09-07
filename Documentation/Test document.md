# Performance
The AI is very fast. It is much faster than what is necessary for it's use case.
It will solve the game as far as possible without guessing in the blink of an eye.

## Manual testing
Manual testing was performed for the game as if a real user was using the software.
I made a script with AutoHotkey that clicks the middle mouse button (AI button)
500 times / second. Next step is to reveal tiles randomly until the AI gains a foothold.

The results from this manual testing can be seen here:\
[YouTube: Minesweeper performance](https://www.youtube.com/watch?v=_HSj1aSjk3o&feature=youtu.be)

This is not the maximum speed of solving though. The application just starts to lag with
quicker inputs. If these solving steps were to be repeated within the application itself,
the solver would most likely be much faster.

## Ability
Through testing it seems the AI can find almost all solutions, and
can solve almost all minesweeper games, as long as it doesn't need to
make guesses. Situations in which the AI cannot make the correct move
have been mentioned [here](https://github.com/SirVeggie/tiralabra-minesweeper-ai/blob/master/Documentation/Implementation.md#improvements).\
The AI also never makes a mistake (clicking on a mine) due to bugs or
any other reason.

## Optimization
In its present state, the algorithm is not very well optimized.
It uses many inefficient actions especially in interpreting
the matrix that is generated and solved from the minefield's state.

The matrix is manipulated into a row echelon form. This is not
the most reduced from however. It is simply easier to write an
algorithm that deduces mine placement information from the
semi-solved form of the matrix. This is because in the
semi-solved form all values are integers. After reducing the
matrix, most numbers will be decimal numbers instead.\
To get all possible information out of the matrix, there is still
a need to compare the rows of the matrix with each other. This
multiplies the amount needed to interpret the matrix by a factor.

A more efficient method would most likely be to manipulate the 
matrix into the reduced row echelon form, from which to extract
information. With a proper interpretation algorithm and
a reduced row form the solver would most likely be much faster.

## JUnit testing
Here we can see the overall JUnit test coverage.

![Test coverage](/Documentation/Images/test_report.png)

Various classes are tested quite extensively, however
the variability of matrix calculation testing is a bit lacking.
In structures, hashmap and entry have not been implemented fully yet,
so their non existent tests are pulling the average down.