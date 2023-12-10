package y23Dec01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek16929 {

    static int N;
    static int M;

    //다시 풀어야 됨 -> 아이디어만 공유하고 싶어서...
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

       char[][] bd = new char[N][M];
       int [][] par = new int[N][M];
        String str;
        for (int i = 0; i < N; i++) {
            str = bf.readLine();
            for (int j = 0; j < M; j++) {
            bd[i][j] = str.charAt(j);
            par[i][j] = N*i + j;
            }
        }

        int[] dx = new int[]{-1,0,1,0};
        int[] dy = new int[]{0,-1,0,1};
        boolean chk = false;
allfor:   for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                for (int k = 0; k < N; k++) {
                    System.out.println(Arrays.toString(par[k]));;
                }
                System.out.println("---------");
                if(j!=0 && i!=0){
                    if(bd[i+dx[0]][j] == bd[i][j]&&bd[i][j+dy[1]] == bd[i][j]
                    &&par[i+dx[0]][j] == par[i][j+dy[1]]
                    ){
                        chk = true;
                        break allfor;
                    }
                }
                int nx = 0;
                int ny = 0;
                int p1;
                int p2;
                for(int k=0;k<4;k++){
                    nx = i + dx[k];
                    ny = j + dy[k];
                    if(nx < 0 || nx >= N || ny < 0 || ny >= M || bd[nx][ny] != bd[i][j]){
                        continue ;
                    }
                    par[i][j] = Math.min(par[nx][ny],par[i][j]);
                    par[nx][ny] = par[i][j];
                }

            }
        }

        if(chk){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

    }

}
