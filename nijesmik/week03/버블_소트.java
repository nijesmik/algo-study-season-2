package nijesmik.week03;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 버블_소트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Bubble> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            q.add(new Bubble(num, i));
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans = Math.max(q.poll().idx - i, ans);
        }
        System.out.println(ans+1);
    }

    static class Bubble implements Comparable<Bubble> {
        int num, idx;
        Bubble(int n, int i) {
            num = n;
            idx = i;
        }
        @Override
        public int compareTo(Bubble o) {
            if (num == o.num) return idx - o.idx;
            return num - o.num;
        }
    }
}
