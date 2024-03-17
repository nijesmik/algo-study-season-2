public class 자물쇠와_열쇠 {
    class Solution {
        static int M, N, count;
        public boolean solution(int[][] key, int[][] lock) {
            M = key.length;
            N = lock.length;
            int[][] map = new int[2*(M-1)+N][2*(M-1)+N];
            for(int i = 0; i< N; i++) {
                for(int j = 0; j < N; j++) {
                    if(lock[i][j] == 0) count++;
                    map[M+i-1][M+j-1] = lock[i][j];
                }
            }
            //lock의 범위 = (M-1,M-1) ~ (M+N-2, M+N-2)
            for(int r = 0; r < M+N-1; r++) {
                for(int c = 0; c < M+N-1; c++) {
                    rot : for(int k = 0; k < 4; k++) {
                        key = rotate(key);
                        int tmp_cnt = 0;
                        for(int i = 0; i< M; i++) {
                            for(int j = 0; j < M; j++) {
                                if(!(r+i>=M-1&&r+i<M+N-1&&c+j>=M-1&&c+j<M+N-1)) continue;
                                if(map[r+i][c+j]==1&&key[i][j]==1) continue rot;
                                if(map[r+i][c+j]==0&&key[i][j]==1) tmp_cnt++;
                            }
                        }
                        // System.out.println(k + "번쨰 " + tmp_cnt);
                        if(tmp_cnt==count) return true;
                    }
                }
            }

            return false;
        }

        static int[][] rotate(int[][] key) {
            int[][] new_key = new int[M][M];
            for(int i = 0; i < M; i++) {
                for(int j = 0; j< M; j++) {
                    new_key[j][M-1-i] = key[i][j];
                }
            }

            return new_key;
        }
    }
}
