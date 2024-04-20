import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_16973_직사각형탈출 {
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		List<int[]> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					list.add(new int[] {i, j});
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int startR = Integer.parseInt(st.nextToken()) - 1;
		int startC = Integer.parseInt(st.nextToken()) - 1;
		int endR = Integer.parseInt(st.nextToken()) - 1;
		int endC = Integer.parseInt(st.nextToken()) - 1;

		list.forEach(e -> {
			int r = e[0];
			int c = e[1];
			for (int i = r - (h - 1); i <= r; i++) {
				for (int j = c - (w - 1); j <= c; j++) {
					if (i < 0 || i > n - 1 || j < 0 || j > m - 1) {
						continue;
					}
					map[i][j] = 1;
				}
			}
		});
		// for (int i = 0; i < n; i++) {
		// 	for (int j = 0; j < m; j++) {
		// 		System.out.printf("%d ",map[i][j]);
		// 	}
		// 	System.out.println();
		// }
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {startR, startC, 0});
		int ans = -1;
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int r = now[0];
			int c = now[1];
			int cost = now[2];
			// System.out.printf("%d %d\n",r,c);
			if (r == endR && c == endC) {
				ans = cost;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nr > n - 1 - (h - 1) || nc < 0 || nc > m - 1 - (w - 1) || map[nr][nc] != 0) {
					continue;
				}
				map[nr][nc] = 1;
				queue.add(new int[] {nr, nc, cost + 1});
			}
		}
		System.out.println(ans);
		br.close();
	}
}
