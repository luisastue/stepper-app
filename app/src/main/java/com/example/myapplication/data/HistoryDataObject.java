package com.example.myapplication.data;

import androidx.room.Entity;

import java.util.Date;

@Entity
public class HistoryDataObject {
    private int id;
    private int steps;
    private int target;
    private Date date;

    public HistoryDataObject(Date date, int steps, int target){
        this.date = date;
        this.steps = steps;
        this.target = target;
    }
    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
