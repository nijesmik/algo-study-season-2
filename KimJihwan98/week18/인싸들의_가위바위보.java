import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 인싸들의_가위바위보 {
  static int N, K;
  static boolean[] visited;
  static int[] pIdx, players;
  static int[][] data;
  static int[] win_count;
  static int[][] rcp;
  static boolean answer;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    answer = false;
    N = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    rcp = new int[N+1][N+1];
    for(int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 1; j <= N; j++) {
        rcp[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    data = new int[20][3];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < 20; i++) {
      data[i][1] = Integer.parseInt(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < 20; i++) {
      data[i][2] = Integer.parseInt(st.nextToken());
    }

    visited = new boolean[N];
    players = new int[2];
    // 0 : 지우 1 : 경희 2 : 민호
    perm(0);
    if (answer) {
      System.out.println(1);
    } else {
      System.out.println(0);
    }

  }
  static void perm(int idx) {
    if(answer) return;
    if(idx==N) {
      if(playGame()) answer = true;
      return;
    }
    for(int i = 0;i < N; i++) {
      if(visited[i]) continue;
      visited[i] = true;
      data[idx][0] = i+1;
      perm(idx+1);
      visited[i] = false;
    }
  }
  static boolean playGame() {
    pIdx = new int[3];
    win_count = new int[3];
    players[0] = 0;
    players[1] = 1;
    while (true) {
      if(pIdx[0]==N) return false;
      int p1 = players[0];
      int p2 = players[1];

      int card1 = data[pIdx[p1]++][p1];
      int card2 = data[pIdx[p2]++][p2];

      if (rcp[card1][card2] == 2) {
        win_count[p1]++;
        players[1] = 3 - p2 - p1;
      } else if (rcp[card1][card2] == 1) {
        if (p1 < p2) {
          win_count[p2]++;
          players[0] = 3 - p1 - p2;
        } else {
          win_count[p1]++;
          players[1] = 3 - p2 - p1;
        }
      } else {
        win_count[p2]++;
        players[0] = 3 - p1 - p2;
      }

      if (win_count[0] == K) {
        return true;
      } else if (win_count[1] == K || win_count[2] == K) {
        break;
      }
    }
    return false;
  }
}
