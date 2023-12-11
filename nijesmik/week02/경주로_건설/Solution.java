package nijesmik.week02.경주로_건설;

import java.util.*;

class Solution {
    int[][] price;
    int R, C;

    public int solution(int[][] board) {
        R = board.length;
        C = board[0].length;
        price = new int[R][C];

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0, 0));
        q.add(new Point(0, 0, 1, 0));
        int[] dr = {1, 0, -1, 0};
        int[] dc = {0, 1, 0, -1};
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = -1; i < 2; i++) {
                int dir = (4 + cur.dir + i) % 4;
                int nr = cur.r + dr[dir], nc = cur.c + dc[dir];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || board[nr][nc] == 1) continue;
                int p = cur.price + 100;
                if (dir != cur.dir) p += 500;
                int cmp = price[nr][nc];
                if (cmp == 0 || p <= cmp + 500) {
                    if (cmp == 0 || p < cmp) {
                        price[nr][nc] = p;
                    }
                    q.add(new Point(nr, nc, dir, p));
                }
            }
        }
        return price[R-1][C-1];
    }

    class Point {
        int r, c, dir, price;
        Point(int r, int c, int dir, int price) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.price = price;
        }
    }
}
