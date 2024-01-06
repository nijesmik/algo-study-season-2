import java.util.*;

public class 벽_부수고_이동하기_4 {
    static Count[][] counts;
    static char[][] map;
    static int rowSize, columnSize;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        rowSize = sc.nextInt();
        columnSize = sc.nextInt();
        map = new char[rowSize][];
        for (int i = 0; i < rowSize; i++) {
            map[i] = sc.next().toCharArray();
        }

        counts = new Count[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                if (counts[i][j] == null && map[i][j] == '0') {
                    bfs(i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < columnSize; c++) {
                int sum = 0;
                if (map[r][c] == '1') {
                    Set<Count> set = new HashSet<>();
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i], nc = c + dc[i];
                        if (nr >= 0 && nr < rowSize && nc >= 0 && nc < columnSize && counts[nr][nc] != null
                                && map[nr][nc] == '0') {
                            set.add(counts[nr][nc]);
                        }
                    }
                    sum++;
                    for (Count count : set) {
                        sum += count.cnt;
                    }
                    sum %= 10;
                }
                sb.append(sum);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };

    static void bfs(int r, int c) {
        Count count = new Count();
        counts[r][c] = count;
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i], nc = cur.c + dc[i];
                if (nr >= 0 && nr < rowSize && nc >= 0 && nc < columnSize && counts[nr][nc] == null
                        && map[nr][nc] == '0') {
                    counts[nr][nc] = count;
                    count.cnt++;
                    q.add(new Point(nr, nc));
                }
            }
        }
    }

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Count {
        int cnt = 1;
    }
}