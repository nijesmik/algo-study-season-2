import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        // [type, r1, c1, r2, c2, degree]
        
        int skillCnt = skill.length;
        
        int[][] presum = new int[board.length+1][board[0].length+1];
        
        for(int idx = 0; idx < skillCnt; idx++ ){
            int type = skill[idx][0];
            int r1 = skill[idx][1];
            int c1 = skill[idx][2];
            int r2 = skill[idx][3];
            int c2 = skill[idx][4];
            int degree = skill[idx][5];
            if(type == 1){
                degree*=-1;
            }
            //  누적합 개념
            presum[r1][c1] += degree;
            presum[r2+1][c2+1] += degree;
            presum[r1][c2+1] -= degree;
            presum[r2+1][c1] -= degree;

        }
        
        // for(int i=0;i<board.length+1;i++){
        //     System.out.println(Arrays.toString(presum[i]));
        // }
        
        // System.out.println("------");
        int answer = 0;
        for(int i=0;i<board.length+1;i++){
            for(int j=1;j<board[0].length+1;j++){
                presum[i][j] += presum[i][j-1];
            }
        }
        for(int j=0;j<board[0].length+1;j++){
            for(int i=1;i<board.length+1;i++){
                presum[i][j] += presum[i-1][j];
            }
        }
        
        // for(int i=0;i<board.length+1;i++){
        //     System.out.println(Arrays.toString(presum[i]));
        // }

        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j] += presum[i][j];
                if(board[i][j] > 0)answer++;
            }
        }
        

        return answer;
    }
}