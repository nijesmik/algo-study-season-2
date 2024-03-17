public class 프로그래머스_미로탈출명령어 {
    static char[] cmd = {'d', 'l', 'r', 'u'};
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static int limit, N, M;
    static String ans = "";
    static boolean flag;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        limit = k;
        N = n;
        M = m;
        flag = false;
        int dist = getDist(x, y, r, c);
        if (dist > k || (dist - k) % 2 != 0) {
            return "impossible";
        }
        dfs(x - 1, y - 1, new char[k], 0, r - 1, c - 1);
        return ans;
    }

    static void dfs(int r, int c, char[] arr, int depth, int endR, int endC) {
        if (flag) {
            return;
        }

        if (getDist(r, c, endR, endC) > limit - depth || (limit - depth - getDist(r, c, endR, endC)) % 2 != 0) {
            return;
        }


        if (depth == limit) {
            flag = true;
            ans = String.valueOf(arr);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr > N - 1 || nc < 0 || nc > M - 1) {
                continue;
            }
            arr[depth] = cmd[i];
            dfs(nr, nc, arr, depth + 1, endR, endC);
        }
    }

    static int getDist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
