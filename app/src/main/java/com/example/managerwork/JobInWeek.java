package com.example.managerwork;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JobInWeek {
    private String title;
    private String desciption;
    private String dateFinish;
    private String hourFinish;

    public JobInWeek() {
    }

    public JobInWeek(String title, String desciption, String dateFinish, String hourFinish) {
        this.title = title;
        this.desciption = desciption;
        this.dateFinish = dateFinish;
        this.hourFinish = hourFinish;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public String getHourFinish() {
        return hourFinish;
    }

    public void setHourFinish(String hourFinish) {
        this.hourFinish = hourFinish;
    }

    public String getDateFormat(Date d) {
        SimpleDateFormat dft=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }

    public String getHourFormat(Date d) {
        SimpleDateFormat dft=new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return dft.format(d);
    }

    @Override
    public String toString() {
        return this.title + " - "+ dateFinish + " - "+ hourFinish;
    }
}
