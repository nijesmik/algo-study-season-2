import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_16974_레벨햄버거 {
    static long[] burger,meat;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        burger = new long[n+1];
        meat = new long[n+1];
        burger[0] = meat[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            burger[i] = burger[i - 1] * 2 + 3;
            meat[i] = meat[i - 1] * 2 + 1;
        }
        System.out.println(search(n, x));
        br.close();
    }
    static long search(int n, long x) {
        if (n == 0) {
            if (x == 0) {
                return 0;
            }
            return 1;
        }

        if (x == 1) {
            return 0;
        }
        if (x >= burger[n]-1) {
            return meat[n];
        }
        if (x == burger[n] / 2 + 1) {
            return meat[n - 1] + 1;
        }
        if (x <= burger[n] / 2) {
            return search(n - 1, x - 1);
        }
        if (x > burger[n] / 2 + 1) {
            return meat[n - 1] + 1 + search(n - 1, x - (burger[n] / 2 + 1));
        }
        return 0;
    }

}
