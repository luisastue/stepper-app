package com.example.myapplication.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class HistoryDataObject {


    @ColumnInfo(name = "steps")
    private int steps;

    @ColumnInfo(name = "target")
    private int target;

    @PrimaryKey
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

    public String getDateFormatted(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(date);
    }

    @Override
    public boolean equals(Object p){
        HistoryDataObject comparedObject = (HistoryDataObject) p;
        return (this.date.equals(comparedObject.getDate())
                && this.steps == comparedObject.getSteps()
                &&this.target == comparedObject.getTarget());
    }


}
