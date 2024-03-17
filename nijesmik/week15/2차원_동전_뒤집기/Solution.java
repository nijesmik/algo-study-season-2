import java.util.*;

class Solution {
    int sizeR, sizeC, origin[][], target[][];

    public int solution(int[][] beginning, int[][] target) {
        sizeR = beginning.length;
        sizeC = beginning[0].length;
        origin = copy(beginning);
        this.target = target;

        int result = flip(0);
        origin = beginning;
        flipColumn(0);
        return Math.max(result, flip(1));
    }

    private int[][] copy(int[][] arr) {
        int[][] copy = new int[sizeR][sizeC];
        for (int i = 0; i < sizeR; i++) {
            for (int j = 1; j < sizeC; j++) {
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }

    private int flip(int cnt) {
        for (int i = 0; i < sizeR; i++) {
            if (origin[i][0] != target[i][0]) {
                cnt++;
                flipRow(i);
            }
        }
        for (int i = 0; i < sizeC; i++) {
            if (origin[0][i] != target[0][i]) {
                cnt++;
                flipColumn(i);
            }
        }
        for (int i = 1; i < sizeR; i++) {
            for (int j = 1; j < sizeC; j++) {
                if (origin[i][j] != target[i][j]) {
                    return -1;
                }
            }
        }
        return cnt;
    }

    private void flipColumn(int colIdx) {
        for (int i = 0; i < sizeR; i++) {
            origin[i][colIdx] = (origin[i][colIdx] + 1) % 2;
        }
    }

    private void flipRow(int rowIdx) {
        for (int i = 0; i < sizeC; i++) {
            origin[rowIdx][i] = (origin[rowIdx][i] + 1) % 2;
        }
    }
}