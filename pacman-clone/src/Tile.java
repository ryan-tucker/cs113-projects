/*
 *  Tile.java
 *
 *  Basic shape to be contained in 2D Tile Array within Map class.
 *  Width and Height are in terms of pixels.
 *
 *  Author:  Victor Vazquez / Ryan Tucker / Carsten Singleton
 *  Version: 1.0
 */
package edu.miracosta.cs113;

import java.awt.*;

public class Tile {
    private Color color;
    private int width;
    private int height;

    /**
    * Constructor.
    * @param Color color color of tile
    */
    public Tile(Color color) {
        this.color = color;
        this.width = 5;
        this.height = 5;
    }

    /**
    * Full constructor.
    * @param Color color color of tile
    * @param int width tile width
    * @param int height tile height
    */
    public Tile(Color color, int width, int height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    /**
    * Gets color.
    * @return color
    */
    public Color getColor() {
        return color;
    }

    /**
    * Sets color.
    * @param Color color color of tile
    */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
    * Gets width.
    * @return tile width
    */
    public int getWidth() {
        return width;
    }

    /**
    * Sets width.
    * @param int width width of tile
    */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
    * Gets height.
    * @return tile height
    */
    public int getHeight() {
        return height;
    }

    /**
    * Sets height.
    * @param int height tile height
    */
    public void setHeight(int height) {
        this.height = height;
    }
}
