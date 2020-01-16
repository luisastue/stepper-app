package com.example.myapplication.data;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDataObjectDAO {

    @Query("Select * from history where id in (:ids)")
    List<HistoryDataObject> loadAllByIds(int[] ids);

    @Query("Select * from history")
    List<HistoryDataObject> getAll();

}
