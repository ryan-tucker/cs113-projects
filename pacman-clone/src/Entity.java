/*
 *  Entity.java
 *
 *  Abstract class for Enemies and Players to extend. Has basic x and y positions.
 *
 *  Author:  Victor Vazquez / Ryan Tucker / Carsten Singleton
 *  Version: 1.0
 */
package edu.miracosta.cs113;

public abstract class Entity {

    private int x;
    private int y;

    /**
     * Default Constructor
     */
    public Entity(){
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constructor for initial position in Map
     *
     * @param x X Position (Row #)
     * @param y Y Position (Column #)
     */
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Abstract move method to be overridden. Should update X and Y instance variables, as well as
     * update the Tiles in Map
     *
     * @param dx Change in X position
     * @param dy Change in Y position
     * @param map Map containing this Entity
     */
    public abstract void move(int dx, int dy, Map map);

    /**
     * Accessor for x location
     *
     * @return int x representing row in Map
     */
    public int getX() {
        return x;
    }

    /**
     * Accessor for y location
     *
     * @return int y representing column in Map
     */
    public int getY() {
        return y;
    }

    /**
     * Mutator for x
     *
     * @param x new row in Map
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Mutator for y
     *
     * @param y new column in Map
     */
    public void setY(int y) {
        this.y = y;
    }
}
