package com.example.emobadaragaminglib.Base;

import android.util.Log;

import com.example.emobadaragaminglib.Components.Sprite;

import java.util.ArrayList;

public abstract class Screen {
    protected final Game game;
    private ArrayList<Sprite> sprites;

    public Screen(Game game) {
        this.game = game;
        sprites=new ArrayList<>();
    }
    private Sprite dragedSprite=null;

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

    public void handleTouchDown(int x,int y, int pointer){
        // handle sprites created by screen
        for (Sprite s:sprites) {
            if(s.contain(x,y)){
                s.setIsDraged(true);
                dragedSprite=s;
                Log.i("amine  TOUCH_DOWN   ",s.getId()+"  (x,y) = "+x+"  ,  "+y);
                break;
            }
            else{
                s.setIsDraged(false);
                dragedSprite=null;
            }
        }
    }
    public void handleTouchUp(int x,int y, int pointer){
        // handle sprites created by screen
        for (Sprite s:sprites) {
            if(s.contain(x,y)){
                s.setIsDraged(false);
                dragedSprite=null;
                Log.i("amine  TOUCH_UP   ",s.getId()+"  (x,y) = "+x+"  ,  "+y);
            }

        }
    }
    public void handleDragging(int x,int y, int pointer){
        // handle sprites created by screen
        for (Sprite s:sprites) {
            if(s.isDraged()) {
                s.setPosition(x,y);
                Log.i("amine  DRAG   ",s.getId()+"  (x,y) = "+x+"  ,  "+y);
            }

        }
    }
    public Sprite createNewSprite(Image image, int x, int y, int height, int width){
        Log.i("amine  createNewSprite","id = ");
        Sprite sprite= new Sprite( game,image, x,y, height, width);
        this.sprites.add(sprite);
        return sprite;
    }
    public void drawSprites(){
        for (Sprite s:sprites) {
            s.draw(game);
        }
    }
    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }

    public Sprite getDragedSprite() {
        return dragedSprite;
    }
}
