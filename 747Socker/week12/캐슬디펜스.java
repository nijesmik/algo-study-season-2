package jechul;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 캐슬디펜스 {
    static int N; 
    static int M; 
    static int D;
    static int[][] board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        D = scanner.nextInt();

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        System.out.println(findMax());
    }

    private static int findMax() {
        int maxCount = 0;
        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                for (int k = j + 1; k < M; k++) {
                    maxCount = Math.max(maxCount, kill(new int[]{i, j, k}));
                }
            }
        }
        return maxCount;
    }

    private static int kill(int[] arrs) {
    	
        Set<String> enemies = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) enemies.add(i + "," + j);
            }
        }

        int count = 0;
        while (!enemies.isEmpty()) {
            Set<String> targets = new HashSet<>();
            for (int arr : arrs) {
                String target = null;
                int minDistance = D + 1;
                for (String enemyPos : enemies) {
                    int x = Integer.parseInt(enemyPos.split(",")[0]);
                    int y = Integer.parseInt(enemyPos.split(",")[1]);
                    int distance = Math.abs(N - x) + Math.abs(arr - y);
                    if (distance <= D && (target == null || distance < minDistance ||
                        (distance == minDistance && y < Integer.parseInt(target.split(",")[1])))) {
                        target = x + "," + y;
                        minDistance = distance;
                    }
                }
                if (target != null) targets.add(target);
            }

            for (String t : targets) enemies.remove(t);
            count += targets.size();

            Set<String> moved = new HashSet<>();
            for (String e : enemies) {
                int x = Integer.parseInt(e.split(",")[0]);
                int y = Integer.parseInt(e.split(",")[1]);
                if (x + 1 < N) moved.add((x + 1) + "," + y);
            }
            enemies = moved;
        }

        return count;
    }
}
