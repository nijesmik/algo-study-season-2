import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 출퇴근길 {

  static int N, M, S, T;
  static List<Integer>[] graph1, graph2;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    graph1 = new ArrayList[N + 1];
    graph2 = new ArrayList[N + 1];
    for (int i = 1; i < N + 1; i++) {
      graph1[i] = new ArrayList<>();
      graph2[i] = new ArrayList<>();
    }
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph1[a].add(b);
      graph2[b].add(a);
    }
    st = new StringTokenizer(br.readLine());
    S = Integer.parseInt(st.nextToken());
    T = Integer.parseInt(st.nextToken());

    boolean[] v1 = new boolean[N + 1];
    v1[T] = true;
    dfs(S, graph1, v1);

    boolean[] v2 = new boolean[N + 1];
    v2[S] = true;
    dfs(T, graph1, v2);

    boolean[] v3 = new boolean[N + 1];
    dfs(S, graph2, v3);

    boolean[] v4 = new boolean[N + 1];
    dfs(T, graph2, v4);

    int count = 0;
    for (int i = 1; i <= N; i++) {
      if (v1[i] && v2[i] && v3[i] && v4[i]) {
        count++;
      }
    }
    System.out.println(count - 2);
  }

  static void dfs(int start, List<Integer>[] graph, boolean[] visited) {
    if (visited[start]) {
      return;
    }
    visited[start] = true;
    for (int i : graph[start]) {
      dfs(i, graph, visited);
    }

  }
}

