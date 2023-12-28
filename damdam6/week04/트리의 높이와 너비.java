package y23Dec18Dec24;

import java.io.*;
import java.util.*;

public class Baek2250new {

    static class Node {
        int num;
        Node left, right;
        int level, position;

        public Node(int num) {
            this.num = num;
        }
    }

    static Node[] nodes;
    static int N, position = 0;
    static Map<Integer, Integer> levelMin = new HashMap<>();
    static Map<Integer, Integer> levelMax = new HashMap<>();

    public static void inorder(Node node, int level) {
        if (node == null) return;

        inorder(node.left, level + 1); // 왼쪽 자식 방문

        node.level = level;
        node.position = ++position;
        levelMin.put(level, Math.min(levelMin.getOrDefault(level, Integer.MAX_VALUE), node.position));
        levelMax.put(level, Math.max(levelMax.getOrDefault(level, Integer.MIN_VALUE), node.position));

        inorder(node.right, level + 1); // 오른쪽 자식 방문
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new Node(i);
        boolean[] isChild = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            if (left != -1) {
                nodes[num].left = nodes[left];
                isChild[left] = true;
            }
            if (right != -1) {
                nodes[num].right = nodes[right];
                isChild[right] = true;
            }
        }

        // 루트 노드 찾기
        int rootNum = 1;
        for (int i = 1; i <= N; i++) {
            if (!isChild[i]) {
                rootNum = i;
                break;
            }
        }

        inorder(nodes[rootNum], 1);

        int maxWidth = 0, level = 1;
        for (int l : levelMin.keySet()) {
            int width = levelMax.get(l) - levelMin.get(l) + 1;
            if (width > maxWidth) {
                maxWidth = width;
                level = l;
            }
        }

        System.out.println(level + " " + maxWidth);
    }
}
