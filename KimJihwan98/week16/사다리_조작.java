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
  static int N, M, H, num, answer;
  static int[][] ladder;
  static List<int[]> draw;
  static boolean[] v;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());
    ladder = new int[H][N];
    num = 1;
    answer = Integer.MAX_VALUE;
    for(int i = 0;i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken())-1;
      int b = Integer.parseInt(st.nextToken())-1;
      ladder[a][b] = num;
      ladder[a][b+1] = num++;
    }



    draw = new ArrayList<>();
    for(int i = 0; i < H; i++) {
      for(int j = 0; j < N-1; j++) {
        if(ladder[i][j] == 0 && ladder[i][j+1]==0) {
          draw.add(new int[] {i, j});
        }
      }
    }
    v = new boolean[draw.size()];
    dfs(0,0);
    if (answer == Integer.MAX_VALUE) {
      System.out.println(-1);
      return;
    }
    System.out.println(answer);
  }


  static void dfs(int idx, int n) {
    if(n>3 || n >= answer) return;
    if (idx == draw.size()) return;
    if(check()) {
        answer = n ;
    }

    int[] tmp = draw.get(idx);
    int tr = tmp[0];
    int tc = tmp[1];
    if(ladder[tr][tc]==0 && ladder[tr][tc+1]==0) {
      ladder[tr][tc] = num;
      ladder[tr][tc + 1] = num++;
      dfs(idx + 1, n + 1);
      ladder[tr][tc] = 0;
      ladder[tr][tc + 1] = 0;
      num--;
    }
    dfs(idx+1, n);

  }

  static Queue<int[]> q;
  static int[] dr = {0,0, 1};
  static int[] dc = {-1, 1, 0};
  static boolean check() {
    for(int i = 0; i < N; i++) {
      int c = i;
      for(int j = 0; j < H; j++) {
        if(c!=N-1) {
          if (ladder[j][c]!=0 && ladder[j][c] == ladder[j][c + 1]) {
            c++;
            continue;
          }
        }
        if(c!=0) {
          if(ladder[j][c]!=0 && ladder[j][c] == ladder[j][c-1]) {
            c--;
          }
        }
      }
      if(c!=i) return false;
    }
    return true;
  }
}
