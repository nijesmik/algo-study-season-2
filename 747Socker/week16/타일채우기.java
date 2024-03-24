import java.util.Scanner;

public class 타일채우기 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] DP = new int[N + 1];


        if (N % 2 != 0) {
            System.out.println(0);
            return;
        }
        
        DP[0] = 1;
        DP[1] = 0;
        DP[2] = 3;

        for (int i = 4; i <= N; i += 2) {
            DP[i] = DP[i - 2] * DP[2];
            for (int j = i - 4; j >= 0; j -= 2) {
                DP[i] = DP[i] + (DP[j] * 2);
            }
        }

        System.out.println(DP[N]);
    }
}
