package nijesmik.week12;
import java.util.*;

public class Main {
    static int sizeR, sizeC;
    static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };
    static char[][] map;
    static boolean[][] visited;
    static Queue<Node> fire, human;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sizeR = sc.nextInt();
        sizeC = sc.nextInt();
        map = new char[sizeR][sizeC];
        visited = new boolean[sizeR][sizeC];
        fire = new LinkedList<>();
        human = new LinkedList<>();
        for (int i = 0; i < sizeR; i++) {
            String row = sc.next();
            for (int j = 0; j < sizeC; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == 'F') {
                    fire.add(new Node(i, j));
                } else if (map[i][j] == 'J') {
                    map[i][j] = '.';
                    visited[i][j] = true;
                    human.add(new Node(i, j));
                }
            }
        }
        System.out.println(bfs());
    }

    static String bfs() {
        int time = 1;
        while (!human.isEmpty()) {
            int size = fire.size();
            while (size-- > 0) {
                Node cur = fire.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i], nc = cur.c + dc[i];
                    if (nr >= 0 && nr < sizeR && nc >= 0 && nc < sizeC && map[nr][nc] == '.') {
                        map[nr][nc] = 'F';
                        fire.add(new Node(nr, nc));
                    }
                }
            }
            size = human.size();
            while (size-- > 0) {
                Node cur = human.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i], nc = cur.c + dc[i];
                    if (nr < 0 || nr >= sizeR || nc < 0 || nc >= sizeC) {
                        return "" + time;
                    } else if (map[nr][nc] == '.' && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        human.add(new Node(nr, nc));
                    }
                }
            }
            time++;
        }
        return "IMPOSSIBLE";
    }

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}