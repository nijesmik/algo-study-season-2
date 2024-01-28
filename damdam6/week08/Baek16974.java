import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LevelBurger {
    private static long[] layerCount;
    private static long[] pattyCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());

        layerCount = new long[N + 1];
        pattyCount = new long[N + 1];

        // 초기 값 설정
        layerCount[0] = 1; // 레벨 0 버거의 총 레이어 수
        pattyCount[0] = 1; // 레벨 0 버거의 패티 수

        // DP를 통해 각 레벨의 총 레이어 수와 패티 수 계산
        for (int i = 1; i <= N; i++) {
            layerCount[i] = layerCount[i - 1] * 2 + 3;
            pattyCount[i] = pattyCount[i - 1] * 2 + 1;
        }

        System.out.println(getPattyCount(N, X));
    }

    private static long getPattyCount(int n, long x) {
        if (n == 0) {
            return 1;
        }
        if (x == 1) {
            return 0;
        }

        long half = layerCount[n] / 2 + 1;

        if (x < half) {
            return getPattyCount(n - 1, x - 1);
        } else if (x == half) {
            return pattyCount[n - 1] + 1;
        } else {
            return pattyCount[n - 1] + 1 + getPattyCount(n - 1, x - half);
        }
    }
}
