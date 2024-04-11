import java.util.*;
public class Main {
    static int N,M;
    static int[][] map;
    static int Fr,Fc;
    static int Sr,Sc;
    static int H,W;
    static boolean[][] visited;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int bfs(){
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{Sr,Sc,0});
        visited[Sr][Sc] = true;

        while (Q.size()!=0) {
            int[] tmp = Q.poll();
            int r = tmp[0];
            int c = tmp[1];
            int move = tmp[2];

            if(r==Fr && c==Fc){
                return move;
            }

            for(int i=0; i<4; i++){
                int nr = r + dx[i];
                int nc = c + dy[i];

                if(nr>=0 && nr<=N-H && nc>=0 && nc<=M-W && !visited[nr][nc] && canMove(nr, nc)){
                    visited[nr][nc]=true;
                    Q.add(new int[]{nr,nc,move+1});
                }
            }

        }


        return -1;
    }

    static boolean canMove(int r,int c){
        for(int i=r; i<r+H; i++){
            for(int j=c; j<c+W; j++){
                if(map[i][j]==1){
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }
        H = sc.nextInt();
        W = sc.nextInt();
        Sr = sc.nextInt()-1;
        Sc = sc.nextInt()-1;
        Fr = sc.nextInt()-1;
        Fc = sc.nextInt()-1;
        
        visited = new boolean[N][M];
        int res = bfs();
        System.out.println(res);
    }
}
