package jechul;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 최단경로 {
    static int inf = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int start = scanner.nextInt();

        ArrayList<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] distance = new int[n + 1];
        Arrays.fill(distance, inf);

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            graph[a].add(new int[]{b, c});
        }

        dijkstra(start, graph, distance);

        for (int i = 1; i <= n; i++) {
            if (distance[i] == inf) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }

    static void dijkstra(int start, ArrayList<int[]>[] graph, int[] distance) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{start, 0});
        distance[start] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int now = current[0];
            int dist = current[1];

            if (distance[now] < dist) {
                continue;
            }

            for (int[] next : graph[now]) {
                int cost = dist + next[1];

                if (cost < distance[next[0]]) {
                    distance[next[0]] = cost;
                    pq.add(new int[]{next[0], cost});
                }
            }
        }
    }
}

