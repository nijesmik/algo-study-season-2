package nijesmik.week05;
import java.util.Scanner;

public class 경사로 {
    static int N, L;
    static boolean[] installed;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();

        int[][] map = new int[N * 2][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i + N][j] = map[j][i];
            }
        }

        int ans = 0;
        for (int i = 0; i < N*2; i++) {
            if (isPassable(map[i])) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean isPassable(int[] line) {
        installed = new boolean[N];
        int idx = 0;
        while (idx < N - 1) {
            int diff = line[idx] - line[idx + 1];
            if (Math.abs(diff) > 1) return false;
            if (!isInstallable(line, idx + (diff+1)/2, diff)) return false;
            if (diff == 1) idx += L-1;
            idx++;
        }
        return true;
    }

    static boolean isInstallable(int[] line, int idx, int dir) {
        if (dir == 0) return true;
        int cnt = 0;
        int height = line[idx];
        while (cnt < L && idx >= 0 && idx < N && line[idx] == height && !installed[idx]) {
            installed[idx] = true;
            cnt++;
            idx += dir;
        }
        if (cnt == L) {
            return true;
        }
        return false;
    }
}