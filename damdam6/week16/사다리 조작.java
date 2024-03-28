import java.io.*;
import java.util.*;

public class Main {
 static int N, M, H;
 static boolean[][] ladder;
 static int answer = Integer.MAX_VALUE;

 public static void main(String[] args) throws Exception {
  BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
  StringTokenizer st = new StringTokenizer(bf.readLine());

  N = Integer.parseInt(st.nextToken()); 
  M = Integer.parseInt(st.nextToken()); 
  H = Integer.parseInt(st.nextToken()); 

  ladder = new boolean[H + 1][N + 1];

  for (int i = 0; i < M; i++) {
   st = new StringTokenizer(bf.readLine());
   int a = Integer.parseInt(st.nextToken());
   int b = Integer.parseInt(st.nextToken());
   ladder[a][b] = true;
  }

  for (int i = 0; i <= 3; i++) {
   dfs(1, 0, i);
   if (answer != Integer.MAX_VALUE) break;
  }
     if(answer == Integer.MAX_VALUE ){
         answer = -1;
     }

  System.out.println(answer);
 }

 static void dfs(int x, int count, int limit) {
  if (count == limit) {
   if (check()) answer = count;
   return;
  }

  for (int i = x; i <= H; i++) {
   for (int j = 1; j < N; j++) {
       
      // 앞 뒤 다 못 갈 때
    if (!ladder[i][j] && !ladder[i][j - 1] && !ladder[i][j + 1]) {
     ladder[i][j] = true;
     dfs(i, count + 1, limit);
     ladder[i][j] = false;
    }
   }
  }
 }

 static boolean check() {
  for (int y = 1; y <= N; y++) {
   int x = y;
   for (int z = 1; z <= H; z++) {
    if (ladder[z][x]) x++;
    else if (x > 1 && ladder[z][x - 1]) x--;
   }
   if (x != y) return false;
  }
  return true;
 }
}
