package com.example.elementtwoplayergame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class ElementHand extends AppCompatActivity {
    private ArrayList<Drawable> elementsInHand;
    private String playerID;

        void ShowElements(ArrayList<Drawable> elements, String player){
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

        }
}





        /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Your Unused Elements - If you hit 0, you lose!");

        LinearLayout handLayout = new LinearLayout(getActivity());
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(getActivity());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        horizontalScrollView.setLayoutParams(layoutParams);
        handLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (Drawable element:
             elements) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageDrawable(element);
            handLayout.addView(imageView);
        }

        horizontalScrollView.addView(handLayout);
        builder.setView(horizontalScrollView);

        builder.setPositiveButton("back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }*/
