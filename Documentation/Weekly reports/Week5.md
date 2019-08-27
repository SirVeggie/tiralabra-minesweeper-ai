# Week 5 Report

Still haven't been able to catch up to schedule by much.

Started doing more tests.

Refactored the code a bit and added function combineArrays
for some current code and for the future **plan**

Even though I still haven't started complex solving,
I have thought about how to make it in my head.
So there is already a plan, now I have to realise it.

## The plan
### Search
First search for all connected strips of unrevealed tiles
that are bordering revealed tiles.
### Generator and Validator
Generate a possible state of mines for a single strip.
Then validate the state using the relevant revealed numbers.
The relevant numbers will most likely be stored in a single
dummy field of sorts, while the generated states will
be stored in other dummy fields of some sort where a mine is
marked as 1 and empty as 0.
### Combination
When a state for a strip has been succesfully validated,
it will be added into a single array, as seen in the example:

1st. state:	0001010010
2nd. state:	0100110011
result:		0101120021

When all valid states have been added together,
we get the needed information easily: If there is a spot
with 0 remaining, that means not a single state had a mine there.
Therefore we know it is safe to open. If there is a spot where
the number equals the amount of valid states, it means all states
had a mine there. Therefore we know for certain there is a mine there.

## Questions
Can the math functions be used as is, for example min()?
Or should I make my own math class?