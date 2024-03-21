package nijesmik.week15.보행자_천국;

import java.util.*;

class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[2][m][n];

        // 0 : right, 1 : down
        for (int c = 0; c < n; c++) {
            if (cityMap[0][c] == 1) {
                break;
            }
            dp[0][0][c] = 1;
        }

        for (int r = 0; r < m; r++) {
            if (cityMap[r][0] == 1) {
                break;
            }
            dp[1][r][0] = 1;
        }

        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                if (cityMap[r - 1][c] != 1) {
                    dp[1][r][c] += dp[1][r - 1][c];
                }
                if (cityMap[r - 1][c] == 0) {
                    dp[1][r][c] += dp[0][r - 1][c];
                }

                if (cityMap[r][c - 1] != 1) {
                    dp[0][r][c] += dp[0][r][c - 1];
                }
                if (cityMap[r][c - 1] == 0) {
                    dp[0][r][c] += dp[1][r][c - 1];
                }

                dp[0][r][c] %= MOD;
                dp[1][r][c] %= MOD;
            }
        }

        return (dp[0][m - 1][n - 1] + dp[1][m - 1][n - 1]) % MOD;
    }
}