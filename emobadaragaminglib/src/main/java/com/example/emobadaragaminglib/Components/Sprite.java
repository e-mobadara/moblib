package com.example.emobadaragaminglib.Components;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Image;

public class Sprite {
    private int id;
    private Image image;
    private int x;
    private int y;
    private int height;
    private int width;
    private boolean isStatique=true;

    private boolean isDraged;

    //public Sprite(Game game,int idRessource, int x, int y, int height, int width) {
    public Sprite(Game game,Image image, int x, int y, int height, int width) {
        //this.id = idRessource;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        //this.image=game.getGraphics().newImage(idRessource, Graphics.ImageFormat.ARGB8888,game.getResources());
        this.image=image;
        isDraged=false;
    }
    public void draw(Game game){
        game.getGraphics().drawImage(image,x,y,width,height);
    }
    public void setPosition(int x,int y){
        this.x=x-width/2;
        this.y=y-height/2;
    }
    public boolean contain(int a,int b){
        //TODO: Improve the accessibility here!
        if(x<=a && a<=(x+width) && y<=b && b<=(y+height) ) return true;
        return false;
    }

    public void dispose() {
        image.dispose();
    }

    public boolean isDraged() {
        return isDraged;
    }

    public void setIsDraged(boolean draged) {
        if(!isStatique)
        isDraged = draged;
    }

    public int getId() {
        return id;
    }

    public boolean isStatique() {
        return isStatique;
    }

    public void setStatique(boolean statique) {
        isStatique = statique;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
