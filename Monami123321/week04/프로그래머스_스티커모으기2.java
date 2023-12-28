class 프로그래머스_스티커모으기2 {
    public int solution(int sticker[]) {
        int len = sticker.length;
        if(len==1) {
            return sticker[0];
        }
        int sum1 = 0;
        int[] dp = new int[len];
        dp[0] = sticker[0];
        dp[1] = dp[0];
        for(int i = 2; i<len-1;i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+sticker[i]);
        }
        sum1 = dp[len-2];
        int sum2 =0;
        dp[0] = 0;
        dp[1] = sticker[1];
        for(int i = 2; i<len;i++) {
            dp[i] = Math.max(dp[i-1],dp[i-2]+sticker[i]);
        }
        sum2 = dp[len-1];
        
        return Math.max(sum1,sum2);
    }
}