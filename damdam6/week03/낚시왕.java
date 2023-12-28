package y23Dec18Dec24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Baek17143new {

    static int R;
    static int C;
    static int M;


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dirMap.put(1,2);
        dirMap.put(2,1);
        dirMap.put(3,4);
        dirMap.put(4,3);

         shk[][] sea = new shk[R][C];
         shk[] arr = new shk[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sea[r-1][c-1] =  new shk(r-1,c-1,s,d,z, i);
            arr[i] = new shk(r-1,c-1,s,d,z,i);
        }

        int fisher = 0;

        //어부의 이동 for문
        for (int i = 0; i < C; i++) {

            //가장 위의 상어 잡기
            int idx = 0;
            while(idx < R && sea[idx][i] == null){
                idx++;
            }
            if(idx < R){
                fisher += sea[idx][i].z;
                arr[sea[idx][i].idx] = null;
                sea[idx][i] = null;
            }
//            System.out.println(fisher);
//            System.out.println(Arrays.toString(arr));

            //상어 전부 이동 시킴
            for (int j = 0; j < M; j++) {
                if(arr[j] == null)continue;
                arr[j].move();
            }

            sea = new shk[R][C];
            for (int j = 0; j < M; j++) {
                if(arr[j] == null)continue;
                if(sea[arr[j].r][arr[j].c] != null){
                    if(arr[j].z > sea[arr[j].r][arr[j].c].z){
                        arr[sea[arr[j].r][arr[j].c].idx] = null;
                        sea[arr[j].r][arr[j].c] = new shk(arr[j]);
                    }else{
                        arr[j] = null;
                    }
                    continue;
                }
                sea[arr[j].r][arr[j].c] = new shk(arr[j]);
            }

//            for (int j = 0; j < R; j++) {
//                System.out.println(Arrays.toString(sea[j]));
//
//            }
//            System.out.println("--------------");

        }

        System.out.println(fisher);
    }
    static int[] dx = new int[]{0,-1,1,0,0 };
    static int[] dy = new int[]{0,0,0,1,-1};
    static HashMap<Integer,Integer> dirMap = new HashMap<>();

    public static class shk{
        int r, c, s, d, z;
        int idx;

        public shk(int r, int c, int s, int d, int z, int idx) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.idx = idx;
        }
        public shk(shk sh){
            this.r = sh.r;
            this.c = sh.c;
            this.s = sh.s;
            this.d = sh.d;
            this.z = sh.z;
            this.idx = sh.idx;
        }

        //1 <-> 2
        //3 <-> 4
        public void move() {
            int totalDistance = s % ((this.d <= 2 ? R : C) * 2 - 2); // 최대 이동 가능 거리를 고려하여 모듈러 연산
            for (int i = 0; i < totalDistance; i++) {
                int nr = this.r + dx[this.d];
                int nc = this.c + dy[this.d];

                if(nr < 0 || nc < 0 || nr >= R || nc >= C) {
                    this.d = dirMap.get(this.d); // 방향 변경
                    nr = this.r + dx[this.d]; // 변경된 방향으로 새 위치 계산
                    nc = this.c + dy[this.d];
                }
                this.r = nr;
                this.c = nc;
            }
        }


        @Override
        public String toString() {
            return "shk{" +
                    "r=" + r +
                    ", c=" + c +
                    ", s=" + s +
                    ", d=" + d +
                    ", z=" + z +
                    '}';
        }


    }
}
