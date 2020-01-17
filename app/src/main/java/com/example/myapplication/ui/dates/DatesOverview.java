package com.example.myapplication.ui.dates;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ui.chart.ChartOverview;

public class DatesOverview extends Fragment {

    public static DatesOverview newInstance() {
        return new DatesOverview();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dates_overview_fragment, container, false);
        final TextView textView = root.findViewById(R.id.text_dates);
        textView.setText("Value set");
        return root;
    }

}
