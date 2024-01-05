package test;
import java.util.Scanner;

/**
 * 원판_돌리기
 */
public class 원판_돌리기 {
    static int circles[][], circleCount, numberCount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        circleCount = sc.nextInt();
        numberCount = sc.nextInt();
        int rotationCount = sc.nextInt();
        circles = new int[circleCount+1][numberCount];
        for (int i = 1; i <= circleCount; i++) {
            for (int j = 0; j < numberCount; j++) {
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
            while (idx <= circleCount) {
                rotate(rotation[1], rotation[2], int idx);
                delete(rotation[2]);
                idx += rotation[0];
            }
        }
    }

    static void delete(int number) {
        for (int i = 0; i < circleCount; i++) {
            if ()
        }
    }

    static void rotate(int direction, int delta, int idx) {
        if (direction == 1) {
            for (int i = 0; i < numberCount; i++) {
                int[] rotated = new int[numberCount];
                rotated[(i + delta) % numberCount] = circles[idx][i];
                circles[idx] = rotated;
            }
        } else {
            int i = numberCount;
            while (i-- > 0) {
                circles[idx][i+1] = circles[idx][i];
            }
            circles[idx][0] = circles[idx][numberCount];
        }
    }
}