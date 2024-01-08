import java.util.*;

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        
        int N = money.length;
        
        int[] dp = new int[N];
        
        
        
        //시작 집의 돈을 훔칠 때
        dp[0] = money[0];
        dp[1] = money[0];
        
        //n번째 돈을 훔치기 위해서는 n-2 + (n)
        //n번째 돈을 훔칠 수 없으면 n-1이 가장 큰 경우
        
        for(int i=2;i <N;i++){
            if(i==N-1){
                //마지막 칸은 훔칠 수 없음
                dp[i] = dp[i-1];
                break;
            }
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        //System.out.println(Arrays.toString(dp));
        int a = dp[N-1];
        
        //시작 집의 돈을 안 훔칠 때
        dp[0] = 0;
        dp[1] = money[1];
        
        for(int i=2;i <N;i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + money[i]);
        }
        int b = dp[N-1];
        //System.out.println(Arrays.toString(dp));
        answer = Math.max(a,b);
        return answer;
    }
}