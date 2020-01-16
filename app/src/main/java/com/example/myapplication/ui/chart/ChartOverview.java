package com.example.myapplication.ui.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDestination;

import com.example.myapplication.R;
import com.example.myapplication.data.History;
import com.example.myapplication.data.HistoryDataObject;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartOverview extends Fragment {

    private ChartOverviewViewModel mViewModel;
    private LineChart chart;

    public static ChartOverview newInstance() {
        return new ChartOverview();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.chart_overview_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChartOverviewViewModel.class);


        chart = (LineChart) getView().findViewById(R.id.linechart);

        History history = new History();
        List<HistoryDataObject> objects = history.getObjects();
        List<Entry> entries = new ArrayList<Entry>();

        for (int i=0;i< objects.size(); i++){
            HistoryDataObject o = objects.get(i);
            entries.add(new Entry(i, o.getSteps()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK); // styling, ...

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate(); // refresh


        // TODO: Use the ViewModel


    }





}
