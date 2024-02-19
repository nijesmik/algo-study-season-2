import java.util.*;

public class 프로그래머스_셔틀버스 {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] bus = new int[n];
        bus[0] = 540;
        for (int i = 1; i < n; i++) {
            bus[i] = bus[i - 1] + t;
        }
        int[] busCapa = new int[n];
        int[] line = new int[timetable.length];
        int len = timetable.length;

        for (int i = 0; i < len; i++) {
            String[] tmp = timetable[i].split(":");
            line[i] = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
        }
        Arrays.sort(line);
        int lineIndex = 0;
        for (int i = 0; i < n; i++) {
            int now = bus[i];
            while (lineIndex < len && busCapa[i] < m) {
                if (line[lineIndex] <= now) {
                    busCapa[i]++;
                    lineIndex++;
                } else {
                    break;
                }
            }
        }
        int ans = 0;
        if (busCapa[n - 1] < m) {
            ans = bus[n - 1];
        } else {
            ans = line[lineIndex - 1] - 1;
        }
        String hour = String.valueOf(ans / 60);
        if (hour.length() == 1) {
            hour = "0" + hour;
        }
        String minute = String.valueOf(ans % 60);
        if (minute.length() == 1) {
            minute = "0" + minute;
        }

        return hour + ":" + minute;
    }

}
