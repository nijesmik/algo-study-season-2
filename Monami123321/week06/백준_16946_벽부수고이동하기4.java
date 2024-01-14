import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_16946_벽부수고이동하기4 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static int n, m, bfsIndex;
    static HashMap<Integer, Integer> hashMap;

    static void bfs(int startR, int startC) {
        int sum = 0;
        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> queue2 = new LinkedList<>();
        queue.add(new int[]{startR, startC});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            sum++;
            queue2.add(now);
            for (int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1 || visited[nr][nc] || map[nr][nc] != 0) {
                    continue;
                }
                queue.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
        while (!queue2.isEmpty()) {
            int[] now = queue2.poll();
            map[now[0]][now[1]] = bfsIndex;
        }
        hashMap.put(bfsIndex++, sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        bfsIndex = 0;
        hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), "01", true);
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 || visited[i][j]) {
                    continue;
                }
                bfs(i, j);
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1 || !visited[nr][nc]) {
                            continue;
                        }
                        set.add(map[nr][nc]);
                    }
                    int tmp = 1;
                    for (Integer k : set) {
                        tmp += hashMap.get(k);
                    }
                    map[i][j] = tmp % 10;
                    set.clear();
                    sb.append(map[i][j]);
                } else {
                    sb.append(0);
                }

            }
            sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
