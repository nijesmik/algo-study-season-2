package nijesmik.week14.추석_트래픽;

class Solution {
    int N;

    public int solution(String[] lines) {
        N = lines.length;
        Log[] logs = new Log[N];
        for (int i = 0; i < N; i++) {
            logs[i] = new Log(lines[i]);
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            for (int j = i + 1; j < N; j++) {
                if (logs[j].start < logs[i].end + 1000) {
                    cnt++;
                }
            }
            ans = Math.max(cnt, ans);
        }
        return ans;
    }

    class Log {
        int start, end;

        Log(String line) {
            String[] time = line.split("[ ]");
            String[] endTime = time[1].split("[:]");
            end = (int) (Double.parseDouble(endTime[2]) * 1000);
            end += Integer.parseInt(endTime[1]) * 60_000;
            end += Integer.parseInt(endTime[0]) * 3600_000;
            start = end - (int) (Double.parseDouble(time[2].substring(0, time[2].length() - 1)) * 1000) + 1;
        }
    }
}
