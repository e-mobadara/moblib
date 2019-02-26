package com.ensias.emobadara.projetframework2dgame.example.Utils;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Components.Sprite;

public class Anneau  extends Sprite {
    //rouge=0        bleu= 1      vert=2
    public int type;
    //public Rect rectangle;
    public boolean isDraged;
    public boolean isNotInserted;

    public Anneau(Game game, Image image, int x, int y, int height, int width, int type) {
        super(game, image, x, y, height, width);
        this.type=type;
        //rectangle=new Rect(x,y,GameScreen.widthAnneau+x,GameScreen.widthAnneau-y);
        isDraged=false;
        isNotInserted=true;
        setStatique(false);
    }

}
