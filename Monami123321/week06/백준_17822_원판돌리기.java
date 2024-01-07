import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_17822_원판돌리기 {

    static int[][] plate;
    static int n, m, t;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void rotate(int[] arr, int dir, int weight) {
        int[] tmp = new int[m];
        for (int i = 0; i < m; i++) {
            tmp[i] = arr[i];
        }
        // 0 - 시계방향 1 - 반시계
        if (dir == 0) {
            for (int i = 0; i < m; i++) {
                arr[i] = tmp[(m - weight + i) % m];
            }
        } else {
            for (int i = 0; i < m; i++) {
                arr[i] = tmp[(weight + i) % m];
            }
        }
    }

    static boolean findAdj(boolean[][] visited) {
        boolean flag = false;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                if (plate[i][j] == -1) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (nr < 1 || nr > n) {
                        continue;
                    }
                    if (nc == -1) {
                        nc = m - 1;
                    } else if (nc == m) {
                        nc = 0;
                    }
                    if (plate[nr][nc] == plate[i][j]) {
                        visited[i][j] = true;
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    static void cal() {
        double sum = 0;
        int num = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                if (plate[i][j] == -1) {
                    continue;
                }
                num++;
                sum += plate[i][j];
            }
        }
        double ref = sum / num;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                if (plate[i][j] == -1) {
                    continue;
                }
                if (plate[i][j] > ref) {
                    plate[i][j] -= 1;
                } else if (plate[i][j] < ref) {
                    plate[i][j] += 1;
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        plate = new int[n + 1][m];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                plate[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int leftNum = m * n;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if (leftNum == 0) {
                System.out.println(0);
                return;
            }

            for (int j = target; j < n + 1; j += target) {
                rotate(plate[j], dir, weight);
            }
            boolean[][] visited = new boolean[n + 1][m];
            if (findAdj(visited)) {
                for (int j = 1; j < n + 1; j++) {
                    for (int k = 0; k < m; k++) {
                        if (visited[j][k]) {
                            plate[j][k] = -1;
                            leftNum--;
                        }
                    }
                }
            } else {
                cal();
            }
        }
        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                if (plate[i][j] == -1) {
                    continue;
                }
                ans += plate[i][j];
            }
        }
        System.out.println(ans);
        br.close();
    }
}
