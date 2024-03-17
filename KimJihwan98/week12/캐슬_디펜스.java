import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 캐슬_디펜스 {
    static int N, M, D, answer, cnt;
    static int[][] map;
    static PriorityQueue<int[]> pq;
    static boolean[] people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        answer = 0;
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        people = new boolean[M];
        pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]) {
                return o1[1] - o2[1];
            }
            return o1[2] - o2[2];
        });
//        cnt = 0;
        comb(0, 0);
        System.out.println(answer);
    }

    static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    static void comb(int nidx, int r) {
        if (r == 3) {
            int max = game(people);
//            cnt++;
            answer = Math.max(max, answer);
            return;
        }
        if (nidx >= M) return;

        people[nidx] = true;
        comb(nidx + 1, r + 1);
        people[nidx] = false;
        comb(nidx + 1, r);
    }

    static int game(boolean[] arrows) {
        int[][] tmp_map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tmp_map[i] = map[i].clone();
        }
        HashSet<String> hs = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();
        for (int r = N; r > 0; r--) {
            for (int i = 0; i < M; i++) { // 궁수있는 열
                if (!arrows[i]) continue;
                for (int k = 1; k <= D; k++) { // 행 : r-k
                    if (r - k < 0) continue;
                    for (int j = 0; j < M; j++) {
                        int d = getDistance(r - k, j, r, i);
                        if (tmp_map[r - k][j] == 1 && d <= D) {
                            pq.add(new int[]{r - k, j, d});
                        }
                    }
                }
                if (!pq.isEmpty()) {
                    int[] tmp = pq.poll();
                    q.add(tmp);
                    String s = tmp[0] + "" + tmp[1];
                    hs.add(s);
                }
                pq.clear();
            }
            while(!q.isEmpty()) {
                int[] temp = q.poll();
                tmp_map[temp[0]][temp[1]] = 0;
            }
        }
//        for (String s : hs) {
//            System.out.println(cnt + "번쨰 : " + s);
//        }
        return hs.size();
    }
}