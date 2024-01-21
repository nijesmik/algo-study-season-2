import java.util.Scanner;

public class Main {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (check(i, true)) count++; // 가로 길 확인
            if (check(i, false)) count++; // 세로 길 확인
        }

        System.out.println(count);
    }

    static boolean check(int line, boolean isRow) {
        boolean[] visited = new boolean[N];
        int[] height = new int[N];

        for (int i = 0; i < N; i++) {
            height[i] = isRow ? map[line][i] : map[i][line];
        }

        for (int i = 0; i < N - 1; i++) {
            if (height[i] == height[i + 1]) continue;
            if (Math.abs(height[i] - height[i + 1]) > 1) return false;

            if (height[i] > height[i + 1]) {
                for (int j = i + 1; j <= i + L; j++) {
                    if (j >= N || visited[j] || height[j] != height[i + 1]) return false;
                    visited[j] = true;
                }
            } else {
                for (int j = i; j > i - L; j--) {
                    if (j < 0 || visited[j] || height[j] != height[i]) return false;
                    visited[j] = true;
                }
            }
        }
        return true;
    }
}
