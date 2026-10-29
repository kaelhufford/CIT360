package Activity2;

public class MazeDemo {
    public static void main(String[] args) {
        Maze maze = new Maze(50, 50);

        MazeSolver solver = new MazeSolver();

        // Clear old terminal output and move to the top-left.
        System.out.print("\u001B[2J\u001B[H");
        System.out.flush();

        solver.solveDFS(maze);

        solver.solveBFS(maze);
        
    }
}