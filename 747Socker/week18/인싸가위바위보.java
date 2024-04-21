import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int[] kh;
    static int[] mh;
    static int[] jw;
    static int[] winCnt;
    static int[] idx;
    static boolean[] visited;
    static boolean flag;
    static int[][] arr;

    static void permu(int cnt){
        if(flag) return;
        if(cnt == N){
            winCnt = new int[3];
            idx = new int[3];
            doSimul(0,1);
            return;
        }
        for(int i=1; i<=N; i++){
            if(!visited[i]){
                visited[i] = true;
                jw[cnt] = i;
                permu(cnt+1);
                visited[i] = false;
            }
        }
    }

    static void doSimul(int o, int t){
        if(winCnt[0]>=K){
            flag=true;
            return;
        }
        if(idx[0]>=N || winCnt[1] >= K || winCnt[2]>=K){
            return;
        }
        int nP = 3-o-t;

        int p1 = 0;
        int p2 = 0;

        if (o == 0) {
            p1 = jw[idx[0]++];
        } else if (o == 1) {
            p1 = kh[idx[1]++];
        } else {
            p1 = mh[idx[2]++];
        }
    
        if (t == 0) {
            p2 = jw[idx[0]++];
        } else if (t == 1) {
            p2 = kh[idx[1]++];
        } else {
            p2 = mh[idx[2]++];
        }
    
        int res = arr[p1][p2];

        if(res ==2){
            winCnt[o]++;
            doSimul(o, nP);
        }else if(res==0){
            winCnt[t]++;
            doSimul(nP, t);
        }else{
            if(o>t){
                winCnt[o]++;
                doSimul(o, nP);
            }else{
                winCnt[t]++;
                doSimul(nP, t);
            }
        }

    }
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        kh = new int[20];
        mh = new int[20];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<20; i++){
            kh[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<20; i++){
            mh[i] = Integer.parseInt(st.nextToken());
        }
        flag = false;
        jw = new int[N+1];
        visited = new boolean[N+1];
        permu(0);
        
        if(flag) System.out.println(1);
        else System.out.println(0);

    }
}