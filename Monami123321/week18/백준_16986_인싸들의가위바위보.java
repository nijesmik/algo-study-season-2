import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_16986_인싸들의가위바위보 {
    static class Player {
        int[] actions;
        int id, win, index;

        public Player(int[] actions, int id) {
            this.actions = actions;
            this.id = id;
        }
    }

    static int[][] map;
    static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 동작 갯수
        k = Integer.parseInt(st.nextToken()); // 목표 승수
        if (k > n) {
            System.out.println(0);
            return;
        }
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int[] action1 = new int[20];
        for (int i = 0; i < 20; i++) {
            action1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] action2 = new int[20];
        for (int i = 0; i < 20; i++) {
            action2[i] = Integer.parseInt(st.nextToken());
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        Player p0 = new Player(arr, 0);
        Player p1 = new Player(action1, 1);
        Player p2 = new Player(action2, 2);
        do {
            p0.win = 0;
            p1.win = 0;
            p2.win = 0;
            p0.index = 0;
            p1.index = 0;
            p2.index = 0;
            if (play(p0, p1, p2)) {
                System.out.println(1);
                return;
            }
        } while (nextPermutation(arr));
        System.out.println(0);
        br.close();
    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) {
            --i;
        }
        if (i == 0) {
            return false;
        }
        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j]) {
            --j;
        }
        swap(arr, i - 1, j);
        int k = arr.length - 1;
        while (k > i) {
            swap(arr, i++, k--);
        }
        return true;
    }

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static boolean play(Player p0, Player p1, Player p2) {
        Queue<Player> queue = new LinkedList<>();
        queue.add(p0);
        queue.add(p1);
        queue.add(p2);

        while (p0.index < n) {
            Player first = queue.poll();
            Player second = queue.poll();
            int firstAction = first.actions[first.index++];
            int secondAction = second.actions[second.index++];
            // 비김 -> id순으로
            if (firstAction == secondAction) {
                if (first.id > second.id) {
                    first.win++;
                    queue.add(first);
                    queue.add(second);
                } else {
                    second.win++;
                    queue.add(second);
                    queue.add(first);
                }
            } else {
                // first가 진 경우
                if (map[firstAction - 1][secondAction - 1] == 0) {
                    second.win++;
                    queue.add(second);
                    queue.add(first);
                } else {
                    first.win++;
                    queue.add(first);
                    queue.add(second);
                }
            }
            if (p0.win == k) {
                return true;
            } else if (p1.win == k) {
                return false;
            } else if (p2.win == k) {
                return false;
            }
        }
        return false;
    }


}
