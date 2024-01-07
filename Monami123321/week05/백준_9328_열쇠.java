import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class 백준_9328_열쇠 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int h, w, ans;
    static char[][] map;
    static boolean[][] visited;
    static HashMap<Character, ArrayList<int[]>> hashMap;

    static void bfs(int startR, int startC) {
        if (visited[startR][startC] || map[startR][startC] == '*'){
            return;
        }
        if ('A' <= map[startR][startC] && map[startR][startC] < 'a') {
            return;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startR, startC});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];

            if ('a' <= map[r][c] && map[r][c] <= 'z') {
                if (hashMap.get((char) (map[r][c] ^ 32)) != null) {
                    hashMap.get((char) (map[r][c] ^ 32)).forEach(e -> {
                        if (visited[e[0]][e[1]]) {
                            return;
                        }

                        for (int i = 0; i < 4; i++) {
                            int nr = e[0] + dr[i];
                            int nc = e[1] + dc[i];

                            if (nr < 0 || nr > h - 1 || nc < 0 || nc > w - 1) {
                                visited[e[0]][e[1]] = true;
                                map[e[0]][e[1]] = '.';
                                queue.add(new int[]{e[0], e[1]});
                                return;
                            }

                            if (visited[nr][nc]) {
                                visited[e[0]][e[1]] = true;
                                map[e[0]][e[1]] = '.';
                                queue.add(new int[]{e[0], e[1]});
                                return;
                            }
                        }
                        map[e[0]][e[1]] = '.';
                    });
                }
            }

            if (map[r][c] == '$') {
                ans++;
            }


            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr < 0 || nr > h - 1 || nc < 0 || nc > w - 1 || visited[nr][nc] || map[nr][nc] == '*') {
                    continue;
                }
                if (map[nr][nc] < 'a' && map[nr][nc] >= 'A') {
                    continue;
                }
                queue.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int testCases = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCases; tc++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
            }
            List<Character> keys = br.readLine().chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(ArrayList::new));
            hashMap = new HashMap<>();
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '*' || map[i][j] == '.' || map[i][j] == '$') {
                        continue;
                    }
                    if (map[i][j] >= 'A' && map[i][j] < 'a') {
                        ArrayList<int[]> tmp = hashMap.getOrDefault(map[i][j], new ArrayList<>());
                        tmp.add(new int[]{i, j});
                        hashMap.put(map[i][j], tmp);
                    }
                }
            }

            keys.forEach(key -> {
                if (hashMap.get((char) (key ^ 32)) == null) {
                    return;
                }
                hashMap.get((char) (key ^ 32)).forEach(e -> {
                    map[e[0]][e[1]] = '.';
                });
                hashMap.remove((char) (key ^ 32));
            });

            ans = 0;

            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                bfs(i, 0);
                bfs(i, w - 1);
            }
            for (int i = 0; i < w; i++) {
                bfs(0, i);
                bfs(h - 1, i);
            }

            sb.append(ans).append("\n");
        }
        System.out.print(sb);


        br.close();
    }
}
