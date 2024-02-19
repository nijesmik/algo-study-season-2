import java.util.*;

public class 야구 {
    static boolean[] visited = new boolean[9];
    static int players[], N, result[][], ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        result = new int[N][9];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 9; j++) {
                result[i][j] = sc.nextInt();
            }
        }
        players = new int[9];
        visited[0] = true;
        players[3] = 0;
        dfs(0);
        System.out.println(ans);
    }

    static void deriveScore() {
        int idx = 0; score = 0;
        for (int inning = 0; inning < N; inning++) {
            int out = 0;
            int[] base = new int[4];
            while (out < 3) {
                int res = result[inning][players[idx]];
                if (res == 0) {
                    out++;
                } else {
                    base[0] = 1;
                    base = moveBase(base, res);
                }
                idx = (idx + 1) % 9;
            }
        }
        ans = Math.max(ans, score);
    }

    static int score;

    static int[] moveBase(int[] oldBase, int res) {
        for (int i = 0; i < res; i++) {
            score += oldBase[3 - i];
        }
        int[] newBase = new int[4];
        for (int i = res; i < 4; i++) {
            newBase[i] = oldBase[i - res];
        }
        return newBase;
    }

    static void dfs(int depth) {
        if (depth == 9) {
            deriveScore();
            return;
        }

        int delta = 1;
        if (depth == 2) {
            delta++;
        }
        for (int i = 0; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                players[depth] = i;
                dfs(depth + delta);
                visited[i] = false;
            }
        }
    }
}