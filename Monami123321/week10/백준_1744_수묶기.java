import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_1744_수묶기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        if (n == 1) {
            System.out.println(arr[0]);
            return;
        }
        Arrays.sort(arr);
        int start = 0;
        int mid = 0;
        int end = arr.length - 1;
        while (mid <= end && arr[mid] <= 0) {
            mid++;
        }
        int sum = 0;
        while (start < end && start < mid) {
            if (arr[start] + arr[start + 1] < arr[start] * arr[start + 1]) {
                sum += arr[start] * arr[start + 1];
                start += 2;
            } else {
                sum += arr[start++];
            }
        }
        if (mid >= end) {
            if (start == mid) {
                System.out.println(sum + arr[mid]);
                return;
            } else {
                System.out.println(sum);
                return;
            }
        }
        while (end > 0 && end > start) {
            if (arr[end] + arr[end - 1] < arr[end] * arr[end - 1]) {
                sum += arr[end] * arr[end - 1];
                end -= 2;
            } else {
                sum += arr[end--];
            }
        }
        if (start == end) {
            sum += arr[end];
        }
        System.out.println(sum);
        br.close();
    }
}
