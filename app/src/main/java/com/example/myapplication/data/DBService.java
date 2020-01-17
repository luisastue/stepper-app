package com.example.myapplication.data;

import android.content.Context;

import androidx.room.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class DBService {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private static DBService dbService;
    private AppDatabase db;
    private HistoryDataObjectDAO historyDao;
    private Context context;
    private HistoryDataObject currentObject;

    public static DBService getInstance(){
        if(dbService==null){
            dbService = new DBService();
        }
        return dbService;
    }

    public void init(Context context){
        this.context = context;
    }
    private void open(){
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
        historyDao = db.getHistoryDao();
    }

    private void close(){
        db.close();
    }

    public void setCurrent(int steps, int target){
        open();
        if (currentObject == null){
            currentObject = new HistoryDataObject(getNow(), steps, target);
            historyDao.insert(currentObject);
        } else{
            currentObject.setSteps(steps);
            currentObject.setTarget(target);
            historyDao.update(currentObject);
        }
        close();
    }

    public List<HistoryDataObject> getAll(){
        open();
        List<HistoryDataObject> list = historyDao.getAll();
        close();
        return list;
    }

    public List<HistoryDataObject> getLastFive(){
        open();
        List<HistoryDataObject> list = historyDao.getLastSix();
        list.remove(list.get(0));
        close();
        return list;
    }

    public boolean updateSteps(int steps){
        open();
        currentObject.setSteps(steps);
        historyDao.update(currentObject);
        close();
        return true;
    }

    public boolean updateTarget(int target){
        open();
        currentObject.setTarget(target);
        historyDao.update(currentObject);
        close();
        return true;
    }

    public int getSteps(){
        if(currentObject!=null){
            return currentObject.getSteps();
        }
        else return 0;
    }

    public int getTarget(){
        if(currentObject!=null){
            return currentObject.getTarget();
        }
        else return 0;
    }

    private Date getNow(){
        Date date = new Date(System.currentTimeMillis());
        try {
            String s = simpleDateFormat.format(date);
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}
