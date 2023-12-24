package nijesmik.week04;
import java.util.Scanner;

/**
 * 트리의_높이와_너비
 */
public class 트리의_높이와_너비 {
    static final int LEFT = 0, RIGHT = 1, PARENT = 2;
    static int N, cnt, level, max;
    static int[][] tree, width;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        tree = new int[N+1][3];
        for (int i = 0; i < N; i++) {
            int pidx = sc.nextInt();
            int lidx = sc.nextInt();
            if (lidx == -1) lidx = 0;
            int ridx = sc.nextInt();
            if (ridx == -1) ridx = 0;
            tree[pidx][LEFT] = lidx;
            tree[pidx][RIGHT] = ridx;
            tree[lidx][PARENT] = pidx;
            tree[ridx][PARENT] = pidx;
        }
        cnt = 0;
        level = 1;
        width = new int[N+1][2];
        dfs(getRoot(), 1);
        System.out.printf("%d %d\n", level, max+1);
    }

    static void dfs(int idx, int depth) {
        if (idx == 0) return;
        dfs(tree[idx][LEFT], depth+1);
        cnt++;
        if (width[depth][0] == 0) {
            width[depth][0] = cnt;
        } else {
            int w = cnt - width[depth][0];
            width[depth][1] = w;
            if (w == max) {
                level = Math.min(max, depth);
            } else if (w > max) {
                max = w;
                level = depth;
            }
        }
        dfs(tree[idx][RIGHT], depth+1);
    }

    static int getRoot() {
        for (int i = 1; i <= N; i++) {
            if (tree[i][PARENT] == 0) return i;
        }
        return 0;
    }
}
