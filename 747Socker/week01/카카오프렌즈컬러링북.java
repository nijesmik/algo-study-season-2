package bj;
import java.util.*;

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {

    int[] dx = {-1, 0 , 1, 0};
    int[] dy = {0, 1, 0, -1};
    boolean[][] chk;

    public int[] solution(int m, int n, int[][] picture) {
    	chk = new boolean[m][n];
        int area = 0; 
        int maxi = 0; 

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(picture[i][j] != 0 && !chk[i][j]) {
                    maxi = Math.max(maxi, bfs(m, n, i, j, picture));
                    area++;
                }
            }
        }

        return new int[]{area, maxi};
    }

    public int bfs(int m, int n, int x, int y, int[][] picture) {
        int ans = 1;
        int target = picture[x][y];
        Queue<Point> q = new LinkedList<>();

        chk[x][y] = true;
        q.offer(new Point(x, y));

        while(!q.isEmpty()) {
            Point p = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx >= 0 && nx < m && ny >= 0 && ny < n && picture[nx][ny] == target && !chk[nx][ny]) {
                    q.offer(new Point(nx, ny));
                    chk[nx][ny] = true;
                    ans++;
                }
            }
        }

        return ans;
    }

}