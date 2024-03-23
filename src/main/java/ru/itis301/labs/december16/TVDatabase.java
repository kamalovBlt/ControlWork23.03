package ru.itis301.labs.december16;

import java.util.*;
import java.time.LocalTime;

public class TVDatabase {
    private final Map<String, List<TVProgram>> TV = new HashMap<>();
    private final List<TVProgram> programsAll = new ArrayList<>();

    public TVDatabase(List<String> strings) {
        for (int i = 0; i < strings.size(); ++i) {
            String currentElem = strings.get(i);
            if (currentElem.startsWith("#")) {
                List<TVProgram> programs = new ArrayList<>();
                int count = 1;
                while (i + count < strings.size() && strings.get(i + count).charAt(0) != '#') {
                    TVProgram tvProgram = new TVProgram(currentElem, new BroadcastsTime(strings.get(i + count)), strings.get(i + count + 1));
                    programs.add(tvProgram);
                    programsAll.add(tvProgram);
                    count += 2;
                }
                programs.sort(new TVProgramComparator());
                TV.put(currentElem, programs);
                i += count;

            }
        }
        programsAll.sort(new TVProgramComparator());
    }

    public Map<String, List<TVProgram>> getTV() {
        return TV;
    }
    public void printallPrograms() {
        for(TVProgram i: programsAll) {
            System.out.println(i.toString());
        }
    }
    public void printnowPrograms() {
        LocalTime currentTime = LocalTime.now();
        BroadcastsTime currentTimeBroadcast = new BroadcastsTime(currentTime);
        for (Map.Entry<String, List<TVProgram>> entry : TV.entrySet()) {
            String key = entry.getKey();
            List<TVProgram> value = entry.getValue();
            for (int i = 0; i < value.size() - 1; ++i) {
                if (currentTimeBroadcast.between(value.get(i).getTime(), value.get(i + 1).getTime())) {
                    System.out.println(value.get(i).toString());
                }
            }
        }
    }

    public List<TVProgram> findProgram(String name) {
        List<TVProgram> programs = new ArrayList<>();
        for (TVProgram i: programsAll) {
            if (i.getTVCanaleName().equals(name)) {
                programs.add(i);
            }
        }
        return programs;
    }

    public List<TVProgram> findProgramNowOfCanal(String name) {
        List<TVProgram> programs = new ArrayList<>();
        List<TVProgram> thisCanalprograms = TV.get(name);
        LocalTime currentTime = LocalTime.now();
        BroadcastsTime currentTimeBroadcast = new BroadcastsTime(currentTime);
        for (int i = 0; i < thisCanalprograms.size() - 1; ++i) {
            if (currentTimeBroadcast.between(thisCanalprograms.get(i).getTime(), thisCanalprograms.get(i + 1).getTime())) {
                programs.add(thisCanalprograms.get(i));
            }
        }
        return programs;
    }

    public List<TVProgram> findProgramNowOfCanalTime(String name, BroadcastsTime
                                                     t1, BroadcastsTime t2) {
        List<TVProgram> programs = new ArrayList<>();
        List<TVProgram> thisCanalprograms = TV.get(name);
        for (TVProgram thisCanalprogram : thisCanalprograms) {
            if (thisCanalprogram.getTime().between(t1, t2)) {
                programs.add(thisCanalprogram);
            }
        }
        return programs;
    }

    private static class TVProgramComparator implements Comparator<TVProgram> {
        @Override
        public int compare(TVProgram program1, TVProgram program2) {
            return program1.getTime().compareTo(program2.getTime());
        }
    }
}
