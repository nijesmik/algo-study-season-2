class 프로그래머스_산모양타일링 {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[2][n+1];
        if(tops[0] == 0) {
            dp[0][1] = 1;
            dp[1][1] = 2;
        } else {
            dp[0][1] = 1;
            dp[1][1] = 3;

        }
        for(int i = 2; i < n+1; i++) {
            if(tops[i-1] == 0) {
                dp[0][i] = (dp[0][i-1] + dp[1][i-1]) % 10007;
                dp[1][i] = (dp[1][i-1]*2 + dp[0][i-1]) %10007;
            } else {
                dp[0][i] = (dp[0][i-1] + dp[1][i-1]) % 10007;
                dp[1][i] = (dp[1][i-1]*3 + dp[0][i-1]*2) %10007;
            }
        }
        return (dp[0][n]+dp[1][n]) % 10007;
    }
}