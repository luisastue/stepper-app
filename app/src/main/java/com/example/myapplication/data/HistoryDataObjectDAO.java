package com.example.myapplication.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface HistoryDataObjectDAO {

    @Query("Select * from historydataobject where date in (:dates)")
    List<HistoryDataObject> loadAllByDates(Date[] dates);

    @Query("Select steps from historydataobject where date = (:date)")
    int getSteps(Date date);

    @Query("Select * from historydataobject where date = (:date)")
    List<HistoryDataObject> get(Date date);

    @Query("Select * from historydataobject order by date desc limit 6")
    List<HistoryDataObject> getLastSix();

    @Query("Select * from historydataobject where date = (:currentDate)")
    HistoryDataObject getAtDate(Date currentDate);

    @Update
    void update(HistoryDataObject object);

    @Query("Select * from historydataobject")
    List<HistoryDataObject> getAll();

    @Insert
    void insertAll(HistoryDataObject... objects);

    @Insert
    void insert(HistoryDataObject object);
}
