import java.util.ArrayList;
import java.util.List;

public class 프로그래머스_늑대와양 {
    static class Node {
        int type, id;
        Node parent;
        List<Node> children;

        public Node(int id, int type) {
            this.id = id;
            this.type = type;
            this.children = new ArrayList<>();
        }
    }

    static boolean[] visited;
    static int ans;

    public int solution(int[] info, int[][] edges) {
        int len = info.length;
        visited = new boolean[1 << (len)];
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(i, info[i]);
        }
        for (int i = 0; i < len - 1; i++) {
            nodes[edges[i][0]].children.add(nodes[edges[i][1]]);
        }
        ans = 0;
        dfs(nodes[0], 1, 1, 0, new ArrayList<>());
        return ans;
    }

    static void dfs(Node node, int check, int sheep, int wolf, ArrayList<Node> routes) {
        if (visited[check]) {
            return;
        }
        check |= (1 << node.id);
        ans = Math.max(ans, sheep);

        ArrayList<Node> nextRoutes = new ArrayList<>(routes);
        nextRoutes.remove(node);
        node.children.forEach(nextRoutes::add);

        for (Node n : nextRoutes) {
            if (n.type == 0) {
                dfs(n, check, sheep + 1, wolf, nextRoutes);
            } else {
                if (wolf + 1 >= sheep) {
                    continue;
                } else {
                    dfs(n, check, sheep, wolf + 1, nextRoutes);
                }
            }
        }

    }
}
