package nijesmik.week17;
import java.util.Scanner;

public class 종이_조각 {
    static int sizeR, sizeC, board[][], max;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sizeR = sc.nextInt();
        sizeC = sc.nextInt();
        board = new int[sizeR][sizeC];
        for (int i = 0; i < sizeR; i++) {
            String input = sc.next();
            for (int j = 0; j < sizeC; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }
        visited = new boolean[sizeR][sizeC];
        max = 0;
        dfs(0);
        System.out.println(max);
    }

    static void dfs(int depth) {
        if (depth == sizeR * sizeC) {
            calculate();
            return;
        }

        int r = depth / sizeC;
        int c = depth % sizeC;
        visited[r][c] = true;
        dfs(depth + 1);
        visited[r][c] = false;
        dfs(depth + 1);
    }

    static void calculate() {
        int total = 0;
        for (int i = 0; i < sizeR; i++) {
            int sum = 0;
            for (int j = 0; j < sizeC; j++) {
                if (visited[i][j]) {
                    sum = sum * 10 + board[i][j];
                } else {
                    total += sum;
                    sum = 0;
                }
            }
            total += sum;
        }
        for (int j = 0; j < sizeC; j++) {
            int sum = 0;
            for (int i = 0; i < sizeR; i++) {
                if (!visited[i][j]) {
                    sum = sum * 10 + board[i][j];
                } else {
                    total += sum;
                    sum = 0;
                }
            }
            total += sum;
        }
        max = Math.max(max, total);
    }
}