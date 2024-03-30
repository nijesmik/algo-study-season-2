package nijesmik.week17.등굣길;

class Solution {
    public int solution(int C, int R, int[][] puddles) {
        int[][] dp = new int[R + 1][C + 1];
        for (int[] puddle : puddles) {
            int c = puddle[0];
            int r = puddle[1];
            dp[r][c] = -1;
        }
        dp[1][1] = 1;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (dp[i][j] == -1) {
                    continue;
                }
                if (dp[i][j - 1] > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
                if (dp[i - 1][j] > 0) {
                    dp[i][j] += dp[i - 1][j];
                }
                dp[i][j] %= 1_000_000_007;
            }
        }
        return dp[R][C];
    }
}