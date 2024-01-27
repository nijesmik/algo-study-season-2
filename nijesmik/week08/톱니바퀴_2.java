import java.util.Scanner;

public class 톱니바퀴_2 {
    static int N, gears[][], dir[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        gears = new int[N][8];
        for (int i = 0; i < N; i++) {
            String row = sc.next();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = row.charAt(j) - '0';
            }
        }
        int K = sc.nextInt();
        while (K-- > 0) {
            int idx = sc.nextInt() - 1;
            dir = new int[N];
            dir[idx] = sc.nextInt();
            testLeft(idx);
            testRight(idx);
            rotate();
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (gears[i][0] == 1) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    static void rotate() {
        for (int i = 0; i < N; i++) {
            if (dir[i] == 1) {
                clockwise(gears[i]);
            } else if (dir[i] == -1) {
                counterclockwise(gears[i]);
            }
        }
    }

    static void clockwise(int[] gear) {
        int last = gear[7];
        int idx = 8;
        while (idx-- > 1) {
            gear[idx] = gear[idx - 1];
        }
        gear[0] = last;
    }

    static void counterclockwise(int[] gear) {
        int first = gear[0];
        for (int i = 0; i < 7; i++) {
            gear[i] = gear[i + 1];
        }
        gear[7] = first;
    }

    static void testLeft(int idx) {
        if (idx == 0) {
            return;
        }
        if (gears[idx - 1][2] != gears[idx][6]) {
            dir[idx - 1] = -dir[idx];
            testLeft(idx - 1);
        }
    }

    static void testRight(int idx) {
        if (idx == N - 1) {
            return;
        }
        if (gears[idx][2] != gears[idx + 1][6]) {
            dir[idx + 1] = -dir[idx];
            testRight(idx + 1);
        }
    }
}