import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_16929_TwoDots {


    static int n, m;
    static boolean flag;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static void dfs(int r, int c, int prevR, int prevC, int startR, int startC, char target) {
        if (flag) {
            return;
        }
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1 || map[nr][nc] != target) {
                continue;
            }


            if (nr == startR && nc == startC && !(prevR == startR && prevC == startC)) {
                flag = true;
                return;
            }

            if (visited[nr][nc]) {
                continue;
            }

            dfs(nr, nc, r, c, startR, startC, target);


        }


        visited[r][c] = false;

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new boolean[n][m];
        flag = false;
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    continue;
                }

                dfs(i, j, -1, -1, i, j, map[i][j]);
                if (flag) {
                    System.out.println("Yes");
                    return;
                }

            }

        }

        System.out.println("No");


    }
}
