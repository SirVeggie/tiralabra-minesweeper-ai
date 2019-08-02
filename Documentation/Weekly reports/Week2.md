
- Created the gradle project.

- Forked a Minesweeper clone from 
[janbodnar](https://github.com/janbodnar/Java-Minesweeper-Game).

- Wrote the requirements specification.

However there were some things that were hard to write about. 
I have no idea which specific algorithms should be used to solve minesweeper. 
Based on this it is very hard to estimate the time and space complexities.

I am also unsure if my project is challenging/complex enough for this course.


The current plan of implementation is that the Minesweeper game will call 
the AI class' function. It will receive back info on where to click, and 
then click there. After clicking on those tiles, the game board will update 
and the AI class will be called again with the updated game state 
repeating this cycle.

