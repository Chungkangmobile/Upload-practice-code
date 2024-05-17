package com.example.d0516_2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
DatePicker datePicker1;
EditText edittext1;
Button button1;
String filname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setTitle("데스노트");

        datePicker1 = (DatePicker) findViewById(R.id.datepicker1);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        button1 = (Button) findViewById(R.id.button1);

        Calendar cal = Calendar.getInstance();
        int y = cal.get(Calendar.YEAR);       //연
        int m = cal.get(Calendar.MONTH);      //월
        int d = cal.get(Calendar.DAY_OF_YEAR);//일

        datePicker1.init(y, m, d, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int i, int i1, int i2) {
                filname = Integer.toString(i) + "_"
                        + Integer.toString(i1) + "_"
                        + Integer.toString(i2) + ".txt";
                String str = readDiary(filname);  //일기내용
                edittext1.setText(str); //EditText에 보이기
                button1.setEnabled(true);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream out = openFileOutput(filname, Context.MODE_PRIVATE);
                    String str = edittext1.getText().toString();
                    out.write(str.getBytes());
                    out.close();
                    Toast.makeText(getApplicationContext(), filname + "이(가)저장되었습니다", Toast.LENGTH_LONG).show();
                } catch (IOException e) {

                }
            }
        });
    }
        String readDiary(String fname){
            String diayystr =null;
            FileInputStream in;
            try {
                in=openFileInput(fname);
                byte[] txt=new byte[1000];
                in.read(txt);
                in.close();
                diayystr=(new String(txt)).trim();
                button1.setText("수정");
            }catch (IOException e){
                edittext1.setHint("No File");
                button1.setText("새로저장");
            }
        return diayystr;

    }
}