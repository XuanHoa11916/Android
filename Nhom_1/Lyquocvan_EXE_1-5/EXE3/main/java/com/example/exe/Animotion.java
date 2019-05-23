package com.example.exe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Animotion extends AppCompatActivity {
    private ImageView diabay;
    private ImageView saobang;
    private ImageView saobang01;
    private ImageView saobang02;
    private ImageView phithuyen;
    private ImageView sao;
    private ImageView sao01;
    private ImageView sao02;
    private ImageView sao03;
    private ImageView stara;
    private ImageView starb;
    private ImageView traidat;
    private ImageView tenlua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animotion_layout);
        setControl();
        setEvent();

    }

    public void setControl() {
        diabay = (ImageView) findViewById(R.id.diabay);
        saobang = (ImageView) findViewById(R.id.saobang);
        saobang01 = (ImageView) findViewById(R.id.saobang01);
        saobang02 = (ImageView) findViewById(R.id.saobang02);
        phithuyen = (ImageView) findViewById(R.id.phithuyen);
        sao = (ImageView) findViewById(R.id.sao);
        sao01 = (ImageView) findViewById(R.id.sao01);
        sao02 = (ImageView) findViewById(R.id.sao02);
        sao03 = (ImageView) findViewById(R.id.sao03);
        stara = (ImageView) findViewById(R.id.stara);
        starb = (ImageView) findViewById(R.id.starb);
        traidat = (ImageView) findViewById(R.id.traidat);
        tenlua = (ImageView) findViewById(R.id.tenlua);
    }

    public void setEvent() {
        traidat();
        stara();
        starb();
        sao();
        sao01();
        sao02();
        sao03();
        phithuyen();
        saobang();
        diabay();
        tenlua();
    }

    public void tenlua() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_o);
        tenlua.startAnimation(animation);

    }

    public void diabay() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoominzomout);
        diabay.startAnimation(animation);

    }

    public void saobang() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animotion_move);
        saobang.startAnimation(animation);
        saobang01.startAnimation(animation);
        saobang02.startAnimation(animation);
    }

    public void phithuyen() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        phithuyen.startAnimation(animation);
    }

    public void starb() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        starb.startAnimation(animation);
    }

    public void stara() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        stara.startAnimation(animation);
    }

    public void traidat() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        traidat.startAnimation(animation);
    }

    public void sao() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        sao.startAnimation(animation);
    }

    public void sao01() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        sao01.startAnimation(animation);
    }

    public void sao02() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        sao02.startAnimation(animation);
    }

    public void sao03() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        sao03.startAnimation(animation);
    }
}
