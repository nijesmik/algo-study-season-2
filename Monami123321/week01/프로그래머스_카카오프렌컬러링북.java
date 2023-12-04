import java.util.*;

class 프로그래머스_카카오프렌즈컬러링북 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int ans;
    static int cnt;
    static boolean[][] visited;
    static Queue<int[]> queue;

    static int bfs(int r, int c, int target, int[][] picture, int m, int n) {
        int res = 1;
        queue.add(new int[]{r, c});
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();


            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];

                if (nr < 0 || nr > m - 1 || nc < 0 || nc > n - 1 || visited[nr][nc] || picture[nr][nc] != target) {
                    continue;
                }
                visited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
                res++;

            }

        }


        return res;

    }

    public int[] solution(int m, int n, int[][] picture) {
        // 영역 갯수 저장
        cnt = 0;
        ans = 0;
        queue = new LinkedList<>();
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || picture[i][j] == 0) {
                    continue;
                }
                int res = bfs(i, j, picture[i][j], picture, m, n);
                ans = ans < res ? res : ans;
                cnt++;

            }
        }

        return new int[]{cnt, ans};


    }
}