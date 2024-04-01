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

    for(int i = 0; i < N; i++) {
      String s = br.readLine();
      for(int j = 0; j < M; j++) {
        map[i][j] = s.charAt(j) - '0';
      }
    }
    dfs(0,0);
    System.out.println(answer);
  }
  static void dfs(int r, int c) {
    if(r==N) {
      sum();
      return;
    }
    if(c==M) {
      dfs(r+1, 0);
      return;
    }

    visited[r][c] = true; // 지금 타일이 가로로 짤린 경우
    dfs(r, c+1);

    visited[r][c] = false; // 세로로 짤린 경우
    dfs(r,c+1);
  }

  static void sum() {
    int sum = 0;
    for(int i = 0; i < N; i++) {
      int temp = 0;
      for(int j = 0; j < M; j++) {
        if(visited[i][j]) {
          temp *= 10;
          temp += map[i][j];
        } else {
          sum += temp;
          temp = 0;
        }
      }
      sum += temp;
    }

    for(int i = 0; i < M; i++) {
      int temp = 0;
      for(int j = 0; j < N; j++) {
        if(!visited[j][i]) {
          temp *= 10;
          temp += map[j][i];
        } else {
          sum += temp;
          temp = 0;
        }
      }
      sum += temp;
    }

    answer = Math.max(answer, sum);
  }

}
