package y23Dec02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Baek16929 {
    static int N;
    static int M;
    static int[][] vt;
    static char[][] bd;

    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        mapIdx = new HashMap<>();
        mapIdx.put(0,1);
        mapIdx.put(1,0);
        mapIdx.put(2,3);
        mapIdx.put(3,2);

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        bd = new char[N][M];
        vt = new int[N][M];
        String str;
        for (int i = 0; i < N; i++) {
            str = bf.readLine();
            for (int j = 0; j < M; j++) {
                bd[i][j] = str.charAt(j);
            }
        }
        boolean chk = false;
 all:   for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(vt[i][j] == 1) continue ;
                if(bfs(i,j,bd[i][j])){
                    chk = true;
                    break all;
                }

            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(bd[i]));
//        }


        if(chk) System.out.println("Yes");
        else System.out.println("No");

    }


    static Map<Integer,Integer> mapIdx;
    static public boolean bfs( int i, int j, char c){

        boolean chk = false;
        Deque<pos> qu = new ArrayDeque<>();
        vt[i][j] = 1;
        qu.add(new pos(i,j,c, -1));

        pos tmp;
        while(!qu.isEmpty()){
//            for (int l = 0; l < N; l++) {
//                System.out.println(Arrays.toString(vt[l]));
//
//            }
//            System.out.println("-----------------");

            tmp = qu.poll();

            for (int k = 0; k < 4; k++) {
                if(k == tmp.dir)continue;
                int nx = tmp.x + dx[k];
                int ny = tmp.y + dy[k];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M)continue;
                if(bd[nx][ny] != tmp.c)continue;
                if(vt[nx][ny] == 1 ){
                    chk = true;
                    continue;
                };
                vt[nx][ny] = 1;
                qu.add(new pos(nx, ny, bd[nx][ny], mapIdx.get(k)));
            }


        }

        return chk;
    }

    static class pos{
        int x, y;
        char c;
        int dir;

        public pos(int x, int y, char c, int dir) {
            this.x = x;
            this.y = y;
            this.c = c;
            this.dir = dir;
        }
    }

}
