import java.util.Arrays;
import java.util.Scanner;

public class 공유기_설치 {
    static int N, C;
    static int[] pos;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        C = sc.nextInt();
        pos = new int[N];
        for (int i = 0; i < N; i++) {
            pos[i] = sc.nextInt();
        }
        Arrays.sort(pos);
        int start = 1, end = pos[N-1] - pos[0], ans = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (calcCount(mid) >= C) {
                ans = Math.max(ans, mid);
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(ans);
    }

    static int calcCount(int dist) {
        int cnt = 1, cur = pos[0];
        for (int i = 0; i < N; i++) {
            if (pos[i] - cur >= dist) {
                cnt++;
                cur = pos[i];
            }
        }
        return cnt;
    }
}
