package inf;

import java.util.Arrays;
import java.util.Scanner;

import java.util.Scanner;

public class 톱니바퀴2 {
    static int[][] inpArr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        inpArr = new int[T][8];

        for (int i = 0; i < T; i++) {
            String tmp = sc.next();
            for (int j = 0; j < 8; j++) {
                inpArr[i][j] = tmp.charAt(j) - '0';
            }
        }

        int K = sc.nextInt();
        for (int i = 0; i < K; i++) {
            int N = sc.nextInt() - 1;
            int D = sc.nextInt();
            op(N, D, T);
        }

        int ans = 0;
        for (int i = 0; i < T; i++) {
            if (inpArr[i][0] == 1) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static void rotate(int N, int D) {
        if (D == 1) {
            int temp = inpArr[N][7];
            for (int i = 7; i > 0; i--) {
                inpArr[N][i] = inpArr[N][i - 1];
            }
            inpArr[N][0] = temp;
        } else {
            int temp = inpArr[N][0];
            for (int i = 0; i < 7; i++) {
                inpArr[N][i] = inpArr[N][i + 1];
            }
            inpArr[N][7] = temp;
        }
    }

    static void left(int N, int D, int T) {
        if (N < 0) return;
        if (N > 0 && inpArr[N][6] != inpArr[N - 1][2]) {
            left(N - 1, -D, T);
            rotate(N - 1, D);
        }
    }

    static void right(int N, int D, int T) {
        if (N >= T) return;
        if (N < T - 1 && inpArr[N][2] != inpArr[N + 1][6]) {
            right(N + 1, -D, T);
            rotate(N + 1, D);
        }
    }

    static void op(int N, int D, int T) {
        boolean[] rotated = new boolean[T];
        rotateGears(N, D, T, rotated);
    }

    static void rotateGears(int N, int D, int T, boolean[] rotated) {
        rotated[N] = true;
        int leftN = N - 1;
        int rightN = N + 1;
        int leftD = D == 1 ? -1 : 1;
        int rightD = leftD;

        if (leftN >= 0 && !rotated[leftN] && inpArr[N][6] != inpArr[leftN][2]) {
            rotateGears(leftN, leftD, T, rotated);
        }

        if (rightN < T && !rotated[rightN] && inpArr[N][2] != inpArr[rightN][6]) {
            rotateGears(rightN, rightD, T, rotated);
        }

        rotate(N, D);
    }
}

