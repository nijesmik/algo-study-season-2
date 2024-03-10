package jechul;

import java.text.SimpleDateFormat;
import java.util.Date;

class 추석트래픽 {
    public int solution(String[] lines) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        int[] counts = new int[lines.length * 2];
        int idx = 0;

        long[] times = new long[lines.length * 2];
        for (String line : lines) {
            String[] li = line.split(" ");
            long end = format.parse(li[0] + " " + li[1]).getTime();
            long start = end - (int) (Double.parseDouble(li[2].substring(0, li[2].length() - 1)) * 1000) + 1;
            times[idx++] = start;
            times[idx++] = end;
        }

        for (int i = 0; i < times.length; i++) {
            long startRange = times[i];
            //1s
            long endRange = startRange + 999; 
            int count = 0;
            for (int j = 0; j < times.length; j += 2) {
                if (times[j] <= endRange && times[j + 1] >= startRange) {
                    count++;
                }
            }
            counts[i] = count;
        }

        int answer = 0;
        for (int count : counts) {
            answer = Math.max(answer, count);
        }
        return answer;
    }
}
