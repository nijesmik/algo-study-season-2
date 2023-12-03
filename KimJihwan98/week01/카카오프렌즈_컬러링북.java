import java.util.*;

class Solution {
    static int numberOfArea, maxSizeOfOneArea;
    static int m, n;
    public int[] solution(int m1, int n1, int[][] picture) {
        m = m1;
        n = n1;
        numberOfArea = 0;
        maxSizeOfOneArea = 0;
        
        boolean[][] visited = new boolean[m][n];
        q = new LinkedList<>();
        for(int i = 0; i < m; i++ ) {
            for(int j = 0; j < n; j++) {
                if(picture[i][j] == 0 || visited[i][j]) continue;
                numberOfArea++;
                int col = picture[i][j];
                q.add(new int[] {i, j});
                bfs(col, picture, visited);
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    static Queue<int[]> q;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static void bfs(int color, int[][] picture, boolean[][] visited) {
        int count = 0;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];

            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(!(nr>=0 && nr<m && nc>=0 && nc<n)) continue;
                if(picture[nr][nc] == 0 || visited[nr][nc]) continue;
                if(picture[nr][nc] != color) continue;
                
                visited[nr][nc] = true;
                count++;
                q.add(new int[] {nr, nc});
            }
        }
        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
    }
}