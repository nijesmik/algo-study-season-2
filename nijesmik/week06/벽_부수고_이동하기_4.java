import java.util.*;

public class 벽_부수고_이동하기_4 {
    static boolean[][] visited;
    static int rowSize, columnSize, map[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        rowSize = sc.nextInt();
        columnSize = sc.nextInt();
        map = new int[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            String row = sc.next();
            for (int j = 0; j < columnSize; j++) {
                map[i][j] = row.charAt(j) - '0';
            }
        }

        visited = new boolean[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < columnSize; c++) {
                sb.append(map[r][c] % 10);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };

    static void bfs(int r, int c) {
        Set<Integer> set = new HashSet<>();
        int count = 1;
        visited[r][c] = true;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i], nc = cur.c + dc[i];
                if (nr < 0 || nr >= rowSize || nc < 0 || nc >= columnSize || visited[nr][nc])
                    continue;
                if (map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    count++;
                    q.add(new Point(nr, nc));
                } else {
                    set.add(nr * 10000 + nc);
                }
            }
        }
        for (int pos : set) {
            map[pos / 10000][pos % 10000] += count;
        }
    }

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}