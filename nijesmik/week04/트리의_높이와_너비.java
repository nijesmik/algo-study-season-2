package nijesmik.week04;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 트리의_높이와_너비
 */
public class 트리의_높이와_너비 {
    static final int LEFT = 0, RIGHT = 1, PARENT = 2;
    static int N, cnt;
    static int[][] tree;
    static Map<Integer, int[]> map;
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
        map = new HashMap<>();
        dfs(getRoot(), 1);
        int level = 1, width = 0;
        for (int i = 1; i < N; i++) {
            int[] cur = map.get(i);
            if (cur == null) break;
            if (width < cur[1]) {
                level = i;
                width = cur[1];
            }
        }
        System.out.printf("%d %d\n", level, width+1);
    }

    static void dfs(int idx, int depth) {
        if (idx == 0) return;
        dfs(tree[idx][LEFT], depth+1);
        cnt++;
        int[] val = map.get(depth);
        if (val == null) {
            map.put(depth, new int[]{cnt, 0});
        } else {
            val[1] = cnt - val[0];
            map.put(depth, val);
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
