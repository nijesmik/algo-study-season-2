package nijesmik.week18;
import java.util.*;

public class 합이_0 {
    static int n, students[], arr[], ans;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        students = new int[n];
        for (int i = 0; i < n; i++) {
            students[i] = sc.nextInt();
        }
        Arrays.sort(students);
        arr = new int[2];
        ans = 0;
        permutation(0, 0);
        System.out.println(ans);
    }

    static void permutation(int idx, int depth) {
        if (depth == 2) {
            if (Arrays.binarySearch(Arrays.copyOfRange(students, arr[1], n), (students[arr[0]] + students[arr[1]] * -1)) >= 0) {
                ans++;
            }
            return;
        }

        for (int i = idx; i < n; i++) {
            arr[depth] = i;
            permutation(i + 1, depth + 1);
        }
    }
}