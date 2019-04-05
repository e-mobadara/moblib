package com.example.emobadaragaminglib.Components;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Base.Screen;

/**
 * Main component in this library. This is what the user can see and interact with
 */
public class Sprite {
    private Image image;
    private int x;
    private int y;
    private int height;
    private int width;
    private boolean isStatic =true;
    private boolean isDragged;

    /**
     * Constructor
     * @param image Bitmap of the Sprite
     * @param x LeftTop Corner X-coordinate from where to start drawing
     * @param y LeftTop Corner Y-coordinate from where to start drawing
     * @param height Height of the Sprite
     * @param width Width of the sprite
     */
    public Sprite(Image image, int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.image=image;
        isDragged =false;
    }

    /**
     * Draw Sprite
     * @param game where to draw the Sprite
     */
    public void draw(Game game){
        game.getGraphics().drawImage(image,x,y,width,height);
    }

    /**
     * Change the position of the Sprite
     * @param x new x-coordinate
     * @param y new Y-coordinate
     */
    public void setPosition(int x,int y){
        this.x=x-width/2;
        this.y=y-height/2;
    }

    /**
     * Does your Sprite contains the Point in the screen that has the coordination (a,b)
     * @param a the x-coordination of the Point
     * @param b the y-coordination of the point
     * @return
     */
    public boolean contain(int a,int b){
        //TODO: Improve the accessibility here!
        if(x<=a && a<=(x+width) && y<=b && b<=(y+height) ) return true;
        return false;
    }

    /**
     * Free the memory used by the Sprite
     */
    public void dispose() {
        image.dispose();
    }

    /**
     * Is the user dragging this sprite
     * @return
     */
    public boolean isDragged() {
        return isDragged;
    }

    /**
     * handle this sprite as being dragged if it is not static
     * @param dragged
     */
    public void setDragged(boolean dragged) {
        if(!isStatic)
        isDragged = dragged;
    }

    /**
     * Can the user drag this sprite around or not
     * @return
     */
    public boolean isStatic() {
        return isStatic;
    }

    /**
     * make the sprite draggable or not
     * @param aStatic
     */
    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    /**
     * Get the Image of this Sprite
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * Change the image of this Sprite
     * @param image new Image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * get the Left Corner x-coordination of this sprite
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * get the Left Corner Y-coordination of this sprite
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * get the Height of the Sprite
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the Width of the Sprite
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Change the X-coordinate of the Sprite
     * @param x new x-coordination
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Change the y-coordination of the sprite
     * @param y new y-coordination
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Change the Height of the Image
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Change the Width of the image
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }
}
