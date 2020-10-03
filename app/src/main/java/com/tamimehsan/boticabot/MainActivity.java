package com.tamimehsan.boticabot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button connectBTButton;
    private Button goToActivity;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_main);

        final Bluetooth bluetooth = Bluetooth.getInstance();
        bluetooth.setContext(this);
        connectBTButton = (Button) findViewById(R.id.connectBTButton);
        goToActivity = (Button) findViewById(R.id.goToActivity);
        connectBTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ConnectBTActivity.class);
                startActivity(intent);
            }
        });
        goToActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, sendAngle.class);
                startActivity(intent);
            }
        });
    }
}
