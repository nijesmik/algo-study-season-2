package nijesmik.week02;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 연구소_2 {
    static int N, M, V, ans, wall;
    static Pos[] virus;
    static boolean[] select;
    static boolean[][] canGo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        V = 0; wall = 0;
        ans = Integer.MAX_VALUE;
        canGo = new boolean[N + 2][N + 2];
        virus = new Pos[N * N];
        select = new boolean[N * N];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int item = sc.nextInt();
                if (item == 1) {
                    wall++;
                    continue;
                }
                canGo[i][j] = true;
                if (item == 2) {
                    virus[V++] = new Pos(i, j);
                }
            }
        }
        dfs(0,0);
        if (ans == Integer.MAX_VALUE) {
            ans = 0;
        }
        System.out.println(ans-1);
    }

    static void dfs(int idx, int cnt) {
        if (cnt == M) {
            bfs();
            return;
        }
        if (idx == V) {
            return;
        }
        select[idx] = true;
        dfs(idx+1, cnt+1);
        select[idx] = false;
        dfs(idx+1, cnt);
    }

    static void bfs() {
        int total = N * N - wall - M;
        boolean[][] visited = new boolean[N + 2][N + 2];
        Queue<Pos> q = new LinkedList<Pos>();
        for (int i = 0; i < V; i++) {
            if (select[i]) {
                Pos v = virus[i];
                q.add(v);
                visited[v.r][v.c] = true;
            }
        }

        int[] dr = { 1, -1, 0, 0 };
        int[] dc = { 0, 0, 1, -1 };
        int time = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Pos cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i], nc = cur.c + dc[i];
                    if (canGo[nr][nc] && !visited[nr][nc]) {
                        total--;
                        visited[nr][nc] = true;
                        q.add(new Pos(nr, nc));
                    }
                }
            }
            time++;
            if (time >= ans) {
                return;
            }
        }
        if (total == 0) {
            ans = Math.min(time, ans);
        }
    }

    static class Pos {
        int r, c;

        Pos(int i, int j) {
            r = i;
            c = j;
        }
    }
}
