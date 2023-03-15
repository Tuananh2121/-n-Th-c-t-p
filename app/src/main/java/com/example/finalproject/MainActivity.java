package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnSubmit;
    DatabaseReference mData_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPassword = findViewById(R.id.edt_password);
        edtUsername = findViewById(R.id.edt_username);
        btnSubmit   = findViewById(R.id.btn_submit);

        mData_id     = FirebaseDatabase.getInstance().getReference();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (edtUsername.getText().toString().equals("admin")
                        && edtPassword.getText().toString().equals("admin"))
                {
                    Intent intent_gioithieu = new Intent(MainActivity.this,Menu.class);
                    startActivity(intent_gioithieu);
                    Toast.makeText(getApplicationContext(),
                            "Login Successfully !!! \n Wellcome to My Final Project", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getApplicationContext(),
                            "Username & Password Không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mData_id.child("ID_RFID");
        mData_id.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (Objects.equals(snapshot.getValue(), "SAI THẺ NHIỀU LẦN")) {
                    notification();
                } else if (Objects.equals(snapshot.getValue(), "Thẻ Không Hợp Lệ")) {
                    notification1();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void notification(){
        String message = "BÁO ĐỘNG ";
        String note = "QUẸT SAI THẺ QUÁ NHIỀU LẦN";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"n")
                .setContentText("Notification")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setAutoCancel(true)
                .setContentText(message + note);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
    }
    private void notification1(){
        String message = "Thẻ Không Hợp Lệ";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"n")
                .setContentText("Notification")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setAutoCancel(true)
                .setContentText(message);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
    }
}