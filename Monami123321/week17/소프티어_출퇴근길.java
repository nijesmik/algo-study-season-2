import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 소프티어_출퇴근길 {
    static List<Integer>[] adjList, adjListR;
    static int n, m, S, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n + 1];
        adjListR = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            adjList[i] = new ArrayList<>();
            adjListR[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjListR[b].add(a);
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        boolean[] StoT = bfs(S, T);
        boolean[] TtoS = bfs(T, S);
        boolean[] reverseStoT = bfsR(S, T);
        boolean[] reverseTtoS = bfsR(T, S);
        int ans = 0;
        for (int i = 1; i < n + 1; ++i) {
            if (StoT[i] && TtoS[i] && reverseStoT[i] && reverseTtoS[i]) {
                ++ans;
            }
        }
        System.out.print(ans);
        br.close();
    }

    static boolean[] bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            adjList[now].forEach(e -> {
                if (visited[e] || e == end) {
                    return;
                }
                visited[e] = true;
                q.add(e);
            });
        }
        visited[start] = false;
        return visited;
    }

    static boolean[] bfsR(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            adjListR[now].forEach(e -> {
                if (visited[e]) {
                    return;
                }
                visited[e] = true;
                q.add(e);
            });
        }
        visited[start] = false;
        return visited;
    }
}
