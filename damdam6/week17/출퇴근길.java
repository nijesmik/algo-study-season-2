import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        N = n;
        map = new int[n+1][n+1];

        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }
        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] startVt = bfs(start, end);
        int[] endVt = bfs(end, start);

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (startVt[i] == 1 && endVt[i] == 1) cnt++;
        }
        System.out.println(cnt);

    }

    static public int[] bfs(int start, int end) {
        int[] vt = new int[N+1];
        ArrayDeque<Integer> qu = new ArrayDeque<>();
        qu.add(start);

        while (!qu.isEmpty()) {
            int temp = qu.poll();
            if (temp == end) continue;

            for (int i = 0; i < N; i++) {
                if(i== end)continue;
                if (vt[i] == 1) continue;
                if (map[temp][i] == 0) continue;
                qu.add(i);
                vt[i] = 1;
            }

        }

        return vt;
    }
}
