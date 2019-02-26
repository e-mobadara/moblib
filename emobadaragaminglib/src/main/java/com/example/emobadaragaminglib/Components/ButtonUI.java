package com.example.emobadaragaminglib.Components;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Image;

public class ButtonUI extends Sprite {
    private Image imageClicked;
    private Image imageNotClicked;
    private boolean isClicked=false;
    public ButtonUI(Game game, Image image,Image imageClicked, int x, int y, int height, int width) {
        super(game, image, x, y, height, width);
        this.imageClicked=imageClicked;
        this.imageNotClicked=getImage();
    }

    public void onTouchDown(int x,int y){
        if(this.contain(x,y)){
            this.setImage(imageClicked);
            isClicked=true;
        }
    }
    public boolean onTouchUp(int x,int y){
        if(isClicked){
            this.setImage(imageNotClicked);
            isClicked=false;
        }
        if(this.contain(x,y)) return true;
        else return false;
    }

    public boolean isClicked() {
        return isClicked;
    }

}
