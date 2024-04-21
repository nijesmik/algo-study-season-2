
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(bf.readLine());

        ans = 0;
        arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            if (arr[i] > 0) break;
            int start = i + 1;
            int end = N - 1;

            while (start < end) {
                int s = 1;
                int e = 1;

                int now = arr[i] + arr[start] + arr[end];

                if (now == 0) {
                    if (arr[start] == arr[end]) {
                        ans += comb(end - start + 1);
                        break;
                    }
                    while (start + 1 < end && arr[start] == arr[start+1]) {
                        s++;
                        start++;
                    }
                    while (start < end - 1 && arr[end] == arr[end-1]) {
                        e++;
                        end--;
                    }
                    ans += s * e;
                }
                if (now > 0) {
                    end -= 1;
                } else {
                    start += 1;
                }

            }

        }
        System.out.println(ans);

    }

    static int comb(int c) {
        return c * (c - 1) / 2;
    }
}
