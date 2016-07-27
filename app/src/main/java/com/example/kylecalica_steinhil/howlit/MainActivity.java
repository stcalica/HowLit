package com.example.kylecalica_steinhil.howlit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.FloatProperty;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;


public class MainActivity extends Activity implements SensorEventListener {

    private SensorManager sM;
    private Sensor accel;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    private long lastUpdate = 0;
    private float last_x, last_y, last_z, score;
    private static final String TAG = "SHAKING";
    private String artist;
    private static final int SHAKE_THRESHOLD_LOW = 17;
    private static final int SHAKE_THRESHOLD_MID = 40;
    private static final int SHAKE_THRESHOLD_HIGH = 110;

    private String[] artist_list = new String[4];

//    public static float high;
//    public static float last_high;

    public static final float smoking = 5;
    public static final float ember = 10;
    public static final float flame = 22;
    public static final float lit = 40;
    public static final float nuclear = 80;




    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("artists.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artist_list[0] = "The Weekend";
        artist_list[1] = "Iggy Azella";
        artist_list[2] = "Ice Cube";
        artist_list[3] = "Eazy E";

//
//        try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset());
//            Log.v("JSON", obj.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        final ProgressBar pb = (ProgressBar) findViewById(R.id.pb);
       // final Button btn = (Button) findViewById(R.id.btn);
        score = 0;
        //drawFire();

        // sensor init

        sM = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sM.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);

        Intent intent = getIntent();
        if(intent != null){
            artist = intent.getStringExtra("artist");
            if(artist == null ){
                Toast.makeText(MainActivity.this, "No Artist Selected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, artist, Toast.LENGTH_SHORT).show();
            }
        }


        // init
//        high = 0;
//        last_high = 0;
        lastUpdate = System.currentTimeMillis();

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                progressStatus = 0;
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        while (progressStatus < 100) {
//                            progressStatus += 1;
//                            try {
//                                Thread.sleep(20);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            handler.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    pb.setProgress(progressStatus);
//                                }
//                            });
//                        }
//                    }
//                }
//                ).start();
//            }
//        });
    }


    public void listArtists(View view){
        Intent listArtists = new Intent(MainActivity.this, ListArtist.class);
        if(artist != null){
            //update artist with API
            Toast.makeText(MainActivity.this, "Updated "+ artist + " with score of " + Float.toString(score), Toast.LENGTH_SHORT ).show();
        }

        startActivity(listArtists);
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
            //differences
            float speed = Math.abs(x + y + z - last_x - last_y - last_z);
            if(speed > SHAKE_THRESHOLD_LOW ){
                if(speed > SHAKE_THRESHOLD_MID){
                    if(speed > (SHAKE_THRESHOLD_HIGH)) {
                        score = (float) (score + 0.5);
                    } else {
                        score = (float) (score + 0.05);
                    }
                }  else {
                        score = (float) (score + 0.01);
                }

            }

            //Log.v(TAG, "speed: " + Float.toString(speed));
            setLevel(score);

        }
    }

    public void setLevel(float speed) {

        TextView scoreboard = (TextView) findViewById(R.id.score);
        scoreboard.setText(Float.toString(speed));


//        if (speed > high){
            if (speed > smoking) {
                drawFire(500);
//                setHighest(smoking);
                setMessage(smoking);

            }
            if (speed > ember) {
                drawFire(300);
//                setHighest(ember);
                setMessage(ember);

            }
            if (speed > flame) {
                drawFire(100);
//                setHighest(flame);
                setMessage(flame);

            }
            if (speed > lit) {

//                setHighest(lit);
                setMessage(lit);

            }

            if (speed > nuclear) {

//                setHighest(nuclear);
                setMessage(nuclear);

            }
//        }
    }



//    public void setHighest(float level) {
//
//        Log.v(TAG, "LEVEL: " + Float.toString(level) );
//        if (level > high) {
//
//            high = level;
//            Log.v(TAG, "NEWEST HIGH IS      " + Float.toString(level));
//
//        }
//
//    }

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

    private void drawFire(long speed) {
        ImageView imageView = (ImageView) findViewById(R.id.fire);
        imageView.setImageResource(R.drawable.fire_emoji);

        AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(speed);
        fadeIn.setRepeatCount(Animation.INFINITE);
        fadeIn.setRepeatMode(Animation.REVERSE);

        imageView.startAnimation(fadeIn);
    }


    }


