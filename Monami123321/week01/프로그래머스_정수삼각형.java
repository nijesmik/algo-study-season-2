class Solution {
    public int solution(int[][] triangle) {
        int len = triangle.length;
        int[][] dp = new int[len+1][len+1];
        for(int j = 1; j < len+1; j++) {
            for(int i = j; i < len+1; i++) {
                dp[i][j] = Math.max(dp[i-1][j]+triangle[i-1][j-1],dp[i-1][j-1]+triangle[i-1][j-1]);
            }

        }
        int res = 0;
        for(int tmp : dp[len]) {
            res = res < tmp ? tmp : res;
        }
        return res;

    }
}