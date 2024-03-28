import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 사다리_조작 {
  static int N, M, H, answer;
  static int[][] ladder;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());
    ladder = new int[H][N];
    answer = Integer.MAX_VALUE;
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken()) - 1;
      int b = Integer.parseInt(st.nextToken()) - 1;
      ladder[a][b] = 1;
    }

    for(int i = 0; i <= 3; i++) {
      dfs(0,0,i);
      if(answer!=Integer.MAX_VALUE) break;
    }
    if(answer==Integer.MAX_VALUE) {
      System.out.println(-1);
      return;
    }
    System.out.println(answer);
  }
  static void dfs(int row, int count, int max_count) {
    if(count==max_count) {
      if(check()) answer = count;
      return;
    }

    for(int i = row; i < H; i++) {
      for(int j = 0; j < N-1; j++) {
        if(j == 0 || ladder[i][j-1] == 0) {
          if (ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
            ladder[i][j] = 1;
            dfs(i, count + 1, max_count);
            ladder[i][j] = 0;
          }
        }
      }
    }
  }

  static boolean check() {
    for(int c = 0; c < N; c++) {
      int col = c;
      for(int r = 0; r < H; r++) {
        if(ladder[r][col] == 1) col++;
        else if(col>0 && ladder[r][col-1]==1) col--;
      }
      if(col!=c) return false;
    }
    return true;
  }
}
