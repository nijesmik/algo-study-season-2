import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 소프티어_순서대로방문하기 {
    static int ans = 0;
    static int[][] map, path;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        path = new int[n][n];
        visited = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken()) - 1;
        int startC = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 1; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            path[r][c] = i;
        }
        visited[startR][startC] = true;
        dfs(startR, startC, n, 1, m - 1);
        System.out.println(ans);
        br.close();
    }

    static void dfs(int r, int c, int n, int index, int end) {
        if (index == end + 1) {
            ++ans;
            return;
        }
        for (int i = 0; i < 4; ++i) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr > n - 1 || nc < 0 || nc > n - 1 || visited[nr][nc] || map[nr][nc] != 0) {
                continue;
            }
            if (path[nr][nc] != 0 && path[nr][nc] != index) {
                continue;
            }

            visited[nr][nc] = true;
            if (path[nr][nc] == index) {
                dfs(nr, nc, n, index + 1, end);
            } else {
                dfs(nr, nc, n, index, end);
            }

            visited[nr][nc] = false;
        }

    }
}
