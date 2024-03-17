import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_2295_세수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++) {
            tmp[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(tmp);
        int index = 0;
        int[] arr = new int[(n * (n + 1)) >> 1];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                arr[index++] = tmp[i] + tmp[j];
            }
        }
        Arrays.sort(arr);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (binarySearch(arr, tmp[i] - tmp[j])) {
                    System.out.println(tmp[i]);
                    return;
                }
            }
        }
        br.close();
    }

    static boolean binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (end >= start) {
            int mid = (start + end) / 2;

            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }
}
