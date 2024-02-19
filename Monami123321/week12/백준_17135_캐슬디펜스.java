import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_17135_캐슬디펜스 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] caseMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] arr = new int[M];
        for (int i = 0; i < 3; i++) {
            arr[M - 1 - i] = 1;
        }
        int ans = 0;
        do {
            initMap(map, caseMap);
            int turn = 0;
            int cnt = 0;
            while (!isGameEnd(caseMap, turn)) {
                int[][] res = attack(arr, caseMap, turn, D);
                for (int i = 0; i < 3; i++) {
                    int enemyR = res[i][0];
                    int enemyC = res[i][1];
                    if (enemyR == Integer.MAX_VALUE) {
                        continue;
                    }
                    if (caseMap[enemyR][enemyC] != 0) {
                        caseMap[enemyR][enemyC] = 0;
                        cnt++;
                    }
                }
                move(caseMap, turn);
            }
            ans = Math.max(ans, cnt);
        } while (nextPermutation(arr));
        System.out.println(ans);


        br.close();
    }

    static void initMap(int[][] refMap, int[][] caseMap) {
        for (int i = 0; i < refMap.length; i++) {
            for (int j = 0; j < refMap[0].length; j++) {
                caseMap[i][j] = refMap[i][j];
            }
        }
    }

    static void move(int[][] caseMap, int turn) {
        for (int i = caseMap.length - 1; i > turn; i--) {
            caseMap[i] = caseMap[i - 1];
        }
        caseMap[turn] = new int[caseMap[0].length];
    }

    static boolean isGameEnd(int[][] caseMap, int turn) {
        boolean res = true;
        outer:
        for (int i = turn; i < caseMap.length; i++) {
            for (int j = 0; j < caseMap[0].length; j++) {
                if (caseMap[i][j] != 0) {
                    res = false;
                    break outer;
                }
            }
        }
        return res;
    }

    static int[][] attack(int[] archer, int[][] caseMap, int turn, int limit) {
        int[][] res = new int[3][2];
        for (int i = 0; i < 3; i++) {
            res[i][0] = Integer.MAX_VALUE;
            res[i][0] = Integer.MAX_VALUE;
        }
        int r = caseMap.length;
        int archerIndex = 0;
        for (int i = 0; i < archer.length; i++) {
            if (archer[i] != 0) {
                int distNow = Integer.MAX_VALUE;
                for (int j = turn; j < caseMap.length; j++) {
                    for (int k = 0; k < caseMap[0].length; k++) {
                        if (caseMap[j][k] != 0) {
                            int dist = getDist(r, i, j, k);
                            if (dist > limit) {
                                continue;
                            }
                            if (distNow > dist) {
                                res[archerIndex][0] = j;
                                res[archerIndex][1] = k;
                                distNow = dist;
                            } else if (distNow == dist && res[archerIndex][1] > k) {
                                res[archerIndex][0] = j;
                                res[archerIndex][1] = k;
                            }
                        }
                    }
                }
                archerIndex++;
            }

        }
        return res;
    }


    static int getDist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
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
        while (arr[i - 1] >= arr[j]) {
            j--;
        }
        swap(arr, i - 1, j);

        int k = arr.length - 1;
        while (i < k) {
            swap(arr, i++, k--);
        }
        return true;
    }

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
