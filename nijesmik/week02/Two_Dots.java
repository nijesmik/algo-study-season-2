package nijesmik.week02;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Two_Dots {
    static int R, C;
    static char[][] board;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        board = new char[R][];
        for (int i = 0; i < R; i++) {
            board[i] = sc.next().toCharArray();
        }
        System.out.println(answer());
    }

    static String answer() {
        for (int i = 0; i < R - 1; i++) {
            for (int j = 0; j < C - 1; j++) {
                if (bfs(i, j, board[i][j])) {
                    return "Yes";
                }
            }
        }
        return "No";
    }

    static boolean bfs(int i, int j, char ch) {
        boolean[][] visited = new boolean[R][C];
        visited[i][j] = true;
        if (board[i+1][j] != ch || board[i][j+1] != ch) {
            return false;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j+1});
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int k = 0; k < 4; k++) {
                int nr = cur[0] + dr[k], nc = cur[1] + dc[k];
                if (nr == i+1 && nc == j) {
                    return true;
                }
                if (0 <= nr && nr < R && 0 <= nc && nc < C && board[nr][nc] == ch && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}
