package nijesmik.week14;

import java.util.*;

public class 드래곤_커브 {
	public static void main(String[] args) {
		int[] dr = {0, -1, 0, 1}, dc = {1, 0, -1, 0};
		boolean[][] board = new boolean[101][101];

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while (n-- > 0) {
			int y = sc.nextInt(), x = sc.nextInt(), d = sc.nextInt(), gen = sc.nextInt();
			board[x][y] = true;
			Deque<Integer> prevCurve = new ArrayDeque<>();
			x += dr[d];
			y += dc[d];
			board[x][y] = true;
			prevCurve.addLast(d);
			for (int g = 1; g <= gen; g++) {
				Deque<Integer> curCurve = new ArrayDeque<>();
				while (!prevCurve.isEmpty()) {
					int prevDir = prevCurve.pollLast();
					int nextDir = (prevDir + 1) % 4;
					x += dr[nextDir];
					y += dc[nextDir];
					if (x >= 0 && x <= 100 && y >= 0 && y <= 100) {
						board[x][y] = true;
					}
					curCurve.addFirst(prevDir);
					curCurve.addLast(nextDir);
				}
				prevCurve = curCurve;
			}
		}
		int ans = 0;
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				if (board[i][j] && board[i - 1][j] && board[i][j - 1] && board[i - 1][j - 1]) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
}