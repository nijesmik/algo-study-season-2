public class 이차원_동전_뒤집기 {
    class Solution {
        int[][] map;
        int[][] targetMap;
        int N, M, count;
        public int solution(int[][] beginning, int[][] target) {
            int answer = 0;
            map = beginning;
            targetMap = target;
            N = map.length;
            M = map[0].length;
            count = 0;

            return answer;
        }
        public void flip(int idx, int dir) {
            if(dir==0) { //행
                for(int i = 0; i < M; i++) {
                    map[idx][i] = map[idx][i]==0? 1 : 0;
                }
            } else { //열
                for(int i = 0; i < N; i++) {
                    map[i][idx] = map[i][idx]==0? 1 : 0;
                }
            }

        }
    }
}
