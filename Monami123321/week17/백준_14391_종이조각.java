import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14391_종이조각 {
    static int N, M, ans;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - 48;
            }
        }
        ans = 0;
        dfs(0, 0);
        System.out.println(ans);
        br.close();
    }

    static void dfs(int depth, int sum) {
        if (depth == M * N) {
            ans = Math.max(ans, sum);
            return;
        }
        // 이 칸만 찢기
        int r = depth / M;
        int c = depth % M;
        if (visited[r][c]) {
            dfs(depth + 1, sum);
        } else {
            visited[r][c] = true;
            dfs(depth + 1, sum + map[r][c]);
            visited[r][c] = false;
            // 가로 찢기
            int caseSum = map[r][c];
            for (int i = c + 1; i < M; i++) {
                if (visited[r][i]) {
                    break;
                }
                caseSum *= 10;
                caseSum += map[r][i];
                for (int j = c + 1; j <= i; j++) {
                    visited[r][j] = true;
                }
                dfs(depth + 1, sum + caseSum);
                for (int j = c + 1; j <= i; j++) {
                    visited[r][j] = false;
                }
            }

            // 세로 찢기
            caseSum = map[r][c];
            for (int i = r + 1; i < N; i++) {
                if (visited[i][c]) {
                    break;
                }
                caseSum *= 10;
                caseSum += map[i][c];
                for (int j = r + 1; j <= i; j++) {
                    visited[j][c] = true;
                }
                dfs(depth + 1, sum + caseSum);
                for (int j = r + 1; j <= i; j++) {
                    visited[j][c] = false;
                }
            }
        }

    }
}
