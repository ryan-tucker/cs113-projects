/*
 *  Map.java
 *
 *  Contains a 2D Tile array which will be displayed to the user every frame.
 *  Also has instance variables for the Player and Enemies belonging to this Map.
 *  Constants are used for display and movement in Enemy and Player classes.
 *  Also has a MatrixGraph which represents connections between Tiles.
 *
 *  Author:  Victor Vazquez / Ryan Tucker / Carsten Singleton
 *  Version: 1.0
 */
package edu.miracosta.cs113;

import edu.miracosta.cs113.Graph.MatrixGraph;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
    public static final int TILE_SIZE = 35;
    public static final Tile WALKABLE_TILE = new Tile(Color.WHITE, TILE_SIZE, TILE_SIZE);
    public static final Tile BARRIER_TILE = new Tile(Color.BLACK, TILE_SIZE, TILE_SIZE);
    public static final Tile PLAYER_TILE = new Tile(Color.GREEN, TILE_SIZE, TILE_SIZE);
    public static final Tile ENEMY_TILE = new Tile(Color.RED, TILE_SIZE, TILE_SIZE);
    public static final Tile ITEM_TILE = new Tile(Color.BLUE, TILE_SIZE, TILE_SIZE);
    private Player player;
    private ArrayList<Enemy> enemies;
    private int rows;
    private int columns;
    private Tile[][] tiles;
    private MatrixGraph graph;

    /**
     * Constructor with width and height
     * @param rows number of rows
     * @param columns number of columns
     */
    public Map(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        tiles = new Tile[rows][columns];
        player = new Player(0,1);
        enemies = new ArrayList<>();
    }

    /**
     * Default Constructor
     */
    public Map()
    {
        enemies = new ArrayList<>();
        graph = new MatrixGraph(this);
    }

    /**
     * Constructor with file containing Map structure
     *
     * @param file name of file
     */
    public Map(String file) {
        enemies = new ArrayList<>();
        readFile(file);
        graph = new MatrixGraph(this);
    }

    /**
     * Accessor for Tile from tiles
     *
     * @param x row in tiles
     * @param y column in tiles
     * @return Tile from [x][y]
     */
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    /**
     * Accessor for player instance variable
     *
     * @return Player player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Accessor for enemies array list
     *
     * @return enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Accessor for rows instance variable (# of rows in tiles)
     *
     * @return int representing rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Accessor for columns instance variable (# of columns in tiles)
     *
     * @return int representing columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Accessor for tiles 2D array
     *
     * @return Tile[][] tiles
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    /**
     * Accessor for graph instance variable
     *
     * @return MatrixGraph graph
     */
    public MatrixGraph getGraph() {
        return graph;
    }

    /**
     * Mutator for graph instance variable
     *
     * @param graph graph representing this map
     */
    public void setGraph(MatrixGraph graph) {
        this.graph = graph;
    }

    /**
     * Constructs a new map based on this file.
     * First int: # of columns
     * Second int: # of rows
     *
     * 0's represent open tiles
     * 1's represent barriers
     * 2's represent Players
     * 3's represent Enemies
     *
     * Sample:
     * 3
     * 3
     *
     * 001
     * 100
     * 010
     *
     * @param file String file name containing
     */
    public void readFile(String file)
    {
        player = new Player(0,1);
        Scanner reader = null;
        try
        {
            reader = new Scanner( new FileInputStream(file));

            this.columns = Integer.parseInt(reader.nextLine());
            this.rows = Integer.parseInt(reader.nextLine());
            tiles = new Tile[rows][columns];

            while (reader.hasNext())
            {
                for(int i = 0; i < this.rows;i++)
                {
                    for (int j = 0; j< this.columns; j++)
                    {
                        int value = reader.nextInt();

                        if(value == 0)
                        {
                            tiles[i][j] = WALKABLE_TILE;
                        }
                        else if(value == 1)
                        {
                            tiles[i][j] = BARRIER_TILE;
                        }
                        else if(value == 2)
                        {
                            player.setX(i);
                            player.setY(j);
                            tiles[i][j] = PLAYER_TILE;
                        }
                        else
                        {
                            enemies.add(new Enemy(i,j));
                            tiles[i][j] = ENEMY_TILE;
                        }

                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Converts a 2D index to a Row-Ordered 1D index
     *
     * @param row row in tiles
     * @param column column in tiles
     *
     * @return Row-Ordered 1D index
     */
    public int get1DIndex(int row, int column) {
        return column + row * columns;
    }

    /**
     * Converts a Row-Ordered 1D index into a 2D index
     *
     * @param index Row-Ordered 1D index
     *
     * @return int[] containing {row,column}
     */
    public int[] get2DIndex(int index) {
        int column = index % columns;
        int row = index / columns;
        return new int[]{row,column};
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < columns; j ++) {
                if (tiles[i][j] == WALKABLE_TILE) {
                    sb.append(0);
                } else if (tiles[i][j] == BARRIER_TILE) {
                    sb.append(1);
                } else if (tiles[i][j] == PLAYER_TILE) {
                    sb.append(2);
                } else if (tiles[i][j] == ENEMY_TILE) {
                    sb.append(3);
                } else if (tiles[i][j] == ITEM_TILE) {
                    sb.append(4);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
