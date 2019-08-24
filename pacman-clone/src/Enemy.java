/*
 *  Enemy.java
 *
 *  The class has logic for an enemy object movement and location in the map.
 *  For the movement, Enemy Objects use Dijkstra's from their position to the Player's position.
 *
 *  Author:  Victor Vazquez / Ryan Tucker / Carsten Singleton
 *  Version: 1.0
 */
package edu.miracosta.cs113;

public class Enemy extends Entity {

    /**
     * Default Constructor
     */
    public Enemy() {
        super();
    }

    /**
     * Full Constructor
     *
     * @param x x position
     * @param y y position
     */
    public Enemy(int x, int y) {
        super(x,y);
    }

    /**
     * To be called in Game's update method.
     *
     * @param map Map containing enemy
     */
    public void update(Map map) {
        moveTowardsPlayer(map);
    }


    /**
     * Moves towards the player based on Dijkstra's algorithm
     *
     * @param map Map containing Enemy and Player
     */
    public void moveTowardsPlayer(Map map) {
        int[] path = map.getGraph().getPath(map.get1DIndex(getX(),getY()),map.get1DIndex(map.getPlayer().getX(),map.getPlayer().getY()));
        int[] nextMove = map.get2DIndex(path[0]);
        move(nextMove[0]- getX(),nextMove[1] - getY(), map);
    }

    /**
     * Error checks for out of bounds. Can only move onto a walkable tile or a Player tile.
     *
     * @param dx Change in X position
     * @param dy Change in Y position
     * @param map Map containing this Entity
     */
    public void move(int dx, int dy, Map map) {
        if (getX() + dx < map.getRows() && getX() + dx > -1 && getY() + dy < map.getColumns() && getY() + dy > -1) {
            if (map.getTile(getX() + dx, getY() + dy) == Map.WALKABLE_TILE || map.getTile(getX() + dx, getY() + dy) == Map.PLAYER_TILE) {
                setX(getX() + dx);
                setY(getY() + dy);
                map.getTiles()[getX()][getY()] = Map.ENEMY_TILE;
                map.getTiles()[getX() - dx][getY() - dy] = Map.WALKABLE_TILE;
            }
        }
    }

}
