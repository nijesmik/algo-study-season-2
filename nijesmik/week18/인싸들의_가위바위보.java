import java.util.*;

public class 인싸들의_가위바위보 {
    static int n, k;
    static int[][] gestures, scorecard;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        scorecard = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                scorecard[i][j] = sc.nextInt();
            }
        }
        gestures = new int[3][20];
        for (int i = 1; i < n; i++) {
            gestures[0][i] = i;
        }
        for (int i = 0; i < 20; i++) {
            gestures[1][i] = sc.nextInt() - 1;
        }
        for (int i = 0; i < 20; i++) {
            gestures[2][i] = sc.nextInt() - 1;
        }
        System.out.println(testAll());
    }

    static int testAll() {
        do {
            if (testOne() == 0) {
                return 1;
            }
        } while (nextPermutation(gestures[0]));
        return 0;
    }

    static int testOne() {
        Player[] playerInfo = new Player[3];
        for (int i = 0; i < 3; i++) {
            playerInfo[i] = new Player(i);
        }
        int[] roundInfo = { 0, 1 };
        while (playerInfo[0].round < n) {
            int winner = getWinner(playerInfo[roundInfo[0]], playerInfo[roundInfo[1]]);
            if (playerInfo[winner].win() == k) {
                return winner;
            }
            roundInfo[0] = 3 - roundInfo[0] - roundInfo[1];
            roundInfo[1] = winner;
        }
        return -1;
    }

    static boolean nextPermutation(int[] arr) {
        int top = n - 1;
        while (top > 0 && arr[top - 1] > arr[top]) {
            top--;
        }
        if (top == 0) {
            return false;
        }
        int target = n - 1;
        while (arr[top - 1] > arr[target]) {
            target--;
        }
        swap(arr, top - 1, target);
        target = n - 1;
        while (top < target) {
            swap(arr, top++, target--);
        }
        return true;
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static int getWinner(Player first, Player second) {
        int result = scorecard[first.gesture()][second.gesture()];
        if (result == 2 || (result == 1 && first.id > second.id)) {
            return first.id;
        }
        return second.id;
    }

    static class Player {
        int id, round, score;

        Player(int id) {
            this.id = id;
        }

        int win() {
            return ++score;
        }

        int gesture() {
            return gestures[id][round++];
        }
    }
}
