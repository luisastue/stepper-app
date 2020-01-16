package com.example.myapplication;


import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.History;
import com.example.myapplication.data.HistoryDataObject;
import com.example.myapplication.data.HistoryDataObjectDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class TestHistoryData {

    private HistoryDataObjectDAO historyDao;
    private AppDatabase db;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        historyDao = db.getHistoryDao();
    }
    @After
    public void closeDB() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        History history = new History();

        HistoryDataObject object1 = history.getObjects().get(0);
        historyDao.insert(object1);
        List<HistoryDataObject> selectedObject = historyDao.get(object1.getId());
        assertEquals(selectedObject.get(0), object1);
    }
}
