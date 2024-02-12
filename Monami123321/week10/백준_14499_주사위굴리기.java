import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14499_주사위굴리기 {
    static class Dice {
        int r, c, pointer;
        int[] arr; // 바닥 위 동서북남

        public Dice(int r, int c) {
            this.r = r;
            this.c = c;
            this.arr = new int[6];
        }
    }

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Dice dice = new Dice(x, y);

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int cmd = Integer.parseInt(st.nextToken()) - 1;
            int nr = dice.r + dr[cmd];
            int nc = dice.c + dc[cmd];
            if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1) {
                continue;
            }
            // 주사위는 바닥 위 동서북남
            // 1 2 3 4 동 서 북 남 -1
            roll(cmd, dice, nr, nc);
            dice.r = nr;
            dice.c = nc;

            sb.append(dice.arr[1]).append("\n");
        }
        System.out.print(sb);


        br.close();
    }

    static void roll(int cmd, Dice dice, int nr, int nc) {
        int[] next = new int[6];
        int[] prev = dice.arr;
        if (map[nr][nc] == 0) {
            map[nr][nc] = prev[2 + cmd];
            next[0] = map[nr][nc];
        } else {
            next[0] = map[nr][nc];
            map[nr][nc] = 0;
        }
        // 바닥 위 동 서 북 남
        // cmd 0 1 2 3 -> 동 서 북 남
        if (cmd == 0) {
            next[1] = prev[3];
            next[2] = prev[1];
            next[3] = prev[0];
            next[4] = prev[4];
            next[5] = prev[5];
        } else if (cmd == 1) {
            next[1] = prev[2];
            next[2] = prev[0];
            next[3] = prev[1];
            next[4] = prev[4];
            next[5] = prev[5];
        } else if (cmd == 2) {
            next[1] = prev[5];
            next[2] = prev[2];
            next[3] = prev[3];
            next[4] = prev[1];
            next[5] = prev[0];
        } else {
            next[1] = prev[4];
            next[2] = prev[2];
            next[3] = prev[3];
            next[4] = prev[0];
            next[5] = prev[1];
        }
        dice.arr = next;
    }
}
