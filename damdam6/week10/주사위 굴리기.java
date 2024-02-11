package y24Feb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek14499 {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] yArr = new int[]{5, 2, 0, 3};
        int[] xArr = new int[]{5, 4, 0, 1};

        int[] dice = new int[6];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < K; i++) {
            System.out.println(Arrays.toString(dice));
            int dir = Integer.parseInt(st.nextToken());
            switch (dir) {
                case 1:
                    if (y + 1 < M) {
                        y++;
                    } else {
                        continue;
                    }
                    break;
                case 2:
                    if (y - 1 >= 0) {
                        y--;
                    } else {
                        continue;
                    }
                    break;
                case 3:
                    if (x - 1 >= 0) {
                        x--;
                    } else {
                        continue;
                    }
                    break;
                case 4:
                    if (x + 1 < N) {
                        x++;
                    } else {
                        continue;
                    }
                    break;
            }

            int xIdx = xArr[(x + 6) % 6];
            int yIdx = yArr[(y + 6) % 6];

            if (dir == 3 || dir == 4) {
                if (map[x][y] == 0) {
                    map[x][y] = dice[xIdx];
                } else {
                    dice[xIdx] = map[x][y];
                    map[x][y] = 0;
                }
                System.out.println(dice[5 - yIdx]);
            } else if (dir == 1 || dir == 2) {
                if (map[x][y] == 0) {
                    map[x][y] = dice[yIdx];
                } else {
                    dice[yIdx] = map[x][y];
                    map[x][y] = 0;
                }
                System.out.println(dice[5 - xIdx]);
            }

            for (int j = 0; j < N; j++) {
                System.out.println(Arrays.toString(map[j]));
            }

        }
    }
}
