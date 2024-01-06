public class 프로그래머스_도둑질 {
    class Solution {
        public int solution(int[] money) {

            int[] dp = new int[money.length];
            dp[0] = money[0];
            dp[1] = dp[0];
            for(int i = 2; i< money.length-1; i++) {
                dp[i] = Math.max(dp[i-1],dp[i-2]+money[i]);
            }
            int ans = dp[money.length-2];
            dp[0] = 0;
            dp[1] = money[1];
            for(int i = 2; i< money.length; i++) {
                dp[i] = Math.max(dp[i-1],dp[i-2]+money[i]);
            }
            ans = Math.max(ans,dp[money.length-1]);

            return ans;

        }
    }
}
