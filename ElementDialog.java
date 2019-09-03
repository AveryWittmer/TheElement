package com.example.elementtwoplayergame;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ElementDialog extends Fragment
{
    private ArrayList<Drawable> elementsInHand;
    private String playerID;
    private int x2;
    private int y2;

    public ElementDialog(ArrayList<Drawable> elements, String player){
        elementsInHand = elements;
        playerID = player;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test, container, false);

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        /*layoutParams.leftMargin = 50;
        layoutParams.rightMargin = 50;
        layoutParams.topMargin = 50;
        layoutParams.bottomMargin = 50;*/
        view.setLayoutParams(layoutParams);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getRawX();
                int y = (int) motionEvent.getRawY();
                switch(motionEvent.getAction() & MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_DOWN:
                        FrameLayout.LayoutParams downParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                        x2 = x - downParams.leftMargin;
                        y2 = y - downParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        FrameLayout.LayoutParams upParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                        x2 = x - upParams.leftMargin;
                        y2 = y - upParams.topMargin;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        FrameLayout.LayoutParams newParams2 = (FrameLayout.LayoutParams) view.getLayoutParams();
                        newParams2.leftMargin = x - x2;
                        newParams2.topMargin = y - y2;
                        view.setLayoutParams(newParams2);
                        break;
                }
                return true;
            }
        });
        final LinearLayout handLayout = view.findViewById(R.id.handLayout);
        for (Drawable element:
                elementsInHand) {
            final ImageView imageView = new ImageView(view.getContext());
            imageView.setImageDrawable(element);
            handLayout.addView(imageView);
        }
        if(playerID.equals("Opponent")){
            view.setRotation(180);
        }
        return view;
    }
}

