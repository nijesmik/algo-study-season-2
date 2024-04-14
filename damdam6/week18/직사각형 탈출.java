package y24Apr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Baek16973 {


    static int N, M;
    static int[][] bd;
    static int[][] vt;
    static int H, W;
    static int Sx, Sy, Fx, Fy;

    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bd = new int[N][M];
        vt = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                bd[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        st = new StringTokenizer(bf.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        Sx = Integer.parseInt(st.nextToken()) - 1;
        Sy = Integer.parseInt(st.nextToken()) - 1;
        Fx = Integer.parseInt(st.nextToken()) - 1;
        Fy = Integer.parseInt(st.nextToken()) - 1;

        System.out.println(bfs());

    }

    public static int bfs() {

        ArrayDeque<pos> qu = new ArrayDeque<>();
        qu.add(new pos(Sx, Sy, 0));
        vt[Sx][Sy] = 1;
        while (!qu.isEmpty()) {
            pos tmp = qu.poll();

            if (tmp.x == Fx && tmp.y == Fy) {
                return tmp.cnt;
            }

            for (int i = 0; i < 4; i++) {

                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if (nx == Fx && ny == Fy) {
                    return tmp.cnt+1;
                }

                if (!checkCango(nx, ny)) continue;
                if (vt[nx][ny] == 1) continue;
                qu.add(new pos(nx, ny, tmp.cnt + 1));
                vt[nx][ny] = 1;
            }


        }

        return -1;
    }

    static public boolean checkCango(int x, int y) {
        if (x >= N || y >= M || x < 0 || y < 0) return false;
        if (x + H > N || y + W > M || x < 0 || y < 0) return false;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (bd[x + i][y + j] == 1) return false;
            }
        }

        return true;
    }

    static int[] dx = new int[]{0, 0, -1, 1};
    static int[] dy = new int[]{-1, 1, 0, 0};

    static class pos {
        int x, y, cnt;

        pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
