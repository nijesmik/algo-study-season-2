import java.util.*;

public class 프로그래머스_아이템줍기 {
    class Solution {
        static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
        static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};

        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            int[][] map = new int[104][104];
            for (int[] rec : rectangle) {
                for (int i = rec[1] * 2; i <= rec[3] * 2; i++) {
                    for (int j = rec[0] * 2; j <= rec[2] * 2; j++) {
                        map[i][j] = 1;
                    }
                }
            }
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{0, 0});

            while (!q.isEmpty()) {
                int[] now = q.poll();
                int r = now[0];
                int c = now[1];

                for (int i = 0; i < 8; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr < 0 || nr > 103 || nc < 0 || nc > 103 || map[nr][nc] == 2 || map[nr][nc] == -1) {
                        continue;
                    }

                    if (map[nr][nc] == 1) {
                        map[nr][nc] = -1;
                    } else {
                        q.add(new int[]{nr, nc});
                        map[nr][nc] = 2;
                    }
                }

            }


            q.add(new int[]{characterY * 2, characterX * 2, 0});
            map[characterY * 2][characterX * 2] = 2;

            int ans = 0;
            while (!q.isEmpty()) {
                int[] now = q.poll();
                int r = now[0];
                int c = now[1];
                int cost = now[2];

                if (r == itemY * 2 && c == itemX * 2) {
                    ans = cost;
                    break;
                }


                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (map[nr][nc] != -1) {
                        continue;
                    }
                    q.add(new int[]{nr, nc, cost + 1});
                    map[nr][nc] = 2;

                }
            }

            return ans / 2;

        }
    }
}
