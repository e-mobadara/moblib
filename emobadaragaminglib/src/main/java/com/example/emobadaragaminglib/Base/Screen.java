package com.example.emobadaragaminglib.Base;

import android.util.Log;

import com.example.emobadaragaminglib.Components.Sprite;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Screen is the interface that shows to the user and the one that has all the sprites that
 * the user can interact with.
 * This is your workshop and the class responsible for what the user sees.
 */
public abstract class Screen {
    protected final Game game;
    private ArrayList<Sprite> sprites;

    public Screen(Game game) {
        this.game = game;
        sprites=new ArrayList<>();
    }
    private Sprite draggedSprite =null;
    private ListIterator<Sprite> itr;
    // update & draw
    public abstract void render(float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public void dispose(){
        //dispose
        for (Sprite s:sprites) {
           // s.dispose();
        }
    }

    public abstract void backButton();

    /**
     * This Function will be Called as soon as you touch the screen
     * @param x x-coordination
     * @param y y-coordination
     * @param pointer
     */
    public void handleTouchDown(int x,int y, int pointer){
        //Iterating backwards to Respect the order of sprites
        itr = sprites.listIterator(sprites.size());
        while(itr.hasPrevious()) {
            if(itr.previous().contain(x,y)){
                itr.previous().setDragged(true);
                draggedSprite =itr.previous();
                Log.i("TOUCH_DOWN   ",itr.previous().getId()+"  (x,y) = "+x+"  ,  "+y);
                break;
            }
            else{
                itr.previous().setDragged(false);
                draggedSprite =null;
            }
        }
    }

    /**
     * This Function will get called as soon as you pull your finger away from the screen
     * @param x x-coordination
     * @param y y-coordination
     * @param pointer
     */
    public void handleTouchUp(int x,int y, int pointer){
        //Iterating backwards to Respect the order of sprites
        itr = sprites.listIterator(sprites.size());
        while(itr.hasPrevious()) {
            if(itr.previous().contain(x,y)){
                itr.previous().setDragged(false);
                draggedSprite =null;
                Log.i("TOUCH_UP   ",itr.previous().getId()+"  (x,y) = "+x+"  ,  "+y);
            }

        }
    }

    /**
     * This function gets called when you drag on the screen.
     * Some Small TouchDown might be considered as a Dragging action.
     * @param x current x-coordination
     * @param y current y-coordination
     * @param pointer
     */
    public void handleDragging(int x,int y, int pointer){
        //Iterating backwards to Respect the order of sprites
        itr = sprites.listIterator(sprites.size());
        while(itr.hasPrevious()) {
            if(itr.previous().isDragged()) {
                itr.previous().setPosition(x,y);
                Log.i("DRAG   ",itr.previous().getId()+"  (x,y) = "+x+"  ,  "+y);
            }
        }
    }

    /**
     * Create a new Sprite and add it the the sprite arraylist
     * @param image
     * @param x
     * @param y
     * @param height
     * @param width
     * @return the new sprite
     */
    public Sprite createNewSprite(Image image, int x, int y, int height, int width){
        Log.i("createNewSprite","id = ");
        Sprite sprite= new Sprite( game,image, x,y, height, width);
        this.sprites.add(sprite);
        return sprite;
    }

    /**
     * Draw the Sprites on the order that you added them.
     * Be careful on your Order of Sprites. The one on top a-k-a the latest one on the list
     * will be the one considered as the one being touched if there are sprites on top of each other
     */
    public void drawSprites(){
        //Respect the order and Draw the Sprites with their current order
        for (Sprite s:sprites) {
            s.draw(game);
        }
    }

    /**
     * Add a sprite to the Screen's arraylist of sprites
     * @param sprite
     */
    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }

    /**
     * Return the Sprite that the User is currently interacting with
     * it can be null. So watch out for that.
     * @return
     */
    public Sprite getDraggedSprite() {
        return draggedSprite;
    }
}
