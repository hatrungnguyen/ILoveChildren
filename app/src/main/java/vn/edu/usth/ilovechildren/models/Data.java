package vn.edu.usth.ilovechildren.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "data")
public class Data {
    @PrimaryKey(autoGenerate = true)
    private long date;
    private double sleep_hours;
    private int steps;
    private int heart_rate;
    private int systolic_pressure;
    private int diastolic_pressure;

    public Data(long date, double sleep_hours, int steps, int heart_rate, int systolic_pressure, int diastolic_pressure) {
        this.date = date;
        this.sleep_hours = sleep_hours;
        this.steps = steps;
        this.heart_rate = heart_rate;
        this.systolic_pressure = systolic_pressure;
        this.diastolic_pressure = diastolic_pressure;
    }

    // Getters and Setters
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getSleep_hours() {
        return sleep_hours;
    }

    public void setSleep_hours(double sleep_hours) {
        this.sleep_hours = sleep_hours;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getHeart_rate() {
        return heart_rate;
    }

    public void setHeart_rate(int heart_rate) {
        this.heart_rate = heart_rate;
    }

    public int getSystolic_pressure() {
        return systolic_pressure;
    }

    public void setSystolic_pressure(int systolic_pressure) {
        this.systolic_pressure = systolic_pressure;
    }

    public int getDiastolic_pressure() {
        return diastolic_pressure;
    }

    public void setDiastolic_pressure(int diastolic_pressure) {
        this.diastolic_pressure = diastolic_pressure;
    }
}
