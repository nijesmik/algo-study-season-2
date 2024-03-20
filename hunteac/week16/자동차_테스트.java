import java.io.*;
import java.util.*;

public class 자동차_테스트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(nums);

        for (int i = 0; i < q; i++) {
            int target = Integer.parseInt(br.readLine());
            int before = 0; // 타겟보다 작은 수 개수
            int after = 0; // 타겟보다 큰 수 개수

            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == target) {
                    after = nums.length - (j + 1);
                    break;
                }

                before++;
            }

            sb.append(before * after).append("\n"); // 중앙값 경우의 수
        }

        System.out.println(sb);
    }
}
