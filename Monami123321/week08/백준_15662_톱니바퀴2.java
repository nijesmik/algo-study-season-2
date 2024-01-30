import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_15662_톱니바퀴2 {
    static class Wheel {
        Wheel left, right;
        int[] arr;
        int pointer;

        public Wheel(int[] arr) {
            this.arr = arr;
        }
    }

    static Wheel[] wheels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        wheels = new Wheel[t];

        // 0 - N극  1 - S극 1 시계방향 -1 반시계 방향
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine(), "01", true);
            int[] arr = new int[8];
            for (int j = 0; j < 8; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            wheels[i] = new Wheel(arr);
        }
        for (int i = 0; i < t - 1; i++) {
            wheels[i].right = wheels[i + 1];
            wheels[t - 1 - i].left = wheels[t - 1 - i - 1];
        }
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            int wheelNum = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());
            if (dir == -1) {
                dir = 0;
            }
            dfs(wheels[wheelNum],0,dir);
        }
        int ans = 0;
        for (int i = 0; i < t; i++) {
            if (wheels[i].arr[wheels[i].pointer] == 1) {
                ans++;
            }
        }
        System.out.println(ans);

        br.close();
    }

    static void dfs(Wheel wheel, int dir, int rotateDir) {
        // dir 어느 방향에서 옴? -1 1 rotateDir 어느 방향으로 돌 거? 시 1 반 0
        if (dir == 0) {
            // 왼쪽 확인
            if (wheel.left != null && wheel.arr[(wheel.pointer + 6) % 8] != wheel.left.arr[(wheel.left.pointer + 2) % 8]) {
                dfs(wheel.left, -1, rotateDir ^ 1);
            }
            // 오른쪽 확인
            if (wheel.right != null && wheel.arr[(wheel.pointer + 2) % 8] != wheel.right.arr[(wheel.right.pointer + 6) % 8]) {
                dfs(wheel.right, 1, rotateDir ^ 1);
            }
            // 돌리기
            if (rotateDir == 0) {
                wheel.pointer = (wheel.pointer + 1) % 8;
            } else {
                wheel.pointer = (wheel.pointer + 7) % 8;
            }

        } else if (dir == -1) {
            // 왼쪽 확인
            if (wheel.left != null && wheel.arr[(wheel.pointer + 6) % 8] != wheel.left.arr[(wheel.left.pointer + 2) % 8]) {
                dfs(wheel.left, -1, rotateDir ^ 1);
            }

            // 돌리기
            if (rotateDir == 0) {
                wheel.pointer = (wheel.pointer + 1) % 8;
            } else {
                wheel.pointer = (wheel.pointer + 7) % 8;
            }

        } else {
            // 오른쪽 확인
            if (wheel.right != null && wheel.arr[(wheel.pointer + 2) % 8] != wheel.right.arr[(wheel.right.pointer + 6) % 8]) {
                dfs(wheel.right, 1, rotateDir ^ 1);
            }
            // 돌리기
            if (rotateDir == 0) {
                wheel.pointer = (wheel.pointer + 1) % 8;
            } else {
                wheel.pointer = (wheel.pointer + 7) % 8;
            }

        }

    }
}
