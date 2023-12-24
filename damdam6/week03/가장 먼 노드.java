import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        boolean[][] graph = new boolean[n + 1][n + 1];
        boolean[] vt = new boolean[n + 1];
        Deque<Integer> qu = new ArrayDeque<>();
        int[] dis = new int[n + 1];

        for (int[] link : edge) {
            graph[link[0]][link[1]] = graph[link[1]][link[0]] = true;
        }

        qu.add(1);
        vt[1] = true;

        // BFS
        while (!qu.isEmpty()) {
            int node = qu.poll();

            for (int i = 1; i <= n; i++) {
                if (graph[node][i] && !vt[i]) {
                    qu.add(i);
                    vt[i] = true;
                    dis[i] = dis[node] + 1;
                }
            }
        }

        int maxDistance = -1;
        for(int distance : dis){
            maxDistance = Math.max(maxDistance, distance);
        }
        int count = 0;
        for (int distance : dis) {
            if (distance == maxDistance) {
                count++;
            }
        }

        return count;
    }
}
