import java.io.*;
import java.util.*;

public class 순서대로_방문하기 {
    static class Pos {
        int r;
        int c;
        int cnt;
        boolean[][] visited;

        Pos (int r, int c, int cnt, boolean[][] visited) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.visited = visited;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[n][n]; // 목표 지점 방문 체크
        int[][] map = new int[n][n];
        int[][] order = new int[m][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            order[i][0] = r;
            order[i][1] = c;

            visited[r][c] = true; // 목표지점들 방문처리
        }

        Queue<Pos> queue = new LinkedList<>();

        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, 1, 0, -1 };

        int startR = order[0][0];
        int startC = order[0][1];

        queue.add(new Pos(startR, startC, 0, new boolean[n][n])); // 시작지점

        int answer = 0; // 경우의 수

        while (!queue.isEmpty()) {
            Pos p = queue.poll();

            int r = p.r; // 현재 지점
            int c = p.c;
            int cnt = p.cnt; // 다음 목표 순서
            boolean[][] before = p.visited; // 이전까지 방문 체크

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == 1 || before[nr][nc]) continue;

                int targetR = order[cnt + 1][0]; // 다음 목표 지점
                int targetC = order[cnt + 1][1];

                if (nr == targetR && nc == targetC) { // 다음 목표 지점 도달
                    if (cnt + 1 < m - 1) queue.add(new Pos(nr, nc, cnt + 1, before));
                    else if (cnt + 1 == m - 1) answer++; // 최종 도착
                } else {
                    if (visited[nr][nc]) continue;
                    boolean[][] curr = new boolean[n][n]; // 현재까지 방문 체크
                    for (int i = 0; i < n; i++) { // 깊은 복사
                        curr[i] = before[i].clone();
                    }
                    curr[nr][nc] = true;

                    queue.add(new Pos(nr, nc, cnt, curr));
                }
            }
        }

        System.out.println(answer);
    }
}
