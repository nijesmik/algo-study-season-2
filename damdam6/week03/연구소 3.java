package y23Dec11Dec17;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek17142 {

    static int N;
    static int M;
    static int[][] bd;
    static int emtyCnt;
    static ArrayList<virus> virusNactive;
    static int[] selectedArr;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        emtyCnt = 0;
        bd = new int[N][N];

        virusNactive = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                bd[i][j] = Integer.parseInt(st.nextToken());
                if(bd[i][j] == 2){
                    virusNactive.add(new virus(i,j,0));
                }else if(bd[i][j]==0){
                }
            }

        }

        selectedArr = new int[virusNactive.size()];
        minTime = Integer.MAX_VALUE;
        dfs(0,0);

        if (minTime == Integer.MAX_VALUE)minTime=-1;
        System.out.println(minTime);



    }

    //bfs
    // 바뀐 빈칸의 갯수가 기존 빈칸의 갯수와 동일하면! 멈추기! (비활성 위치 체크용)

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    static public int bfs(){

        int[][] bdCopy = new int[N][N];
        for (int i = 0; i < N ; i++) {
            bdCopy[i] = bd[i].clone();
        }

        ArrayDeque<virus> qu = new ArrayDeque<>();

        virus tmp;
        for (int i = 0; i < virusNactive.size(); i++) {
            if(selectedArr[i] == 0)continue;
            tmp = virusNactive.get(i);
            bdCopy[tmp.x][tmp.y] = 3;
            qu.add(new virus(tmp));
        }



        int min = Integer.MAX_VALUE;
        while (!qu.isEmpty()){
            tmp = qu.poll();


            if(chk(bdCopy)){
                min = Math.min(min, tmp.time);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = tmp.x + dx[i];
                int ny = tmp.y + dy[i];

                if(nx<0 || ny < 0 || nx>= N || ny >= N )continue;
                if(bdCopy[nx][ny] == 1 || bdCopy[nx][ny] ==3 )continue;
                if(bdCopy[nx][ny] == 0 || bdCopy[nx][ny] == 2){

                    bdCopy[nx][ny] = 3;
                    qu.add(new virus(nx,ny, tmp.time+1));

                }

            }

        }

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(bdCopy[i]));
        }
        System.out.println("-----------"+min);
        if(min==3) System.out.println(Arrays.toString(selectedArr));

        return min;

    }

    static boolean chk(int[][] arr){

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == 0){
                    return false;
                }
            }
        }

        return true;
    }

    //dfs(조합)
    static int minTime;
    static public void dfs(int idx, int selectedCnt){

        if(selectedCnt==M){
            minTime = Math.min(minTime, bfs());
            return;
        }

        if(idx >= virusNactive.size()){
            return;
        }

        selectedArr[idx] = 1;
        dfs(idx+1,selectedCnt+1);
        selectedArr[idx] = 0;
        dfs(idx+1,selectedCnt);
    }

    static class virus{
        int x;
        int y;
        int time;

        public virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
        public virus(virus v){
            this.x = v.x;
            this.y = v.y;
            this.time = v.time;
        }

    }
}


