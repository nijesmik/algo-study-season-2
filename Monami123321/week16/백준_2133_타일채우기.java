import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 백준_2133_타일채우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if ((n & 1) != 0) {
            System.out.println(0);
            return;
        }
        if (n == 2) {
            System.out.println(3);
            return;
        } else if (n == 4) {
            System.out.println(11);
            return;
        }
        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[2] = 3;
        arr[4] = 11;
        for (int i = 6; i <= n; i += 2) {
            arr[i] = arr[i - 2] * 3 + 2;
            for (int j = i - 4; j >= 0; j -= 4) {
                arr[i] += arr[j] << 1;
            }
        }
        System.out.println(arr[n]);
        br.close();
    }
}
