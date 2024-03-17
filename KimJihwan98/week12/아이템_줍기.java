package 백준;

import java.util.*;

public class 아이템_줍기 {
    class Solution {
        static int[][] map;
        static int[] dr = {0,1,0,-1};
        static int[] dc = {1,0,-1,0};
        static boolean[][] visited;
        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            map = new int[101][101];
            visited = new boolean[101][101];
            for(int[] rec : rectangle) {
                for(int i = rec[0]*2; i <= rec[2]*2; i++ ) {
                    for(int j = rec[1]*2; j <= rec[3]*2; j++) {
                        if(i==rec[0]*2 || i==rec[2]*2 || j==rec[1]*2 || j==rec[3]*2) {
                            if(map[i][j] == 1) continue;
                            map[i][j]++;
                        }
                        else map[i][j] += 2;
                    }
                }
            }

            int answer = 0;
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[] {characterX*2, characterY*2, 0});
            visited[characterX*2][characterY*2] = true;
            loop : while(!q.isEmpty()) {
                int[] now = q.poll();
                for(int i = 0; i < 4; i++) {
                    int nr = now[0] + dr[i];
                    int nc = now[1] + dc[i];
                    if(nr==itemX*2 && nc==itemY*2) {
                        answer=(now[2]+1)/2;
                        break loop;
                    }
                    if(!(nr>=0 && nr <101 && nc>=0 && nc<101)) continue;
                    if(visited[nr][nc]) continue;
                    if(map[nr][nc] != 1) continue;
                    visited[nr][nc] = true;
                    q.add(new int[] {nr, nc, now[2]+1});
                }
            }

            return answer;
        }
    }
}
