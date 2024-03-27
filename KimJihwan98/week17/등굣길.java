import java.util.Arrays;
import java.util.*;

public class 등굣길 {
  class Solution {
    long MOD = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
      int answer = 0;

      long[][] map = new long[m][n];
      for(int i = 0;i < m; i++) {
        Arrays.fill(map[i], 1);
      }

      for(int[] pud : puddles) {
        int r = pud[0]-1;
        int c = pud[1]-1;
        if(r == 0) {
          for(int i = c; i < n; i++) {
            map[0][i] = 0;
          }
        } else if(c == 0) {
          for(int i = r; i < m; i++) {
            map[i][0] = 0;
          }
        }

        map[r][c] = 0;
      }

      for(int i = 1; i < m; i++) {
        for(int j = 1; j < n; j++) {
          if(map[i][j] == 0) continue;
          long up = map[i-1][j] == 0 ? 0 : map[i-1][j];
          long left = map[i][j-1] == 0 ? 0 : map[i][j-1];
          map[i][j] = (up + left) % MOD;
        }
      }
      answer = (int) map[m-1][n-1];

      return answer;
    }
  }
}
