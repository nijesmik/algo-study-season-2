import java.util.Scanner;

public class 레벨_햄버거 {
    static long[] hamburger, patty;
    static long ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int level = sc.nextInt();
        long layer = sc.nextLong();
        hamburger = new long[level];
        patty = new long[level];
        hamburger[0] = 1;
        patty[0] = 1;
        for (int i = 1; i < level; i++) {
            hamburger[i] = 3 + hamburger[i - 1] * 2;
            patty[i] = patty[i - 1] * 2 + 1;
        }
        ans = 0;
        eat(level, layer);
        System.out.println(ans);
    }

    static void eat(int level, long layer) {
        int idx = getIndex(layer, level);
        if (idx < 0) {
            return;
        }
        layer -= level - idx;
        ans += patty[idx];
        layer -= hamburger[idx];
        if (layer > 0) {
            ans++;
            layer--;
        }
        if (idx > 0) {
            eat(idx, layer);
        } else if (layer > 0) {
            ans++;
        }
    }

    static int getIndex(long layer, int level) {
        int idx = level;
        while (idx-- > 0) {
            if (hamburger[idx] + level - idx <= layer) {
                break;
            }
        }
        return idx;
    }
}