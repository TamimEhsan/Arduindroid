package com.tamimehsan.boticabot;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ledControl extends AppCompatActivity {

    Button buttonForward, buttonBackward, buttonLeft ,buttonRight,buttonStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_control);

        buttonForward = (Button) findViewById(R.id.buttonForward);
        buttonBackward = (Button) findViewById(R.id.buttonBackward);
        buttonRight = (Button) findViewById(R.id.buttonRight);
        buttonLeft = (Button) findViewById(R.id.buttonLeft);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        final Bluetooth bluetooth = Bluetooth.getInstance();
        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetooth.sendSignal("F");
            }
        });
        buttonBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetooth.sendSignal("B");
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetooth.sendSignal("R");
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetooth.sendSignal("L");
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetooth.sendSignal("D");
            }
        });

    }
}