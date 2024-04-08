import java.util.Scanner;

public class 사다리_조작 {
    static int R, C, ans;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt();
        int M = sc.nextInt();
        R = sc.nextInt();

        visited = new boolean[R][C + 1];

        while (M-- > 0) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt();
            visited[r][c] = true;
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
                if (!visited[i][j] && !visited[i][j - 1] && !visited[i][j + 1]) {
                    visited[i][j] = true;
                    dfs(depth + 1, max);
                    visited[i][j] = false;
                }
            }
        }
    }

    static boolean test() {
        for (int i = 1; i < C; i++) {
            if (!testColumn(i)) {
                return false;
            }
        }
        return true;
    }

    static boolean testColumn(int start) {
        int c = start;
        for (int i = 0; i < R; i++) {
            if (visited[i][c]) {
                c++;
            } else if (visited[i][c - 1]) {
                c--;
            }
        }
        return (c == start);
    }
}