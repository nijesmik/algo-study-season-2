import java.util.*;
class Solution {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static void bfs() {

    }
    static Queue<int[]> queue;

    public int solution(int[][] board) {
        int n = board.length;

        int[][][] visited = new int[4][n][n];
        for(int i= 0; i<4; i++) {
            for(int j = 0; j<n; j++) {
                for(int k = 0; k<n; k++) {
                    visited[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        queue = new LinkedList<>();
        queue.add(new int[] {0,0,3,0});
        queue.add(new int[] {0,0,1,0});

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            int prevDir = now[2];
            int total = now[3];

            for(int i = 0; i < 4; i++) {
                int nr = r+dr[i];
                int nc = c+dc[i];
                int cost = prevDir == i ? 100 : 600;

                if(nr<0 || nr > n-1 || nc<0 || nc > n-1 || board[nr][nc] != 0) {
                    continue;
                }

                if(visited[i][nr][nc] < total+cost ) {
                    continue;
                }

                queue.add(new int[] {nr,nc,i,total+cost});
                visited[i][nr][nc] = total+cost;

            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i =0; i<4; i++) {
            ans = Math.min(visited[i][n-1][n-1],ans);
        }

        return ans;

    }
}