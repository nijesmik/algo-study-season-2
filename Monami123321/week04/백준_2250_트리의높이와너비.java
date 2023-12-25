import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2250_트리의높이와너비 {

    static class Node {
        Node left, right, parent;
        int depth, index, leftSize, rightSize;
    }

    static int calculateNodeSpace(Node node, int depth) {
        if (node == null) {
            return 0;
        }
        node.depth = depth;
        int leftSize = calculateNodeSpace(node.left, depth + 1);
        int rightSize = calculateNodeSpace(node.right, depth + 1);
        node.leftSize = leftSize;
        node.rightSize = rightSize;

        return leftSize + rightSize + 1;
    }

    static void setIndex(Node node, int ref, int dir) {
        if (node == null) {
            return;
        }
        // 1. 왼쪽 위에서 오른쪽으로 내려온 경우 2. 반대
        if (dir == 1) {
            node.index = ref + node.leftSize + 1;
            setIndex(node.left, node.index, -1);
            setIndex(node.right, node.index, 1);
        } else {
            node.index = ref - node.rightSize - 1;
            setIndex(node.left, node.index, -1);
            setIndex(node.right, node.index, 1);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new Node();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Node node = nodes[Integer.parseInt(st.nextToken())];
            Node left, right;
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp == -1) {
                left = null;
            } else {
                left = nodes[tmp];
            }
            tmp = Integer.parseInt(st.nextToken());
            if (tmp == -1) {
                right = null;
            } else {
                right = nodes[tmp];
            }
            node.left = left;
            node.right = right;

            if (left != null) {
                left.parent = node;
            }
            if (right != null) {
                right.parent = node;
            }
        }

        Node root = null;
        for (int i = 1; i < n + 1; i++) {
            if (nodes[i].parent == null) {
                root = nodes[i];
                break;
            }
        }

        calculateNodeSpace(root, 1); // 루트부터돌면서 각자 차지하는 공간 계산
        setIndex(root, 0, 1); // 한 번 더 돌면서 인덱스 결정

        // 너비계산시작
        int prevDepth = 0;
        int prevMin = 1 << 20;
        int prevMax = ~(1 << 20) + 1;

        int[] ans = {0, -1}; // {정답깊이, 정답너비}

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (true) {
            Node node = queue.poll();
            if (node.depth > prevDepth) {
                if (prevMax - prevMin > ans[1]) {
                    ans[0] = prevDepth;
                    ans[1] = prevMax - prevMin;
                }
                prevDepth = node.depth;
                prevMin = 1 << 20;
                prevMax = ~(1 << 20) + 1;
            }
            prevMin = Math.min(prevMin, node.index);
            prevMax = Math.max(prevMax, node.index);

            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);

            if (queue.isEmpty()) {
                if (prevMax - prevMin > ans[1]) {
                    ans[0] = prevDepth;
                    ans[1] = prevMax - prevMin;
                }
                break;

            }
        }

        System.out.printf("%d %d", ans[0], ans[1] + 1);


        br.close();
    }
}
