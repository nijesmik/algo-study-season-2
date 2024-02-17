import java.util.*;

public class 리모컨 {
    static int digit, target, ans;
    static boolean[] disable;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String targetString = sc.next();
        target = Integer.parseInt(targetString);
        digit = targetString.length();
        int n = sc.nextInt();
        disable = new boolean[10];
        while (n-- > 0) {
            disable[sc.nextInt()] = true;
        }
        ans = Math.abs(target - 100);
        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int depth, int created) {
        if (depth > 0) {
            ans = Math.min(ans, Math.abs(target - created) + depth);
        }
        if (depth == digit + 1) {
            return;
        }
        for (int i = 0; i < 10; i++) {
            if (!disable[i]) {
                dfs(depth + 1, created * 10 + i);
            }
        }
    }
}
