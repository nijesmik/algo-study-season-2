public class 프로그래머스_징검다리건너기 {
    class Solution {
        public int solution(int[] stones, int k) {
            int max = stones[0];
            int min = stones[0];
            for(int i = 1; i<stones.length;i++) {
                max = Math.max(max,stones[i]);
                min = Math.min(min,stones[i]);
            }
            int start = min;
            int end = max;
            int ans = 0;
            outer:while(end>=start) {
                int mid = (start+end)/2;
                int cnt = 0;
                for(int i = 0; i< stones.length; i++) {
                    if(stones[i]-mid < 0) {
                        cnt++;
                    } else {
                        cnt = 0;
                    }
                    if(cnt > k-1) {
                        end = mid-1;
                        continue outer;
                    }
                }
                ans = mid;
                start = mid+1;
            }
            return ans;

        }
    }
}
