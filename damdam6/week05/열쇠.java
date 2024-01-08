package y24Jan02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Baek9328 {
    static int h, w;
    static char[][] map;
    static boolean[][] visited;
    static boolean[] keys;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCases = Integer.parseInt(br.readLine());

        while (testCases-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken()) + 2;
            w = Integer.parseInt(st.nextToken()) + 2;
            map = new char[h][w];
            visited = new boolean[h][w];
            keys = new boolean[26];

            for (int i = 0; i < h; i++) {
                Arrays.fill(map[i], '.');
            }
            for (int i = 1; i < h - 1; i++) {
                String line = br.readLine();
                for (int j = 1; j <= line.length(); j++) {
                    map[i][j] = line.charAt(j - 1);
                }
            }

            String initialKeys = br.readLine();
            for (char key : initialKeys.toCharArray()) {
                if (key != '0') {
                    keys[key - 'a'] = true;
                }
            }

            System.out.println(bfs());
        }
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        int documents = 0;

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < h && ny >= 0 && ny < w && !visited[nx][ny] && map[nx][ny] != '*') {
                    visited[nx][ny] = true;
                    char c = map[nx][ny];

                    if (c == '.' || c == '$' || (Character.isLowerCase(c) && keys[c - 'a']) || (Character.isUpperCase(c) && keys[Character.toLowerCase(c) - 'a'])) {
                        if (c == '$') {
                            documents++;
                        } else if (Character.isLowerCase(c)) {
                            keys[c - 'a'] = true;
                            queue.clear();
                            visited = new boolean[h][w];
                            queue.offer(new int[]{0, 0});
                            continue;
                        }
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        return documents;
    }
}
