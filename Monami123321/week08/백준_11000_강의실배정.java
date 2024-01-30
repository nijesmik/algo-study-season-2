import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_11000_강의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] plan = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            plan[i][0] = start;
            plan[i][1] = end;
        }
        Arrays.sort(plan, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int ans = 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(plan[0][1]);

        for (int i = 1; i < n; i++) {
            if (plan[i][0] >= pq.peek()) {
                pq.poll();
                pq.add(plan[i][1]);
            } else {
                ans++;
                pq.add(plan[i][1]);
            }
        }
        System.out.println(ans);
        br.close();
    }
}
