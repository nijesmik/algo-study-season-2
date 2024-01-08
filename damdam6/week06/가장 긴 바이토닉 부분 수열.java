import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] dpInc = new int[N];  // 증가하는 부분 수열의 길이
        int[] dpDec = new int[N];  // 감소하는 부분 수열의 길이

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 증가하는 부분 수열 계산
        for (int i = 0; i < N; i++) {
            dpInc[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i] && dpInc[i] < dpInc[j] + 1) {
                    dpInc[i] = dpInc[j] + 1;
                }
            }
        }

        // 감소하는 부분 수열 계산
        for (int i = N - 1; i >= 0; i--) {
            dpDec[i] = 1;
            for (int j = N - 1; j > i; j--) {
                if (A[j] < A[i] && dpDec[i] < dpDec[j] + 1) {
                    dpDec[i] = dpDec[j] + 1;
                }
            }
        }

        // 가장 긴 바이토닉 부분 수열의 길이 계산
        int maxLength = 0;
        for (int i = 0; i < N; i++) {
            maxLength = Math.max(maxLength, dpInc[i] + dpDec[i] - 1);
        }

        System.out.println(maxLength);
    }
}
