package com.example.myapplication.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface HistoryDataObjectDAO {


    @Query("Select * from historydataobject where date = (:date)")
    List<HistoryDataObject> get(Date date);

    @Query("Select * from historydataobject order by date desc limit 6")
    List<HistoryDataObject> getLastSix();

    @Update
    void update(HistoryDataObject object);

    @Query("Select * from historydataobject order by date desc")
    List<HistoryDataObject> getAll();

    @Insert
    void insert(HistoryDataObject object);
}
