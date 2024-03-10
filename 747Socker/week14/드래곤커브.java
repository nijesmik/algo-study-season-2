package jechul;

import java.util.ArrayList;
import java.util.Scanner;

public class 드래곤커브 {
    static int[] dx = {1, 0, -1, 0}; 
    static int[] dy = {0, -1, 0, 1}; 
    static boolean[][] map = new boolean[101][101]; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); 

        for (int i = 0; i < N; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int d = scanner.nextInt();
            int g = scanner.nextInt();
            dragonCurve(x, y, d, g);
        }

        System.out.println(doCount()); 
    }

    private static void dragonCurve(int x, int y, int direction, int gen) {
        ArrayList<Integer> directions = new ArrayList<>();
        directions.add(direction); 

        for (int i = 0; i < gen; i++) {
            int size = directions.size();
            for (int j = size - 1; j >= 0; j--) {
                directions.add((directions.get(j) + 1) % 4); 
            }
        }

        
        map[y][x] = true; 
        for (int dir : directions) {
            x += dx[dir];
            y += dy[dir];
            map[y][x] = true;
        }
    }

    private static int doCount() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
                    count++; 
                }
            }
        }
        return count;
    }
}

