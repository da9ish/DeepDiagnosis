package com.vatos.deepdiagnosis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by bilal on 26-03-2017.
 */

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button skin = (Button) findViewById(R.id.skin);
        Button diabetes = (Button) findViewById(R.id.diabetes);

        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skin_intent = new Intent(MenuActivity.this, MainActivity.class);
                skin_intent.putExtra("classification", "skin");
                startActivity(skin_intent);
            }
        });

        diabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent diabetes_intent = new Intent(MenuActivity.this, MainActivity.class);
                diabetes_intent.putExtra("classification", "diabetes");
                startActivity(diabetes_intent);
            }
        });
    }
}
