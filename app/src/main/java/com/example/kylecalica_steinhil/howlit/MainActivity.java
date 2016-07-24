package com.example.kylecalica_steinhil.howlit;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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


    public static float high;
    public static float last_high;

    public static final float smoking = 20;
    public static final float ember = 40;
    public static final float flame = 50;
    public static final float lit = 120;
    public static final float nuclear = 150;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sensor init

        sM = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sM.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);

        // init
        high = 0;
        last_high =0;
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
            Log.v(TAG, "speed: " + Float.toString(speed));
            setLevel(speed);

        }
    }

    public void setLevel(float speed) {


        if (speed > high){
            if (speed > smoking) {
                setHighest(smoking);
                setMessage(smoking);

            }
            if (speed > ember) {

                setHighest(ember);
                setMessage(ember);

            }
            if (speed > flame) {

                setHighest(flame);
                setMessage(flame);

            }
            if (speed > lit) {

                setHighest(lit);
                setMessage(lit);

            }

            if (speed > nuclear) {

                setHighest(nuclear);
                setMessage(nuclear);

            }
        }
    }



    public void setHighest(float level) {

        Log.v(TAG, "LEVEL: " + Float.toString(level) );
        if (level > high) {

            high = level;
            Log.v(TAG, "NEWEST HIGH IS      " + Float.toString(level));

        }

    }

    public void setMessage(float level){
        String smokeMsg = "Move it! Move it!";
        String emberMsg = "Light it up!";
        String flameMsg = "When a fire starts to burn...";
        String litMsg = "IT'S LIT!!!";
        String nuclearMsg = "BOOM! YOUR NUCLEAR!";

        TextView txt = (TextView) findViewById(R.id.message);
        switch((int)level){

            case (int)smoking: txt.setText(smokeMsg); break;

            case (int)ember: txt.setText(emberMsg); break;

            case (int)flame: txt.setText(flameMsg); break;

            case (int)lit: txt.setText(litMsg); break;

            case (int)nuclear: txt.setText(nuclearMsg); break;
        }



    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
