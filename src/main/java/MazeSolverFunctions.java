import java.util.Stack;

public class MazeSolverFunctions {
    
    // Constants for representing different states in the maze
    static final int PATH = 0;
    static final int WALL = 1;
    static final int VISITED = 2;

    // Inner class representing a point in the maze
    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Function to solve the maze using a stack
    public static boolean solveMaze(int[][] maze, int startX, int startY, int endX, int endY) {
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(startX, startY));

        while (!stack.isEmpty()) {
            Point current = stack.pop();

            // Check if the current move is valid
            if (isValidMove(maze, current, endX, endY)) {
                // Mark the current position as visited
                markVisited(maze, current);

                // Check if the current position is the exit
                if (isExit(current, endX, endY)) {
                    System.out.println("Maze solved!");
                    printMaze(maze);
                    return true;
                }

                // Push adjacent positions onto the stack
                pushAdjacentPositions(stack, current);
            }
        }

        System.out.println("No solution found.");
        return false;
    }

    // Function to check if a move is valid
    private static boolean isValidMove(int[][] maze, Point point, int endX, int endY) {
        return !isOutOfBounds(maze, point) && !isWall(maze, point) && !isVisited(maze, point);
    }

    // Function to check if a point is out of bounds
    private static boolean isOutOfBounds(int[][] maze, Point point) {
        int x = point.x;
        int y = point.y;
        return (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length);
    }

    // Function to check if a point is a wall
    private static boolean isWall(int[][] maze, Point point) {
        int x = point.x;
        int y = point.y;
        return (maze[x][y] == WALL);
    }

    // Function to check if a point has been visited
    private static boolean isVisited(int[][] maze, Point point) {
        int x = point.x;
        int y = point.y;
        return (maze[x][y] == VISITED);
    }

    // Function to mark a point as visited
    private static void markVisited(int[][] maze, Point point) {
        int x = point.x;
        int y = point.y;
        maze[x][y] = VISITED;
    }

    // Function to check if a point is the exit
    private static boolean isExit(Point current, int endX, int endY) {
        int x = current.x;
        int y = current.y;
        return (x == endX && y == endY);
    }

    // Function to push adjacent positions onto the stack
    private static void pushAdjacentPositions(Stack<Point> stack, Point current) {
        int x = current.x;
        int y = current.y;
        stack.push(new Point(x + 1, y));
        stack.push(new Point(x - 1, y));
        stack.push(new Point(x, y + 1));
        stack.push(new Point(x, y - 1));
    }

    // Function to print the current state of the maze
    private static void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // Main method to test the maze solver
    public static void main(String[] args) {
        int[][] maze = {
                {0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 1, 0},
                {1, 1, 0, 0, 0}
        };

        int startX = 0;
        int startY = 0;
        int endX = 4;
        int endY = 4;

        solveMaze(maze, startX, startY, endX, endY);
    }
}
