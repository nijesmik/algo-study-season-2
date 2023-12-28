package nijesmik.week04.당구_연습;

class Solution {
    int[] symmetryX, symmetryY;
    int startX, startY;
    public int[] solution(int m, int n, int x, int y, int[][] balls) {
        startX = x; startY = y;
        symmetryX = new int[]{-x, 2*m-x, x, x};
        symmetryY = new int[]{y, y, -y, 2*n-y};
        int size = balls.length;
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = getMinDist(balls[i]);
        }
        return ans;
    }

    int getMinDist(int[] ball) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            if (isValid(i, ball[0], ball[1])) {
                min = Math.min(min, getDist(i, ball));
            }
        }
        return min;
    }

    int getDist(int i, int[] ball) {
        int x = symmetryX[i] - ball[0];
        int y = symmetryY[i] - ball[1];
        return x*x + y*y;
    }

    boolean isValid(int i, int x, int y) {
        if (i == 0 && y == startY && x < startX) return false;
        if (i == 1 && y == startY && x > startX) return false;
        if (i == 2 && x == startX && y < startY) return false;
        if (i == 3 && x == startX && y > startY) return false;
        return true;
    }
}
