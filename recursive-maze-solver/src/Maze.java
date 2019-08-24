public class Maze {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private Spot[][] maze;
    private int width;
    private int height;

    public Maze(){
        maze = new Spot[20][20];
        this.width = 20;
        this.height = 20;
    }

    public Maze(int width, int height){
        maze = new Spot[width][height];
        this.width = width;
        this.height = height;
    }

    public void generateRandomMaze(){
        for (int i = 0; i < width; i ++){
            for (int j = 0; j < height; j++){
                if ((Math.random() * 10) > 7){
                    maze[i][j] = new Spot(1);
                }else{
                    maze[i][j] = new Spot(0);
                }
            }
        }

        maze[width - 1][height - 1] = new Spot(4);
    }

    public void outputMaze(){
        for (int i = 0; i < width; i ++){
            for (int j = 0; j < height; j++){
                if (maze[i][j].type == 0){
                    System.out.print(ANSI_BLUE + " O ");
                }else if (maze[i][j].type == 1){
                    System.out.print(ANSI_CYAN + " * ");
                }else if (maze[i][j].type == 2){
                    System.out.print(ANSI_GREEN + " V ");
                }else if (maze[i][j].type == 3){
                    System.out.print(ANSI_RED + " X ");
                }else if (maze[i][j].type == 4){
                    System.out.print(" ! ");
                }
            }
            System.out.print("\n");
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public boolean set(int x, int y, int type){
        if (x >= width || x < 0 || y >= height || y < 0){
            return false;
        }else{
            maze[x][y] = new Spot(type);
            return true;
        }
    }

    public int get(int x, int y){
        if (x >= width || x < 0 || y >= height || y < 0){
            return -1;
        }else{
            return maze[x][y].type;
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private class Spot{
        private int type; //0 == empty, 1 == barrier, 2 == path, 3 == temp, 4 == exit

        public Spot(int type){
            if (type > 0 && type < 5){
                this.type = type;
            }else{
                this.type = 0;
            }
        }
    }
}
