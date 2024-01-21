import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_23291_어항정리 {
    static int N, K;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[N - 1][i] = Integer.parseInt(st.nextToken());
        }
        int loopCase = 0;
        while (!check()) {
            loopCase++;
            init();
            fold();
            move();
            makeLine();
            halfAndHalf();
        }
        System.out.println(loopCase);


        br.close();
    }

    static boolean check() {
        int min = map[N - 1][0];
        int max = map[N - 1][0];
        for (int i = 0; i < N; i++) {
            min = Math.min(map[N - 1][i], min);
            max = Math.max(map[N - 1][i], max);
        }
        return max - min <= K;
    }

    static void init() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            min = Math.min(map[N - 1][i], min);
        }
        for (int i = 0; i < N; i++) {
            if (map[N - 1][i] == min) {
                map[N - 1][i] += 1;
            }
        }
    }

    static void fold() {
        Queue<Integer> queue = new LinkedList<>();
        int r = 1; // 왼쪽 맨 아래부터 쌓일순서대로 몇 개나 위쪽으로 뽑을건지? -> base랑 비교할 것
        int c = 1; // 몇개나 왼쪽으로 뽑을건지?
        int base = N; // 맨 아래 깔린 수
        int time = 1;
        while (base - c >= r) {
            int[] nextRow = new int[N];
            for (int i = 0; i < c; i++) {
                for (int j = 0; j < r; j++) {
                    queue.add(map[N - 1 - j][i]);
                }
            } // 쌓을 블록 순서대로 큐에
            for (int i = c, index = 0; i < base; i++) {
                nextRow[index++] = map[N - 1][i];
            } // 떙겨서 다음 맨 밑줄로
            for (int i = 0; i < c; i++) {
                for (int j = 0; j < r; j++) {
                    map[N - 1 - c + i][j] = queue.poll();
                }
            } // 돌려서 올려놨음
            map[N - 1] = nextRow; // 떙겨서 처리해놓은것으로 교체
            base -= c;
            if ((time & 1) != 0) {
                r++;
            } else {
                c++;
            }
            time++;
        }
    }

    static void move() {
        int[][] diff = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    continue;
                }
                int next = 0;

                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (nr < 0 || nr > N - 1 || nc < 0 || nc > N - 1 || map[nr][nc] == 0) {
                        continue;
                    }
                    int gap = (map[i][j] - map[nr][nc]) / 5;
                    if (gap > 0) {
                        next -= gap;
                    } else if (gap < 0) {
                        next -= gap;
                    }

                }
                diff[i][j] = next;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += diff[i][j];
            }
        }


    }

    static void makeLine() {
        int r = N - 1;
        int c = 0;
        Queue<Integer> queue = new LinkedList<>();
        while (true) {
            if (map[r][c] == 0) {
                break;
            }
            while (r >= 0 && map[r][c] != 0) {
                queue.add(map[r--][c]);
            }
            c++;
            r = N - 1;

        }
        int[][] nextMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            nextMap[N - 1][i] = queue.poll();
        }
        map = nextMap;
    }

    static void halfAndHalf() {
        for (int i = 0; i < N / 2; i++) {
            map[N - 2][i] = map[N - 1][N / 2 - 1 - i];
        }
        for (int i = 0; i < N / 2; i++) {
            map[N - 1][i] = map[N - 1][N / 2 + i];
            map[N - 1][N / 2 + i] = 0;
        }

        for (int i = 0; i < N / 4; i++) {
            for (int j = 0; j < 2; j++) {
                map[N - 4 + j][i] = map[N - 1 - j][N / 4 - 1 - i];
            }
        }
        for (int i = 0; i < N / 4; i++) {
            for (int j = 0; j < 2; j++) {
                map[N - 2 + j][i] = map[N - 2 + j][N / 4 + i];
                map[N - 2 + j][N / 4 + i] = 0;
            }
        }
        move();
        makeLine();
    }
}
