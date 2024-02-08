package jechul;

public class 산모양타일링 {
	static int modNum = 10007;

	public int solution(int n, int[] tops) {
		int[][] dp = new int[n + 1][2];

		dp[0][0] = 1;

		for (int i = 0; i < n; i++) {
			if (tops[i] == 1) {
				dp[i + 1][0] = (dp[i][0] * 3 + dp[i][1] * 2) % modNum;
			} else {
				dp[i + 1][0] = (dp[i][0] * 2 + dp[i][1]) % modNum;
			}

			dp[i + 1][1] = (dp[i][0] + dp[i][1]) % modNum;
		}

		return (dp[n][0] + dp[n][1]) % modNum;
	}
}