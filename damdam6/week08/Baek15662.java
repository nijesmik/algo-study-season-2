package y24Jan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek16974 {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());
        char[][] gears = new char[T][8];
        for (int i = 0; i < T; i++) {
            gears[i] = bf.readLine().toCharArray();
        }

        int K = Integer.parseInt(bf.readLine());
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            // 회전 방향을 저장하는 배열
            int[] directions = new int[T];
            directions[gearNum] = direction;

            // 왼쪽 톱니바퀴 확인 및 회전 방향 설정
            for (int i = gearNum - 1; i >= 0; i--) {
                if (gears[i][2] != gears[i + 1][6]) {
                    directions[i] = -directions[i + 1];
                } else {
                    break;
                }
            }

            // 오른쪽 톱니바퀴 확인 및 회전 방향 설정
            for (int i = gearNum + 1; i < T; i++) {
                if (gears[i][6] != gears[i - 1][2]) {
                    directions[i] = -directions[i - 1];
                } else {
                    break;
                }
            }

            // 모든 톱니바퀴 회전
            for (int i = 0; i < T; i++) {
                if (directions[i] != 0) {
                    rotate(gears[i], directions[i]);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < T; i++) {
            if (gears[i][0] == '1') {
                result++;
            }
        }

        System.out.println(result);
    }

    // 톱니바퀴 회전 함수
    private static void rotate(char[] gear, int direction) {
        if (direction == 1) { // 시계 방향
            char temp = gear[7];
            for (int i = 7; i > 0; i--) {
                gear[i] = gear[i - 1];
            }
            gear[0] = temp;
        } else if (direction == -1) { // 반시계 방향
            char temp = gear[0];
            for (int i = 0; i < 7; i++) {
                gear[i] = gear[i + 1];
            }
            gear[7] = temp;
        }
    }
}
