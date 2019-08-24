public class MazeSolver {
    Maze maze;

    public MazeSolver(int width, int height){
        maze = new Maze(width, height);
    }

    public MazeSolver(){
        maze = new Maze();
        maze.generateRandomMaze();
    }


    public boolean solve(int x, int y){
        maze.outputMaze();
        if (maze.get(x,y) < 0){
            return false;
        }else if(maze.get(x,y) == 1 || maze.get(x,y) == 2 || maze.get(x,y) == 3){
            return false;
        }else if(maze.get(x,y) == 4){
            maze.set(x,y,2);
            return true;
        }else{
            boolean all = false;
            maze.set(x,y,2);
            if (solve(x +1, y)){
                all = true;
            }
            if (solve(x -1, y)){
                all = true;
            }
            if (solve(x, y+1)){
                all = true;
            }
            if (solve(x, y-1)){
                all = true;
            }

            if (!all){
                maze.set(x,y,3);
                return false;
            }else{
                return true;
            }
        }
    }

    public static void main(String[] arg){
        MazeSolver test = new MazeSolver(40,40);
        test.solve(0,0);
    }
}
