import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_2110_공유기설치 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int end = arr[N - 1] - arr[0];
        int start = 0;


        while (start<=end) {
            int mid = (start + end) / 2;
            int cnt = 1;
            int prev = 0;

            for (int i = 1; i < N; i++) {
                if (arr[i] - arr[prev] >= mid) {
                    cnt++;
                    prev = i;
                }

            }

            if (cnt >= C) {
                start = mid + 1;
            } else if (cnt < C) {
                end = mid - 1;
            }


        }

        System.out.println(end);


    }

}
