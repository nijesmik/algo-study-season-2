class Solution {
    static int[][] map; 
    static int[][] tar; 
    static int min;
    static int n; 
    static int m; 

    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length; // x
        m = beginning[0].length; // y
        map = beginning;
        tar = target;
        min = Integer.MAX_VALUE;
        dfs(0, 0);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    static void dfs(int x, int count) {
        if (x == n) { 
            int result = count;
            for (int y = 0; y < m; y++) { 
                if (diffYCount(y) == n) result++; 
                else if (diffYCount(y) != 0) return; 
            }
            min = Math.min(min, result);
            return;
        }

        turnX(x); 
        dfs(x + 1, count + 1);
        turnX(x); 
        dfs(x + 1, count); 
    }


    static void turnX(int x) {
        for (int y = 0; y < m; y++) {
            map[x][y] = turn(map[x][y]);
        }
    }


    static int turn(int coin) {
        return Math.abs(coin-1);
    }

    static int diffYCount(int y) {
        int count = 0;
        for (int x = 0; x < n; x++) {
            if (map[x][y] != tar[x][y]) count++;
        }
        return count;
    }
}