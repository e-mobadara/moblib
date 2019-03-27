package com.ensias.emobadara.projetframework2dgame.example;

import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Implementation.AndroidGame;
import com.ensias.emobadara.projetframework2dgame.R;

public class SampleGame extends AndroidGame {
    public static int niveau=0;
    public static int[][] niveaux={{1,0,0,1,0,0},
            {3,0,0,1,0,0},{4,1,0,1,1,0},{3,2,2,1,1,1},{4,4,4,0,0,0}	};
    @Override
    public Screen getInitScreen() {
        Assets.imageAnneauBleuInsere=getGraphics().newImage(R.drawable.anneau_bleu_insere, Graphics.ImageFormat.ARGB8888,getResources());
        Assets.imageAnneauRougeInsere=getGraphics().newImage(R.drawable.anneau_rouge_insere, Graphics.ImageFormat.ARGB8888,getResources());
        Assets.imageAnneauVertInsere=getGraphics().newImage(R.drawable.anneau_vert_insere, Graphics.ImageFormat.ARGB8888,getResources());

        Assets.imageAnneauBleu=getGraphics().newImage(R.drawable.anneau_bleu, Graphics.ImageFormat.ARGB8888,getResources());
        Assets.imageAnneauRouge=getGraphics().newImage(R.drawable.anneau_rouge, Graphics.ImageFormat.ARGB8888,getResources());
        Assets.imageAnneauVert=getGraphics().newImage(R.drawable.anneau_vert, Graphics.ImageFormat.ARGB8888,getResources());

        Assets.imageConeBleu=getGraphics().newImage(R.drawable.case_bleu, Graphics.ImageFormat.ARGB8888,getResources());
        Assets.imageConeRouge=getGraphics().newImage(R.drawable.case_rouge, Graphics.ImageFormat.ARGB8888,getResources());
        Assets.imageConeVert=getGraphics().newImage(R.drawable.case_vert, Graphics.ImageFormat.ARGB8888,getResources());

        Assets.imageButton=getGraphics().newImage(R.drawable.next_fr, Graphics.ImageFormat.ARGB8888,getResources());
        Assets.imageButtonClicked=getGraphics().newImage(R.drawable.next_fr_click, Graphics.ImageFormat.ARGB8888,getResources());

        Assets.click = getAudio().createSound(R.raw.click1);
        Assets.music = getAudio().createMusic(R.raw.shaunthesheep);

        boolean bol1=false,bol2=false,bol3=false;
        if(niveaux[0][3]==1) bol1=true;
        if(niveaux[0][4]==1) bol2=true;
        if(niveaux[0][5]==1) bol3=true;
        return new GameScreen(this,niveaux[0][0],niveaux[0][1],niveaux[0][2],bol1,bol2,bol3);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Assets.imageConeVert.dispose();
        Assets.imageConeRouge.dispose();
        Assets.imageConeBleu.dispose();
        Assets.imageAnneauBleu.dispose();
        Assets.imageAnneauRouge.dispose();
        Assets.imageAnneauVert.dispose();
        Assets.imageAnneauBleuInsere.dispose();
        Assets.imageAnneauRougeInsere.dispose();
        Assets.imageAnneauVertInsere.dispose();
        Assets.imageButtonClicked.dispose();
        Assets.imageButton.dispose();
        Assets.click.dispose();
    }
}