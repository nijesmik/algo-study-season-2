package bj;
import java.util.*;
 
public class 원자소멸시뮬레이터 {
 
    private static class Node {
        int x, y, dir, value;
 
        Node(int x, int y, int dir, int value) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.value = value;
        }
    }
 
    static int size, result;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int[][] map = new int[4002][4002];
    static Queue<Node> q = new LinkedList<>();
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
 
        for (int t = 1; t <= T; t++) {
            size = sc.nextInt();
            result = 0;
            for (int i = 0; i < size; i++) {
                int y = (sc.nextInt() + 1000) << 1;
                int x = 4000 - ((sc.nextInt() + 1000) << 1);
                int dir = sc.nextInt();
                int value = sc.nextInt();
 
                map[x][y] = value;
                q.add(new Node(x, y, dir, value));
            }
             
            bfs();
            System.out.println("#" + t + " " + result);
        }
    }
 
    private static void bfs() {
 
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (map[n.x][n.y] != n.value) {
                result += map[n.x][n.y];
                map[n.x][n.y] = 0;
                continue;
            }
 
            int nx = n.x + dx[n.dir];
            int ny = n.y + dy[n.dir];
 
            if (nx >= 0 && ny >= 0 && nx <= 4000 && ny <= 4000) {
                if (map[nx][ny] == 0) {
                    map[nx][ny] = n.value;
                    q.add(new Node(nx, ny, n.dir, n.value));
                } else {
                    map[nx][ny] += n.value;
                }
            }
            map[n.x][n.y] = 0;
        }
    }
}