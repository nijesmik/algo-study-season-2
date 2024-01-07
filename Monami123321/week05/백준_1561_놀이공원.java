import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1561_놀이공원 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        if (n <= m) {
            System.out.println(n);
            return;
        }

        int[] arr = new int[m];

        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }
        long start = 0;
        long end = n / m * max;

        while (end >= start) {
            long sum = 0;
            long mid = (start + end) / 2;
            for (int i = 0; i < m; i++) {
                sum += 1 + mid / arr[i];
            }
            if (sum >= n) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        start -= 1;
        for (int i = 0; i < m; i++) {
            n -= 1 + start / arr[i];
        }
        int ans = 0;
        start += 1;
        for (int i = 0; i < m; i++) {
            if (start % arr[i] == 0) {
                n--;
            }
            if (n == 0) {
                ans = i;
                break;
            }
        }
        System.out.println(ans+1);


        br.close();
    }
}
