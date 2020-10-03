package com.tamimehsan.boticabot;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class sendAngle extends AppCompatActivity {

    private Button buttonSend;
    private EditText firstMotorEditText;
    private EditText secondMotorEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_angle);

        final Bluetooth bluetooth = Bluetooth.getInstance();

        buttonSend = (Button) findViewById(R.id.buttonSend);
        firstMotorEditText = (EditText) findViewById(R.id.firstMotorEditText);
        secondMotorEditText = (EditText) findViewById(R.id.secondMotorEditText);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String angle1 = firstMotorEditText.getText().toString().trim();
                if( angle1 == null )
                    angle1 = "0000";
                String angle2 = secondMotorEditText.getText().toString().trim();
                if(angle2 == null)
                    angle2 = "0000";
                int num1 = Integer.parseInt(angle1);
                int num2 = Integer.parseInt(angle2);
                int finalInt = num1*1000+num2;
                bluetooth.sendSignal(finalInt+"");
            }
        });

    }
}