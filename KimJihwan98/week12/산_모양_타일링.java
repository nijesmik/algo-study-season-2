package 백준;

public class 산_모양_타일링 {
    class Solution {
        public int solution(int n, int[] tops) {
            int answer = 0;
            // An[0] : 아래 삼각형이 오른쪽이랑 마름모 이루지 않는 경우
            // An[1] : 아래 삼각형이 오른쪽이랑 마름모를 이루고 있는 경우
            // 1. tops[n+1]=1일 때
            //    An+1[0] = An[0]*3 + An[1]*2
            //    An+1[1] = An[0] + An[1]
            // 2. tops[n+1] = 0 일 떄
            //    An+1[0] = An[0]*2 + An[1]
            //    An+1[1] = An[0] + An[1]

            int[] dp1 = new int[n];
            int[] dp2 = new int[n];
            if(tops[0]==1) {
                dp1[0] = 3;
                dp2[0] = 1;
            } else {
                dp1[0] = 2;
                dp2[0] = 1;
            }
            for(int i = 1; i < n; i++) {
                if(tops[i] == 1) {
                    dp1[i] = dp1[i-1]*3 + dp2[i-1]*2;
                    dp2[i] = dp1[i-1] + dp2[i-1];
                } else {
                    dp1[i] = dp1[i-1]*2 + dp2[i-1];
                    dp2[i] = dp1[i-1] + dp2[i-1];
                }
                dp1[i] %= 10007;
                dp2[i] %= 10007;
            }

            return (dp1[n-1]+dp2[n-1])%10007;
        }
    }
}
