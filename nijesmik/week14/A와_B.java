package nijesmik.week14;
import java.util.Scanner;

public class A와_B {
    static String origin;
    static char[] target;
    static int len;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        origin = sc.next();
        target = sc.next().toCharArray();
        len = target.length;
        while (len > origin.length()) {
            if (target[len-- - 1] == 'B') {
                char[] reverse = new char[len];
                for (int i = 0; i < len; i++) {
                    reverse[i] = target[len - 1 - i];
                }
                target = reverse;
            }
        }
        System.out.println(answer());
    }

    static int answer() {
        for (int i = 0; i < len; i++) {
            if (target[i] != origin.charAt(i)) {
                return 0;
            }
        }
        return 1;
    }
}
