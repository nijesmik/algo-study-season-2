import java.util.Scanner;

public class 사다리_조작 {
    static int R, C, ans;
    static boolean[][] right, left;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt();
        int M = sc.nextInt();
        R = sc.nextInt();

        right = new boolean[R][C + 2];
        left = new boolean[R][C + 2];

        while (M-- > 0) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt();
            right[r][c] = left[r][c + 1] = true;
        }

        ans = -1;
        for (int i = 0; i <= 3; i++) {
            dfs(0, i);
        }
        System.out.println(ans);
    }

    static void dfs(int depth, int max) {
        if (ans > -1) {
            return;
        }
        if (depth == max) {
            if (test()) {
                ans = max;
            }
            return;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (!right[i][j] && !left[i][j] && !right[i][j+ 1] && !left[i][j + 1]) {
                    right[i][j] = true;
                    left[i][j + 1] = true;
                    dfs(depth + 1, max);
                    right[i][j] = false;
                    left[i][j + 1] = false;
                }
            }
        }
    }

    static boolean test() {
        for (int i = 0; i < C; i++) {
            if (!testColumn(i)) {
                return false;
            }
        }
        return true;
    }

    static boolean testColumn(int start) {
        int c = start;
        for (int i = 0; i < R; i++) {
            if (right[i][c] && left[i][c + 1]) {
                c++;
            } else if (left[i][c] && right[i][c - 1]) {
                c--;
            }
        }
        return (c == start);
    }
}