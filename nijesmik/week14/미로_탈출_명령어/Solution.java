package nijesmik.week14.미로_탈출_명령어;

class Solution {
    String ans;
    int n, m, r, c, k, comb[];
    String[] dir = { "d", "l", "r", "u" };
    int[] dr = { 1, 0, 0, -1 }, dc = { 0, -1, 1, 0 };

    boolean test(int x, int y, int depth) {
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if (dist + depth > k || (k - dist - depth) % 2 == 1) {
            return false;
        }
        return true;
    }

    void dfs(int depth, int x, int y) {
        if (ans != null || !test(x, y, depth)) {
            return;
        }

        if (depth == k) {
            if (x == r && y == c) {
                StringBuilder sb = new StringBuilder();
                for (int i : comb) {
                    sb.append(dir[i]);
                }
                ans = sb.toString();
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nr = x + dr[i], nc = y + dc[i];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                comb[depth] = i;
                dfs(depth + 1, nr, nc);
            }

        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r - 1;
        this.c = c - 1;
        this.k = k;
        comb = new int[k];
        dfs(0, x - 1, y - 1);

        if (ans == null) {
            ans = "impossible";
        }
        return ans;
    }
}
