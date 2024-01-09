package 징검다리_건너기;

class Solution {
    int N, prevMin, ans, stones[], jump;

    public int solution(int[] stones, int k) {
        this.stones = stones;
        jump = k;
        N = stones.length;
        prevMin = 0;
        ans = 0;
        while (move()) {
        }
        return ans;
    }

    boolean move() {
        int min = 200_000_000;
        int zeroCount = 0;
        for (int i = 0; i < N; i++) {
            stones[i] -= prevMin;
            if (stones[i] > 0) {
                min = Math.min(min, stones[i]);
                zeroCount = 0;
            } else {
                zeroCount++;
                if (zeroCount >= jump) {
                    return false;
                }
            }
        }
        ans += min;
        prevMin = min;
        return true;
    }
}
