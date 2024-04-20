import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 직사각형_탈출 {
  static int N, M, H, W, Sr, Sc, Fr, Fc;
  static int[][] map;
  static List<int[]> walls;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    map = new int[N][M];
    walls = new ArrayList<>();
    for(int i = 0;i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j] == 1) walls.add(new int[] {i, j});
      }
    }
    st = new StringTokenizer(br.readLine());
    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());
    Sr = Integer.parseInt(st.nextToken())-1;
    Sc = Integer.parseInt(st.nextToken())-1;
    Fr = Integer.parseInt(st.nextToken())-1;
    Fc = Integer.parseInt(st.nextToken())-1;

    int answer = bfs();
    System.out.println(answer);
  }
  static int[] dr = {0, 1, 0, -1};
  static int[] dc = {1, 0, -1, 0};
  static int bfs() {
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[] {Sr, Sc, 0});
    boolean[][] visited = new boolean[N][M];
    visited[Sr][Sc] = true;
    while(!q.isEmpty()) {
      int[] now = q.poll();
      int r = now[0];
      int c = now[1];
      int w = now[2];

      if(r == Fr && c == Fc) return w;
      for(int i = 0; i < 4; i++) {
        int nr = r + dr[i];
        int nc = c + dc[i];
        if(!(nr>=0&&nr<N-H+1&&nc>=0&&nc<M-W+1)) continue;
        if(visited[nr][nc]) continue;
        if(!check(nr, nc)) continue;
        visited[nr][nc] = true;
        q.add(new int[] {nr, nc, w+1});
      }
    }
    return -1;
  }
  static boolean check(int nr, int nc) {
    for(int[] wall : walls) {
      int r = wall[0];
      int c = wall[1];

      if((r >= nr && r < nr+H) && (c>=nc && c < nc+W)) return false;
    }
    return true;
  }
}
