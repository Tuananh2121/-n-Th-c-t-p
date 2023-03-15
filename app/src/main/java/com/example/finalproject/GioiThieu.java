package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GioiThieu extends AppCompatActivity {

    Button btn_batdau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);

        btn_batdau    = (Button) findViewById(R.id.batdau);
        btn_batdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_menu = new Intent(GioiThieu.this,Menu.class);
                startActivity(intent_menu);
            }
        });
    }
}