package com.example.myapplication.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryDataObjectDAO {

    @Query("Select * from historydataobject where id in (:ids)")
    List<HistoryDataObject> loadAllByIds(int[] ids);

    @Query("Select * from historydataobject where id = (:objectId)")
    List<HistoryDataObject> get(int objectId);

    @Query("Select * from historydataobject")
    List<HistoryDataObject> getAll();

    @Insert
    void insertAll(HistoryDataObject... objects);

    @Insert
    void insert(HistoryDataObject object);
}
