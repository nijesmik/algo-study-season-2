package nijesmik.week16;
import java.util.Scanner;

public class 타일_채우기 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] dp = new int[n + 1][3];
        if (n > 1) {
            dp[2][0] = 1;
            dp[2][1] = 1;
            dp[2][2] = 1;
        }
        for (int i = 4; i <= n; i++) {
            dp[i][0] = dp[i - 2][0] + dp[i - 2][1] + dp[i - 2][2];
            dp[i][1] = dp[i - 2][0] + dp[i - 2][1] * 2 + dp[i - 2][2];
            dp[i][2] = dp[i - 2][0] + dp[i - 2][1] + dp[i - 2][2] * 2;
        }
        System.out.println(dp[n][0] + dp[n][1] + dp[n][2]);
    }
}