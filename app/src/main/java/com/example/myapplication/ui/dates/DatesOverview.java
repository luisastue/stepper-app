package com.example.myapplication.ui.dates;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.DBService;
import com.example.myapplication.data.HistoryDataObject;
import com.example.myapplication.ui.chart.ChartOverview;

import java.util.ArrayList;
import java.util.List;

public class DatesOverview extends Fragment {
    
    private List<HistoryDataObject> list;

    public static DatesOverview newInstance() {
        return new DatesOverview();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dates_overview_fragment, container, false);
        TableLayout tableLayout = root.findViewById(R.id.historytable);

        list = DBService.getInstance().getLastFive();

        if(list.size()<1){
            TextView textView = new TextView(getContext());
            textView.setText("No steps tracked in the past");
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            tableLayout.addView(textView);
        } else {
            TableRow tableRow = new TableRow(getContext());
            TextView d = new TextView(getContext());
            d.setText("Date");
            TextView s = new TextView(getContext());
            s.setText("Steps");
            TextView t = new TextView(getContext());
            t.setText("Target");
            TextView p = new TextView(getContext());
            p.setText("Percentage");
            d.setPadding(3,3,3,3);
            s.setPadding(3,3,3,3);
            t.setPadding(3,3,3,3);
            p.setPadding(3,3,3,3);
            tableRow.addView(d);
            tableRow.addView(s);
            tableRow.addView(t);
            tableRow.addView(p);
            tableLayout.addView(tableRow);
        }

        for(int i=0; i<list.size(); i++){
            TableRow tableRow = new TableRow(getContext());
            TextView date = new TextView(getContext());
            date.setText(list.get(i).getDateFormatted());
            TextView stepView = new TextView(getContext());
            int steps = list.get(i).getSteps();
            stepView.setText(steps + "");
            int target = list.get(i).getTarget();
            TextView targetView = new TextView(getContext());
            targetView.setText(target + "");
            TextView percentView = new TextView(getContext());
            int percent = steps * 100 / target;
            percentView.setText(percent + " %");

            date.setPadding(3,3,3,3);
            stepView.setPadding(3,3,3,3);
            targetView.setPadding(3,3,3,3);
            percentView.setPadding(3,3,3,3);
            tableRow.addView(date);
            tableRow.addView(stepView);
            tableRow.addView(targetView);
            tableRow.addView(percentView);
            tableLayout.addView(tableRow);
        }
        return root;
    }

}
