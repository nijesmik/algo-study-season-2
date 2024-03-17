package nijesmik.week12.아이템_줍기;

import java.util.*;

class Solution {
    final int MAX = 102;
    int map[][], itemX, itemY;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[MAX][MAX];
        this.itemX = itemX * 2;
        this.itemY = itemY * 2;

        for (int[] r : rectangle) {
            for (int i = 0; i < 4; i++) {
                r[i] *= 2;
            }
            for (int x = r[0]; x <= r[2]; x++) {
                for (int y = r[1]; y <= r[3]; y++) {
                    if (x > r[0] && x < r[2] && y > r[1] && y < r[3]) {
                        map[x][y] = 2;
                    } else if (map[x][y] == 0) {
                        map[x][y] = 1;
                    }
                }
            }
        }

        return bfs(characterX * 2, characterY * 2) / 2;
    }

    int bfs(int cx, int cy) {
        boolean[][] visited = new boolean[MAX][MAX];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { cx, cy, 0 });
        visited[cx][cy] = true;
        int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
                if (nx == itemX && ny == itemY) {
                    return cur[2] + 1;
                }
                if (map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[] { nx, ny, cur[2] + 1 });
                }
            }
        }
        return 0;
    }
}