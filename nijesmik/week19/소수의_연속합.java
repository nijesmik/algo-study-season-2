import java.util.*;

public class 소수의_연속합 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        int root = (int) Math.sqrt(n) + 1;
        for (int i = 2; i < root; i++) {
            if (isPrime[i]) {
                for (int j = i * 2; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                int sum = i;
                for (int j = i + 1; j < n && sum < n; j++) {
                    if (isPrime[j]) {
                        sum += j;
                    }
                }
                if (sum == n) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}