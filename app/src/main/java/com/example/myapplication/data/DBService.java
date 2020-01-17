package com.example.myapplication.data;

import android.content.Context;

import androidx.room.Room;


import java.util.ArrayList;
import java.util.List;


public class DBService {

    private static DBService dbService;
    private AppDatabase db;
    private HistoryDataObjectDAO historyDao;
    private Context context;

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
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        historyDao = db.getHistoryDao();
    }

    private void close(){
        db.close();
    }

    public List<HistoryDataObject> getAll(){
        open();
        List<HistoryDataObject> list = historyDao.getAll();
        close();
        return list;
    }
    public List<HistoryDataObject> getLastFive(){

        open();
        List<HistoryDataObject> list = historyDao.getLastFive();
        close();

        return list;
    }


}
