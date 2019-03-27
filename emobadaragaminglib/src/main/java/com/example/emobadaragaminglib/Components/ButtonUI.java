package com.example.emobadaragaminglib.Components;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Image;

/**
 * Use this class if you want to have a button that changes the way it looks when you press it
 */
public class ButtonUI extends Sprite {
    private Image imageClicked; //When the Button is clicked this image will show
    private Image imageNotClicked; //Image when the button is not clicked
    private boolean isClicked=false;

    public ButtonUI(Image image,Image imageClicked, int x, int y, int height, int width) {
        super(image, x, y, height, width);
        this.imageClicked=imageClicked;
        this.imageNotClicked=getImage();
    }

    /**
     * This Function handles the Image changing for you
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void onTouchDown(int x,int y){
        if(this.contain(x,y)){
            this.setImage(imageClicked);
            isClicked=true;
        }
    }

    /**
     * If the user clicked the button but he dragged his finger away, then The picture should behave
     * correctly and the button shouldn't be considered pressed
     * @param x x-coordinate
     * @param y y-coordinate
     * @return whether the button was pressed or not
     */
    public boolean onTouchUp(int x,int y){
        if(isClicked){
            this.setImage(imageNotClicked);
            isClicked=false;
        }
        if(this.contain(x,y)) return true;
        else return false;
    }

    /**
     * tells you if this button was pressed or not
     * @return
     */
    public boolean isClicked() {
        return isClicked;
    }

}
