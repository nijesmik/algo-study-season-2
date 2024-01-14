import java.util.Arrays;

public class 프로그래머스_광고삽입 {
    public String solution(String play_time, String adv_time, String[] logs) {
        int[] arr = new int[toInt(play_time)+1];
        int adv = toInt(adv_time);
        Arrays.stream(logs).forEach(e -> {
            String tmp[] = e.split("-");
            int from = toInt(tmp[0]);
            int to = toInt(tmp[1]);
            for(int i = from; i < to; i++) {
                arr[i]++;
            }
        });
        int ans = 0;
        long sum = 0;
        int start = 0;
        for(int i = 0; i < adv; i++) {
            sum += arr[i];
        }
        long prev = sum;
        while(adv < arr.length-1) {
            sum -= arr[start++];
            sum += arr[adv++];

            if(prev<sum) {
                ans = start;
                prev = sum;

            }
        }
        return formatTime(ans);


    }

    static int toInt(String time) {
        String[] tmp = time.split(":");
        return Integer.parseInt(tmp[0]) * 3600 + Integer.parseInt(tmp[1]) * 60 + Integer.parseInt(tmp[2]);

    }

    static String formatTime(int time) {
        int hour = time/3600;
        int min = (time - hour*3600)/60;
        int sec = time - hour*3600 - min*60;
        String res = "";

        if(hour < 10) {
            res += "0"+hour+":";
        } else {
            res += hour + ":";
        }


        if(min < 10) {
            res += "0" + min + ":";
        } else {
            res += min + ":";
        }

        if(sec < 10) {
            res += "0" + sec;
        } else {
            res += sec;
        }

        return res;
    }

}