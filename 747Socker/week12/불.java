package jechul;

import java.util.*;

public class 불 {
    static int R, C;
    static char[][] maze;
    static int[][] fireTime;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int res;
    static Position pos;
    
    static class Position {
        int x;
        int y;
        int time;

        Position(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        R = scanner.nextInt();
        C = scanner.nextInt();
        maze = new char[R][C];
        fireTime = new int[R][C];
        visited = new boolean[R][C];

        for (int[] row : fireTime) Arrays.fill(row, Integer.MAX_VALUE); 

        Queue<Position> fireQueue = new LinkedList<>();


        for (int i = 0; i < R; i++) {
            String str = scanner.next();
            for (int j = 0; j < C; j++) {
                maze[i][j] = str.charAt(j);
                if (maze[i][j] == 'J') {
                    pos = new Position(j, i, 0);
                } else if (maze[i][j] == 'F') {
                    fireQueue.offer(new Position(j, i, 0));
                    fireTime[i][j] = 0; 
                }
            }
        }

        fire(fireQueue);
 
    }

    public static void fire(Queue<Position> queue) {
        while (!queue.isEmpty()) {
            Position current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= C || ny < 0 || ny >= R || maze[ny][nx] == '#') {
                    continue;
                }

                if (fireTime[ny][nx] > current.time + 1) {
                    fireTime[ny][nx] = current.time + 1;
                    queue.offer(new Position(nx, ny, current.time + 1));
                }
            }
        }
        
        res = escape(pos);

        if (res == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(res);
        }
    }

    public static int escape(Position pos) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(pos);
        visited[pos.y][pos.x] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (current.x == 0 || current.x == C-1 || current.y == 0 || current.y == R-1) {
                return current.time + 1; 
            }

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= C || ny < 0 || ny >= R || visited[ny][nx] || maze[ny][nx] == '#' || current.time + 1 >= fireTime[ny][nx]) {
                    continue;
                }

                visited[ny][nx] = true;
                queue.offer(new Position(nx, ny, current.time + 1));
            }
        }

        return -1; 
    }
}
