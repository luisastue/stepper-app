package com.example.myapplication.data;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class History implements HistoryDataHandler {
    private ArrayList<HistoryDataObject> historyData = new ArrayList();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private HistoryDataObject currentObject;

    public History(){
        historyData.add(new HistoryDataObject(getDate("1.1.2020"), 5462, 10000));
        historyData.add(new HistoryDataObject(getDate("2.1.2020"), 5462, 10000));
        historyData.add(new HistoryDataObject(getDate("3.1.2020"), 5412, 10000));
        historyData.add(new HistoryDataObject(getDate("4.1.2020"), 4462, 10000));
        historyData.add(new HistoryDataObject(getDate("5.1.2020"), 8462, 10000));
        historyData.add(new HistoryDataObject(getDate("6.1.2020"), 10462, 10000));
        historyData.add(new HistoryDataObject(getDate("7.1.2020"), 3462, 10000));
        historyData.add(new HistoryDataObject(getDate("8.1.2020"), 9462, 10000));
        historyData.add(new HistoryDataObject(getDate("9.1.2020"), 7002, 10000));
        historyData.add(new HistoryDataObject(getDate("10.1.2020"), 8462, 10000));
        historyData.add(new HistoryDataObject(getDate("11.1.2020"), 8105, 10000));
    }

    public ArrayList<HistoryDataObject> getObjects(){
        return historyData;
    }

    private Date getDate(String string){
        Date date = null;
        try {
            date = simpleDateFormat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public void init(Context context){
        //nothing
        currentObject = historyData.get(historyData.size()-1);
    }
    public void setCurrent(int steps, int target){
        currentObject.setSteps(steps);
        currentObject.setTarget(target);
    }

    public List<HistoryDataObject> getAll(){
        return historyData;
    }

    public List<HistoryDataObject> getLastFive(){
        int e = historyData.size()-2;
        List<HistoryDataObject> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            list.add(historyData.get(e));
            e--;
        }
        return list;
    }

    public boolean updateSteps(int steps){
        currentObject.setSteps(steps);
        return true;
    }

    public boolean updateTarget(int target){
        currentObject.setTarget(target);
        return true;
    }

    public int getSteps(){
        return currentObject.getSteps();
    }

    public int getTarget(){
        return currentObject.getTarget();
    }




}
