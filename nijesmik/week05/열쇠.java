package nijesmik.week05;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 열쇠 {
    static int H, W;
    static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };
    static boolean[] hasKey;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcase = sc.nextInt();
        while (testcase-- > 0) {
            H = sc.nextInt() + 2;
            W = sc.nextInt() + 2;
            char[][] map = new char[H][W];
            for (int i = 1; i < H - 1; i++) {
                String row = sc.next();
                for (int j = 1; j < W - 1; j++) {
                    map[i][j] = row.charAt(j - 1);
                }
            }

            hasKey = new boolean[26];
            char[] keys = sc.next().toCharArray();
            for (char key : keys) {
                if (key == '0')
                    break;
                hasKey[key - 'a'] = true;
            }

            int ans = 0;
            Queue<Node> q = new LinkedList<>();
            boolean[][] visited = new boolean[H][W];
            q.add(new Node(0, 0));
            visited[0][0] = true;
            while (!q.isEmpty()) {
                Node cur = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i], nc = cur.c + dc[i];
                    if (nr < 0 || nr >= H || nc < 0 || nc >= W || visited[nr][nc] || isBlocked(map[nr][nc]))
                        continue;
                    char type = map[nr][nc];
                    if (type >= 'a' && type <= 'z') {
                        hasKey[type - 'a'] = true;
                        visited = new boolean[H][W];
                    } else if (type == '$') {
                        ans++;
                    }
                    map[nr][nc] = 0;
                    q.add(new Node(nr, nc));
                    visited[nr][nc] = true;
                }
            }
            System.out.println(ans);
        }
    }

    static boolean isBlocked(char type) {
        if (type == '*')
            return true;
        return (type >= 'A' && type <= 'Z' && !hasKey[type - 'A']);
    }

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
