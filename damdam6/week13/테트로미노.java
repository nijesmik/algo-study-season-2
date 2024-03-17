package Date2403;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek14500 {

    static int N;
    static int M;
    static int[][] Bd;

    static int[][] vt;

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Bd = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                Bd[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        vt = new int[N][M];
        maxSum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                vt[i][j] = 1;
                dfs(i, j, 1, Bd[i][j]);
                vt[i][j] = 0;
            }
        }


        System.out.println(maxSum);
    }

    static int maxSum;

    static int[] dx = new int[]{0, 1, -1, 0};
    static int[] dy = new int[]{1, 0, 0, -1};


    static public void dfs(int x, int y, int cnt, int sum) {

        if (maxSum >= sum + 1000 * (4 - cnt)) {
            return;
        }

        if (cnt == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= N || ny >= M || nx < 0 || ny < 0 || vt[nx][ny] == 1) continue;

            if (cnt == 2) {
                vt[nx][ny] = 1;
                dfs(x, y, cnt + 1, sum + Bd[nx][ny]);
                vt[nx][ny] = 0;
            }

            vt[nx][ny] = 1;
            dfs(nx, ny, cnt + 1, sum + Bd[nx][ny]);
            vt[nx][ny] = 0;
        }
    }

}
