import java.util.*;

public class 소수의_연속합 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        int m = 0;
        int[] prime = new int[n];
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                prime[m++] = i;
                for (int j = i * 2; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        int ans = 0;
        int start = 0, end = 0, sum = 0;
        while (end <= m) {
            if (sum >= n) {
                sum -= prime[start++];
            }
            if (sum < n) {
                sum += prime[end++];
            }
            if (sum == n) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}