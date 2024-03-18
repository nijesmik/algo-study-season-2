import java.util.*;

class Solution {
    int sizeR, sizeC, origin[][], target[][], ans;

    public int solution(int[][] beginning, int[][] target) {
        sizeR = beginning.length;
        sizeC = beginning[0].length;
        origin = beginning;
        this.target = target;
        ans = -1;

        dfs(0, 0);
        return ans;
    }

    private void dfs(int depth, int cnt) {
        if (depth == sizeR + sizeC) {
            test(cnt);
            return;
        }

        if (depth < sizeR) {
            dfs(depth + 1, cnt);
            flipRow(depth);
            dfs(depth + 1, cnt + 1);
            flipRow(depth);
        } else {
            dfs(depth + 1, cnt);
            flipColumn(depth - sizeR);
            dfs(depth + 1, cnt + 1);
            flipColumn(depth - sizeR);
        }
    }

    private void test(int cnt) {
        for (int i = 0; i < sizeR; i++) {
            for (int j = 0; j < sizeC; j++) {
                if (origin[i][j] != target[i][j]) {
                    return;
                }
            }
        }
        if (ans == -1) {
            ans = cnt;
        }
        ans = Math.min(ans, cnt);
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