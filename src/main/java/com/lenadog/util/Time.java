package com.lenadog.util;


public class Time {
    
    public static final int TWO_MINUTES_IN_SECONDS = 120;
    
    public static String getFormattedTimeString(long milliseconds) {
        Time time = new Time(milliseconds);
        return String.format("%02d:%02d", time.getMinutes(), time.getSeconds());
    }
    
    public static int fromMinutes(int minutes) {
        return 1000 * 60 * minutes;
    }
    
    public static long toSeconds(long timeInMillis) {
        return timeInMillis / 1000;
    }
    
    private long timeInMillis;
    
    public Time(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public int getMinutes() {
        return (int) (toSeconds(timeInMillis)/60);
    }

    public int getSeconds() {
        return (int) (toSeconds(timeInMillis) % 60);
    }
}