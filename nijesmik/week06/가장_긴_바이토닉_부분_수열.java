import java.util.Scanner;

public class 가장_긴_바이토닉_부분_수열 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int[] increased = new int[N];
        int[] decreased = new int[N];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    increased[i] = Math.max(increased[i], increased[j] + 1);
                }
                if (arr[N - 1 - i] > arr[N - 1 - j]) {
                    decreased[N - 1 - i] = Math.max(decreased[N - 1 - i], decreased[N - 1 - j] + 1);

                }
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, increased[i] + decreased[i] + 1);
        }
        System.out.println(max);
    }
}
