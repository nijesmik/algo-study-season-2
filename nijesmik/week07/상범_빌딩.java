import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 상범_빌딩 {
    static Position start, end;
    static int height, row, column;
    static char[][][] building;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            height = sc.nextInt();
            row = sc.nextInt();
            column = sc.nextInt();
            if (height * row * column <= 0)
                return;
            building = new char[height][row][];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < row; j++) {
                    building[i][j] = sc.next().toCharArray();
                    for (int k = 0; k < column; k++) {
                        if (building[i][j][k] == 'S') {
                            start = new Position(i, j, k, 0);
                        } else if (building[i][j][k] == 'E') {
                            end = new Position(i, j, k, 0);
                        }
                    }
                }
            }
            bfs();
        }
    }

    static int[] dr = { 1, -1, 0, 0, 0, 0 }, dc = { 0, 0, 1, -1, 0, 0 }, dh = { 0, 0, 0, 0, 1, -1 };

    static void bfs() {
        Queue<Position> q = new LinkedList<>();
        q.add(start);
        building[start.h][start.r][start.c] = 'x';
        while (!q.isEmpty()) {
            Position cur = q.poll();
            for (int i = 0; i < 6; i++) {
                int nr = cur.r + dr[i], nc = cur.c + dc[i], nh = cur.h + dh[i];
                if (nr == end.r && nc == end.c && nh == end.h) {
                    System.out.printf("Escaped in %d minute(s).\n", cur.time + 1);
                    return;
                }
                if (nr >= 0 && nr < row && nc >= 0 && nc < column && nh >= 0 && nh < height
                        && building[nh][nr][nc] == '.') {
                    q.add(new Position(nh, nr, nc, cur.time + 1));
                    building[nh][nr][nc] = 'x';
                }
            }
        }
        System.out.println("Trapped!");
    }

    static class Position {
        int h, r, c, time;

        Position(int h, int r, int c, int time) {
            this.h = h;
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}
