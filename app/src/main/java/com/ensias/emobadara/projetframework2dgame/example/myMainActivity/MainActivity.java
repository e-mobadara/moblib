package com.ensias.emobadara.projetframework2dgame.example.myMainActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ensias.emobadara.projetframework2dgame.R;
import com.ensias.emobadara.projetframework2dgame.example.SampleGame;

public class MainActivity extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.start);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent t = new Intent(MainActivity.this,SampleGame.class);
                startActivity(t);
            }
        });
    }
}
