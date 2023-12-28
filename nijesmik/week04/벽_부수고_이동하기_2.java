import java.util.PriorityQueue;
import java.util.Scanner;

public class 벽_부수고_이동하기_2 {
    static int R, C, max;
    static boolean[][][] visited;
    static char[][] map;
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        R = sc.nextInt(); C = sc.nextInt(); max = sc.nextInt();
        visited = new boolean[max+1][R][C];
        map = new char[R][];
        for (int i = 0; i < R; i++) {
            map[i] = sc.next().toCharArray();
        }
        System.out.println(bfs());
    }

    static int bfs() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(0, 0, 0, 1));
        visited[0][0][0] = true;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.r == R-1 && cur.c == C-1) return cur.dist;
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i], nc = cur.c + dc[i];
                if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[cur.cnt][nr][nc]) continue;
                if (cur.cnt > 0 && visited[cur.cnt-1][nr][nc]) continue;
                if (map[nr][nc] == '0') {
                    visited[cur.cnt][nr][nc] = true;
                    q.add(new Node(nr, nc, cur.cnt, cur.dist+1));
                } else if (cur.cnt < max) {
                    visited[cur.cnt+1][nr][nc] = true;
                    q.add(new Node(nr, nc, cur.cnt+1, cur.dist+1));
                }
            }

        }
        return -1;
    }

    static class Node implements Comparable<Node>{
        int r, c, cnt, dist;
        Node (int r, int c, int cnt, int dist) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node o) {
            return (dist - o.dist);
        }
    }
}
