package jechul;
import java.util.Scanner;

public class 테트로미노 {

	    static int[][] dr = {{0, 0, 0}, {1, 2, 3}, {0, 1, 1}, {1, 2, 2}, {0, 0, -1}, {0, 1, 2}, {-1, -1, -1}, {0, -1, -2}, {1, 1, 1},
	            {0, 1, 2}, {0, 0, 1}, {1, 1, 2}, {0, -1, -1}, {1, 1, 2}, {0, 1, 1}, {0, 0, 1}, {1, 1, 2}, {0, -1, 0}, {-1, 0, 1}};
	    static int[][] dc = {{1, 2, 3}, {0, 0, 0}, {1, 0, 1}, {0, 0, 1}, {1, 2, 2}, {1, 1, 1}, {0, 1, 2}, {1, 1, 1}, {0, 1, 2},
	            {1, 0, 0}, {1, 2, 2}, {0, 1, 1}, {1, 1, 2}, {0, -1, -1}, {1, 1, 2}, {1, 2, 1}, {0, 1, 0}, {1, 1, 2}, {1, 1, 1}};
	    static int l = dr.length;

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        int N = scanner.nextInt();
	        int M = scanner.nextInt();

	        int[][] graph = new int[N][M];
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < M; j++) {
	                graph[i][j] = scanner.nextInt();
	            }
	        }

	        int ans = 0;
	        for (int r = 0; r < N; r++) {
	            for (int c = 0; c < M; c++) {
	                ans = Math.max(ans, dfs(r, c, N, M, graph));
	            }
	        }
	        System.out.println(ans);
	    }

	    static int dfs(int r, int c, int N, int M, int[][] graph) {
	        int maxCnt = 0;
	        for (int i = 0; i < l; i++) {
	            int cnt = graph[r][c];
	            for (int j = 0; j < 3; j++) {
	                int nr = r + dr[i][j];
	                int nc = c + dc[i][j];
	                if (!(M > nc && nc >= 0 && 0 <= nr && nr < N)) {
	                    cnt = 0;
	                    break;
	                } else {
	                    cnt += graph[nr][nc];
	                }
	            }
	            maxCnt = Math.max(cnt, maxCnt);
	        }
	        return maxCnt;
	    }
	}
