package nijesmik.week09.도넛과_막대_그래프;

import java.util.*;

class Solution {
    int N = 1_000_000;
    Node[] nodes = new Node[N + 1];
    int start, ans[];

    public int[] solution(int[][] edges) {
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node();
        }

        List<Integer> starts = new ArrayList<>();
        Set<Integer> ends = new HashSet<>();
        for (int[] edge : edges) {
            int idx = edge[0];
            nodes[idx].next++;
            nodes[edge[1]].prev++;
            ends.add(edge[1]);
            if (nodes[idx].next == 2) {
                starts.add(idx);
            }
        }
        ans = new int[4];
        for (int idx : ends) {
            if (nodes[idx].next == 0) {
                ans[2]++;
            }
        }
        for (int idx : starts) {
            if (nodes[idx].prev == 0) {
                start = idx;
            }
        }
        ans[0] = start;
        ans[3] = starts.size() - 1;
        ans[1] = nodes[start].next - ans[3] - ans[2];

        return ans;
    }

    class Node {
        int next, prev;
    }
}