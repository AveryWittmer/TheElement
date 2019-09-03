package com.example.elementtwoplayergame;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CustomDialog extends DialogFragment {
    private ArrayList<Drawable> elementsInHand;
    private String playerID;

    public CustomDialog(ArrayList<Drawable> elements, String player){
        elementsInHand = elements;
        playerID = player;
    }

    public interface HandInterface{
        void DisplayElements(ArrayList<Drawable> elements, String player);
    }

    private HandInterface handInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.custom_dialog, container, false);

        HorizontalScrollView horizontalScrollView = v.findViewById(R.id.horizontalScrollView);
        LinearLayout handLayout = v.findViewById(R.id.handLayout);

        ElementHand elementHand = new ElementHand();
        elementHand.ShowElements(elementsInHand, playerID);
        Button elementButton = v.findViewById(R.id.elementButton);
        elementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handInterface.DisplayElements(elementsInHand, playerID);
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof HandInterface){
            handInterface = (HandInterface) context;
        }
        else{
            throw new RuntimeException("Error in this context: " + context.toString()
                    + ": Must implement HandInterface.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        handInterface = null;
    }

}

/*
    public void ShowElements(ArrayList<Drawable> elements, String player){
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

        horizontalScrollView.addView(handLayout);

        dialog.show();
        /*ElementHand elementHand = new ElementHand();
        elementHand.elements = elementsInHand;
        if(player.equals("Self")){
            elementHand.player = "Self"; //do stuff with this at some point
        }
        else{
            elementHand.player = "Opponent";
        }
        elementHand.show(getSupportFragmentManager(), "element hand");
    }*/

