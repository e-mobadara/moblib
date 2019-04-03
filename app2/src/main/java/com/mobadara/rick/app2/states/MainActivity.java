package com.mobadara.rick.app2.states;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mobadara.rick.app2.R;
import com.mobadara.rick.app2.states.GameActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartGame(View view) {
        Intent mIntent = new Intent(this,GameActivity.class);
        startActivity(mIntent);
    }
}
