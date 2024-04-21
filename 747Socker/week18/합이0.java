import java.io.*;
import java.util.*;

public class 합이0 {
    static long cnt = 0L;
    static int N;
    static int[] arr;

    static void findCnt() {
        for (int i = 0; i < N-2; i++) {
            if(arr[i]>0) break;
            int start = i + 1;
            int end = N - 1;

            while (start < end) {
                int sum = arr[i] + arr[start] + arr[end];
                int sDup = 1;
                int eDup = 1;

                if (sum == 0) {
                    if(arr[start]==arr[end]){
                        int n = end - start + 1;
                        cnt += n * (n - 1) / 2;
                        break;
                    }
                    while(start + 1 < end && arr[start] == arr[start + 1]){
                        sDup++;
                        start++;
                    }
                    
                    while (end - 1 > start && arr[end] == arr[end - 1]) {
                        eDup++;
                        end--;
                    }
                    cnt += eDup * sDup;
                } else if (sum > 0) {
                    end--;
                } else {
                    start++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        findCnt();
        System.out.println(cnt);
    }
}
