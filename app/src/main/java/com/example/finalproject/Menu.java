package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    CardView cardHome;
    CardView cardUser;
    CardView cardControl;
    CardView cardHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cardHome    = findViewById(R.id.cardHome);
        cardUser    = findViewById(R.id.cardUser);
        cardControl    = findViewById(R.id.cardControl);
        cardHistory = findViewById(R.id.cardHistory);

        cardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_home = new Intent(Menu.this,GioiThieu.class);
                startActivity(intent_home);
                showToast("Home Clicked");
            }
        });
/*        cardUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_user = new Intent(Menu.this,User.class);
                startActivity(intent_user);
                showToast("User Clicked");
            }
        });*/
        cardControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_control = new Intent(Menu.this,Control.class);
                startActivity(intent_control);
                showToast("Control Clicked");
            }
        });
        cardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_history = new Intent(Menu.this,History.class);
                startActivity(intent_history);
                showToast("History Clicked");
            }
        });

    }
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}