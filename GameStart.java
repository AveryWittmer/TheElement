package com.example.elementtwoplayergame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class GameStart extends AppCompatActivity implements CustomDialog.HandInterface {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        final String deckSelected = getIntent().getStringExtra("deckSelected");
        final ArrayList<Creature> creatureInfo = GetCardInfo(FileRelated.ChooseExcelFile(deckSelected));
        final ArrayList<Creature> opponentInfo = GetCardInfo(FileRelated.ChooseExcelFile(FileRelated.RandomDeck()));

        final ArrayList<Creature> creatures = GetImages(deckSelected, creatureInfo);
        final ArrayList<Creature> opponentCreatures = GetImages(FileRelated.RandomDeck(), opponentInfo);

        final ArrayList<Drawable> elements = DrawHand(GetElements(deckSelected));

        final Button elementButton = findViewById(R.id.elementButton);
        elementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CardView cardView = findViewById(R.id.customFragment);
                if(cardView.getVisibility() != View.VISIBLE){
                    cardView.setVisibility(View.VISIBLE);
                    ElementDialog elementDialog = new ElementDialog(elements, "Self");
                    getSupportFragmentManager().beginTransaction().replace(R.id.customFragment, elementDialog).commit();
                    Toast elementWarning = Toast.makeText(getApplicationContext(),
                            "These are your unused elements. When you run out of these, you lose the game. Choose Wisely!",
                            Toast.LENGTH_SHORT);
                    elementWarning.show();
                }
                else if(cardView.getVisibility() == View.VISIBLE){
                    cardView.setVisibility(View.INVISIBLE);
                }
            }
        });

        final ArrayList<Drawable> opponentElements = DrawHand(GetElements(FileRelated.RandomDeck()));

        final Button opponentElementButton = findViewById(R.id.opponentElementButton);
        opponentElementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CardView cardView = findViewById(R.id.customFragment);
                if(cardView.getVisibility() != View.VISIBLE){
                    cardView.setVisibility(View.VISIBLE);
                    ElementDialog elementDialog = new ElementDialog(opponentElements, "Opponent");
                    getSupportFragmentManager().beginTransaction().replace(R.id.customFragment, elementDialog).commit();
                    Toast elementWarning = Toast.makeText(getApplicationContext(),
                            "These are your unused elements. When you run out of these, you lose the game. Choose Wisely!",
                            Toast.LENGTH_SHORT);
                    elementWarning.setGravity(Gravity.CENTER, 0, 0);
                    elementWarning.show();
                }
                else if(cardView.getVisibility() == View.VISIBLE){
                    cardView.setVisibility(View.INVISIBLE);
                }
            }
        });

        ImageButton yourDeck = findViewById(R.id.creatureDeck);
        yourDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair<ArrayList<Creature>, Integer> summon = SummonCreature(creatures, "Self");
                creatures.get(summon.second).location = "Field";
                Toast stats = Toast.makeText(getApplicationContext(),
                        creatures.get(summon.second).effect, Toast.LENGTH_SHORT);
                stats.show();
            }
        });

        ImageButton opponentDeck = findViewById(R.id.opponentDeck);
        opponentDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pair<ArrayList<Creature>, Integer> summon = SummonCreature(opponentCreatures, "Opponent");
                opponentCreatures.get(summon.second).location = "Field";
            }
        });
    }

    ArrayList<Creature> GetCardInfo(String deckSelection){
        AssetManager assetManager = getAssets();

        try {
            List<List<String>> cardData = new ArrayList<>();
            String[] data;
            InputStream testFile = assetManager.open("ExcelSheets/" + deckSelection);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(testFile));
            String csvLine;
            while((csvLine = bufferedReader.readLine()) != null){
                data = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                cardData.add(Arrays.asList(data));
            }

            ArrayList<Creature> creatureInfo = new ArrayList<>();
            Creature[] creature = new Creature[cardData.size()];
            for(int i=1; i<cardData.size(); i++){
                creature[i] = new Creature();
                creature[i].name = cardData.get(i).get(0);
                creature[i].imageInGame = cardData.get(i).get(1);
                creature[i].effect = cardData.get(i).get(2);
                creature[i].matterType = cardData.get(i).get(3);
                creature[i].classLetter = cardData.get(i).get(4);
                creature[i].power = Integer.parseInt(cardData.get(i).get(5));
                creatureInfo.add(creature[i]);
            }

            return creatureInfo;
        }
        catch (IOException e){
            return null;
        }
    }

    ArrayList<Creature> GetImages(String deckSelection, ArrayList<Creature> creatureData) {
        String filePath = FileRelated.ChooseDeck(deckSelection);
        try{
            AssetManager assetManager = getAssets();
            String[] cards = assetManager.list(filePath);
            for (int f = 0; f < cards.length; f++) {
                InputStream input = getAssets().open(filePath+ "/" + cards[f]);
                creatureData.get(f).drawableImage = Drawable.createFromStream(input, null);
            }
            return creatureData;
        }
        catch(IOException e){
            return null;
        }
    }

    ArrayList<Drawable> GetElements(String deckSelection) {
        String filePath = FileRelated.ChooseElements(deckSelection);
        try{
            AssetManager assetManager = getAssets();
            String[] cards = assetManager.list(filePath);
            ArrayList<Drawable> drawableCards = new ArrayList<>();
            for (int f = 0; f < cards.length; f++) {
                InputStream input = getAssets().open(filePath + "/" + cards[f]);
                Drawable card = Drawable.createFromStream(input, null);
                drawableCards.add(card);
            }
            return drawableCards;
        }
        catch(IOException e){
            return null;
        }
    }
    Pair<ArrayList<Creature>, Integer> SummonCreature(ArrayList <Creature> creatureCards, String player) {
        LinearLayout cardsOnField;
        if(player.equals("Self")){
            cardsOnField = findViewById(R.id.yourLayout);
        }
        else{
            cardsOnField = findViewById(R.id.opponentLayout);
        }
        float pixelWidth = cardsOnField.getLayoutParams().width * getApplicationContext().getResources().getDisplayMetrics().density;
        float pixelHeight = cardsOnField.getLayoutParams().height * getApplicationContext().getResources().getDisplayMetrics().density;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) pixelWidth, (int) pixelHeight);

        int randomIndex = new Random().nextInt(creatureCards.size());
        while(!creatureCards.get(randomIndex).location.equals("Deck")){
            randomIndex++;
        }
        ImageView card = new ImageView(getApplicationContext());
        card.setImageDrawable(creatureCards.get(randomIndex).drawableImage);
        card.setLayoutParams(layoutParams);
        if(player.equals("Opponent")){
            card.setRotationX(180);
        }
        cardsOnField.addView(card);

        creatureCards.get(randomIndex).location = "Field";

        return new Pair<>(creatureCards, randomIndex);
    }

    ArrayList<Drawable> DrawHand(ArrayList <Drawable> elementsInDeck){
        ArrayList<Drawable> cardsInHand = new ArrayList<>();
        int handSize = 5;
        for(int i=0; i<handSize; i++){
            int randomIndex2 = new Random().nextInt(elementsInDeck.size());
            cardsInHand.add(elementsInDeck.get(randomIndex2));
            elementsInDeck.remove(randomIndex2);
        }
        return cardsInHand;
    }

    @Override
    public void DisplayElements(ArrayList<Drawable> elements, String player) {
        setContentView(R.layout.custom_dialog);
        Dialog dialog = new Dialog(getApplicationContext());
        dialog.setTitle("Your Unused Elements - If you hit 0, you lose!");

        LinearLayout handLayout = findViewById(R.id.handLayout);
        HorizontalScrollView horizontalScrollView = findViewById(R.id.horizontalScrollView);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        horizontalScrollView.setLayoutParams(layoutParams);
        handLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (Drawable element:
                elements) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageDrawable(element);
            handLayout.addView(imageView);
        }

        //horizontalScrollView.addView(handLayout);

        dialog.show();
    }
}
