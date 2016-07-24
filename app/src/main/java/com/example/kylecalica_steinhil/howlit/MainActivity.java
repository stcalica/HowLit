package com.example.kylecalica_steinhil.howlit;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import java.util.logging.Logger;


public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sM;
    private Sensor accel;


    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 5;
    private static final String TAG = "SHAKING";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sensor init

        sM = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sM.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);

        // init

        lastUpdate = System.currentTimeMillis();

//
//        last_x = 0;
//        last_y = 0;
//        last_z = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];
        long cur = System.currentTimeMillis();
        if ((cur - lastUpdate) > 100) {

            long diff = (cur - lastUpdate);
            lastUpdate = cur;

            float speed = Math.abs(x + y + z - last_x - last_y - last_z);
            Log.v(TAG, "speed: "+ Float.toString(speed)  );

//            switch (speed){
//
//
//            }


        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
