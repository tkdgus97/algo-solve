import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> times = new PriorityQueue<>();
        
        List<Integer>[] bus = new ArrayList[n];
        
        for(int i = 0; i < n; i++) {
            bus[i] = new ArrayList<>();
        }
        
        for(String time : timetable) {
            times.add(convertStringToTime(time));
        }
        int busTime = 9 * 60;
        int lastTime = 0;
        for(int i = 0; i < n; i++) {
            while(!times.isEmpty()) {
                int crew = times.poll();
                if(bus[i].size() < m && crew <= busTime) {
                    bus[i].add(crew);
                    lastTime = crew - 1;
                } else {
                    times.add(crew);
                    break;
                }
            }
            busTime += t;
        }
        
        if(bus[n - 1].size() < m) {
            lastTime = busTime - t;
        }
        
        return timeToString(lastTime);
    }
    
    private static int convertStringToTime(String time) {
        String[] s = time.split(":");
        return Integer.parseInt(s[0])*60 + Integer.parseInt(s[1]);
    }
    
    
    private static String timeToString(int time) {
        int h = time/60;
        String hour = h < 10 ? "0" + String.valueOf(h) : String.valueOf(h);
        int m = time % 60;
        String min = m < 10 ? "0" + String.valueOf(m) : String.valueOf(m);
        return hour + ":" + min;
    }
}