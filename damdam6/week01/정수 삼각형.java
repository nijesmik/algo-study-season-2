import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] arr = new int[triangle.length][];
        for(int i=0;i<triangle.length;i++){
            arr[i] = new int[triangle[i].length];
        }
        
        
        for(int i=0;i<triangle.length;i++){
            
            for(int j = 0; j<triangle[i].length;j++){
                arr[i][j] += triangle[i][j];
                
                if(i==triangle.length-1){
                    answer = Math.max(answer, arr[i][j]);
                    continue;
                }
                
                arr[i+1][j] = Math.max(arr[i+1][j],arr[i][j]);
                arr[i+1][j+1] = Math.max(arr[i+1][j+1],arr[i][j]);
                
            }
            
        }
        
        return answer;
    }
}