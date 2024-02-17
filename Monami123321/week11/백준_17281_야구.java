import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_17281_야구 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] play = new int[n][9];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                play[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] tmp = new int[8];
        for (int i = 1; i <= 8; i++) {
            tmp[i - 1] = i;
        }
        int[] arr = new int[9]; // 1번타자 -> 0번 => 3번인덱스
        int ans = 0;
        do {


            for (int i = 0; i < 3; i++) {
                arr[i] = tmp[i];
            }
            for (int i = 0; i < 5; i++) {
                arr[8 - i] = tmp[7 - i];
            }

            // 순서 확정됨 계산 시작
            int index = 0;
            int sum = 0; // 실제 낸 점수
            for (int i = 0; i < n; i++) {
                int out = 0;
                int score = 0; // 그라운드 상황

                while (out < 3) {
                    if (play[i][arr[(index % 9)]] == 0) {
                        out++;
                    } else {
                        int[] res = hit(score, play[i][arr[(index % 9)]]);
                        sum += res[0];
                        score = res[1];
                    }
                    index++;
                }
            }
            ans = Math.max(ans, sum);
        } while (nextPermutation(tmp));
        System.out.println(ans);

        br.close();
    }

    static int[] hit(int score, int k) {
        int cnt = 0;
        for (int i = 1; i <= 3; i++) {
            if ((score & (1 << i)) != 0) {
                cnt++;
            }
        }
        if (k == 4) {
            return new int[]{cnt + 1, 0}; // 낸 점수, 현재 그라운드상황
        }
        score <<= k;
        score |= (1 << k);

        cnt = 0;
        for (int i = 4; i <= 6; i++) {
            if ((score & (1 << i)) != 0) {
                cnt++;
                score ^= (1 << i);
            }
        }
        return new int[]{cnt, score};
    }

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }
        if (i == 0) {
            return false;
        }
        int j = arr.length - 1;

        while (arr[i - 1] >= arr[j]) {
            j--;
        }
        swap(arr, i - 1, j);

        int k = arr.length - 1;
        while (k > i) {
            swap(arr, i++, k--);
        }
        return true;
    }

}
