package jechul;

import java.util.LinkedList;
import java.util.Queue;

class 아이템줍기 {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        boolean[][] map = new boolean[101][101];
        boolean[][] visited = new boolean[101][101];
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int[] rect : rectangle) {
            for (int i = rect[1] * 2; i <= rect[3] * 2; i++) {
                for (int j = rect[0] * 2; j <= rect[2] * 2; j++) {
                    if (i == rect[1] * 2 || i == rect[3] * 2 || j == rect[0] * 2 || j == rect[2] * 2) {
                        map[i][j] = true; 
                    } else {
                        map[i][j] = false;
                    }
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{characterY * 2, characterX * 2, 0});
        visited[characterY * 2][characterX * 2] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[1], y = current[0], dist = current[2];

            if (x == itemX * 2 && y == itemY * 2) {
                answer = dist; 
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx <= 100 && ny <= 100 && map[ny][nx] && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    queue.offer(new int[]{ny, nx, dist + 1});
                }
            }
        }

        return answer/2;
    }
}
