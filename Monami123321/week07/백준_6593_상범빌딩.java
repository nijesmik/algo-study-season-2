import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_6593_상범빌딩 {

    static int dr[] = {-1, 1, 0, 0, 0, 0}; // 상 하 좌 우 위 아래
    static int dc[] = {0, 0, -1, 1, 0, 0};
    static int dz[] = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String FAIL = "Trapped!";
        String SUCCESS_PRE = "Escaped in ";
        String SUCCESS_SUF = " minute(s).";

        testCase:
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            if (L == 0) {
                break;
            }
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            char[][][] map = new char[L][R][C];
            int[] start = new int[3];
            int[] end = new int[3];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String tmp = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = tmp.charAt(k);
                        if (map[i][j][k] == 'S') {
                            start[0] = i;
                            start[1] = j;
                            start[2] = k;
                        } else if (map[i][j][k] == 'E') {
                            end[0] = i;
                            end[1] = j;
                            end[2] = k;
                        }

                    }
                }
                br.readLine();

            }

            Queue<int[]> queue = new LinkedList<>();
            boolean[][][] visited = new boolean[L][R][C];

            queue.add(new int[]{start[0], start[1], start[2], 0});
            visited[start[0]][start[1]][start[2]] = true;

            while (!queue.isEmpty()) {
                int[] now = queue.poll();
                if (now[0] == end[0] && now[1] == end[1] && now[2] == end[2]) {
                    sb.append(SUCCESS_PRE).append(now[3]).append(SUCCESS_SUF).append("\n");
                    continue testCase;
                }

                for (int i = 0; i < 6; i++) {
                    int nz = now[0] + dz[i];
                    int nr = now[1] + dr[i];
                    int nc = now[2] + dc[i];

                    if (nr < 0 || nc < 0 || nz < 0 || nr > R - 1 || nc > C - 1 || nz > L - 1 || visited[nz][nr][nc] || map[nz][nr][nc] == '#') {
                        continue;
                    }

                    queue.add(new int[]{nz, nr, nc, now[3] + 1});
                    visited[nz][nr][nc] = true;

                }

            }
            sb.append(FAIL).append("\n");


        }
        System.out.print(sb);


    }
}
