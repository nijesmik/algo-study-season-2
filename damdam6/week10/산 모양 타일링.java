import java.util.*;

class Solution {
    public int solution(int n, int[] tops) {

        
        //  사다리꼴을 다음 마름모에 침범하도록 사용할 경우
        // f(n-1)
        
        // 침범하지 않도록 짜는 경우
        // 이미 침범당한 경우 
            // 삼각형 있으면 f(n-1)*2
            // 없으면 f(n-1)
        // 침범당하지 않은 경우 
            // 삼각형이 있으면 f(n-1)*2
            // 삼각형이 없으면 f(n-1)*3
    
        
        int[][] dp = new int[n][2];
        
        //0이 연결 안한 친구
        // 1이 연결 한 친구
        
        dp[0][1] = 1;
        if(tops[0] == 1){
            dp[0][0] = 3;
        }else{
            dp[0][0] = 2;
        }
        
        for(int i = 0; i< n-1;i++){
            dp[i+1][0] = dp[i][1]*(tops[i+1]+1) + dp[i][0]*(tops[i+1]+2);
            dp[i+1][1] = (dp[i][0]+dp[i][1]);
            
            dp[i+1][0] = dp[i+1][0]%10007;
            dp[i+1][1] = dp[i+1][1]%10007;
        }
        
        int sum = dp[n-1][0]+dp[n-1][1];
        
        int answer = sum % 10007;
        
        // for(int i = 0; i< n;i++){
        //     System.out.println(" dp "+(dp[i][0])+' '+ dp[i][1]);
        // }
        
        return answer;
    }
}