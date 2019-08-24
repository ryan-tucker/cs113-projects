/*
 *  Game.java
 *
 *  View Class. Contain main game loop (FPS Based), Rendering Logic.
 *  Also contains anonymous KeyListener for player movement.
 *
 *  Author:  Victor Vazquez / Ryan Tucker / Carsten Singleton
 *  Version: 1.0
 */
package edu.miracosta.cs113;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game extends JFrame
{
    private int windowWidth;
    private int windowHeight;
    private Map map;
    private BufferedImage backBuffer;

    //Game variables
    public static final int ENEMY_SPAWN_TIME = 4; // seconds
    public static final double ENEMY_UPDATE_TIME = 12; // frames
    public static final int ITEM_SPAWN_TIME = 10; // seconds
    private int level = 1;
    private boolean addEnemy = false;
    private boolean updateEnemy = false;
    private boolean addItem = false;
    private boolean gameOver = false;

    public static void main(String[] args)
    {
        Game game = new Game("Run Away");
        game.run();
        System.exit(0);
    }

    /**
    * Default Constructor.
    * @param String title game title
    */
    public Game(String title) {
        super();
        setTitle(title);
        this.map = new Map("DefaultMap.txt");
        this.windowWidth = map.getColumns() * Map.TILE_SIZE + 15; // added constant for centering
        this.windowHeight = map.getRows() * Map.TILE_SIZE + 38; // added constant for centering
        backBuffer = new BufferedImage(windowWidth, windowHeight,BufferedImage.TYPE_INT_RGB);
        frameInit();
    }

    /**
    * Main game loop that constantly updates and draws game.
    */
    public void run()
    {
        this.initialize();
        boolean isRunning = true;
        int fps = 60;
        double timePerFrame = 1000000000 / fps;
        long startOfFrame;
        long endOfFrame = System.nanoTime();

        long frameTimer = 0;
        int frameCounter = 0;

        long startOfSecond = 0;

        int enemySpawnTimer = 0;
        int itemSpawnTimer = 0;
        int enemyUpdateTimer = 0;

        while(isRunning) {
            startOfFrame = System.nanoTime();

            if (frameCounter == 0) {
                startOfSecond = System.nanoTime();
            }

            if (frameTimer == 0) {
                this.update();
                this.draw();
            }

            frameTimer += (startOfFrame - endOfFrame);
            endOfFrame = startOfFrame;

            if (frameTimer >= timePerFrame) {
                frameTimer = 0;
                frameCounter++;

                enemyUpdateTimer++;
                if(enemyUpdateTimer >= ENEMY_UPDATE_TIME && !gameOver) {
                    updateEnemy = true;
                    enemyUpdateTimer = 0;
                }
            }

            if (System.nanoTime() - startOfSecond >= 1000000000) {
                //System.out.println(frameCounter);
                frameCounter = 0;

                enemySpawnTimer++;
                if(enemySpawnTimer >= ENEMY_SPAWN_TIME && !gameOver) {
                    addEnemy = true;
                    enemySpawnTimer = 0;
                }

                itemSpawnTimer++;
                if(itemSpawnTimer >= ITEM_SPAWN_TIME && !gameOver) {
                    addItem = true;
                    itemSpawnTimer = 0;
                }
            }
        }
    }

    /**
    * Initializes the setup for the game.
    */
    public void initialize()
    {
        setSize(windowWidth,windowHeight);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(Color.BLACK);
        this.addKeyListener(new KeyListener() {
            Player player = map.getPlayer();
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    player.move(1, 0, map);
                }
                else if (e.getKeyCode() == KeyEvent.VK_W) {
                    player.move(-1, 0, map);
                }
                else if (e.getKeyCode() == KeyEvent.VK_A) {
                    player.move(0, -1, map);

                }
                else if (e.getKeyCode() == KeyEvent.VK_D) {
                    player.move(0, 1, map);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        setVisible(true);
    }

    /**
    * Updates the logic of the game.
    */
    public void update()
    {
        if(addEnemy) {
            map.getEnemies().add(new Enemy(map.getRows()/2,map.getColumns()/2));
            level++;
            addEnemy = false;
        }

        if(addItem) {
            map.getTiles()[map.getRows()-3][map.getColumns()/2] = Map.ITEM_TILE;
            addItem = false;
        }

        if(map.getPlayer().getUseItem()) {
            int size = map.getEnemies().size()/2;

            for (int i = 0; i < size; i++) {
                map.getTiles()[map.getEnemies().get(i).getX()][map.getEnemies().get(i).getY()] = Map.WALKABLE_TILE;
                map.getEnemies().remove(i);
            }
            map.getPlayer().setUseItem(false);
        }

        if(updateEnemy) {
            for (Enemy enemy: map.getEnemies()) {
                enemy.update(map);
                if(enemy.getX() == map.getPlayer().getX() && enemy.getY() == map.getPlayer().getY()) {
                    this.gameOver = true;
                }
            }
            updateEnemy = false;
        }
    }

    /**
    * Draws the game to the JFrame.
    */
    public void draw()
    {
        Graphics bbg = backBuffer.getGraphics();
        Tile current;

        if(!this.gameOver) {
            setTitle("Level " + level);
            for (int i = 0; i < map.getRows(); i ++) {
                for (int j = 0; j < map.getColumns(); j ++) {
                    current = map.getTile(i,j);
                    bbg.setColor(current.getColor());
                    bbg.fillRect(j * Map.TILE_SIZE, i * Map.TILE_SIZE, Map.TILE_SIZE - 1, Map.TILE_SIZE - 1);
                }
            }
        }
        else {
            //Draw Rectangle
            bbg.setColor(Color.BLACK);
            bbg.fillRect(0,(this.windowHeight/2) - 120, this.windowWidth ,this.windowHeight/4 + 30);

            //Draw background Game Over
            bbg.setFont(new Font("TimesRoman", Font.PLAIN, 161));
            bbg.setColor(Color.ORANGE);
            bbg.drawString("GAME OVER", 20, (this.windowHeight/2) +22);

            //Draw foreground Game Over
            bbg.setFont(new Font("TimesRoman", Font.PLAIN, 160));
            bbg.setColor(Color.RED);
            bbg.drawString("GAME OVER", 20, (this.windowHeight/2) +20);

            setTitle("You reached level " + level + "!");
        }
        getContentPane().getGraphics().drawImage(backBuffer,0,0,this);
    }
}
