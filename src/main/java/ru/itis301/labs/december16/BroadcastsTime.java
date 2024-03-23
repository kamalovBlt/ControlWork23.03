package ru.itis301.labs.december16;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BroadcastsTime implements Comparable<BroadcastsTime> {

    private String timeString;
    private LocalTime currentTime;

    public BroadcastsTime(String timeString) {
        this.timeString = timeString;
        this.currentTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
    }

    public BroadcastsTime(LocalTime time) {
        currentTime = time;
    }

    public byte hour() {
        return (byte) currentTime.getHour();
    }

    public byte minutes() {
        return (byte) currentTime.getMinute();
    }

    public boolean after(BroadcastsTime t) {
        if (this.hour() > t.hour()) {
            return true;
        } else if (t.hour() == this.hour()) {
            return this.minutes() >= t.minutes();
        }
        return false;
    }

    public boolean before(BroadcastsTime t) {
        if (this.hour() < t.hour()) {
            return true;
        } else if (this.hour() == t.hour()) {
            return this.minutes() <= t.minutes();
        }
        return false;
    }

    public boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return this.after(t1) && this.before(t2);
    }

    @Override
    public int compareTo(BroadcastsTime t) {

        int hourComparison = Integer.compare(this.hour(), t.hour());
        if (hourComparison != 0) {
            return hourComparison;
        }

        return Integer.compare(this.minutes(), t.minutes());
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

}
