import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1790_수이어쓰기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        int cnt = 1; // 몇 자리수
        long num = 9; // 개수는?

        while (k - cnt * num >= 0) {
            k -= cnt * num;
            cnt++;
            num *= 10;

        }

        int start = (int) Math.pow(10, cnt - 1);

        if (k == 0) {
            if (start - 1 > n) {
                System.out.println(-1);
                return;
            }
            String tmp = String.valueOf(start - 1);
            System.out.println(tmp.charAt(tmp.length() - 1));
            return;
        }
        k--;
        start += (k / cnt);
        if (start > n) {
            System.out.println(-1);
            return;
        }
        int index = (int) k % cnt;
        System.out.println(String.valueOf(start).charAt(index));


        br.close();
    }
}
