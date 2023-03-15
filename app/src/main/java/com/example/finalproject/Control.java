package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Control extends AppCompatActivity {

    DatabaseReference mData_lock;
    /*DatabaseReference mData_camera;*/

    Button btn_open;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    /*Switch swt_cam;*/
   /* WebView ipcam;*/
    ImageView img_lock;
    EditText edt_lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

       /* swt_cam = findViewById(R.id.swt_cam);*/
       /* ipcam = findViewById(R.id.imgView_cam);
        ipcam.setWebViewClient(new WebViewClient());*/

        btn_open    = (Button)    findViewById(R.id.btn_open);
        /*swt_cam     = (Switch)    findViewById(R.id.swt_cam);*/
        img_lock    = (ImageView) findViewById(R.id.lock_main);
        edt_lock    = (EditText)  findViewById(R.id.edt_status);

        mData_lock   = FirebaseDatabase.getInstance().getReference();
       /* mData_camera = FirebaseDatabase.getInstance().getReference();*/
/*
        ipcam.loadUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Hcmute.svg/355px-Hcmute.svg.png");
*/

        mData_lock.child("Status_Door").setValue("Cửa Đóng");
       /* mData_camera.child("Status_Camera").setValue("Camera Off");*/

        mData_lock.child("Status_Door").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // edt_lock.setText(snapshot.getValue().toString());
                if(snapshot.getValue().toString().equals("Cửa Mở"))
                {
                    img_lock.setImageResource(R.drawable.lock5);
                    edt_lock.setText("Cửa Mở");
                }
                else if(snapshot.getValue().toString().equals("Cửa Đóng"))
                {
                    img_lock.setImageResource(R.drawable.lock10);
                    edt_lock.setText("Cửa Đóng");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData_lock.child("Status_Door").setValue("Cửa Mở");
                img_lock.setImageResource(R.drawable.lock5);
                edt_lock.setText("Cửa Mở");
                // delay
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        mData_lock.child("Status_Door").setValue("Cửa Đóng");
                        img_lock.setImageResource(R.drawable.lock10);
                        edt_lock.setText("Cửa Đóng");
                    }
                }, 5000);
            }
        });

    /*    //bắt sự kiện từ Firebase
        mData_lock.child("Status_door").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                edt_lock.setText(snapshot.getValue().toString());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData_lock.child("Status_door").setValue("Cửa Mở");
                img_lock.setImageResource(R.drawable.lock5);
                edt_lock.setText("Cửa Mở");
                // delay
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        mData_lock.child("Status_door").setValue("Cửa Đóng");
                        img_lock.setImageResource(R.drawable.lock10);
                        edt_lock.setText("Cửa Đóng");
                    }
                }, 5000);
            }
        });*/

       /* swt_cam.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void onClick(View v) {
                if(swt_cam.isChecked()){
                    WebSettings webSettings = ipcam.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    ipcam.loadUrl("http://192.168.1.115/mjpeg/1\n");
                    mData_lock.child("Status_Camera").setValue("Camera ON");
                }else{
                    WebSettings webSettings = ipcam.getSettings();
                    webSettings.setJavaScriptEnabled(false);
                    ipcam.loadUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/0/0c/Hcmute.svg/355px-Hcmute.svg.png");
                    mData_lock.child("Status_Camera").setValue("Camera OFF");
                }
            }
        });*/
    }
}