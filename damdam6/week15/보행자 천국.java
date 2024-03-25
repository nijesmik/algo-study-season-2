class Solution {
	  int MOD = 20170805;
	  public int solution(int m, int n, int[][] cityMap) {
	      // dp[][][0] 아래쪽, dp[][][1] 오른쪽
          int[][][] dp = new int[m + 1][n + 1][2];
	      
          dp[1][1][0] = dp[1][1][1] = 1;
          
          for(int x = 1 ; x < m+1 ; ++x){
              for(int y = 1 ; y < n+1 ; ++y){
                  if(cityMap[x-1][y-1] == 0){
                      //어차피 나머지니까 걍 나누면서 기록하기
                      dp[x][y][0] = dp[x][y][0] +  (dp[x-1][y][0] + dp[x][y-1][1]) % MOD;
                      
                      
                      dp[x][y][1] = dp[x][y][1] + (dp[x -1][y][0] + dp[x][y -1][1]) % MOD;
                  } else if(cityMap[x -1][y - 1] == 1){
                      //여기는 못감
                      dp[x][y][0] = 0;
                      dp[x][y][1] = 0;
                  } else {
                      dp[x][y][0] = dp[x-1][y][0] % MOD ;
                      dp[x][y][1] = dp[x][y-1][1] % MOD;
                  }
              }
          }
          return dp[m][n][0]; 
      }
}