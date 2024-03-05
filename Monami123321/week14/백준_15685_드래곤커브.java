import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_15685_드래곤커브 {

    static boolean[][] map = new boolean[101][101];
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());
            map[r][c] = true;
            if (gen == 0) {
                map[r + dr[dir]][c + dc[dir]] = true;
                continue;
            }
            int[] arr = new int[1 << gen];
            arr[0] = dir;
            r += dr[dir];
            c += dc[dir];
            dir = (dir + 1) % 4;
            arr[1] = dir;
            dfs(r, c, arr, gen, 1, 1);
        }

        int ans = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                ans += check(i, j);
            }
        }
        System.out.println(ans);


        br.close();
    }

    static void dfs(int startR, int startC, int[] arr, int gen, int genNow, int pathStartIndex) {

        int r = startR;
        int c = startC;
        map[r][c] = true;
        for (int i = pathStartIndex; i < pathStartIndex + (1 << genNow - 1); i++) {
            int nr = r + dr[arr[i]];
            int nc = c + dc[arr[i]];
            map[nr][nc] = true;
            r = nr;
            c = nc;
        }
        if (gen == genNow) {
            return;
        }
        pathStartIndex += (1 << genNow - 1);

        for (int i = 0; i < 1 << genNow; i++) {
            arr[pathStartIndex + i] = (arr[pathStartIndex - 1 - i] + 1) % 4;
        }
        dfs(r, c, arr, gen, genNow + 1, pathStartIndex);
    }

    static int check(int r, int c) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                if (!map[r + i][c + j]) {
                    return 0;
                }
            }
        }
        return 1;
    }
}
