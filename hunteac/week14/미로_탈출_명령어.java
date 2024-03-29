import java.util.*;

class 미로_탈출_명령어 {
    static class Pos {
        int r;
        int c;
        String str;

        Pos (int r, int c, String str) {
            this.r = r;
            this.c = c;
            this.str = str;
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";

        int[] dr = { 1, 0, 0, -1 }; // 행 탐색
        int[] dc = { 0, -1, 1, 0 }; // 열 탐색

        Queue<Pos> queue = new LinkedList<>();

        queue.add(new Pos(x, y, ""));

        int dist = Math.abs(x - r) + Math.abs(y - c);

        if (dist % 2 == 0) {
            if (k % 2 == 1) return "impossible";
        } else {
            if (k % 2 == 0) return "impossible";
        }

        int cnt = 0; // 이동 횟수

        while (!queue.isEmpty()) {
            if (cnt > k) break;

            int len = queue.size();

            Outer: for (int i = 0; i < len; i++) {
                Pos p = queue.poll();

                int pr = p.r;
                int pc = p.c;
                String str = p.str;

                if (cnt == k && pr == r && pc == c) answer = str; // 도착

                for (int d = 0; d < 4; d++) {
                    int nr = pr + dr[d];
                    int nc = pc + dc[d];

                    if (nr < 1 || nr > n || nc < 1 || nc > m) continue;

                    dist = Math.abs(nr - r) + Math.abs(nc - c);

                    if (d == 0 && dist < k - cnt) {
                        queue.add(new Pos(nr, nc, str + "d"));
                        continue Outer;
                    } else if (d == 1 && dist < k - cnt) {
                        queue.add(new Pos(nr, nc, str + "l"));
                        continue Outer;
                    } else if (d == 2 && dist < k - cnt) {
                        queue.add(new Pos(nr, nc, str + "r"));
                        continue Outer;
                    } else if (d == 3 && dist < k - cnt) {
                        queue.add(new Pos(nr, nc, str + "u"));
                        continue Outer;
                    }
                }
            }

            cnt++;
        }

        if (answer.isEmpty()) return "impossible"; // 탈출 불가능
        else return answer; // 가능
    }
}