import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_2146_다리만들기 {
    static class Node {
        int r, c, id;

        public Node(int r, int c, int id) {
            this.r = r;
            this.c = c;
            this.id = id;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int groupId = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        List<Node> nodes = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || map[i][j] == 0) {
                    continue;
                }
                queue.add(new int[]{i, j, ++groupId});
                visited[i][j] = true;
                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    int r = now[0];
                    int c = now[1];
                    int id = now[2];
                    nodes.add(new Node(r, c, id));
                    for (int k = 0; k < 4; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        if (nr < 0 || nr > n - 1 || nc < 0 || nc > n - 1 || visited[nr][nc] || map[nr][nc] == 0) {
                            continue;
                        }
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc, groupId});
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (Node a : nodes) {
            for (Node b : nodes) {
                if (a.id == b.id) {
                    continue;
                }
                ans = Math.min(ans, getDist(a, b));
            }
        }
        System.out.println(ans);
        br.close();
    }

    static int getDist(Node a, Node b) {
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c) - 1;
    }
}
