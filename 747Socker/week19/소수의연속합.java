import java.util.*;

public class 소수의연속합 {
    static int N;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();

        if (N < 2) {
            System.out.println(0);
            return;
        }

        List<Integer> primes = era(N);
        int result = finds(N, primes);
        System.out.println(result);
    }

    private static List<Integer> era(int N) {
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        for (int p = 2; p * p <= N; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= N; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int p = 2; p <= N; p++) {
            if (isPrime[p]) {
                primes.add(p);
            }
        }
        return primes;
    }

    private static int finds(int N, List<Integer> primes) {
        int count = 0;
        int sum = 0;
        int start = 0;
        for (int end = 0; end < primes.size(); end++) {
            sum += primes.get(end);

            while (sum > N && start <= end) {
                sum -= primes.get(start);
                start++;
            }

            if (sum == N) {
                count++;
            }
        }

        return count;
    }
}
