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
        int inning = 0, idx = 0, out = 0, score = 0;
        Queue<Integer> field = new LinkedList<>(List.of(0, 0, 0));
        while (inning < N) {
            int res = result[inning][players[idx]];
            if (res == 0) {
                if (++out == 3) {
                    out = 0;
                    inning++;
                    field = new LinkedList<>(List.of(0, 0, 0));
                }
            } else {
                field.add(1);
                while (res-- > 0) {
                    score += field.poll();
                    if (res > 0) {
                        field.add(0);
                    }
                }
            }
            idx = (idx + 1) % 9;
        }
        ans = Math.max(ans, score);
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