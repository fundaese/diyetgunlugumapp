package com.example.casperpc.diyetgunlugum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ExplanationActivity extends AppCompatActivity {

    ImageButton imageButtonAciklama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation);

        imageButtonAciklama=findViewById(R.id.imageButtonAciklama);
        imageButtonAciklama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
                startActivity(intent);
            }
        });
    }
}
