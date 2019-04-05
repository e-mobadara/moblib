package com.mobadara.rick.app2.views;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Components.Sprite;
import com.mobadara.rick.app2.assets.Hero;
import com.mobadara.rick.app2.sprites.Dummy;

public class Level1 extends Screen {
    private Dummy mDummy1;
    private Dummy mDummy2;
    public Level1(Game game) {
        super(game);
        mDummy1 = new Dummy(Hero.avatar1,0,0,200,200);
        mDummy2 = new Dummy(Hero.avatar2,200,200,200,200);
        addSprite(mDummy1);
        addSprite(mDummy2);
    }

    @Override
    public void handleTouchDown(int x, int y, int pointer) {
        super.handleTouchDown(x, y, pointer);
        Sprite s = getDraggedSprite();
        if(s!=null) {
            removeSprite(s);

        }

    }

    @Override
    public void render(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void backButton() {
    }
}
