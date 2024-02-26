package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불 {
    static int R, C, answer;
    static boolean[][] visited;
    static Queue<int[]> q, q2;
    static int[][] personMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int sr = 0;
        int sc = 0;
        q = new LinkedList<>();
        q2 = new LinkedList<>();
        visited = new boolean[R][C];
        personMap = new int[R][C];
        for(int i = 0; i < R; i++) {
            Arrays.fill(personMap[i], Integer.MAX_VALUE);
        }

        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                char ch = s.charAt(c);
                if (ch == '#') visited[r][c] = true; // 벽이면 1
                else if (ch == '.') continue; // 지나갈수있으면 2
                else if (ch == 'J') {
                    q2.add(new int[]{r, c, 0});
                } else {
                    visited[r][c] = true;
                    q.add(new int[] {r, c});
                }
            }
        }

        int answer = bfs();
        if(answer == 0) System.out.println("IMPOSSIBLE");
        else System.out.println(answer);
    }

    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static int bfs() {
        while (!q2.isEmpty()) {
            int cnt = q.size();
            while (cnt-->0) {
                int[] now = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = now[0] + dr[i];
                    int nc = now[1] + dc[i];

                    if (!(nr >= 0 && nr < R && nc >= 0 && nc < C)) continue;
                    if (visited[nr][nc]) continue;
                    visited[nr][nc]=true;
                    q.add(new int[] {nr, nc});
                }
            }
            int cnt2 = q2.size();
            while(cnt2-->0) {
                int[] person = q2.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = person[0] + dr[i];
                    int nc = person[1] + dc[i];
                    if (!(nr >= 0 && nr < R && nc >= 0 && nc < C)) return ++person[2];
                    if (visited[nr][nc]) continue;
                    if(person[2]+1 >= personMap[nr][nc]) continue;
                    personMap[nr][nc]=person[2]+1;
//                    System.out.println("nr : " + nr + " nc : " + nc + " 값 : " + person[2]+1);
                    q2.add(new int[]{nr, nc, person[2] + 1});
                }
            }
        }
        return 0;
    }
}
