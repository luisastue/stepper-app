package com.example.myapplication.data;

import android.content.Context;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface HistoryDataHandler {

    public void init(Context context);
    public void setCurrent(int steps, int target);

    public List<HistoryDataObject> getAll();

    public List<HistoryDataObject> getLastFive();

    public boolean updateSteps(int steps);

    public boolean updateTarget(int target);

    public int getSteps();

    public int getTarget();


}
