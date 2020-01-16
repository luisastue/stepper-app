package com.example.myapplication.data;

import java.util.ArrayList;
import java.util.Date;

public class History {
    private ArrayList<HistoryDataObject> historyData = new ArrayList();

    public History(){
        historyData.add(new HistoryDataObject(new Date(20200101), 5462, 10000));
        historyData.add(new HistoryDataObject(new Date(20200102), 5462, 10000));
        historyData.add(new HistoryDataObject(new Date(20200103), 5412, 10000));
        historyData.add(new HistoryDataObject(new Date(20200104), 4462, 10000));
        historyData.add(new HistoryDataObject(new Date(20200105), 8462, 10000));
        historyData.add(new HistoryDataObject(new Date(20200106), 10462, 10000));
        historyData.add(new HistoryDataObject(new Date(20200107), 3462, 10000));
        historyData.add(new HistoryDataObject(new Date(20200108), 9462, 10000));
        historyData.add(new HistoryDataObject(new Date(20200109), 7002, 10000));
        historyData.add(new HistoryDataObject(new Date(20200110), 8462, 10000));
        historyData.add(new HistoryDataObject(new Date(20200111), 8105, 10000));
    }

    public ArrayList<HistoryDataObject> getObjects(){
        return historyData;
    }

    public Date[] getDates(){
        Date[] dates = new Date[historyData.size()];
        for(int i=0; i<historyData.size();i++){
            dates[i] = historyData.get(i).getDate();
        }
        return dates;
    }
    public int[] getSteps(){
        int[] steps = new int[historyData.size()];
        for(int i=0; i<historyData.size();i++){
            steps[i] = historyData.get(i).getSteps();
        }
        return steps;
    }
    public int[] getTargets(){
        int[] targets = new int[historyData.size()];
        for(int i=0; i<historyData.size();i++){
            targets[i] = historyData.get(i).getTarget();
        }
        return targets;
    }
}
