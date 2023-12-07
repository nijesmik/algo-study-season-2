import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_17141_연구소2 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<int[]> queue;
    static List<Point> points;


    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;

    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i] <= arr[i - 1])
            i--;
        if (i == 0) {
            return false;
        }
        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j])
            j--;
        swap(arr, i - 1, j);

        int k = arr.length - 1;

        while (k > i) {
            swap(arr, k--, i++);
        }
        return true;


    }

    static int bfs(int[] arr) {
        int[][] caseMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                caseMap[i][j] = map[i][j];
                if (caseMap[i][j] == 2) {
                    caseMap[i][j] = 0;
                }
            }
        }
        boolean[][] visited = new boolean[N][N];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                Point point = points.get(i);
                queue.add(new int[]{point.r, point.c, 0});
                visited[point.r][point.c] = true;
                caseMap[point.r][point.c] = -1;
            }

        }

        int res = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            int t = now[2];

            res = res < t ? t : res;


            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr > N - 1 || nc < 0 || nc > N - 1 || visited[nr][nc] || caseMap[nr][nc] != 0) {
                    continue;
                }
                queue.add(new int[]{nr, nc, t + 1});
                caseMap[nr][nc] = t + 1;
                visited[nr][nc] = true;

            }

        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (caseMap[i][j] == 0) {
                    return Integer.MAX_VALUE;
                }
            }
        }

        return res;


    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        points = new ArrayList<>();

        map = new int[N][N];
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    points.add(new Point(i, j));
                }
            }
        }
        int pointsSize = points.size();
        int[] combination = new int[pointsSize];
        for (int i = 0; i < M; i++) {
            combination[pointsSize - 1 - i] = 1;

        }

        int ans = Integer.MAX_VALUE;

        do {
            int tmp = bfs(combination);
            ans = ans > tmp ? tmp : ans;

        } while (nextPermutation(combination));

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }


    }
}
