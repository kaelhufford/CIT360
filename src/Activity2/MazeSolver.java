package Activity2;


// DFS
import AbstractDataTypes.Stacks.Stack;

//BFS
import Queues.QueueA;

public class MazeSolver {

    public void solveDFS(Maze maze){
        // Reset the maze for traversal again
        maze.resetMaze();

        Stack<MazeSpaceObject> stack = new Stack<>(maze.getRows() * maze.getColumns());

        // Get maze start
        MazeSpaceObject start = maze.getStart();
        start.setVisited(true);
        stack.push(start);

        while(!stack.isEmpty()){

            // Remove most recent cell
            MazeSpaceObject current = stack.pop();

            // If the finish is found, back track.
            if (current.isFinish()){
                MazeSpaceObject pathCell = current;

                while(pathCell != null){
                    // Set it for visual, get the previous
                    pathCell.setPath(true);
                    pathCell = pathCell.getPrevious();
                }

                System.out.println("\n============= DFS RESULT =============");
                maze.print();
                System.out.println("S Start | F Finish | + Path | - Discovered\n");
                return;
            }

            int row = current.getY();
            int column = current.getX();

            if (row > 0) { //? NORTH  =================================
                MazeSpaceObject north = maze.getCell(row - 1, column);

                if(!north.isWall() && !north.hasVisited()){
                    // Mark it visited
                    north.setVisited(true);
                    // Set the prev of north, to current
                    north.setPrevious(current);
                    //Save for later.
                    stack.push(north);
                }
            }
            //? EAST =================================================
            if (column < maze.getColumns() - 1) {
                // East is the same row, one column to the right
                MazeSpaceObject east = maze.getCell(row, column + 1);
                
                if(!east.isWall() && !east.hasVisited()){
                    // Mark it visited
                    east.setVisited(true);
                    // Set the prev of east, to current
                    east.setPrevious(current);
                    //Save for later.
                    stack.push(east);
                }
            }
            //? SOUTH =================================================
            if (row < maze.getRows() - 1) {
                // South is the same column, one row down
                MazeSpaceObject south = maze.getCell(row + 1, column);
                
                if(!south.isWall() && !south.hasVisited()){
                    // Mark it visiteed
                    south.setVisited(true);
                    // Set the prev of south, to current
                    south.setPrevious(current);
                    //Save for later.
                    stack.push(south);
                }
            }
            //? WEST =================================================
            if (column > 0) {
                // West is the same row, one column to the left
                MazeSpaceObject west = maze.getCell(row, column - 1);
                
                if(!west.isWall() && !west.hasVisited()){
                    // Mark it visited
                    west.setVisited(true);
                    // Set the prev of west, to current
                    west.setPrevious(current);
                    //Save for later.
                    stack.push(west);
                }
            }
        }

        System.out.println("No path was found to reach the finish!");
        maze.print();
    }

    public void solveBFS(Maze maze){
        maze.resetMaze();

        QueueA<MazeSpaceObject> queue = new QueueA<>(maze.getRows() * maze.getColumns());

        // Find the start and place at back of queue
        MazeSpaceObject start = maze.getStart();
        start.setVisited(true);
        queue.enqueue(start);

        // Process cells while any are waitign 
        while (!queue.isEmpty()) {
            // Remove oldest waiting cell
            MazeSpaceObject current = queue.dequeue();

            if (current.isFinish()){
                MazeSpaceObject pathCell = current;

                while(pathCell != null){
                    // Set it for visual, get the previous
                    pathCell.setPath(true);
                    pathCell = pathCell.getPrevious();
                }

                System.out.println("\n============= BFS RESULT =============");
                maze.print();
                System.out.println("S Start | F Finish | + Path | - Discovered\n");
                return;
            }

            int row = current.getY();
            int column = current.getX();

            if (row > 0) { //? NORTH  =================================
                MazeSpaceObject north = maze.getCell(row - 1, column);

                if(!north.isWall() && !north.hasVisited()){
                    // Mark it visited
                    north.setVisited(true);
                    // Set the prev of north, to current
                    north.setPrevious(current);
                    //Save for later.
                    queue.enqueue(north);
                }
            }
            //? EAST =================================================
            if (column < maze.getColumns() - 1) {
                // East is the same row, one column to the right
                MazeSpaceObject east = maze.getCell(row, column + 1);
                
                if(!east.isWall() && !east.hasVisited()){
                    // Mark it visited
                    east.setVisited(true);
                    // Set the prev of east, to current
                    east.setPrevious(current);
                    //Save for later.
                    queue.enqueue(east);
                }
            }
            //? SOUTH =================================================
            if (row < maze.getRows() - 1) {
                // South is the same column, one row down
                MazeSpaceObject south = maze.getCell(row + 1, column);
                
                if(!south.isWall() && !south.hasVisited()){
                    // Mark it visiteed
                    south.setVisited(true);
                    // Set the prev of south, to current
                    south.setPrevious(current);
                    //Save for later.
                    queue.enqueue(south);
                }
            }
            //? WEST =================================================
            if (column > 0) {
                // West is the same row, one column to the left
                MazeSpaceObject west = maze.getCell(row, column - 1);
                
                if(!west.isWall() && !west.hasVisited()){
                    // Mark it visited
                    west.setVisited(true);
                    // Set the prev of west, to current
                    west.setPrevious(current);
                    //Save for later.
                    queue.enqueue(west);
                }
            }
        }

        System.out.println("No path was found to reach the finish!");
        maze.print();
    }
}

