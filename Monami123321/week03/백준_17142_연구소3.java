import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_17142_연구소3 {

    static class Virus {
        int r, c;

        public Virus(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[][] map;
    static int[][] caseMap;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<int[]> queue;

    static int n, m;


    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;

    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }
        if (i == 0) {
            return false;
        }
        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j])
            j--;
        swap(arr, i - 1, j);

        int k = arr.length - 1;
        while (k > i) {
            swap(arr, i++, k--);
        }
        return true;
    }

    static void initMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                caseMap[i][j] = map[i][j];
            }
        }
    }

    static int checkCase() {
        int res = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (caseMap[i][j] == 0)
                    return Integer.MAX_VALUE;
                if (caseMap[i][j] == -1) {
                    continue;
                }
                res = Math.max(res, caseMap[i][j]);
            }
        }
        return res;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        caseMap = new int[n][n];
        List<Virus> virusList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusList.add(new Virus(i, j));
                    map[i][j] = -2;
                } else if (map[i][j] == 1) {
                    map[i][j] = -1;
                }
            }
        }

        queue = new LinkedList<>();

        int virusCnt = virusList.size();

        int[] combination = new int[virusCnt];
        for (int i = 0; i < m; i++) {
            combination[virusCnt - 1 - i] = 1;
        }
        int ans = Integer.MAX_VALUE;

        do {
            initMap();
            if (checkCase() == -1) {
                System.out.println(0);
                return;
            }
            for (int i = 0; i < virusCnt; i++) {
                if (combination[i] != 0) {
                    Virus virus = virusList.get(i);
                    queue.add(new int[]{virus.r, virus.c, 0});
                    caseMap[virus.r][virus.c] = -1;
                }
            }
            while (!queue.isEmpty()) {
                int[] now = queue.poll();
                int r = now[0];
                int c = now[1];
                int t = now[2];

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr < 0 || nr > n - 1 || nc < 0 || nc > n - 1 || caseMap[nr][nc] == -1) {
                        continue;
                    }
                    if (caseMap[nr][nc] > 0) {
                        continue;
                    }

                    if (caseMap[nr][nc] == -2) {
                        queue.add(new int[]{nr, nc, t + 1});
                        caseMap[nr][nc] = -1;

                    } else {
                        queue.add(new int[]{nr, nc, t + 1});
                        caseMap[nr][nc] = t + 1;
                    }

                }
            }

            int caseRes = checkCase();

            ans = Math.min(ans, caseRes);
        } while (nextPermutation(combination));

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);


    }
}
