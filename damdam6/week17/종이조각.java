import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = bf.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }


        int max = 0;
        // 비트 마스킹
        int size = (1 << (N * M));
        for (int idx = 0; idx < size; idx++) {

            int sum = 0;
            int order;
            for (int i = 0; i < N; i++) { 
                order = 1;
                for (int j = M - 1; j >= 0; j--) {
                    int num = j + i * M;
                    if ((idx & (1 << num)) > 0) {
                        sum += (map[i][j] * order);
                        order *= 10;
                    } else {
                        order = 1;
                    }
                }
            }
            for (int j = 0; j < M; j++) { 
                order = 1;
                for (int i = N - 1; i >= 0; i--) {
                    int num = j + i * M;
                    if ((idx & (1 << num)) == 0) {
                        sum += (map[i][j] * order);
                        order *= 10;
                    } else {
                        order = 1;
                    }
                }
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}