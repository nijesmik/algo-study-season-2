package jechul;

import java.util.*;

class 벽_부수고_이동하기2 {
	static int arr[][];
	static int n;
	static int m;
	static int k;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][][] dist;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		arr = new int[n][m];
		dist = new int[n][m][k + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int m = 0; m < k + 1; m++) {
					dist[i][j][m] = -1;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			char[] temp = sc.next().toCharArray();
			for (int j = 0; j < m; j++) {
				arr[i][j] = temp[j] - '0';
			}
		}

		Queue<Point> q = new LinkedList<>();
		dist[0][0][0] = 1;
		q.add(new Point(0, 0, 0));
		while (!q.isEmpty()) {
			Point p = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				if (arr[nx][ny] == 0 && dist[nx][ny][p.z] == -1) {
					dist[nx][ny][p.z] = dist[p.x][p.y][p.z] + 1;
					q.add(new Point(nx, ny, p.z));
				}
				if (arr[nx][ny] == 1 && p.z + 1 <= k && dist[nx][ny][p.z + 1] == -1) {
					dist[nx][ny][p.z + 1] = dist[p.x][p.y][p.z] + 1;
					q.add(new Point(nx, ny, p.z + 1));
				}
			}
		}

		for (int i = 0; i <= k; i++) {
			if (dist[n - 1][m - 1][i] != -1) {
				ans = Math.min(ans, dist[n - 1][m - 1][i]);
			}
		}
		if(ans != Integer.MAX_VALUE) System.out.println(ans);
		else System.out.println(-1);
	}

	static class Point {
		int x, y, z;

		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

}