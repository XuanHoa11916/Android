package com.example.lyquocvan_caithienexe_1_5;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainLaBan extends AppCompatActivity implements SensorEventListener {
    private ImageView imageView;
    private ImageView kimlaban;
    private float[] mGravity = new float[3];
    private float[] mGeomagnetic = new float[3];
    private float azimuth = 0f;
    private float currectAzimuth = 0f;
    private SensorManager mSensorManager;
    private TextView toado;
    Spinner spinner;
    Spinner spinners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_laban);
        imageView = (ImageView) findViewById(R.id.compass);
        kimlaban = (ImageView) findViewById(R.id.kimlaban);
        toado = (TextView) findViewById(R.id.toado);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinners = (Spinner) findViewById(R.id.spinners);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        addSpinnerCompass();
        addSpinnerK();
    }



    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    private void addSpinnerCompass() {
        List<String> list = new ArrayList<>();
        list.add(" ");
        list.add("Compass1");
        list.add("Compass2");
        list.add("Compass3");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinner.getItemAtPosition(position).toString() == " ") {
                    imageView.setImageResource(R.drawable.dv);
                }if (spinner.getItemAtPosition(position).toString() == "Compass1") {
                    imageView.setImageResource(R.drawable.lb1);
                }
                if (spinner.getItemAtPosition(position).toString() == "Compass2") {
                    imageView.setImageResource(R.drawable.lp2);
                }
                if (spinner.getItemAtPosition(position).toString() == "Compass3") {
                    imageView.setImageResource(R.drawable.dv);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void addSpinnerK() {
        List<String> lists = new ArrayList<>();
        lists.add(" ");
        lists.add("k1");
        lists.add("k2");
        lists.add("k3");
        lists.add("k4");

        ArrayAdapter<String> adapters = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, lists);
        adapters.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinners.setAdapter(adapters);

        spinners.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinners.getItemAtPosition(position).toString() == " ") {
                    kimlaban.setImageResource(R.drawable.labann);
                }
                if (spinners.getItemAtPosition(position).toString() == "k1") {
                    kimlaban.setImageResource(R.drawable.k1);
                }
                if (spinners.getItemAtPosition(position).toString() == "k2") {
                    kimlaban.setImageResource(R.drawable.k2);
                }
                if (spinners.getItemAtPosition(position).toString() == "k3") {
                    kimlaban.setImageResource(R.drawable.k3);
                }
                if (spinners.getItemAtPosition(position).toString() == "k4") {
                    kimlaban.setImageResource(R.drawable.labann);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        final float alpha = 0.97f;
        synchronized (this) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                mGravity[0] = alpha * mGravity[0] + (1 - alpha) * sensorEvent.values[0];
                mGravity[1] = alpha * mGravity[1] + (1 - alpha) * sensorEvent.values[1];
                mGravity[2] = alpha * mGravity[2] + (1 - alpha) * sensorEvent.values[2];
            }
            if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                mGeomagnetic[0] = alpha * mGeomagnetic[0] + (1 - alpha) * sensorEvent.values[0];
                mGeomagnetic[1] = alpha * mGeomagnetic[1] + (1 - alpha) * sensorEvent.values[1];
                mGeomagnetic[2] = alpha * mGeomagnetic[2] + (1 - alpha) * sensorEvent.values[2];
            }
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimuth = (float) Math.toDegrees(orientation[0]);
                azimuth = (azimuth + 360) % 360;
                Animation anim = new RotateAnimation(-currectAzimuth, -azimuth, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                currectAzimuth = azimuth;
                anim.setDuration(500);
                anim.setRepeatCount(0);
                anim.setFillAfter(true);
                imageView.startAnimation(anim);
                float a = Math.round(azimuth * 10) / 10;
                if (a < 23 || a >= 338) {
                    toado.setText(String.valueOf(a) + "° Bắc");
                }
                if (a >= 23 && a <= 68) {
                    toado.setText(String.valueOf(a) + "° Đông Bắc");
                }
                if (a > 68 && a <= 112) {
                    toado.setText(String.valueOf(a) + "° Đông");
                }
                if (a > 112 && a <= 168) {
                    toado.setText(String.valueOf(a) + "° Đông Nam");
                }
                if (a > 168 && a <= 202) {
                    toado.setText(String.valueOf(a) + "° Nam");
                }
                if (a > 202 && a <= 247) {
                    toado.setText(String.valueOf(a) + "° Tây Nam");
                }
                if (a > 247 && a <= 292) {
                    toado.setText(String.valueOf(a) + "° Tây");
                }
                if (a > 292 && a < 338) {
                    toado.setText(String.valueOf(a) + "° Tây Bắc");
                }
            }
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}