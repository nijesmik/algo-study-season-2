package y24Mar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Baek17140 {
    static int[][] map = new int[101][101];
    static int r, c, k;
    static int rowSize = 3, colSize = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(simulate());
    }

    static int simulate() {
        for (int time = 0; time <= 100; time++) {
            if (r < rowSize && c < colSize && map[r][c] == k) return time;
            if (rowSize >= colSize) rOperation();
            else cOperation();
        }
        return -1;
    }

    static void rOperation() {
        int newRowSize = 0;
        for (int i = 0; i < rowSize; i++) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int j = 0; j < colSize; j++) {
                int num = map[i][j];
                if (num == 0) continue;
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }
            List<int[]> nums = new ArrayList<>();
            for (var entry : countMap.entrySet()) {
                nums.add(new int[]{entry.getKey(), entry.getValue()});
            }
            nums.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
            int idx = 0;
            for (int[] num : nums) {
                if (idx == 100) break; // 최대 크기 제한
                map[i][idx++] = num[0];
                map[i][idx++] = num[1];
            }
            newRowSize = Math.max(newRowSize, idx);
            for (; idx < colSize; idx++) {
                map[i][idx] = 0;
            }
        }
        colSize = newRowSize;
    }

    static void cOperation() {
        int newColSize = 0;
        for (int j = 0; j < colSize; j++) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int i = 0; i < rowSize; i++) {
                int num = map[i][j];
                if (num == 0) continue;
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }
            List<int[]> nums = new ArrayList<>();
            for (var entry : countMap.entrySet()) {
                nums.add(new int[]{entry.getKey(), entry.getValue()});
            }
            nums.sort((a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
            int idx = 0;
            for (int[] num : nums) {
                if (idx == 100) break; // 최대 크기 제한
                map[idx++][j] = num[0];
                map[idx++][j] = num[1];
            }
            newColSize = Math.max(newColSize, idx);
            for (; idx < rowSize; idx++) {
                map[idx][j] = 0;
            }
        }
        rowSize = newColSize;
    }
}
