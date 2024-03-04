package jechul;

import java.util.*;

class 자물쇠와열쇠 {

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static private boolean check(int[][] key, int[][] lock, int startX, int startY, int M, int N) {
        int[][] lockCopy = new int[M][M];

        for (int i = 0; i < M; i++) {
            System.arraycopy(lock[i], 0, lockCopy[i], 0, M);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int lockX = i + startX;
                int lockY = j + startY;
                if (lockX >= 0 && lockX < M && lockY >= 0 && lockY < M) {
                    lockCopy[lockX][lockY] += key[i][j];
                }
            }
        }

        for (int[] row : lockCopy) {
            for (int r : row) {
                if (r != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static private int[][] rotateKey(int[][] key) {
        int N = key.length;
        int[][] rotated = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotated[j][N - 1 - i] = key[i][j];
            }
        }
        return rotated;
    }

    static int N;
    public boolean solution(int[][] key, int[][] lock) {
        N = key.length;
        int M = lock.length;

        for (int rotation = 0; rotation < 4; rotation++) {
            if (rotation > 0) {
                key = rotateKey(key);
            }

            for (int x = -N + 1; x < M; x++) {
                for (int y = -N + 1; y < M; y++) {
                    if (check(key, lock, x, y, M, N)) {
                        return true;
                    }
                }
            }
        }
        return false; 
    }
}
