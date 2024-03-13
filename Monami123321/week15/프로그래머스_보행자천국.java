public class 프로그래머스_보행자천국 {
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][][] map = new int[2][m + 1][n + 1];
        map[0][0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cityMap[i][j] == 0) {
                    map[0][i][j + 1] += map[0][i][j] % MOD;
                    map[0][i][j + 1] += map[1][i][j] % MOD;
                    map[1][i + 1][j] += map[0][i][j] % MOD;
                    map[1][i + 1][j] += map[1][i][j] % MOD;
                } else if (cityMap[i][j] == 2) {
                    map[0][i][j + 1] += map[0][i][j] % MOD;
                    map[1][i + 1][j] += map[1][i][j] % MOD;
                }
            }
        }
        return ((map[0][m - 1][n - 1] % MOD) + (map[1][m - 1][n - 1] % MOD)) % MOD;
    }
}
