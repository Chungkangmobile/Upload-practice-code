package com.example.d0523b;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
Button btn1,btn2;
myPictureView myPictureView1;
int curNum=0;
File[] imageFiles=new File[0];
String imageFname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setTitle("이미지 불러오기");
        ActivityCompat.requestPermissions(this, new String[]{
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, MODE_PRIVATE);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        myPictureView1 = (myPictureView) findViewById(R.id.myPictureView1);
        File[] allFiles = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures").listFiles();
        for (int i = 0; i < allFiles.length; i++) {
            if (allFiles[i].isFile()) {
                imageFiles = Arrays.copyOf(imageFiles, imageFiles.length + 1);
                imageFiles[imageFiles.length - 1] = allFiles[i];
            }
            imageFname = imageFiles[curNum].toString();
            myPictureView1.imagePath = imageFname;
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curNum <= 0) {
                    Toast.makeText(getApplicationContext(), "첫장입니다.", Toast.LENGTH_LONG).show();
                    ;
                } else {
                    curNum--;
                    imageFname = imageFiles[curNum].toString();
                    myPictureView1.imagePath = imageFname;
                    myPictureView1.invalidate();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curNum >= imageFiles.length - 1) {
                    Toast.makeText(getApplicationContext(), "마지막장 입니다.", Toast.LENGTH_LONG).show();
                } else {
                    curNum++;
                    myPictureView1.imagePath = imageFname;
                    myPictureView1.invalidate();
                }
            }
        });
    }

    }
