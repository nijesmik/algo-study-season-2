
import java.util.*;
 
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int val = 1000000007;
        
        int[][] bd = new int[n + 1][m + 1];
        for(int i = 0; i < puddles.length; i++) {
           bd[puddles[i][1]][puddles[i][0]] = -1; 
        }
        
        bd[1][1] = 1;
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < m + 1; j++) {
                if(bd[i][j] == -1) continue;
                //1에서부터 시작해야됨. -> 0에서 시작해서 조건 체킹하면 시간 오버된다.
                if(bd[i - 1][j] > 0) bd[i][j] += bd[i - 1][j] % val;
                if(bd[i][j - 1] > 0) bd[i][j] += bd[i][j - 1] % val;
            }
        }
        return bd[n][m] % val;
    }
}
