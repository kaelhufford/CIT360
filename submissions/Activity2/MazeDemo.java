package Activity2;

public class MazeDemo {
    public static void main(String[] args) {
        Maze maze = new Maze(20, 30);

        MazeSolver solver = new MazeSolver();

        solver.solveDFS(maze);

        solver.solveBFS(maze);
        
    }
}