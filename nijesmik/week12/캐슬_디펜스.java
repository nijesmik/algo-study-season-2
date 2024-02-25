package nijesmik.week12;
import java.util.*;

public class 캐슬_디펜스 {
    static int sizeR, sizeC, sizeD, map[][], permutation[], max;
    static boolean[][] killed;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sizeR = sc.nextInt();
        sizeC = sc.nextInt();
        sizeD = sc.nextInt();
        map = new int[sizeR][sizeC];
        for (int i = 0; i < sizeR; i++) {
            for (int j = 0; j < sizeC; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        permutation = new int[3];
        getPermutation(0, 0);
        System.out.println(max);
    }

    static void getPermutation(int depth, int idx) {
        if (depth == 3) {
            simulation();
            return;
        }

        for (int i = idx; i < sizeC; i++) {
            permutation[depth] = i;
            getPermutation(depth + 1, i + 1);
        }
    }

    static void simulation() {
        killed = new boolean[sizeR][sizeC];
        int score = 0;
        for (int i = sizeR; i > 0; i--) {
            int[][] enemies = new int[sizeC][];
            for (int j : permutation) {
                for (int d = 1; d <= sizeD && enemies[j] == null; d++) {
                    enemies[j] = kill(i, j, d);
                }
            }
            for (int[] e : enemies) {
                if (e != null && !killed[e[0]][e[1]]) {
                    killed[e[0]][e[1]] = true;
                    score++;
                }
            }
        }
        max = Math.max(max, score);
    }

    static int[] kill(int r, int c, int d) {
        for (int dc = -d + 1; dc < d; dc++) {
            int nr = r - (d - Math.abs(dc));
            int nc = c + dc;
            if (nr >= 0 && nr < sizeR && nc >= 0 && nc < sizeC && map[nr][nc] == 1 && !killed[nr][nc]) {
                return new int[] { nr, nc };
            }
        }
        return null;
    }
}
