import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_1377_버블소트_카운팅 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] count = new int[1_000_001];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            count[arr[i]]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int ans = 0;
        for (int i = arr.length-1; i >= 0; i--) {
            int tmp = i - --count[arr[i]];
            ans = Math.max(ans, tmp);
        }
        System.out.println(ans + 1);


    }
}
