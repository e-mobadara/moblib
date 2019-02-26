package com.ensias.emobadara.projetframework2dgame.example;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.ButtonUI;
import com.example.emobadaragaminglib.Components.Sprite;
import com.ensias.emobadara.projetframework2dgame.example.Utils.Anneau;
import com.ensias.emobadara.projetframework2dgame.example.Utils.ConesContainer;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;


public class GameScreen extends Screen {
    // Variable Setup
    // You would create game objects here.
    Paint paint;
    Sprite mySprite,mySprite2;
    ButtonUI buttonUI;

    public static int widthAnneau;
    public static int padding=10;



    private Anneau dragedAnneau,stopedAnneau;

    private Anneau[] anneausRouge;
    private Anneau[] anneausBleu;
    private Anneau[] anneausVert;
    private ConesContainer conesContainer;
    private boolean termine;
    private float time;
    private boolean stopGame;

    public GameScreen(Game game,int nombreAnneauRouge,int nombreAnneauBleu,int nombreAnneauVert,boolean existRouge,boolean existBleu,boolean existvert) {
        super(game);


        widthAnneau=(game.getScreenHeight()-2*padding)*2/7;
        dragedAnneau=null;
        time=0;
        stopGame=false;
        termine=false;
        int xAnneau=game.getScreenWidth()-widthAnneau;
        int yAnneau=game.getScreenHeight()-widthAnneau ;

        if (nombreAnneauRouge!=0){
            anneausRouge=new Anneau[nombreAnneauRouge];
            for (int i=0 ;i<nombreAnneauRouge;i++){
                anneausRouge[i]=new  Anneau(game,Assets.imageAnneauRouge,xAnneau,yAnneau,widthAnneau,widthAnneau,0);
                addSprite(anneausRouge[i]);
            }
            Log.i("amine ","ANNEAU ROUGE CREE");
        }
        else{
            anneausRouge=null;
        }
        yAnneau-=widthAnneau;
        if (nombreAnneauBleu!=0){
            anneausBleu=new Anneau[nombreAnneauBleu];
            for (int i=0 ;i<nombreAnneauBleu;i++){
                anneausBleu[i]=new  Anneau(game,Assets.imageAnneauBleu,xAnneau,yAnneau,widthAnneau,widthAnneau,1);
                addSprite(anneausBleu[i]);
            }
        }
        else anneausBleu=null;
        yAnneau-=widthAnneau;
        if (nombreAnneauVert!=0){
            anneausVert=new Anneau[nombreAnneauVert];
            for (int i=0 ;i<nombreAnneauVert;i++){
                anneausVert[i]=new  Anneau(game,Assets.imageAnneauVert,xAnneau,yAnneau,widthAnneau,widthAnneau,2);
                addSprite(anneausVert[i]);
            }
        }
        else anneausVert=null;

        conesContainer=new ConesContainer(game,nombreAnneauRouge,nombreAnneauBleu,nombreAnneauVert,existRouge, existBleu, existvert);

        // Initialize game objects here
        /*mySprite=createNewSprite( R.drawable.lock_blue,0,0,70,70);
        mySprite2=createNewSprite(R.drawable.lock_green,0,70,70,70);
        mySprite2.setStatique(false);

        Assets.music = game.getAudio().createMusic(R.raw.menutheme);
        Assets.music.play();*/

        buttonUI=new ButtonUI(game,Assets.imageButton,Assets.imageButtonClicked,game.getScreenWidth()-2*widthAnneau,widthAnneau,widthAnneau,2*widthAnneau);
                // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        //


    }

    @Override
    public void handleTouchDown(int x,int y, int pointer) {
        super.handleTouchDown(x,y,pointer);
        buttonUI.onTouchDown(x,y);
    }

    @Override
    public void handleTouchUp(int x,int y, int pointer) {
        if (getDragedSprite()!=null) {
            if (conesContainer.checkAnneauIfIsInserted((Anneau) getDragedSprite()))  {
                if (conesContainer.nombreAnneauInsere==conesContainer.nombreTotalAnneau) termine=true;
                // MyGdxGame.bravo.play();

            }
            else {
                // MyGdxGame.tryAgain.play();
                stopGame=true;
                stopedAnneau= (Anneau) getDragedSprite();

            }

            //((Anneau)getDragedSprite()).isDraged=false;
            //dragedAnneau=null;
            if (stopedAnneau==null)System.out.println("is null");
            else System.out.println("is not null");
        }
        super.handleTouchUp(x,y,pointer);
        if (termine && buttonUI.onTouchUp(x,y)){
            Assets.click.play(1);
            SampleGame.niveau++;
            SampleGame.niveau%=5;
            boolean bol1=false,bol2=false,bol3=false;
            if(SampleGame.niveaux[SampleGame.niveau][3]==1) bol1=true;
            if(SampleGame.niveaux[SampleGame.niveau][4]==1) bol2=true;
            if(SampleGame.niveaux[SampleGame.niveau][5]==1) bol3=true;
            game.setScreen(new GameScreen(game,SampleGame.niveaux[SampleGame.niveau][0],SampleGame.niveaux[SampleGame.niveau][1],SampleGame.niveaux[SampleGame.niveau][2],bol1,bol2,bol3));
        }

    }

    @Override
    public void handleDragging(int x,int y, int pointer) {
        super.handleDragging(x,y,pointer);
    }

    @Override
    public void render(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawARGB(255, 0, 0, 0);
        /*g.drawRect(700, 380, 100, 100, Color.RED);
        g.drawString("Amine.", 100, 100, paint);
        g.drawLine(50,50,750,430,Color.GREEN);*/

        //g.drawImage(g.newImage("cube.png", Graphics.ImageFormat.ARGB8888,this.game.getResources()),200,200,70,70);
        conesContainer.draw();
        if (anneausRouge!=null){
            for (Anneau anneau: anneausRouge) {
                anneau.draw(game);

            }
        }
        if (anneausBleu!=null){
            for (Anneau anneau: anneausBleu) {
                anneau.draw(game);
            }
        }
        if (anneausVert!=null){
            for (Anneau anneau: anneausVert) {
                anneau.draw(game);
            }
        }
        if (dragedAnneau!=null) dragedAnneau.draw(game);

        if (termine) buttonUI.draw(game);
    }

    @Override
    public void pause() {
        //Assets.music.pause();
    }

    @Override
    public void resume() {
        //Assets.music.play();
    }

    @Override
    public void dispose() {
        super.dispose();


        // Set all variables to null. You will be recreating them in the constructor.
        paint = null;
        // Call garbage collector to clean up memory.
        System.gc();
    }

    @Override
    public void backButton() {
        pause();
    }
}
