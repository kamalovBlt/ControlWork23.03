package ru.itis301.labs.december16;

public class TVProgram {

    private final String TVCanaleName;
    private final BroadcastsTime time;
    private final String TVProgramName;

    public String toString() {
        return TVCanaleName + " " + time.getTimeString() + " " + TVProgramName;
    }

    public String getTVCanaleName() {
        return TVCanaleName;
    }

    public BroadcastsTime getTime() {
        return time;
    }

    public String getTVProgramName() {
        return TVProgramName;
    }

    public TVProgram(String TVCanaleName, BroadcastsTime time, String TVProgramName) {
        this.TVCanaleName = TVCanaleName;
        this.time = time;
        this.TVProgramName = TVProgramName;
    }
}
