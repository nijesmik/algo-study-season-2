package 도둑질;

class Solution {
    public int solution(int[] money) {
        int N = money.length;

        int[] dp1 = new int[N];
        dp1[0] = money[0];
        dp1[1] = Math.max(dp1[0], money[1]);

        int[] dp2 = new int[N];
        dp2[1] = money[1];

        for (int i = 2; i < N; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
        }
        return Math.max(dp1[N - 2], dp2[N - 1]);
    }
}
