import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 세_수의_합 {
    static int N, answer;
    static int[] nums;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        answer = -1;
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int[] sum = new int[N*N];
        int idx = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sum[idx++] = nums[i] + nums[j];
            }
        }

        Arrays.sort(sum);
        for(int i =0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(binarySearch(sum, nums[i]-nums[j])) {
                    answer = Math.max(answer, nums[i]);
                }
            }
        }
        System.out.println(answer);
    }

    static boolean binarySearch(int[] arr, int value) {
        int l = 0;
        int r = arr.length -1;
        while(l<=r) {
            int m = (l+r) / 2;
            if(arr[m] > value) {
                r = m-1;
            } else if( arr[m] < value) {
                l = m+1;
            } else return true;
        }
        return false;
    }
}
