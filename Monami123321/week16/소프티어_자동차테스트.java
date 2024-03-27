import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 소프티어_자동차테스트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        for (int i = 0; i < q; ++i) {
            int target = Integer.parseInt(br.readLine());
            int res = binarySearch(arr, target);
            if (res == -1) {
                sb.append(0).append("\n");
                continue;
            }
            sb.append((res) * (arr.length - res - 1)).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    static int binarySearch(int[] arr, int n) {
        int len = arr.length - 1;
        int start = 0;
        int end = len;
        while (end >= start) {
            int mid = (start + end) / 2;
            if (mid < 0 || mid > len) {
                return -1;
            }
            if (arr[mid] == n) {
                return mid;
            } else if (arr[mid] > n) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
