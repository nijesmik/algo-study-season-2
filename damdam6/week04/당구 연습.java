class Solution {
    static int M;
    static int N;
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        //"원쿠션"// 각 회마다 머쓱이가 친 공이 굴러간 거리의 최솟값의 제곱을 배열에 담아 return 
        N = n;
        M = m;
        for(int i=0; i<balls.length;i++){
            int min = Integer.MAX_VALUE;
            for(int j=0; j< 4; j++){
                min = Math.min(min,distance(startX, startY, balls[i][0], balls[i][1], j));
            }
            answer[i]= min;
            
        }
        return answer;
    }
    
    //dir 0 1 2 3 위 아래 왼 오
    public int distance(int sx, int sy, int bx, int by, int dir){
        int dis = 0;
        
        int a = 0;
        int b = 0;
        int tmp = 0;
        
        switch(dir){
            case 0:
                tmp = Math.abs(bx - sx);
                a = N - sy;
                b= N - by;
                if(sy < by && tmp==0)return Integer.MAX_VALUE;
                break;
            case 1:
                tmp = Math.abs(bx - sx);
                a = sy;
                b = by;
                if(sy > by && tmp==0)return Integer.MAX_VALUE;
                break;
            case 2:
                a = sx;
                b = bx;
                tmp = Math.abs(by-sy);
                if(sx > bx && tmp==0)return Integer.MAX_VALUE;
                break;
            case 3:
                a = M - sx;
                b= M - bx;
                tmp = Math.abs(by-sy);
                if(sx < bx && tmp==0)return Integer.MAX_VALUE;
                break;
        }
        
        
        dis = (a+b)*(a+b)+tmp*tmp;
        //System.out.println(a+" "+b+" "+dis);
        
        return dis;
    }
}