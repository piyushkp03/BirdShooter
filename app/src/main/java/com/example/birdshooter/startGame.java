package com.example.birdshooter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import static android.widget.Toast.*;

public class startGame extends Activity {

    GameView gv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        gv = new GameView(this);
        super.onCreate(savedInstanceState);
        setContentView(gv);
    }


}
