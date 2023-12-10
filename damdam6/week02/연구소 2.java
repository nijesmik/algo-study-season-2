package y23Dec01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baek17141 {

    static int N;
    static int M;
    static int[][] bd;
    static pos[] virusPos;
    public static void main(String[] args) throws Exception {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bd = new int[N][N];

        int cntPos = 0;
        virusPos = new pos[10];
        //연구소 스타트 받기
        // 0 : 빈칸 1 : 벽 2 : 바이러스 가능
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                bd[i][j] = Integer.parseInt(st.nextToken());
                if(bd[i][j] == 2){
                    virusPos[cntPos++] = new pos(i,j);
                }
            }
        }
        //2위치를 모두 찾고 -> 해당 위치의 M개의 조합
        //해당 위치에 놓았을 때 바이러스가 퍼지는 시간

        minTime = Integer.MAX_VALUE;

        comb(cntPos, 0, 0);

        if(minTime == Integer.MAX_VALUE)minTime=-1;
        System.out.println(minTime);

    }

    //바이러스 위치 조합 고르는 함수
    //comb
    static int[] vt = new int[10];
    static int minTime;
    public static void comb(int cntPos, int idx, int cntTrue){

        //다 골랐을때 실행할 것
        if(cntTrue == M){
            int time = bfs();
            if(time == -1)return;
            minTime = Math.min(minTime, time);
            return;
        }
        //범위 벗어났으면 그만~
        if(idx == cntPos){
            return;
        }

        comb(cntPos, idx+1,cntTrue);
        vt[idx] = 1;
        comb(cntPos, idx+1,cntTrue+1);
        vt[idx] = 0;

    }

    public static int bfs(){

        int[][] basicBd = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                basicBd[i][j] = bd[i][j];
            }
        }

        Deque<virus> qu = new ArrayDeque<>();
        //고른 친구들 자리에 virus 놓는 과정(qu에 삽입)
        for (int i = 0; i < 10; i++) {
           if( vt[i] != 1)continue;
           qu.add(new virus(virusPos[i]));
           //바이러스 놨으니까 바이러스 숫자로 바꿔줌
           basicBd[virusPos[i].x][virusPos[i].y] = 3;
        }
        int maxTime = 0;
        virus tmp;
        while (!qu.isEmpty()){

//            for (int i = 0; i < N; i++) {
//
//                for (int j = 0; j < N; j++) {
//                    System.out.print(basicBd[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println("------");
//            System.out.println(maxTime);

            tmp = qu.poll();

            for (int i = 0; i < 4; i++) {
                int nx = tmp.p.x + dx[i];
                int ny = tmp.p.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N){
                    continue;
                }
                if(basicBd[nx][ny] == 1 || basicBd[nx][ny] == 3)continue;
                maxTime = tmp.time+1;
                basicBd[nx][ny] = 3;
                qu.add(new virus(nx, ny, maxTime));

            }

        }
        //갈 수 있는 만큼 다 돌렸음

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(basicBd[i][j] == 0 || basicBd[i][j] == 2){
                    maxTime = -1;
                }
            }
        }
        return maxTime;
    }
    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};

    static class virus{
        pos p;
        int time;

        public virus(int x, int y, int time) {
            this.p = new pos(x,y);
            this.time = time;
        }

        public virus(pos p) {
            this.p = new pos(p);
            this.time = 0;
        }
    }
   static class pos{
        int x;
        int y;
        public pos(pos p){
            this.x = p.x;
            this.y = p.y;
        }

        public pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
