public class 프로그래머스_파괴되지않은건물 {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        int[][] map = new int[n][m];

        for (int i = 0; i < skill.length; i++) {
            if (skill[i][0] == 1) {
                skill[i][5] *= -1;
            }
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int d = skill[i][5];
            map[r1][c1] += d;
            if (c2 != m - 1) {
                map[r1][c2 + 1] -= d;
            }
            if (r2 != n - 1) {
                map[r2 + 1][c1] -= d;
            }
            if (r2 != n - 1 && c2 != m - 1) {
                map[r2 + 1][c2 + 1] += d;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                map[j][i] += map[j - 1][i];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                map[i][j] += map[i][j - 1];
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + map[i][j] > 0) {
                    ans++;
                }
            }
        }

        return ans;

    }
}
