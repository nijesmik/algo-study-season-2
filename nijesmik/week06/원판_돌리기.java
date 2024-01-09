import java.util.Scanner;

public class 원판_돌리기 {
    static int circles[][], C, N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt();
        N = sc.nextInt();
        int rotationCount = sc.nextInt();
        circles = new int[C + 1][N];
        for (int i = 1; i <= C; i++) {
            for (int j = 0; j < N; j++) {
                circles[i][j] = sc.nextInt();
            }
        }
        int[][] rotations = new int[rotationCount][3];
        for (int i = 0; i < rotationCount; i++) {
            for (int j = 0; j < 3; j++) {
                rotations[i][j] = sc.nextInt();
            }
        }
        for (int[] rotation : rotations) {
            int idx = rotation[0];
            while (idx <= C) {
                rotate(rotation[1], rotation[2], idx);
                idx += rotation[0];
            }
                // print();
                if (!delete(rotation[2])) {
                    transform();
                }
                // print();
        }
        int ans = 0;
        for (int i = 1; i <= C; i++) {
            for (int j = 0; j < N; j++) {
                ans += circles[i][j];
            }
        }
        System.out.println(ans);
    }

    static void transform() {
        double sum = 0;
        double count = 0;
        for (int i = 1; i <= C; i++) {
            for (int j = 0; j < N; j++) {
                if (circles[i][j] != 0) {
                    sum += circles[i][j];
                    count++;
                }
            }
        }
        double avg = sum / count;
        for (int i = 1; i <= C; i++) {
            for (int j = 0; j < N; j++) {
                if (circles[i][j] == 0) continue;
                if (circles[i][j] < avg) {
                    circles[i][j]++;
                } else if (circles[i][j] > avg) {
                    circles[i][j]--;
                }
            }
        }
    }

    static void print() {
        for (int i = 1; i <= C; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(circles[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------");
    }

    static boolean delete(int number) {
        boolean deleted = false;
        int[][] copy = new int[C + 1][N + 1];
        for (int i = 1; i <= C; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = circles[i][j];
                if (circles[i][j] != 0 && isNear(i, j)) {
                    copy[i][j] = 0;
                    deleted = true;
                }
            }
        }
        circles = copy;
        return deleted;
    }

    static boolean isNear(int i, int j) {
        if (circles[i][j] == circles[i][(j + 1) % N])
            return true;
        if (circles[i][(j - 1 + N) % N] == circles[i][j])
            return true;
        if (circles[i][j] == circles[i - 1][j])
            return true;
        if (circles[i][j] == circles[(i + 1) % (C+1)][j])
            return true;
        return false;
    }

    static void rotate(int direction, int delta, int idx) {
        int[] rotated = new int[N];
        if (direction == 1) {
            for (int i = 0; i < N; i++) {
                rotated[i] = circles[idx][(i + delta) % N];
            }
        } else {
            for (int j = 0; j < N; j++) {
                rotated[j] = circles[idx][(j + N - delta % N) % N];
            }
        }
        circles[idx] = rotated;
    }
}
