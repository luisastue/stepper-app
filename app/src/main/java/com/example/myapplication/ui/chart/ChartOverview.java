package com.example.myapplication.ui.chart;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDestination;
import androidx.room.Room;

import com.example.myapplication.R;
import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.DBService;
import com.example.myapplication.data.History;
import com.example.myapplication.data.HistoryDataObject;
import com.example.myapplication.data.HistoryDataObjectDAO;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;

public class ChartOverview extends Fragment {

    private LineChart chart;
    private List<HistoryDataObject> historyList;

    public static ChartOverview newInstance() {
        return new ChartOverview();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.chart_overview_fragment, container, false);
        historyList = DBService.getInstance().getLastFive();
        TableLayout layout = root.findViewById(R.id.chartlayout);

        if(historyList.size() > 0) {
            chart = new LineChart(getContext());
            chart.setMinimumHeight(350);
            chart.setDescription(null);
            chart.setDrawGridBackground(false);
            chart.setDrawBorders(false);
            chart.setData(getLineData());
            chart.invalidate();
            layout.addView(chart);
        } else {
            TextView textView = new TextView(getContext());
            textView.setText("No steps tracked in the past");
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            layout.addView(textView);
        }
        return root;
    }


    private LineData getLineData(){
        List<Entry> entries = new ArrayList<>();
        List<Entry> targetEntries = new ArrayList<>();
        int e = 0;
        for (int i=(historyList.size()-1);i>=0; i--){

            HistoryDataObject o = historyList.get(i);
            entries.add(new Entry(e, o.getSteps()));
            targetEntries.add(new Entry(e, o.getTarget()));
            e++;
        }

        LineDataSet dataSet = new LineDataSet(entries, "Steps"); // add entries to dataset
        dataSet = style(dataSet, getContext().getColor(R.color.colorPrimary));
        LineDataSet targetSet = new LineDataSet(targetEntries, "Targets"); // add entries to dataset
        targetSet = style(targetSet, getContext().getColor(R.color.colorAccent));
        return new LineData(dataSet, targetSet);
    }

    private LineDataSet style(LineDataSet dataSet, int color){
        dataSet.setColor(color);
        dataSet.setValueTextSize(0);

        return dataSet;
    }



}
