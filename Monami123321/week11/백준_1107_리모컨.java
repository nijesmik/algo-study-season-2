import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1107_리모컨 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean[] buttons = new boolean[10];
        if (m != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                buttons[Integer.parseInt(st.nextToken())] = true;
            }
        }
        int len = 0;
        if (n <= 100) {
            len = 100;
        } else {
            len = 2 * n - 100;
        }
        int[] arr = new int[len + 1];
        boolean[] visited = new boolean[len + 1];
        outer:
        for (int i = 0; i < len+1; i++) {
            int now = i;
            int cnt = 1;
            while (now / 10 > 0) {
                if (buttons[(now % 10)]) {
                    visited[i] = true;
                    continue outer;
                }
                now /= 10;
                cnt++;
            }
            if (buttons[(now % 10)]) {
                visited[i] = true;
                continue outer;
            }
            arr[i] = cnt + Math.abs(n - i);
        }
        int ans = Math.abs(n - 100);
        for (int i = 0; i <= len; i++) {
            if (visited[i]) {
                continue;
            }
            ans = Math.min(ans, arr[i]);
        }
        System.out.println(ans);
        br.close();
    }
}
