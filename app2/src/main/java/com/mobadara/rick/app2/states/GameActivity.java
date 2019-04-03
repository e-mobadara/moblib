package com.mobadara.rick.app2.states;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Implementation.AndroidGame;
import com.mobadara.rick.app2.utils.Initializer;
import com.mobadara.rick.app2.views.Level1;

public class GameActivity extends AndroidGame {

    @Override
    public Screen getInitScreen() {
        Initializer.initAssets(this);
        return new Level1(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Initializer.disposer();
    }
}
