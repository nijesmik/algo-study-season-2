import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_3151_합이0 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        long ans = 0;
        for (int i = 0; i < n - 2; i++) {
            int target = -arr[i];
            int left = i + 1;
            int right = n - 1;

            while (right > left) {
                if (arr[right] + arr[left] == target) {
                    int rightCnt = 1;
                    int leftCnt = 1;
                    while (right > left && arr[right] == arr[right - 1]) {
                        rightCnt++;
                        right--;
                    }
                    while (left < right && arr[left] == arr[left + 1]) {
                        leftCnt++;
                        left++;
                    }
                    if (rightCnt == 1 && leftCnt == 1) {
                        ans++;
                        right--;
                        continue;
                    }
                    if (right == left) {
                        ans += (rightCnt * (rightCnt - 1)) >> 1;
                    } else {
                        ans += (rightCnt * leftCnt);
                        right--;
                    }
                } else if (arr[right] + arr[left] < target) {
                    left++;
                } else {
                    right--;
                }
            }

        }
        System.out.println(ans);
        br.close();
    }
}
