package nijesmik.week15.보행자_천국;

import java.util.*;

class Solution {
    int MOD = 20170805;
    int[] dr = {1, 0}, dc = {0, 1};
    int[][] map;
    Queue<Record> q;
    
    public int solution(int m, int n, int[][] cityMap) {
        map = cityMap;
        q = new LinkedList<>();

        add(1, 0, 0);
        add(0, 1, 1);
        int ans = 0;
        while (!q.isEmpty()) {
            Record cur = q.poll();
            if (cur.r == m - 1 && cur.c == n - 1) {
                ans = (ans + 1) % MOD;
                continue;
            }
            for (int i = 0; i < 2; i++) {
                int nr = cur.r + dr[i], nc = cur.c + dc[i];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    if (map[cur.r][cur.c] == 2 && cur.prevDir != i) {
                        continue;
                    }
                    add(nr, nc, i);
                }
            }
        }
        return ans;
    }
    
    void add(int r, int c, int prevDir) {
        if (map[r][c] == 1) {
            return;
        }
        q.add(new Record(r, c, prevDir));
    }
    
    class Record {
        int r, c, prevDir;
        Record(int r, int c, int prevDir) {
            this.r = r;
            this.c = c;
            this.prevDir = prevDir;
        }
    }
}