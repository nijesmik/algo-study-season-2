import java.util.*;
class Solution {
  static List<Integer>[] can_ban;
  static int N, M, answer;
  static HashSet<String> hs;
  public int solution(String[] user_id, String[] banned_id) {
    answer = 0;
    N = user_id.length;
    M = banned_id.length;
    can_ban = new ArrayList[M];
    hs = new HashSet<>();
    for(int i = 0; i < M; i++) {
      can_ban[i] = new ArrayList<>();
      String s = banned_id[i];
      userLoop :for(int j = 0; j < N; j++){
        String user = user_id[j];
        if(s.length() != user.length()) continue;
        for(int k = 0; k < s.length(); k++) {
          if(s.charAt(k)=='*') continue;
          if(s.charAt(k) != user.charAt(k)) continue userLoop;
        }
        can_ban[i].add(j);
      }
    }
    for(int i = 0;i < M; i++) {
      for(int j = 0; j < can_ban[i].size(); j++){
        System.out.print(can_ban[i].get(j));
      }
      System.out.println();
    }
    visited = new boolean[N];
    dfs(0);
    answer = hs.size();
    return answer;
  }
  static boolean[] visited;
  static void dfs(int r) {
    if(r==M) {
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < N; i++) {
        if(visited[i]) sb.append(i);
      }
      hs.add(sb.toString());
      return;
    }
    for(int i = 0; i < can_ban[r].size(); i++) {
      if(visited[can_ban[r].get(i)]) continue;
      visited[can_ban[r].get(i)] = true;
      dfs(r+1);
      visited[can_ban[r].get(i)] = false;
    }
  }
}