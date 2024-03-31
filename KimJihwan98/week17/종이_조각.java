import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 종이_조각 {
  static int N, M, answer;
  static int[][] map;
  static boolean[][] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    answer = Integer.MIN_VALUE;
    map = new int[N][M];
    visited = new boolean[N][M];

  }
  static void dfs(int r, int c, int sum) {
    if(visited[r][c]) return;
    if(r>N-1 || c > M-1) return;
    if(check()) {
      answer = Math.max(answer, sum);
      return;
    }
    int idx = 0;

    for(int i = r; i < N; i++) {
      visited[i][c] = true;

    }

  }

  static boolean check() {
    for(int i = 0; i < N; i++) {
      for(int j= 0; j < M ;j++) {
        if(!visited[i][j]) return false;
      }
    }
    return true;
  }
}
