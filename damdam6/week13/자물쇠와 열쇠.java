import java.util.*;


class Solution {
    static int N;
static int M;
static int[][] expandLock;
static int cnt;
    public boolean solution(int[][] key, int[][] lock) {
        // key사이즈에서 하나 뺀 만큼 lock 주변에 붙임
        // key를 돌려서 들어갈 수 있는 곳이 있는지 확인
        // 들어갔을때, lock의 빈곳이 다 가득차는지 확인
        N = lock[0].length;
        M = key[0].length;
        int expand = N + (M-1)*2;
        expandLock = new int[expand][expand];
        
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                expandLock[M-1+i][M-1+j] = lock[i][j];
                if(lock[i][j] == 0)cnt++;
            }
        }
       // System.out.println("cnt "+cnt);
        
    // for(int i=0;i<expand;i++){
    // System.out.println(Arrays.toString(expandLock[i]));
    // }
        
        boolean answer = false;
        
for (int dir = 0; dir < 4; dir++) {
    int[][] turnedkey = new int[M][M];

    for (int i = 0; i < M; i++) {
        for (int j = 0; j < M; j++) {
            switch (dir) {
                case 0: 
                    turnedkey[i][j] = key[i][j];
                    break;
                case 1:
                    turnedkey[j][M - 1 - i] = key[i][j];
                    break;
                case 2: 
                    turnedkey[M - 1 - i][M - 1 - j] = key[i][j];
                    break;
                case 3:
                    turnedkey[M - 1 - j][i] = key[i][j];
                    break;
            }
        }
    }
    //     for(int i=0;i<M;i++){
    // System.out.println(Arrays.toString(turnedkey[i]));
    // }
    // System.out.println();

    for(int i=0;i<M-1+N;i++){
        for(int j=0;j<M-1+N;j++){
            if(check(i,j,turnedkey)){
               //System.out.println("test");
                answer = true;
                return answer;
            };
        }
    }

}
        
        
        
        return answer;
    }
    
    private boolean check(int startX, int startY, int[][] turnedkey){
        int fitCnt = 0;
        for(int i=0;i<M;i++){
            for(int j=0; j<M;j++){
                if(i+startX<M-1 || i+startX >= M-1+N || j+startY <M-1 || j+startY >= M-1+N)continue;
                if(turnedkey[i][j] == 1 && expandLock[i+startX][j+startY] == 1){
                    return false;
                }
                if(turnedkey[i][j] == 1 && expandLock[i+startX][j+startY] == 0 ){
                    fitCnt++;
                }
            }
        }
        //System.out.println(fitCnt);
        if(fitCnt == cnt)return true;
        return false;
    }
}