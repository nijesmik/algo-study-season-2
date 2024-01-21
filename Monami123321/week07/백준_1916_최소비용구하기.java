import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1916_최소비용구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<int[]>[] adjList = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from].add(new int[]{to, cost});
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int[] minEdge = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            minEdge[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        adjList[start].forEach(e -> {
            pq.add(new int[]{e[0], e[1]});
        });
        int ans = 0;
        while (true) {
            int[] now = pq.poll();
            int next = now[0];
            int nextCost = now[1];
            if (minEdge[next] <= nextCost) {
                continue;
            }
            minEdge[next] = nextCost;
            if (next == end) {
                ans = nextCost;
                break;
            }
            adjList[next].forEach(e -> {
                if (e[1] + nextCost >= minEdge[e[0]]) {
                    return;
                }
                pq.add(new int[]{e[0], e[1] + nextCost});
            });
        }
        System.out.println(ans);
        br.close();
    }
}
