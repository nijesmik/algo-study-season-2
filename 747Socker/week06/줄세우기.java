package w06_1_02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 줄세우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        int[] indegree = new int[v + 1];
        ArrayList<Integer>[] graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            indegree[b]++;
        }

        topologySort(v, indegree, graph);
    }

    private static void topologySort(int v, int[] indegree, ArrayList<Integer>[] graph) {
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= v; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();
            result.add(now);

            for (int next : graph[now]) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}

