package com.example.lyquocvan_caithienexe_1_5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_exe1;
    Button btn_exe2;
    Button btn_exe3;
    Button btn_exe4;
    Button btn_exe5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();
        setEvent();
    }

    private void setEvent() {
        btn_exe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button1", Toast.LENGTH_SHORT).show();
            }
        });
        btn_exe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainLaBan.class);
                intent.setFlags(intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent);
            }
        });
        btn_exe3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button3", Toast.LENGTH_SHORT).show();
            }
        });
        btn_exe4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main.class);
                intent.setFlags(intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent);
            }
        });
        btn_exe5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button5", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setControl(){
        btn_exe1 = (Button) findViewById(R.id.btn_exe1);
        btn_exe2 = (Button) findViewById(R.id.btn_exe2);
        btn_exe3 = (Button) findViewById(R.id.btn_exe3);
        btn_exe4 = (Button) findViewById(R.id.btn_exe4);
        btn_exe5 = (Button) findViewById(R.id.btn_exe5);
    }
}
