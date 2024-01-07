import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14890_경사로 {

    static int N, L;
    static int ans;


    static void getRoad(int[] arr) {
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            if (Math.abs(arr[i] - arr[i + 1]) > 1) {
                return;
            }
            if (arr[i] == arr[i + 1]) {
                continue;
            } else if (arr[i] > arr[i + 1]) {
                if (i + L > N - 1) {
                    return;
                }
                for (int j = 1; j < L; j++) {
                    if (arr[i + j] != arr[i + j + 1]) {
                        return;
                    }
                }
                for (int j = 1; j <= L; j++) {
                    visited[i + j] = true;
                }

                i += L - 1;
            } else {
                if (i + 1 - L < 0) {
                    return;
                }

                for (int j = 0; j < L - 1; j++) {
                    if (arr[i - j] != arr[i - j - 1] || visited[i - j]) {
                        return;
                    }

                }
                if (visited[i + 1 - L]) {
                    return;
                }

            }

        }
        ans++;
        return;
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        ans = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            getRoad(map[i]);
            int[] tmp = new int[N];
            for (int j = 0; j < N; j++) {
                tmp[j] = map[j][i];
            }
            getRoad(tmp);
        }

        System.out.println(ans);
        br.close();
    }
}
