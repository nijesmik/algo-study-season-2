package nijesmik.week13;
import java.util.*;

public class 세_수의_합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<Integer> set = new TreeSet<>((a, b) -> b - a);
        while (n-- > 0) {
            set.add(sc.nextInt());
        }
        Set<Integer> sum = new HashSet<>();
        for (int i : set) {
            for (int j : set) {
                sum.add(i + j);
            }
        }
        for (int i : set) {
            for (int j : set) {
                if (sum.contains(i - j)) {
                    System.out.println(i);
                    return;
                }
            }
        }
    }
}