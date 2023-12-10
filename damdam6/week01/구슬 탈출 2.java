package y23Nov;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Baek13460 {

    static char[][] bd;
    static int  N;
    static int M;
    static pos blueBall;
    static pos redBall;
    static pos hall;

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bd = new char[N][M];

        String str;
        for(int i=0;i<N;i++){
            str = bf.readLine();
            for(int j = 0; j < M ;j++){
                char c = str.charAt(j);
                if(c == 'B'){
                    blueBall = new pos(i,j);
                    c = '.';
                }else if(c == 'R'){
                    redBall = new pos(i,j);
                    c = '.';
                }else if(c == 'O'){
                    hall = new pos(i,j);
                }
                bd[i][j]=c;

            }
        }

        minCnt = Integer.MAX_VALUE;

        Deque<playCase> pqu = new ArrayDeque<>();
        pqu.add(new playCase(blueBall, redBall, 0));

        playCase tmp;
        while (!pqu.isEmpty()){

            tmp = pqu.poll();

            //공이 홀에 들어갔으면~  끝
            if(tmp.cnt >= 10){
                break;
            }

            //y좌표 움직일때
            for (int i = 0; i < 4; i++) {

                //파란색이 들어가면 그 방향 안됨
                if(!chkBlue(tmp.blue.x, tmp.blue.y, i))continue;
                if(chkRed(tmp.red.x, tmp.red.y, i)){
                    minCnt = Math.min(minCnt,tmp.cnt+1);
                    continue;
                }

                if(redBall.x == blueBall.x && redBall.y == blueBall.y){
//                    static int[] dx = new int[]{0,0,1,-1};
//                    static int[] dy = new int[]{1,-1,0,0};
                    switch (i){
                        case 0:
                            //y값이 증가 -> y값이 더 작았던 친구 -1
                            if(tmp.blue.y > tmp.red.y){
                                redBall.y -=1;
                            }else {
                                blueBall.y -=1;
                            }
                            break;
                        case 1:
                            //y값 감소 ->  y값이 더 컸던 친구 +1
                            if(tmp.blue.y < tmp.red.y){
                                redBall.y +=1;
                            }else {
                                blueBall.y +=1;
                            }
                            break;

                        case 2:
                            //x값 증가 -> x값이 더 작았던 친구 -1
                            if(tmp.blue.x < tmp.red.x){
                                blueBall.x -= 1;
                            }else{
                                redBall.x -= 1;
                            }
                            break;
                        case 3:
                            //x값 감소 -> x값 이 더 컸던 친구 +1
                            if(tmp.blue.x > tmp.red.x){
                                blueBall.x += 1;
                            }else{
                                redBall.x +=1;
                            }
                            break;
                    }

                }
                //위치 다 바꿨으므로

                pqu.add(new playCase(blueBall,redBall, tmp.cnt+1));

            }


        }
        if(minCnt == Integer.MAX_VALUE)minCnt=-1;
        System.out.println(minCnt);

    }

    static  int minCnt;
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};

    static public boolean chkRed(int x, int y, int idx){
        int nx = x+dx[idx];
        int ny = y+dy[idx];
        while(bd[nx][ny] == '.'){
            nx += dx[idx];
            ny += dy[idx];
        }
        if(bd[nx][ny] == 'O')return true;

        redBall.x = nx -dx[idx];
        redBall.y = ny -dy[idx];
        return false;
    }

   static public boolean chkBlue(int x, int y, int idx){
        int nx = x+dx[idx];
        int ny = y+dy[idx];
        while(bd[nx][ny] == '.'){
            nx += dx[idx];
            ny += dy[idx];
        }
        if(bd[nx][ny] == 'O')return false;

       //벽 전의 위치
        blueBall.x = nx -dx[idx];
        blueBall.y = ny -dy[idx];
        return true;
    }

    static class playCase implements Comparable<playCase> {
        pos blue;
        pos red;

        int cnt;
        playCase(pos blue, pos red, int cnt){
            this.blue = new pos(blue);
            this.red = new pos(red);
            this.cnt = cnt;
        }

        public int compareTo(playCase p){
            return this.cnt - p.cnt;
        }


    }

    static class pos{
        int x;
        int y;
        pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        pos(pos p){
            this.x = p.x;
            this.y = p.y;
        }
    }
}
