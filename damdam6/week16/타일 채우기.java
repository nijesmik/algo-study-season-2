import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());


        int[] dp = new int[N + 1];

        // 홀수면 안됨.
        if (N % 2 == 0) {
            dp[0] = 1; 
            dp[2] = 3; 

            for (int i = 4; i <= N; i += 2) {

                /// 이전거에 + 2칸 나간 방법 곱
                dp[i] = dp[i - 2] * dp[2];

                  // 2개짜리 외의 모양
                for (int j = 4; j<= i; j += 2) {
                    
                    dp[i] += dp[i - j] * 2;
                }
            }
        }

        System.out.println(dp[N]);
    }
}
