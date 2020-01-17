package com.example.myapplication.ui.chart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
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

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;

public class ChartOverview extends Fragment {

    private LineChart chart;

    public static ChartOverview newInstance() {
        return new ChartOverview();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.chart_overview_fragment, container, false);
        chart = root.findViewById(R.id.linechart);
        chart.setData(getLineData());

        chart.invalidate();
        return root;
    }


    public LineData getLineData(){
        History history = new History();
        List<HistoryDataObject> objects = history.getObjects();
        List<Entry> entries = new ArrayList<Entry>();

        for (int i=0;i< objects.size(); i++){
            HistoryDataObject o = objects.get(i);
            entries.add(new Entry(i, o.getSteps()));
        }

        LineDataSet dataSet = new LineDataSet(entries, "Graph"); // add entries to dataset
        dataSet.setColor(BLUE);
        dataSet.setValueTextColor(BLACK); // styling, ...

        return new LineData(dataSet);
    }



}
