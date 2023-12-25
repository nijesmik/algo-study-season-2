import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_4991_로봇청소기 {


    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] dist;
    static char[][] map;
    static int[][] spots;
    static int w, h, spotSize, ans, INF;

    static void bfs(int startR, int startC, int spotIndex) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        visited[startR][startC] = true;
        queue.add(new int[]{startR, startC, 0});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            int cost = now[2];

            if (map[r][c] == '*') {
                for (int i = 1; i < spotSize; i++) {
                    if (spots[i][0] == r && spots[i][1] == c) {
                        dist[spotIndex][i] = cost;
                        break;
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr > h - 1 || nc < 0 || nc > w - 1 || visited[nr][nc] || map[nr][nc] == 'x') {
                    continue;
                }
                queue.add(new int[]{nr, nc, cost + 1});
                visited[nr][nc] = true;

            }

        }


    }

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static void permutation(int[] arr, int depth) {
        if (depth == arr.length) {
            int res = 0;
            for (int i = 0; i < arr.length - 1; i++) {
                int tmp = dist[arr[i]][arr[i + 1]];
                if (tmp == INF) {
                    return;
                }
                res += tmp;
            }
            ans = Math.min(ans, res);
            return;
        }


        for (int i = depth; i < arr.length; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1);
            swap(arr, depth, i);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        INF = 1 << 25;

        tc:
        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            if (w == 0) {
                break;
            }
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            spots = new int[11][2];
            spotSize = 1;

            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 'o') {
                        spots[0][0] = i;
                        spots[0][1] = j;
                    } else if (map[i][j] == '*') {
                        spots[spotSize][0] = i;
                        spots[spotSize++][1] = j;
                    }
                }
            }

            dist = new int[spotSize][spotSize];
            for (int i = 0; i < spotSize; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }




            for (int i = 0; i < spotSize; i++) {
                bfs(spots[i][0], spots[i][1], i);
            }



            // 0 ~ spotSize만큼 순열 -> 모든 경우의 수에 대해서 가장 빠른 경로를 구해야
            ans = Integer.MAX_VALUE;
            int[] arr = new int[spotSize];
            for (int i = 0; i < spotSize; i++) {
                arr[i] = i;
            }
            permutation(arr, 1);
            if (ans == Integer.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(ans).append("\n");
            }


        }

        System.out.print(sb);
    }


}
