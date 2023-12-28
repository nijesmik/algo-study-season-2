package nijesmik.week04;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 로봇_청소기 {
    static int R, C, N, robot, ans;
    static char[][] map;
    static int[][] dist;
    static List<Pos> point;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            C = sc.nextInt(); R = sc.nextInt(); N = 0;
            if (R == 0 && C == 0) break;
            map = new char[R][];
            point = new ArrayList<>();
            for (int i = 0; i < R; i++) {
                map[i] = sc.next().toCharArray();
                for (int j = 0; j < C; j++) {
                    char a = map[i][j];
                    if (a == '.' || a == 'x') continue;
                    point.add(new Pos(i, j, 0));
                    if (a == 'o') robot = N;
                    N++;
                }
            }
            dist = new int[N][N];
            getDist();
            ans = Integer.MAX_VALUE;
            dfs(robot, 1 << robot, 0);
            if (ans == Integer.MAX_VALUE) ans = -1;
            System.out.println(ans);
        }
    }

    static void dfs(int idx, int visited, int total) {
        if (total > ans) return;
        if (visited == (1 << N) - 1) {
            ans = Math.min(ans, total);
            return;
        }
        for (int i = 0; i < N; i++) {
            if ((visited >> i & 1) == 0 && dist[idx][i] > 0) {
                dfs(i, visited | 1 << i, total + dist[idx][i]);
            }
        }
    }

    static void getDist() {
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                dist[i][j] = bfs(i, j);
                dist[j][i] = dist[i][j];
            }
        }
    }

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int bfs(int to, int from) {
        Pos end = point.get(from);
        boolean[][] visited = new boolean[R][C];
        Queue<Pos> q = new LinkedList<>();
        q.add(point.get(to));
        while (!q.isEmpty()) {
            Pos cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i], nc = cur.c + dc[i];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == 'x') continue;
                if (nr == end.r && nc == end.c) return cur.dist+1;
                visited[nr][nc] = true;
                q.add(new Pos(nr, nc, cur.dist+1));
            }
        }
        return -1;
    }

    static class Pos {
        int r, c, dist;
        Pos(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}
