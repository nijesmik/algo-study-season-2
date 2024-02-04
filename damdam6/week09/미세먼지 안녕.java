import java.io.*;
import java.util.*;

public class Main {
    static int[][] room;
    static int R, C, T;
    static int[] airCleaner;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new int[R][C];
        airCleaner = new int[2];

        int cleanerIndex = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1) {
                    airCleaner[cleanerIndex++] = i;
                }
            }
        }

        for (int t = 0; t < T; t++) {
            spreadDust();
            cleanAir();
        }

        System.out.println(sumDust());
    }

    static void spreadDust() {
        int[][] temp = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (room[r][c] > 0) {
                    int amount = room[r][c] / 5;
                    int spreadCount = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dx[d];
                        int nc = c + dy[d];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && room[nr][nc] != -1) {
                            temp[nr][nc] += amount;
                            spreadCount++;
                        }
                    }
                    room[r][c] -= amount * spreadCount;
                }
            }
        }

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                room[r][c] += temp[r][c];
            }
        }
    }

    static void cleanAir() {
        // 위쪽 공기청정기
        for (int i = airCleaner[0] - 1; i > 0; i--) room[i][0] = room[i - 1][0];
        for (int i = 0; i < C - 1; i++) room[0][i] = room[0][i + 1];
        for (int i = 0; i < airCleaner[0]; i++) room[i][C - 1] = room[i + 1][C - 1];
        for (int i = C - 1; i > 1; i--) room[airCleaner[0]][i] = room[airCleaner[0]][i - 1];
        room[airCleaner[0]][1] = 0;

        // 아래쪽 공기청정기
        for (int i = airCleaner[1] + 1; i < R - 1; i++) room[i][0] = room[i + 1][0];
        for (int i = 0; i < C - 1; i++) room[R - 1][i] = room[R - 1][i + 1];
        for (int i = R - 1; i > airCleaner[1]; i--) room[i][C - 1] = room[i - 1][C - 1];
        for (int i = C - 1; i > 1; i--) room[airCleaner[1]][i] = room[airCleaner[1]][i - 1];
        room[airCleaner[1]][1] = 0;
    }

    static int sumDust() {
        int sum = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (room[r][c] > 0) sum += room[r][c];
            }
        }
        return sum;
    }
}
