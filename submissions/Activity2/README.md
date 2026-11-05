# Activity 2: Maze traversal

MazeDemo creates one random 15x15 interior maze and runs DFS followed by BFS.
Maze adds a border and random obstacles and selects distinct start/finish cells.
DFS uses the custom array stack; BFS uses the custom FIFO queue.
Cells are marked discovered before insertion to prevent duplicate work.
Each discovered cell records its previously visited spot. 

On success, the solver follows
these links from finish to start and marks the path for display.
Search state is reset between searches; the maze layout stays the same.
DFS finds a route, while BFS finds the shortest route.
Both searches report failure when the finish is unreachable. 

***Random generation
does not guarantee a solvable maze.***

Legend: S = start, F = finish, + = solution path, - = discovered, blocks = walls.