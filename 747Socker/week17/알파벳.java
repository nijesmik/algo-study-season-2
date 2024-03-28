package ns;

import java.util.Scanner;

public class 알파벳 {
    static int R, C;
    static char[][] map;
    static boolean[] visited = new boolean[26]; 
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int max = 0;

    static void dfs(int x, int y, int depth) {
        int index = map[x][y] - 'A'; 
        if(visited[index]) return; 
        
        visited[index] = true; 
        max = Math.max(max, depth); 
        
        for(int d = 0; d < 4; d++) { 
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
                dfs(nx, ny, depth + 1);
            }
        }
        
        visited[index] = false; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];

        for(int i = 0; i < R; i++) {
            String row = sc.next();
            for(int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);
            }
        }

        dfs(0, 0, 1); 
        System.out.println(max); 
        sc.close();
    }
}
