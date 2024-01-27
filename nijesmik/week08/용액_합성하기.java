import java.util.Arrays;
import java.util.Scanner;

public class 용액_합성하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), arr[] = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int start = 0, end = N - 1;
        int ans = arr[start] + arr[end];
        while (start < end) {
            int sum = arr[start] + arr[end];
            if (Math.abs(sum) < Math.abs(ans)) {
                ans = sum;
            }
            if (sum < 0) {
                start++;
            } else if (sum > 0) {
                end--;
            } else {
                break;
            }
        }
        System.out.println(ans);
    }
}
