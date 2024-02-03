package nijesmik.week09.도넛과_막대_그래프;

import java.util.*;

class Solution {
    int N = 1_000_000;
    Node[] nodes = new Node[N + 1];
    int start, ans[];
    boolean[] visited = new boolean[N + 1];

    public int[] solution(int[][] edges) {
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node();
        }

        List<Integer> starts = new ArrayList<>();
        for (int[] edge : edges) {
            int idx = edge[0];
            nodes[idx].next.add(edge[1]);
            nodes[edge[1]].prev++;
            if (nodes[idx].next.size() == 2) {
                starts.add(idx);
                visited[idx] = true;
            }
        }
        for (int idx : starts) {
            if (nodes[idx].prev == 0) {
                start = idx;
            }
        }

        ans = new int[4];
        ans[0] = start;
        for (int node : nodes[start].next) {
            s = node;
            e = 0;
            n = 1;
            visited[node] = true;
            dfs(node);
            if (n == e) {
                ans[1]++;
            } else if (n - 1 == e) {
                ans[2]++;
            } else {
                ans[3]++;
            }
        }

        return ans;
    }

    int s, n, e;

    void dfs(int cur) {
        if (n < e) {
            return;
        }
        while (!nodes[cur].next.isEmpty()) {
            int idx = nodes[cur].next.pop();
            if (!visited[idx]) {
                visited[idx] = true;
                n++;
            }
            e++;
            dfs(idx);
        }
    }

    class Node {
        Stack<Integer> next = new Stack<>();
        int prev = 0;
    }
}