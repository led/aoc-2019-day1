#Advent of Code 2019 - Day 1

## Quickstart
Assumes that you have shadow-cljs and clj-kondo installed.

Run tests: `make test`
Run the solution: `make run`

## Task
Taking the [AOC-2019](https://adventofcode.com/2019) coding puzzles and implementing [Day 1](https://adventofcode.com/2019/day/1) in clojure(script)
targetting the Node platform.


## Notes
The actual task is straightforward, but in terms of not having done *that* much node
programming, the interest (for me) is primarily in using an async style for processing
the data file rather than just a straight reduction over a sequence as I'd
do normally. I chose not to just pull in a library from npm for the line
processing and just implement one using node's core functions.

The AOC approach is nice as it gives you (some) tests up-front so that you can
quickly get into the TDD flow towards a solution.

Options for another time: create a browser version (fileReader interface,
drag-and-drop data file etc).
