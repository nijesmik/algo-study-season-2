import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14921_용액합성하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int start = 0;
        int end = n - 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int ans = Integer.MAX_VALUE;
        while (end > start) {
            if (Math.abs(ans) > Math.abs(arr[start] + arr[end])) {
                ans = arr[start] + arr[end];
            }
            if (arr[start] + arr[end] > 0) {
                end--;
            } else if (arr[start] + arr[end] < 0) {
                start++;
            } else {
                break;
            }
        }
        System.out.println(ans);
        br.close();
    }
}
