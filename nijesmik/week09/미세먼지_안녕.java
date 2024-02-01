package nijesmik.week09;
import java.util.Scanner;

public class 미세먼지_안녕 {
    static int R, C, purifier, total, map[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        int T = sc.nextInt();
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = sc.nextInt();
                total += map[i][j];
                if (map[i][j] == -1) {
                    purifier = i;
                    total++;
                }
            }
        }
        while (T-- > 0) {
            map = spread(map);
            purify();
            purifyDown();
        }
        System.out.println(total);
    }

    static void purify() {
        int r = purifier - 1, c = 0, dir = 0;
        while (dir < 4) {
            int nr = r + dr[dir], nc = c + dc[dir];
            if (nr < 0 || nc < 0 || nr > purifier - 1 || nc >= C) {
                dir++;
            } else {
                map[r][c] = map[nr][nc];
                r = nr;
                c = nc;
            }
        }
        total -= map[purifier - 1][0];
        map[purifier - 1][0] = -1;
        map[purifier - 1][1] = 0;
    }

    static void purifyDown() {
        int r = purifier, c = 0, dir = 0;
        while (dir < 4) {
            int nr = r + dr[dir] * -1, nc = c + dc[dir];
            if (nr < purifier || nc < 0 || nr >= R || nc >= C) {
                dir++;
            } else {
                map[r][c] = map[nr][nc];
                r = nr;
                c = nc;
            }
        }
        total -= map[purifier][0];
        map[purifier][0] = -1;
        map[purifier][1] = 0;
    }

    static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 };

    static int[][] spread(int[][] before) {
        int[][] after = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (before[r][c] > 0) {
                    int cnt = 0;
                    int amount = before[r][c] / 5;
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i], nc = c + dc[i];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && before[nr][nc] > -1) {
                            cnt++;
                            after[nr][nc] += amount;
                        }
                    }
                    after[r][c] += before[r][c] - amount * cnt;
                }
            }
        }
        after[purifier][0] = after[purifier - 1][0] = -1;
        return after;
    }
}
