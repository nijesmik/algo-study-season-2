import java.util.*;

public class 놀이_공원 {
    static long N;
    static int M, rideTimes[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextLong();
        M = sc.nextInt();

        if (N <= M) {
            System.out.println(N);
            return;
        }

        rideTimes = new int[M];
        for (int i = 0; i < M; i++) {
            rideTimes[i] = sc.nextInt();
        }

        long total = totalRideTime() - 1;
        long cnt = M;
        for (int i = 0; i < M; i++) {
            cnt += total / rideTimes[i];
        }
        total++;
        for (int i = 0; i < M; i++) {
            if (total % rideTimes[i] == 0) {
                cnt++;
            }
            if (cnt == N) {
                System.out.println(i+1);
                return;
            }
        }
    }

    static long totalRideTime() {
        long time = 0;
        long st = 0;
        long ed = N / M * 30;
        while (st <= ed) {
            long mid = (st + ed) / 2;
            long cnt = M;
            for (int i = 0; i < M; i++) {
                cnt += mid / rideTimes[i];
            }
            if (cnt < N) {
                st = mid + 1;
            } else {
                ed = mid - 1;
                time = mid;
            }
        }
        return time;
    }
}
