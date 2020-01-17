package com.example.myapplication;


import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.DBService;
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
public class TestDB {

    private HistoryDataObjectDAO historyDao;
    private AppDatabase db;
    private History history = new History();
    private HistoryDataObject currentObject;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        DBService.getInstance().init(context);
    }
    @After
    public void closeDB() throws IOException {
        //db.close();
    }

    @Test
    public void checkDao() throws Exception {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        historyDao = db.getHistoryDao();
        HistoryDataObject object1 = history.getObjects().get(0);
        historyDao.insert(object1);
        List<HistoryDataObject> selectedObject = historyDao.get(object1.getDate());
        assertEquals(selectedObject.get(0), object1);
        db.close();
    }

    @Test
    public void setCurrentAndCheckOnDB() throws Exception {
        DBService.getInstance().setCurrent(8000, 10000);
        assertEquals(DBService.getInstance().getSteps(), 8000);
    }

    @Test
    public void setTargetAndCheckOnDB() throws Exception {
        DBService.getInstance().updateTarget(9000);
        assertEquals(DBService.getInstance().getTarget(), 9000);
    }
    @Test
    public void setStepsAndCheckOnDB() throws Exception {
        DBService.getInstance().updateSteps(8100);
        assertEquals(DBService.getInstance().getSteps(), 8100);
    }
}
