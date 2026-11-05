package Activity2;

import java.util.Random;

public class Maze {

    private MazeSpaceObject[][] maze;
    private MazeSpaceObject startSpot;
    private MazeSpaceObject finishSpot;

    private int rows, columns;

    public Maze(int xLen, int yLen) {
        if (xLen < 5 || yLen < 5) {throw new IllegalArgumentException("Maze must be at least 5x5!");}
        
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
     * Build a grid with a wall border and randomly placed interior obstacles.
     * Select distinct start and finish cells, making both traversable.
     * MazeSolver searches the grid and reconstructs paths using previous links.
     */

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

                // If on top row, bottom row, left column or right column, make border/wall
                boolean border = row == 0 || row == rows - 1 || column == 0 || column == columns -1;

                // Make border cells walls; randomly make 25% of interior cells walls.
                boolean isWall = border; // || Math.random() < 0.25;

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
        startSpot = maze[1][1];
        startSpot.setStartSpot();

        while((randomStartX == randomFinishX) && (randomStartY == randomFinishY)){
            randomFinishX = random.nextInt(columns - 2) + 1;
            randomFinishY = random.nextInt(rows - 2) + 1;           
        }



        // Mark the finish only after selecting a position different from the start.
        finishSpot = maze[randomFinishY][randomFinishX];
        finishSpot = maze[rows-2][columns-2];
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
