package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.data.DBService;
import com.example.myapplication.ui.ErrorMessage;
import com.example.myapplication.ui.chart.ChartOverview;
import com.example.myapplication.ui.dates.DatesOverview;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;
import com.example.myapplication.ui.ProgressBar.CircularProgressBar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, SensorEventListener, TextView.OnEditorActionListener, ActivityCompat.OnRequestPermissionsResultCallback {


    private SensorManager sensorManager;
    private Sensor mStep;
    private int steps = 0;
    private boolean permissionGranted = false;
    CircularProgressBar circularProgressBar;
    private static final int MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION = 982;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSensor();
        if(permissionGranted){
            mStep = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            sensorManager.registerListener(this, mStep, SensorManager.SENSOR_DELAY_NORMAL);
        }
        DBService.getInstance().init(this.getApplicationContext());
        steps = DBService.getInstance().getSteps();
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, ChartOverview.newInstance()).commit();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        circularProgressBar = findViewById(R.id.circularProgress);
        circularProgressBar.setmMaxProgress(DBService.getInstance().getTarget());
        circularProgressBar.setTextColor(getColor(R.color.colorPrimary));
        circularProgressBar.setProgress(steps);
        circularProgressBar.setProgressColor(getColor(R.color.colorPrimary));


        EditText editText = findViewById(R.id.targetEdit);
        editText.setText(DBService.getInstance().getTarget() + "");
        editText.setOnEditorActionListener(this);

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
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_STEP_DETECTOR) {
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

    private boolean requestPermission(){
         /*
        / Check if the App has access rights for the STEP data
        */
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACTIVITY_RECOGNITION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACTIVITY_RECOGNITION},
                        MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            permissionGranted = true;
        }
        return permissionGranted;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true;

                }
                return;
            }

        }
    }

    private boolean checkSensor(){

        boolean available = true;
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            requestPermission();
        } else {
            ErrorMessage errorMsg = new ErrorMessage();
            errorMsg.createDialog("Der Sensor ist nicht im Handy vorhanden!", "Error Message", MainActivity.this);
            available = false;
        }
        return available;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            DBService.getInstance().updateTarget(Integer.parseInt(v.getText().toString()));
            circularProgressBar.setmMaxProgress(DBService.getInstance().getTarget());
            circularProgressBar.setProgress(steps);
            return true;

        }
        return false;
    }
}