import java.util.*;
class 보행자_천국 {
    int MOD = 20170805;
    int[] dr = {0, 1};
    int[] dc = {1, 0};
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        int[][][] countMap = new int[m][n][2];
        for(int i = 0; i < n; i++) {
            if(cityMap[0][i] == 1) break;
            countMap[0][i][1] = 1;
        }
        for(int i = 0; i < m; i++) {
            if(cityMap[i][0] == 1) break;
            countMap[i][0][0] = 1;
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(cityMap[i][j] == 1) continue;

                if(cityMap[i-1][j]==0) countMap[i][j][0] = countMap[i-1][j][0] + countMap[i-1][j][1];
                else if(cityMap[i-1][j] == 2) countMap[i][j][0] = countMap[i-1][j][0];

                if(cityMap[i][j-1]==0) countMap[i][j][1] = countMap[i][j-1][0] + countMap[i][j-1][1];
                else if(cityMap[i][j-1] == 2) countMap[i][j][1] = countMap[i][j-1][1];

                countMap[i][j][0] %= MOD;
                countMap[i][j][1] %= MOD;
            }
        }
        // for(int i = 0; i < m; i++) {
        //     for(int j = 0; j < n; j++) {
        //         System.out.print(Arrays.toString(countMap[i][j]));
        //     }
        //     System.out.println();
        // }
        answer = (countMap[m-1][n-1][0] + countMap[m-1][n-1][1])%MOD;
        return answer;
    }
}