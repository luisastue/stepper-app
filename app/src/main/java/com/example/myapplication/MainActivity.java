package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;

import com.example.myapplication.data.DBService;
import com.example.myapplication.data.ErrorMessage;
import com.example.myapplication.ui.chart.ChartOverview;
import com.example.myapplication.ui.dates.DatesOverview;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.ui.ProgressBar.CircularProgressBar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, SensorEventListener {


    private SensorManager sensorManager;
    private Sensor mStep;
    private int steps = 0;
    CircularProgressBar circularProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DBService.getInstance().init(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, ChartOverview.newInstance()).commit();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        circularProgressBar = findViewById(R.id.circularProgress);
        circularProgressBar.setProgress(62);
        circularProgressBar.setProgressColor(Color.rgb(51, 181, 189));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_chart:
                selectedFragment = ChartOverview.newInstance();
                break;
            case R.id.navigation_dates:
                selectedFragment = DatesOverview.newInstance();
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            mStep = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        } else {
            ErrorMessage errorMsg = new ErrorMessage();
            errorMsg.createDialog("Sensor im Handy nicht vorhanden!", "Error Message", MainActivity.this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.equals(mStep)) {
            steps++;
            circularProgressBar.setProgress(steps);
            if (steps % 100 == 0) {
                DBService.getInstance().updateSteps(steps);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}