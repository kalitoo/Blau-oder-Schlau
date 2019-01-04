package com.example.blauoderschlau.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game implements Serializable {

    // time stamp when game was finished
    private Date datePlayed;

    // resulting per mill value (the score)
    private double perMill;

    //using to convert date
    private SimpleDateFormat simpleDateFormat;

    //pattern to display date
    String pattern = "yyyy-MM-dd";

    //the actual date used
    String date;


    public Game() {

    }

    public void setPerMill(double perMill) {
        this.perMill = perMill;
    }

    public void setDate() {
        datePlayed = new Date();
        simpleDateFormat = new SimpleDateFormat(pattern);
        date = simpleDateFormat.format(datePlayed);
    }

    public void setDateFromDB(String date) {
        this.date = date;
    }

    public double getPerMill() {
        return perMill;
    }

    public Date getDate() {
        return datePlayed;
    }

    public String getDateAsString() {
        return date;
    }
}
