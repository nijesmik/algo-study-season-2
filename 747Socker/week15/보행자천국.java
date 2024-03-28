package ns;

public class 보행자천국 {
    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {
        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (cityMap[i - 1][j - 1] == 1) { 
                    continue;
                }

                if (i > 1) {
                    if (cityMap[i - 2][j - 1] != 2) { 
                        dp[i][j] += dp[i - 1][j];
                    } else { 
                        dp[i][j] += dp[i - 1][j];
                    }
                }

                if (j > 1) {
                    if (cityMap[i - 1][j - 2] != 2) { 
                        dp[i][j] += dp[i][j - 1];
                    } else { 
                        dp[i][j] += dp[i][j - 1];
                    }
                }

                dp[i][j] %= MOD;
            }
        }

        return dp[m][n];
    }
}

