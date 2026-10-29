package Activity2;

import java.util.Random;

public class Maze {

    private MazeSpaceObject[][] maze;
    private MazeSpaceObject startSpot;
    private MazeSpaceObject finishSpot;

    private int rows, columns;

    public Maze(int xLen, int yLen) {
        if (xLen < 5 || yLen < 5) { throw new IllegalArgumentException("Maze must be at least 5x5!");}
        
        this.maze = createMaze(xLen, yLen);
    }

    public MazeSpaceObject getCell(int row, int column) {return maze[row][column];}

    public void resetMaze() {reset();}

    public MazeSpaceObject getStart() {return startSpot;}
    public MazeSpaceObject getFinish() {return finishSpot;}
    
    public int getRows() {return this.rows;}
    public int getColumns() {return this.columns;}
    
    public void print() {printMaze(maze);}

    /**
     ** 0. Create a spaceObject object with property of "isWall", "isStart", "isFinish", "hasVisited", "location", "dist"
     *
     ** 1. Create 2d array (X by Y) filled with spaceObject.
     *?  a. When generating the wall add 1 to X and Y to make the outer border all walls
     *
     ** 2. Randomly fill array with "obsticles"
     *?  a. if(rnd < .25) {spaceObject.setWall()}
     *
     ** 3. Set random start and end spaceObject
     *?   a. If random start spot is a wall, override spaceObject to be the start spot
     *?   b. If random end spot it wall, override spaceObject to be is finish spot
     *?   c. Create a dist var to track distance
     *
     ** 4. Push start to stack // Need to do with queues.
     *?  a. Continue to push stuff to stack until the stack is empty Ex:
     *      1. while (!stack.isEmpty()) {
     *      2.   curr = stack.pop()
     *      3.   //look around for walkable spots (if !spaceObject.isWall, it is walkable)
     *      4.      Also check if NWSE spaceObject isFinish == true
     *      5.      if North isWalkable && !hasVisited, n.dist = dist, then push onto stack
     *      6.      if East isWalkable && !hasVisited, n.dist = dist, then push onto stack
     *      7.      if South isWalkable && !hasVisited, n.dist = dist, then push onto stack
     *      8.      if West isWalkable && !hasVisited, n.dist = dist, then push onto stack
     *      9.
     *     10.   if (curr.isFinish) {break;} // Maze is complete and finished.
     *     12.   dist++;
     *     13. }
     *
     ** 5. Travel back to the starting position using the lowest dist value of alls spaceObjects, or only available spaceObjects
     *?  a. Look into storing the path visited in an array to prevent having to do this ^^ and reset everytime you go back to a spot adjacent to the starting spot
     */

    // If finished early and works, find ways to add new things to make it more interesting.

    private MazeSpaceObject[][] createMaze(int xLen, int yLen){

        // Add a border
        int rows = yLen +2;
        int columns = xLen +2;

        // Needs to be after because it needs the vars `rows` and `columns` to make a new instance
        MazeSpaceObject[][] mazeMaking = new MazeSpaceObject[rows][columns];

        // Iterate through each row
        for (int row = 0; row < rows; row++ ){

            // Iterate through each column
            for (int column = 0; column < columns; column++){

                // If on top row, bottom row, left column or middle column, make border/wall
                boolean border = row == 0 || row == rows - 1 || column == 0 || column == columns -1;

                // Make object a wall if its a border of randomly assigned
                boolean isWall = border || Math.random() < 0.25;

                mazeMaking[row][column] = new MazeSpaceObject(column, row, isWall);

            }
        }

        this.rows = rows;
        this.columns = columns;

        setStartFinish(mazeMaking);

        return mazeMaking;
    }

    private void setStartFinish(MazeSpaceObject[][] maze){
        int rows = maze.length;
        int columns = maze[0].length;

        Random random = new Random();

        int randomStartX, randomFinishX;
        randomStartX = randomFinishX = random.nextInt(columns - 2) + 1;

        int randomStartY, randomFinishY;
        randomStartY = randomFinishY = random.nextInt(rows - 2) + 1;
        
        startSpot = maze[randomStartY][randomStartX];
        startSpot.setStartSpot();

        while((randomStartX == randomFinishX) && (randomStartY == randomFinishY)){
            randomFinishX = random.nextInt(columns - 2) + 1;
            randomFinishY = random.nextInt(rows - 2) + 1;           
        }

        // Apply outside otherwise it'll make a ton the finish.
        finishSpot = maze[randomFinishY][randomFinishX];
        finishSpot.setFinishSpot();
    }

    private void reset(){
        for (int row = 0; row < maze.length; row++){
            for (int column = 0; column < maze[row].length; column++){
                //Reset -> visited, previous, current, and path
                maze[row][column].setVisited(false);
                maze[row][column].setPrevious(null);
                maze[row][column].setCurrent(false);
                maze[row][column].setPath(false);
            }
        }
    }

    private void printMaze(MazeSpaceObject[][] maze){
        for (int row = 0; row < maze.length; row++){
            for (int column = 0; column < maze[row].length; column++){
                System.out.print(maze[row][column].toString());
            }
            System.out.println();
        }
    }
}
