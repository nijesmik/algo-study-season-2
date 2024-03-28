import java.io.*;
import java.util.*;

public class 순서대로_방문하기 {

  public class Main {
    static int N, M, answer;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      map = new int[N][N];
      for(int i = 0; i < N; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j= 0; j < N; j++) {
          map[i][j] = Integer.parseInt(st.nextToken());
          if(map[i][j] == 1) map[i][j] = -1;
        }
      }
      int cnt = 1;
      int sr = 0; int sc = 0;
      for(int i = 0; i < M; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken())-1;
        int y = Integer.parseInt(st.nextToken())-1;
        map[x][y] = cnt++;
        if(map[x][y] == 1) {
          sr = x;
          sc = y;
        }
      }
      answer = 0;
      visited = new boolean[N][N];
      visited[sr][sc] = true;
      dfs(sr, sc, 1);
      System.out.println(answer);
    }
    static void dfs(int sr, int sc, int now) {
      if(now == M) {
        answer++;
        return;
      }
      for(int i = 0; i < 4; i++) {
        int nr = sr + dr[i];
        int nc = sc + dc[i];
        if(!(nr>=0&&nr<N&&nc>=0&&nc<N)) continue;
        if(visited[nr][nc]) continue;
        if(map[nr][nc]!=0 && map[nr][nc]!=now+1) continue;
        if(map[nr][nc] == 0) {
          visited[nr][nc] = true;
          dfs(nr, nc, now);
          visited[nr][nc] = false;
        } else {
          visited[nr][nc] = true;
          dfs(nr, nc, now+1);
          visited[nr][nc] = false;
        }
      }

    }
  }

}
