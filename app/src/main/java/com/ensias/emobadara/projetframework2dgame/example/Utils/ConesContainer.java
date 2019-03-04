package com.ensias.emobadara.projetframework2dgame.example.Utils;

import android.util.Log;

import com.example.emobadaragaminglib.Base.Game;
import com.ensias.emobadara.projetframework2dgame.example.Assets;
import com.ensias.emobadara.projetframework2dgame.example.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ConesContainer {

    protected final Game game;

    private final int nombreLignes=3;
    private final int nombreCollones=4;
    private final int nombreCases=nombreCollones*nombreLignes;

    public Cone[] cones=new Cone[nombreCases];
    public int nombreAnneauInsere,nombreTotalAnneau;

    public ConesContainer(Game game,int nombreRouge,int nombreBleu,int nombreVert,boolean existRouge,boolean existBleu,boolean existvert) {
        this.game=game;
        nombreTotalAnneau=nombreBleu+nombreRouge+nombreVert;
        int x=GameScreen.padding;
        int y=GameScreen.padding;
        nombreAnneauInsere=0;
        int index=0;
        ArrayList<Integer> arrayList=getListRandomInteger(nombreRouge,nombreBleu,nombreVert, existRouge,existBleu,existvert);
        Iterator iterator= arrayList.iterator();
        for (int j=0;j<nombreLignes;j++){
            for (int i=0;i<nombreCollones;i++){
                if (iterator.hasNext()){
                    int type = (Integer) iterator.next();
                    switch (type){
                        case 0:
                            cones[index]=new Cone(game, Assets.imageConeRouge,x,y,GameScreen.widthAnneau,GameScreen.widthAnneau,0);
                            break;
                        case 1:
                            cones[index]=new Cone(game,Assets.imageConeBleu,x,y,GameScreen.widthAnneau,GameScreen.widthAnneau,1);
                            break;
                        case 2:
                            cones[index]=new Cone(game, Assets.imageConeVert,x,y,GameScreen.widthAnneau,GameScreen.widthAnneau,2);
                            break;

                    }
                }
                x+=GameScreen.widthAnneau;
                index++;
            }
            x=GameScreen.padding;
            y+=GameScreen.widthAnneau;
        }
    }
    public void draw(){
        for (int j=0;j<nombreCases;j++){
            cones[j].draw(game);
        }
    }

    public boolean checkAnneauIfIsInserted(Anneau anneau){
        boolean resultat=false;
        Log.i("inserted","enter "+" = "+anneau.type);
        for (Cone c : cones){
            if ( anneau.type==c.type && c.contains(anneau)){
                Log.i("inserted"," "+c.type+" = "+anneau.type);
                anneau.isNotInserted=false;
                anneau.setX(c.getX());
                anneau.setY(c.getY());
                anneau.setDragged(false);
                anneau.setStatic(true);

                nombreAnneauInsere++;
                switch (anneau.type){
                    case 0:
                        anneau.setImage(Assets.imageAnneauRougeInsere);
                        break;
                    case 1:
                        anneau.setImage(Assets.imageAnneauBleuInsere);
                        break;
                    case 2:
                        anneau.setImage(Assets.imageAnneauVertInsere);
                        break;
                }
                return true;
            }
        }
        return resultat;
    }

    private ArrayList<Integer> getListRandomInteger(int nombreRouge, int nombreBleu, int nombreVert, boolean existRouge, boolean existBleu, boolean existvert){
        ArrayList<Integer> arrayListExist=new ArrayList<Integer>();
        if (existRouge) arrayListExist.add(0);
        if (existBleu) arrayListExist.add(1);
        if (existvert) arrayListExist.add(2);

        ArrayList<Integer> arrayListInit=new ArrayList<Integer>();
        ArrayList<Integer> arrayListFinal=new ArrayList<Integer>();
        Random random=new Random();
        for (int i=0;i<nombreRouge;i++){
            arrayListInit.add(0);
        }
        for (int i=0;i<nombreBleu;i++){
            arrayListInit.add(1);
        }
        for (int i=0;i<nombreVert;i++){
            arrayListInit.add(2);
        }
        if ((nombreBleu+nombreRouge+nombreVert)<nombreCases){
            int nombreToAdd=nombreCases-(nombreBleu+nombreRouge+nombreVert);
            for (int i=0;i<nombreToAdd;i++){
                arrayListInit.add(arrayListExist.get(random.nextInt(arrayListExist.size())));
            }
        }
        int index,element;
        for (int i=0;i<nombreCases;i++){
            index=random.nextInt(arrayListInit.size());
            element=arrayListInit.get(index);
            arrayListInit.remove(index);
            arrayListFinal.add(element);
        }

        return arrayListFinal;
    }
}
