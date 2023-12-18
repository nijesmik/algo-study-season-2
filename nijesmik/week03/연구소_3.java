package nijesmik.week03;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 연구소_3 {
static int N, M, V, ans, blank;
    static Pos[] virus;
    static boolean[] select;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); M = sc.nextInt();
        V = 0; blank = 0;
        ans = Integer.MAX_VALUE;
        map = new int[N][N];
        virus = new Pos[N * N];
        select = new boolean[N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) {
                    blank++;
                } else if (map[i][j] == 2) {
                    virus[V++] = new Pos(i, j);
                }
            }
        }
        dfs(0,0);
        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);
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
        int total = blank;
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
        while (total > 0 && !q.isEmpty()) {
            time++;
            if (time >= ans) {
                return;
            }
            int size = q.size();
            while (size-- > 0) {
                Pos cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i], nc = cur.c + dc[i];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1 || visited[nr][nc]) {
                        continue;
                    }
                    if (map[nr][nc] == 0) {
                        total--;
                    }
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc));
                }
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
