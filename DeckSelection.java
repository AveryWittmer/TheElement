package com.example.elementtwoplayergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class DeckSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_selection);

        Button earthButton = findViewById(R.id.earthButton);
        Button waterButton = findViewById(R.id.waterButton);
        Button fireButton = findViewById(R.id.fireButton);
        Button metalButton = findViewById(R.id.metalButton);
        Button woodButton = findViewById(R.id.woodButton);
        Button customButton = findViewById(R.id.customButton);

        earthButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                Intent gameIntent = new Intent(DeckSelection.this, GameStart.class);
                gameIntent.putExtra("deckSelected", "earth");
                startActivity(gameIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        waterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                Intent gameIntent = new Intent(DeckSelection.this, GameStart.class);
                gameIntent.putExtra("deckSelected", "water");
                startActivity(gameIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        fireButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                Intent gameIntent = new Intent(DeckSelection.this, GameStart.class);
                gameIntent.putExtra("deckSelected", "fire");
                startActivity(gameIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        metalButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                Intent gameIntent = new Intent(DeckSelection.this, GameStart.class);
                gameIntent.putExtra("deckSelected", "metal");
                startActivity(gameIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        woodButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                Intent gameIntent = new Intent(DeckSelection.this, GameStart.class);
                gameIntent.putExtra("deckSelected", "wood");
                startActivity(gameIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        customButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
                Intent gameIntent = new Intent(DeckSelection.this, DeckBuilder.class);
                startActivity(gameIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
}
