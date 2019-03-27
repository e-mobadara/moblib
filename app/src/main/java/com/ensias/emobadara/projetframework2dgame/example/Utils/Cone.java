package com.ensias.emobadara.projetframework2dgame.example.Utils;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Components.Sprite;
import com.ensias.emobadara.projetframework2dgame.example.GameScreen;

public class Cone extends Sprite {
    public int type;
    //public Rect rectangle;

    public Cone( Image image, int x, int y, int height, int width, int type) {
        super( image, x, y, height, width);
        this.type=type;
        //rectangle=new Rect(x-GameScreen.widthAnneau/5,y-GameScreen.widthAnneau/5,x+GameScreen.widthAnneau+2*GameScreen.widthAnneau/5,y-GameScreen.widthAnneau+2*GameScreen.widthAnneau/5);

    }
    public boolean contains(Anneau anneau){
        if(anneau.getX()>=this.getX()-GameScreen.widthAnneau/5 && anneau.getY()>=this.getY()-GameScreen.widthAnneau/5 &&
                (this.getX()+this.getWidth()+2*GameScreen.widthAnneau/5)>= (anneau.getX()+anneau.getWidth()) &&
                (this.getY()+this.getHeight()+2*GameScreen.widthAnneau/5)>= (anneau.getY()+anneau.getHeight()) ){
            return true;
        }
        return false;
    }
}
