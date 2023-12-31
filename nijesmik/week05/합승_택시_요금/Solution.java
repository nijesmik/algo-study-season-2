package nijesmik.week05.합승_택시_요금;

import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dist = new int[n+1][n+1];
        int INF = Integer.MAX_VALUE / 201 / 3;
        for (int[] row : dist) {
            Arrays.fill(row, INF);
        }
        for (int i = 0; i <= n; i++) {
            dist[i][i] = 0;
        }
        for (int[] fare : fares) {
            dist[fare[0]][fare[1]] = fare[2];
            dist[fare[1]][fare[0]] = fare[2];
        }
        for (int i = 1; i <= n; i++) {
            for (int st = 1; st <= n; st++) {
                for (int en= 1; en <= n; en++) {
                    dist[st][en] = Math.min(dist[st][en], dist[st][i] + dist[i][en]);
                }
            }
        }
        int min = INF;
        for (int i = 1; i <= n; i++) {
            min = Math.min(min, dist[i][s] + dist[i][a] + dist[i][b]);
        }
        return min;
    }
}
