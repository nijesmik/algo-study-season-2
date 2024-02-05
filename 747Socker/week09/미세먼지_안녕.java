package jechul;
import java.util.Scanner;

public class 미세먼지_안녕 {

    static int R, C, T;
    static int[][] map;
    static int[] cleaner = new int[2];
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    static void spread() {
        int[][] spreaded = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    int dust = map[i][j] / 5;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != -1) {
                            spreaded[nx][ny] += dust;
                            spreaded[i][j] -= dust;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += spreaded[i][j];
            }
        }
    }

    static void clean() {
        for (int i = cleaner[0] - 1; i > 0; i--)
            map[i][0] = map[i - 1][0];
        for (int i = 0; i < C - 1; i++)
            map[0][i] = map[0][i + 1];
        for (int i = 0; i < cleaner[0]; i++)
            map[i][C - 1] = map[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--)
            map[cleaner[0]][i] = map[cleaner[0]][i - 1];
        map[cleaner[0]][1] = 0;

        for (int i = cleaner[1] + 1; i < R - 1; i++)
            map[i][0] = map[i + 1][0];
        for (int i = 0; i < C - 1; i++)
            map[R - 1][i] = map[R - 1][i + 1];
        for (int i = R - 1; i > cleaner[1]; i--)
            map[i][C - 1] = map[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--)
            map[cleaner[1]][i] = map[cleaner[1]][i - 1];
        map[cleaner[1]][1] = 0;

        map[cleaner[0]][0] = -1;
        map[cleaner[1]][0] = -1;
    }

    static int calc() {
        int res = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    res += map[i][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        T = sc.nextInt();

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == -1) {
                    cleaner[cleaner[0] == 0 ? 0 : 1] = i;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            spread();
            clean();
        }

        System.out.println(calc());
    }
}
