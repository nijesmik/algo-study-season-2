package week10;

import java.util.*;

public class 주사위_굴리기 {
    static int[] dr = { 0, 0, -1, 1 }, dc = { 1, -1, 0, 0 },
            topRightBottomLeft = new int[4], topBackBottomFront = new int[4];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sizeR = sc.nextInt(), sizeC = sc.nextInt();
        int r = sc.nextInt(), c = sc.nextInt();
        int sizeCmd = sc.nextInt();
        int[][] map = new int[sizeR][sizeC];
        for (int i = 0; i < sizeR; i++) {
            for (int j = 0; j < sizeC; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < sizeCmd; i++) {
            int cmd = sc.nextInt() - 1;
            r += dr[cmd];
            c += dc[cmd];
            if (r < 0 || r >= sizeR || c < 0 || c >= sizeC) {
                r -= dr[cmd];
                c -= dc[cmd];
                continue;
            }

            if (cmd < 2) {
                rollDice(cmd, topRightBottomLeft, topBackBottomFront);
            } else {
                rollDice(cmd, topBackBottomFront, topRightBottomLeft);
            }

            if (map[r][c] == 0) {
                map[r][c] = topBackBottomFront[2];
            } else {
                topBackBottomFront[2] = map[r][c];
                topRightBottomLeft[2] = map[r][c];
                map[r][c] = 0;
            }
            System.out.println(topBackBottomFront[0]);
        }
    }

    static void rollDice(int cmd, int[] dice, int[] dice2) {
        if (cmd % 2 == 0) {
            int left = dice[3];
            int i = 4;
            while (i-- > 1) {
                dice[i] = dice[i - 1];
            }
            dice[0] = left;
        } else {
            int top = dice[0];
            int i = 0;
            while (i++ < 3) {
                dice[i - 1] = dice[i];
            }
            dice[3] = top;
        }
        dice2[0] = dice[0];
        dice2[2] = dice[2];
    }
}