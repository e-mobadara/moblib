package com.example.emobadaragaminglib.Base;

import android.content.res.Resources;
import android.graphics.Paint;

public interface Graphics {
    /**
     * For more control over your images
     */
    public static enum ImageFormat {
        ARGB8888, ARGB4444, RGB565
    }

    /**
     * Create a new Image Object
     * @param id The resource ID. example: R.drawable.my_pic
     * @param format ARGB888 | ARGB444 | RGB565
     * @param res The resources from where to search, if you have no idea just put getGraphics()
     * @return an Image Object.
     */
    public Image newImage(int id, ImageFormat format,Resources res);

    /**
     * Hides everything from the Screen with a color
     * It doesn't dispose of your Sprites.
     * @param color
     */
    public void clearScreen(int color);

    /**
     * Draw a Line that passes from two points
     * @param x x of first Point
     * @param y Y of first Point
     * @param x2 x of Second Point
     * @param y2 y of Second Point
     * @param color Color of the Line
     */
    public void drawLine(int x, int y, int x2, int y2, int color);

    /**
     *  Draw a Rectangle starting from its UpperLeft corner
     * @param x x of UpperLeft Corner
     * @param y y of UpperLeft Corner
     * @param width Width of rectangle
     * @param height Height of rectangle
     * @param color Color To fill the Rectangle
     */
    public void drawRect(int x, int y, int width, int height, int color);

    /**
     * Draw Image Object
     * @param image The image to Draw
     * @param x x of UpperLeft Corner
     * @param y y of UpperLeft Corner
     * @param srcX
     * @param srcY
     * @param srcWidth
     * @param srcHeight
     */
    public void drawImage(Image image, int x, int y, int srcX, int srcY,
                          int srcWidth, int srcHeight);

    /**
     * Draw Image from left Top Corner using the original width and height of the Image
     * @param image Image to Draw
     * @param x Left
     * @param y Top
     */
    public void drawImage(Image image, int x, int y);

    /**
     * Draw Image with Custom Width and height
     * @param image Image
     * @param x Left
     * @param y Top
     * @param width width
     * @param height height
     */
    public void drawImage(Image image, int x, int y,int width,int height);

    /**
     * Write A text on the Screen
     * @param text The String to Write
     * @param x Left
     * @param y Top
     * @param paint The Painting Object to write your String Where you can customize how your String looks
     */
    void drawString(String text, int x, int y, Paint paint);

    /**
     * Get Width of Device Screen
     * @return Device's Width
     */
    public int getWidth();

    /**
     * Get Height of Device Screen
     * @return Device's Height
     */
    public int getHeight();

    /**
     * Fill the current space with a color using its ARGB code
     * @param a : Alpha (0-255)
     * @param r : red (0-255)
     * @param g : green (0-255)
     * @param b : blue (0-255)
     */
    public void drawARGB(int a, int r, int g, int b);

}