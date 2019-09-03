package com.example.elementtwoplayergame;


import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;


public class Creature extends AppCompatActivity {
    String name;
    String imageInGame;
    Drawable drawableImage;
    String effect;
    String matterType;
    String classLetter;
    int power;
    String location = "Deck";
    /* Valid values for 'location:
    Deck, Field, Graveyard.
     */

    public int ChangePower(int newPower){
        power = newPower;
        return power;
    }
    public String ChangeLocation(String newLocation){
        location = newLocation;
        return location;
    }
}
