package com.example.elementtwoplayergame;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

public class CustomButtonClass extends AppCompatImageView {
    public CustomButtonClass(Context context){
        super(context);
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        super.onTouchEvent(motionEvent);
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                performClick();
                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return false;
    }

    @Override
    public boolean performClick(){
        super.performClick();
        return false;
    }
}
