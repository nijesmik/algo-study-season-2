public class 프로그래머스_추석트래픽 {
    static class Node {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    public int solution(String[] lines) {
        int len = lines.length;
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            String[] tmp = lines[i].split(" ");
            int timeSpent = (int) (Double.parseDouble(tmp[2].substring(0, tmp[2].length() - 1)) * 1000);
            int start = getIntTime(tmp[1], timeSpent);
            int end = getIntTime(tmp[1], 0) - 1;
            nodes[i] = new Node(start, end);
        }
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int sum = 1;
            for (int j = i + 1; j < len; j++) {

                if (nodes[i].end + 1000 > nodes[j].start) {
                    sum++;
                }
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

    static int getIntTime(String time, int spent) {
        String[] tmp = time.split(":");
        int hour = Integer.parseInt(tmp[0]) * 3600 * 1000;
        int minute = Integer.parseInt(tmp[1]) * 60 * 1000;
        int second = (int) (Double.parseDouble(tmp[2]) * 1000);
        return hour + minute + second - spent + 1;

    }

}
