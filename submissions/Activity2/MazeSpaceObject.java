package Activity2;

public class MazeSpaceObject {
    //* spaceObject object with property of "isWall", "isStart", "isFinish", "hasVisited", "location"
    
    /**
     * Space object has the following properties
     * - isWall : If the space is a wall
     * - isStart : If the space is the starting point
     * - isFinish : If the space is the finish spot
     * 
     * - hasVisited : Tells whether or not the solver has visited this space before
     * - location 
     *  - row / y
     *  - column / x
     * 
     */

    private boolean isWall, isStart, isFinish, hasVisited, isCurrent, isPath;
    private MazeSpaceObject previous;
    private int x, y;

    public MazeSpaceObject(int x, int y, boolean isWall){
        this.x = x;
        this.y = y;
        this.isWall = isWall;
    }

    public void setWall(){
        this.isWall = true;

        this.isStart = false;
        this.isFinish = false;
    }

    public void setStartSpot(){
        this.isStart = true;

        this.isWall = false;
        this.isFinish = false;
    }

    public void setFinishSpot(){
        this.isFinish = true;

        this.isWall = false;
        this.isStart = false;
    }

    public int getX() {return this.x;}
    public int getY() {return this.y;}

    public void setVisited(boolean newVal) {this.hasVisited = newVal;}

    public boolean isWall() {return this.isWall;}
    public boolean isStart() {return this.isStart;}
    public boolean isFinish() {return this.isFinish;}
    public boolean hasVisited() {return this.hasVisited;}
    public MazeSpaceObject getPrevious() {return previous;}

    public void setPath(boolean path) {this.isPath = path;}
    public void setCurrent(boolean current) {this.isCurrent = current;}
    public void setPrevious(MazeSpaceObject previous) {this.previous = previous;}

    public String toString() {
        // Restore the normal color after each cell.
        String reset = "\u001B[0m";

        // Single symbols have one space on each side.
        if (this.isCurrent) {return "\u001B[93m @ " + reset;}
        if (this.isWall) {return "\u001B[90m███" + reset;}
        if (this.isFinish) {return "\u001B[91m F " + reset;}
        if (this.isStart) {return "\u001B[92m S " + reset;}
        if (this.isPath) {return "\u001B[96m + " + reset;}
        if (this.hasVisited) {return "\u001B[90m - " + reset;}

        return "   "; // Three spaces for an open cell.
    }
}
