package y24Mar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baek17140 {

    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new int[101][101];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int xL = 3;
        int yL = 3;


    }

    static public class Cnt {
        int num;
        int count;

        Cnt(int num, int count) {
            this.count = count;
            this.num = num;
        }
    }

    static void R(int key, int yL) {
        PriorityQueue<Cnt> pq = new PriorityQueue<>();
        Map<Integer, Integer> cntMap = new HashMap<>();

        for (int i = 1; i <= yL; i++) {
            if (map[key][i] == 0) continue;
            cntMap.compute(map[key][i], (num, count) -> count == null ? 1 : count + 1);
        }

        cntMap.forEach((k, v) -> pq.add(new Cnt(k, v)));

        int i = 1;
        while (!pq.isEmpty()) {
            Cnt p = pq.poll();
            map[key][i++] = p.num;
            map[key][i++] = p.count;
        }

        yL = Math.max(yL, i);

        while (i <= 99) {
            map[key][i++] = 0;
            map[key][i++] = 0;
        }
    }


}
