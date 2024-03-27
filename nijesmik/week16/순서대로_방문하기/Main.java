package nijesmik.week16.순서대로_방문하기;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Node[] nodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int nodeSize = sc.nextInt();
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        nodes = new Node[nodeSize];
        for (int i = 0; i < nodeSize; i++) {
            nodes[i] = new Node(sc.nextInt() - 1, sc.nextInt() - 1);
        }

        int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };
        Queue<Record> q = new LinkedList<>();
        q.add(new Record(nodes[0].r, nodes[0].c));
        int ans = 0;
        while (!q.isEmpty()) {
            Record cur = q.poll();
            if (cur.idx == nodeSize) {
                ans++;
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i], nc = cur.c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 1 || cur.visited[nr][nc])
                    continue;
                q.add(new Record(nr, nc, cur));
            }
        }
        System.out.println(ans);
    }

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Record extends Node {
        boolean[][] visited;
        int idx;

        Record(int r, int c) {
            super(r, c);
            idx = 1;
            visited = new boolean[N][N];
            visited[r][c] = true;
        }

        Record(int r, int c, Record record) {
            super(r, c);
            idx = record.idx;
            if (r == nodes[idx].r && c == nodes[idx].c) {
                idx++;
            }
            visited = new boolean[N][];
            for (int i = 0; i < N; i++) {
                visited[i] = Arrays.copyOf(record.visited[i], N);
            }
            visited[r][c] = true;
        }
    }
}
