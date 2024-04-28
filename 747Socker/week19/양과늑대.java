import java.io.*;
import java.util.*;

public class 양과늑대 {
    static int R;
    static int C;
    static char[][] map;
    static boolean flag;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static boolean[][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];
        int wCnt = 0;
        int sCnt = 0;
        Queue<int[]> Q = new LinkedList<>();
        visited = new boolean[R][C];

        for(int i=0; i<R; i++){
            String tmp = sc.next();
            for(int j=0; j<C; j++){
                char temp = tmp.charAt(j);
                if(temp=='S') sCnt++;
                else if(temp=='W') {
                    Q.add(new int[]{i,j});
                    visited[i][j] = true;
                    wCnt++;
                }else if(temp=='.') temp='D';
                map[i][j] = temp;
            }
        }
        flag = true;
        

        while(!Q.isEmpty()){
            int[] cur = Q.poll();
            int x = cur[0];
            int y = cur[1];
            for(int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(nx>=0 && nx<R && ny>=0 && ny<C && map[nx][ny] == 'S' ){
                    flag = false;
                    return;
                }
            }
        }
        if (flag) {
            System.out.println("1");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
        } else {
            System.out.println("0");
        }
    }
}