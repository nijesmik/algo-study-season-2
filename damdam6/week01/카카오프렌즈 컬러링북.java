import java.util.*;

class Solution {
    static int[][] vt;
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        vt = new int[picture.length][picture[0].length];
        
        for(int i=0;i<picture.length;i++){
            for(int j=0;j<picture[0].length;j++){
                if(picture[i][j]==0 || vt[i][j] == 1)continue;
                numberOfArea+=1;
                maxSizeOfOneArea = Math.max(bfs(i,j, picture),maxSizeOfOneArea);
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    
    public int bfs(int x, int y, int[][] arr){
        
        Deque<pos> qu = new ArrayDeque<>();
        vt[x][y] = 1;
        qu.add(new pos(x, y));
        int cnt = 0;
        pos tmp;
        while(!qu.isEmpty()){
            tmp = qu.poll();
            cnt++;
            //System.out.println(cnt);
            //System.out.println(qu.toString());
            for(int i=0;i<4;i++){
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];
                if(nx < 0 || nx >= arr.length || ny < 0 || ny >= arr[0].length)continue;
                if(arr[tmp.x][tmp.y] != arr[nx][ny])continue;
                if(vt[nx][ny] == 1)continue;
                
                vt[nx][ny] = 1;
                qu.add(new pos(nx, ny));
            }
            
        }
        return cnt;
        
    }
    
    static class pos{
        int x;
        int y;
        
        pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public String toString(){
            return "x "+x+" y "+y;
        }
    }
    
}