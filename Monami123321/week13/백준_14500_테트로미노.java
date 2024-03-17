import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14500_테트로미노 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m, ans;

    static void dfs(boolean[][] visited, int[][] map, int r, int c, int sum, int depth) {
        if (depth == 4) {
            ans = Math.max(ans, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1 || visited[nr][nc]) {
                continue;
            }
            if (depth == 2) {
                visited[nr][nc] = true;
                dfs(visited, map, r, c, sum + map[nr][nc], depth + 1);
                visited[nr][nc] = false;
            }
            visited[nr][nc] = true;
            dfs(visited, map, nr, nc, sum + map[nr][nc], depth + 1);
            visited[nr][nc] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = 0;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(visited, map, i, j, map[i][j], 1);
                visited[i][j] = false;

            }
        }

        System.out.println(ans);
        br.close();
    }
}
