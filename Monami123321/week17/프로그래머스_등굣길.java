public class 프로그래머스_등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int MOD = 1_000_000_007;
        int[][] map = new int[n][m];

        for (int i = 0; i < puddles.length; ++i) {
            for (int j = 0; j < 2; ++j) {
                int c = puddles[i][0] - 1;
                int r = puddles[i][1] - 1;
                map[r][c] = -1;
            }
        }
        for (int i = 1; i < m; ++i) {
            if (map[0][i] == -1) {
                break;
            }
            map[0][i] = 1;
        }
        for (int i = 1; i < n; ++i) {
            if (map[i][0] == -1) {
                break;
            }
            map[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                if (map[i][j] == -1) {
                    continue;
                }
                map[i][j] = (((map[i][j - 1] != -1 ? map[i][j - 1] : 0) % MOD) + ((map[i - 1][j] != -1 ? map[i - 1][j] : 0) % MOD)) % MOD;
            }
        }
        return map[n - 1][m - 1];


    }
}
