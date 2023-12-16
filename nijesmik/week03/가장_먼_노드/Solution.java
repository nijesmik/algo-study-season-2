package nijesmik.week03.가장_먼_노드;

import java.util.*;

class Solution {
    class Node {
        Queue<Integer> next = new LinkedList<>();
    }

    public int solution(int n, int[][] edge) {
        Node[] graph = new Node[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new Node();
        }
        boolean[] visited = new boolean[n+1];
        for (int[] e : edge) {
            graph[e[0]].next.add(e[1]);
            graph[e[1]].next.add(e[0]);
        }
        int ans = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;
        while (!q.isEmpty()) {
            int size = ans = q.size();
            while (size-- > 0) {
                int cur = q.poll();
                for (int i : graph[cur].next) {
                    if (!visited[i]) {
                        visited[i] = true;
                        q.add(i);
                    }
                }
            }
        }
        return ans;
    }
}
