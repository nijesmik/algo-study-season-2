public class 프로그래머스_2차원동전뒤집기 {
    public int solution(int[][] beginning, int[][] target) {
        int height = beginning.length;
        int width = beginning[0].length;
        int[][] map = new int[height][width];
        int ans = Integer.MAX_VALUE;

        // 0 ~ 9 비트가 row 10 ~ 19 col
        for (int i = 0; i < (1 << height + width); i++) {
            initMap(map, beginning);
            int cnt = 0;
            for (int j = 0; j < height; j++) {
                if ((i & (1 << j)) != 0) {
                    cnt++;
                    flip(map, j, 0);
                }
            }

            for (int j = height; j < height + width; j++) {
                if ((i & (1 << j)) != 0) {
                    cnt++;
                    flip(map, j - height, 1);
                }
            }
            if (isGoal(map, target)) {
                ans = Math.min(ans, cnt);
            }
        }
        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        return ans;
    }

    static void flip(int[][] map, int index, int order) {
        // 0 row 1 col
        if (order == 0) {
            for (int i = 0; i < map[0].length; i++) {
                map[index][i] ^= 1;
            }
        } else {
            for (int i = 0; i < map.length; i++) {
                map[i][index] ^= 1;
            }
        }
    }

    static boolean isGoal(int[][] map, int[][] target) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;

    }

    static void initMap(int[][] map, int[][] orgMap) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = orgMap[i][j];
            }
        }
    }
}
