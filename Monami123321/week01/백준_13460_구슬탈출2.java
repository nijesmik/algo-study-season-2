import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_13460_구슬탈출2 {
    static class Ball {
        int r, c;

        public Ball() {
        }

        public Ball(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


    static int n, m, ans;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static char[][] map;
    static Ball red, blue;


    // dir 0 1 2 3 상 하 좌 우
    static int move(int dir, int r, int c) {
        int cnt = 0;

        while (true) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (map[nr][nc] == 'O') {
                return -1; // 구멍에 빠졌으면 -1 리턴
            } else if (map[nr][nc] == '#') {
                break;

            } else {
                cnt++;
                r = nr;
                c = nc;

            }

        }
        // 현 위치에서 벽 앞까지가 몇 칸인지 리턴함 (dir 기준)
        return cnt;

    }

    static void dfs(int depth, boolean[][][][] visited, int redR, int redC, int blueR, int blueC) {
//        System.out.printf("%d %d 빨간공",redR,redC);
        visited[redR][redC][blueR][blueC] = true; // 4차원 배열로 현재 좌표 다 정리

        if (depth == 10) {
            visited[redR][redC][blueR][blueC] = false;
            return;
        }

        // 빨간공 파란공 4방으로 움직이기
        for (int i = 0; i < 4; i++) {
            int redDist = move(i, redR, redC);
            int blueDist = move(i, blueR, blueC);

            int nextRedR = redR + dr[i] * redDist;
            int nextRedC = redC + dc[i] * redDist;

            int nextBlueR = blueR + dr[i] * blueDist;
            int nextBlueC = blueC + dc[i] * blueDist;

            // 골인?
            if (redDist == -1) {
                if (blueDist == -1) {
                    continue;
                } else {
                    ans = Math.min(ans, depth + 1);
                    continue;
                }
            }
            // 파랑이가 빠짐
            if (blueDist == -1) {
                continue;
            }


            // 위치조정 -> 같은 위치면 먼저 떨어진 쪽이 우선

            if (nextRedR == nextBlueR && nextRedC == nextBlueC) {
                // 빨간 공이 더 늦음 -> 한칸 덜 내려와야 함
                if (redDist > blueDist) {
                    nextRedR -= dr[i];
                    nextRedC -= dc[i];
                } else {
                    nextBlueR -= dr[i];
                    nextBlueC -= dc[i];
                }
            }

            // 다음 위치가 갔던 적 있는 경우?
            if (visited[nextRedR][nextRedC][nextBlueR][nextBlueC]) {
                continue;
            }
            dfs(depth + 1, visited, nextRedR, nextRedC, nextBlueR, nextBlueC);
        }
        visited[redR][redC][blueR][blueC] = false;

    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n 세로  m 가로
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        red = new Ball(); // 빨간 공
        blue = new Ball(); // 파란 공
        ans = Integer.MAX_VALUE; // 최종 답

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'R') {
                    red.r = i;
                    red.c = j;
                } else if (map[i][j] == 'B') {
                    blue.r = i;
                    blue.c = j;
                }
            }
        } // 맵 입력 끝

//        System.out.println(red.r + "redR");
//        System.out.println(red.c + "redC");
//        System.out.println(blue.r + "blueR");
//        System.out.println(blue.c + "blueC");

        dfs(0, new boolean[n][m][n][m], red.r, red.c, blue.r, blue.c);

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }


    }
}
