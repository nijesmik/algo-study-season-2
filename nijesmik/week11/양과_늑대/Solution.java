import java.util.*;

class Solution {
    Node[] nodes;

    public int solution(int[] info, int[][] edges) {
        int N = info.length;
        nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(info[i]);
        }
        for (int[] edge : edges) {
            nodes[edge[0]].children.add(edge[1]);
        }
        Queue<Comb> q = new LinkedList<>();
        q.add(new Comb(nodes[0]));
        int ans = 0;
        while (!q.isEmpty()) {
            Comb cur = q.poll();
            ans = Math.max(ans, cur.sheep);
            int size = cur.next.size();
            while (size-- > 0) {
                int next = cur.next.poll();
                Node node = nodes[next];
                if (cur.sheep > node.wolf + cur.wolves) {
                    q.add(new Comb(cur, node));
                }
                cur.next.add(next);
            }
        }
        return ans;
    }

    class Comb {
        int sheep, wolves;
        Queue<Integer> next;

        Comb(Node node) {
            this.sheep = node.sheep;
            this.wolves = node.wolf;
            this.next = new LinkedList<>();
            for (int idx : node.children) {
                this.next.add(idx);
            }
        }

        Comb(Comb comb, Node node) {
            this.sheep = comb.sheep + node.sheep;
            this.wolves = comb.wolves + node.wolf;
            this.next = new LinkedList<>(comb.next);
            for (int idx : node.children) {
                this.next.add(idx);
            }
        }
    }

    class Node {
        int sheep, wolf;
        List<Integer> children = new ArrayList<>();

        Node(int wolf) {
            this.wolf = wolf;
            sheep = 1 - wolf;
        }
    }
}
