import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_14442_벽부수고이동하기2 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), "01", true);
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[k + 1][n][m];

        queue.add(new int[]{0, 0, 1, 0});
        visited[0][0][0] = true;
        int ans = -1;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            int cost = now[2];
            int wall = now[3];
            if (r == n - 1 && c == m - 1) {
                ans = cost;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1) {
                    continue;
                }
                int nextWall = wall;
                if (map[nr][nc] != 0) {
                    nextWall++;
                }
                if (nextWall > k || visited[nextWall][nr][nc]) {
                    continue;
                }
                queue.add(new int[]{nr, nc, cost + 1, nextWall});
                visited[nextWall][nr][nc] = true;
            }

        }
        System.out.println(ans);


    }
}
