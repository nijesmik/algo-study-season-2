package nijesmik.week13.파괴되지_않은_건물;

class Solution {
    public int solution(int[][] board, int[][] skills) {
        int R = board.length, C = board[0].length;
        int[][] sum = new int[R + 1][C + 1];

        for (int[] skill : skills) {
            int degree = skill[5];
            if (skill[0] == 1) {
                degree *= -1;
            }
            int r1 = skill[1], c1 = skill[2], r2 = skill[3] + 1, c2 = skill[4] + 1;
            sum[r1][c1] += degree;
            sum[r1][c2] += -degree;
            sum[r2][c1] += -degree;
            sum[r2][c2] += degree;
        }

        for (int i = 0; i < R; i++) {
            for (int j = 1; j < C; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }
        for (int j = 0; j < C; j++) {
            for (int i = 1; i < R; i++) {
                sum[i][j] += sum[i - 1][j];
            }
        }

        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] + sum[i][j] > 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
