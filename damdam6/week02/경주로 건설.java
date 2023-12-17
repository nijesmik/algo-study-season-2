import java.util.*;
class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int answer = 0;
        
        //[w / d]//
        int[][] rec = new int[n][n];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                rec[i][j] = Integer.MAX_VALUE;
            }
        }
        rec[0][0] = 0;
        
        PriorityQueue<pos> pqu = new PriorityQueue<>();
        if(board[1][0] != 1){
            rec[1][0] = 100;
            pqu.add(new pos(1,0,100,2));
        }
        if(board[0][1] != 1){
            rec[0][1] = 100;
        pqu.add(new pos(0,1,100,1));
        }
        
        pos tmp;
        while(!pqu.isEmpty()){
            
//         for(int i=0;i<n;i++){
                
//             System.out.println(Arrays.toString(rec[i]));
//         }
//             System.out.println("--------");
            
            tmp = pqu.poll();
            
            //최종 도달 시 비교 & 체크
            // if(tmp.x == n-1 && tmp.y == n-1){
            //     answer = Math.min(answer, tmp.w);
            //     continue;
            // }
            
            for(int i=0;i<4;i++){
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx][ny] == 1)continue;
                int nw = tmp.w + 100;
                if(tmp.inDir != i)nw+=500;
                
                if(rec[nx][ny] < nw)continue;
                rec[nx][ny] = nw;
                pqu.add(new pos(nx,ny,nw,i)); 
            }

        }
        answer = rec[n-1][n-1];
        return answer;
    }
    
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{-1,1,0,0};
    
    class pos implements Comparable<pos>{
        int x;
        int y;
        int w;
        int inDir;
        
        pos(int x, int y, int wage, int inDir){
           this.x = x;
            this.y = y;
            this.w = wage;
            this.inDir = inDir;
        }
                
        public int compareTo(pos p){
            if(this.x > p.x )return 1;
            if(this.x < p.x)return -1;
            
            if(this.y > p.y )return 1;
            if(this.y < p.y)return -1;
            
            if(this.w > p.w)return 1;
            if(this.w < p.w )return -1;
            return 0;
        }
    }
}