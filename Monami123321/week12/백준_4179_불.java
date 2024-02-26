import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_4179_불 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] map = new char[R][C];
        int[] startPoint = new int[2];
        List<int[]> firePoint = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'J') {
                    startPoint[0] = i;
                    startPoint[1] = j;
                } else if (map[i][j] == 'F') {
                    firePoint.add(new int[]{i, j});
                }
            }
        }
        Queue<int[]> personQ = new LinkedList<>();
        personQ.add(new int[]{startPoint[0], startPoint[1], 0});
        Queue<int[]> fireQ = new LinkedList<>();
        firePoint.forEach(e -> {
            fireQ.add(new int[]{e[0], e[1], 0});
        });
        int time = 0;
        boolean flag = false;
        loop:
        while (!personQ.isEmpty()) {

            while (!fireQ.isEmpty() && fireQ.peek()[2] == time) {
                int[] now = fireQ.poll();
                int r = now[0];
                int c = now[1];
                int t = now[2];


                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (nr < 0 || nr > R - 1 || nc < 0 || nc > C - 1 || map[nr][nc] == 'F' || map[nr][nc] == '#') {
                        continue;
                    }
                    fireQ.add(new int[]{nr, nc, t + 1});
                    map[nr][nc] = 'F';
                }
            }
            while (!personQ.isEmpty() && personQ.peek()[2] == time) {
                int[] now = personQ.poll();
                int r = now[0];
                int c = now[1];
                int t = now[2];
                if (r == 0 || r == R - 1 || c == 0 || c == C - 1) {
                    flag = true;
                    break loop;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (map[nr][nc] == 'F' || map[nr][nc] == '#' || map[nr][nc] == 'J') {
                        continue;
                    }
                    personQ.add(new int[]{nr, nc, t + 1});
                    map[nr][nc] = 'J';
                }
            }
            time++;
        }
        if (flag) {
            System.out.println(time + 1);
        } else {
            System.out.println("IMPOSSIBLE");
        }


        br.close();
    }
}
