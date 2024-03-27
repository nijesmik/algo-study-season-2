import java.util.Scanner;

public class 타일_채우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if(N % 2 == 1) {
            System.out.println(0);
            return;
        }
        int M = N/2;
        int[] dp1 = new int[M];
        dp1[0] = 3;
        int[] dp2 = new int[M];
        dp2[0] = 2;

        for(int i = 1; i < M; i++) {
            dp1[i] = dp2[i-1] + 3*dp1[i-1];
            dp2[i] = i==M-1 ? 0 : dp2[i-1] + 2*dp1[i-1];
        }
        if(M == 1) dp2[0] = 0;
        System.out.println(dp1[M-1] + dp2[M-1]);
    }
}
