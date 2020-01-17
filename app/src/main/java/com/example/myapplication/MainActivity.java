package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.myapplication.ui.chart.ChartOverview;
import com.example.myapplication.ui.dates.DatesOverview;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ProgressBar.CircularProgressBar;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_chart, R.id.navigation_dates)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        CircularProgressBar circularProgressBar = findViewById(R.id.circularProgress);
        circularProgressBar.setProgress(62);
        circularProgressBar.setProgressColor(Color.rgb(51,181,189));
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
      Log.e("es geht", "ja");
        return true;
    }

}