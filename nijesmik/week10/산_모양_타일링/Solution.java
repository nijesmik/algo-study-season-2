package nijesmik.week10.산_모양_타일링;

class Solution {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[4][n];
        dp[0][0] = dp[1][0] = dp[2][0] = 1;
        if (tops[0] > 0) {
            dp[3][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = (dp[0][i - 1] + dp[1][i - 1] + dp[2][i - 1] + dp[3][i - 1]) % 10007;
            dp[1][i] = (dp[0][i - 1] + dp[1][i - 1] + dp[3][i - 1]) % 10007;
            dp[2][i] = dp[0][i];
            if (tops[i] > 0) {
                dp[3][i] = dp[0][i];
            }
        }
        int ans = 0;
        int i = 4;
        while (i-- > 0) {
            ans += dp[i][n - 1];
        }
        return ans % 10007;
    }
}
