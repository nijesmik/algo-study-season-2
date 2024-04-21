package y24Apr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek16986 {

    static int N, K;
    static int[][] map;
    static int[][] player;
    static int[] vt;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        vt = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        player = new int[3][20];
        for (int i = 1; i < 3; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 20; j++) {
                player[i][j] = Integer.parseInt(st.nextToken()) - 1;
            }
        }

    }

    public static void per(int n) {
        if (n == N) {
            // 종료 로직
            if (play()) {
                // 이겼다면
                return;
            }
            // 이겼는지 체크

            return;
        }
        // N개 내는 순서대로 일단 dfs
        for (int i = 0; i < N; i++) {
            if (vt[i] == 1) continue;
            player[0][n] = i;
            vt[i] = 1;
            per(n + 1);
            vt[i] = 0;
        }
    }

    public static boolean play() {

        // 플레이어 순서대로 게임하기

        return false;
    }
}
