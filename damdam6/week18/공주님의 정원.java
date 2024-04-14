package y24Apr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek2457 {
    static int N;

    static Map<Integer, Integer> month = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());

        for (int i = 3; i < 12; i++) {
            if (i % 2 == 1) month.put(i, 31);
            else month.put(i, 30);
        }

        int date = 0;
        int endDate = 0;
        for (int i = 3; i < 12; i++) {
            endDate += month.get(i);
        }

        PriorityQueue<Flower> pqu = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            pqu.add(new Flower(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            
        }

        while (date < endDate) {
            Flower f = pqu.poll();
            
        }

    }


    public static class Flower implements Comparable<Flower> {
        int start, end;
        int dura;

        Flower(int startM, int startD, int endM, int endD) {
            this.start = startD;
            this.end = endD;
            for (int i = 3; i < startM; i++) {
                this.start += month.get(i);
            }
            for (int i = 3; i < endM; i++) {
                this.end += month.get(i);
            }
            this.dura = this.end - this.start;
        }

        public int compareTo(Flower o) {
            if (this.dura > o.dura) return -1;
            if (this.dura < o.dura) return 1;
            if (this.start < o.start) return -1;
            if (this.start > o.start) return 1;
            return 0;
        }
    }
}
