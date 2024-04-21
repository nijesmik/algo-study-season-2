import java.util.Arrays;

public class 프로그래머스_길찾기게임 {

    static class Node {
        Node left, right;
        int r, c, id;

        public Node(int r, int c, int id) {
            this.r = r;
            this.c = c;
            this.id = id;
        }
    }

    static int index;

    public int[][] solution(int[][] nodeinfo) {
        int len = nodeinfo.length;
        Node[] nodes = new Node[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(nodeinfo[i][1], nodeinfo[i][0], i + 1);
        }
        Arrays.sort(nodes, (a, b) -> {
            if (a.r != b.r) {
                return b.r - a.r;
            }
            return a.c - b.c;
        });
        Node root = nodes[0];
        for (int i = 1; i < len; i++) {
            addChild(root, nodes[i]);
        }


        index = 0;
        int[][] ans = new int[2][len];
        preOrder(root, ans[0]);
        index = 0;
        postOrder(root, ans[1]);

        return ans;
    }

    static void addChild(Node parent, Node child) {
        if (parent.c > child.c) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                addChild(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                addChild(parent.right, child);
            }
        }
    }

    static void preOrder(Node root, int[] arr) {
        if (root == null) {
            return;
        }
        arr[index++] = root.id;
        preOrder(root.left, arr);
        preOrder(root.right, arr);
    }

    static void postOrder(Node root, int[] arr) {
        if (root == null) {
            return;
        }
        postOrder(root.left, arr);
        postOrder(root.right, arr);
        arr[index++] = root.id;
    }
}
