import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_17144_미세먼지_안녕 {
    static int R, C, T;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        int AC = 0; // 공기청정기 아랫부분  r 좌표


        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    AC = i;
                }
            }
        }
        for (int i = 0; i < T; i++) {
            spread(new int[R][C]);
            circulate(AC);
        }
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ans += map[i][j];
            }
        } // 공기청정기까지 그냥 더함
        System.out.println(ans + 2);


        br.close();
    }

    static void spread(int[][] nextMap) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) {
                    nextMap[i][j] = -1;
                    continue;
                }
                int cnt = 0;
                int diff = map[i][j] / 5;
                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (nr < 0 || nr > R - 1 || nc < 0 || nc > C - 1 || map[nr][nc] == -1) {
                        continue;
                    }
                    cnt++;
                    nextMap[nr][nc] += diff;
                }
                nextMap[i][j] += map[i][j] - diff * cnt;
            }
        }
        map = nextMap;
    }

    static void circulate(int r) {
        // 아래쪽 순환
        for (int i = r + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];

        }
        for (int i = R - 1; i > r; i--) {
            map[i][C - 1] = map[i - 1][C - 1];

        }
        for (int i = C - 1; i > 1; i--) {
            map[r][i] = map[r][i - 1];
        }
        map[r][1] = 0;

        // 위쪽 순환
        r -= 1;
        for (int i = r - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < r; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[r][i] = map[r][i - 1];
        }
        map[r][1] = 0;
    }
}
