package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 원판_돌리기 {
	static int N, M, T;
	static int[][] map;
	static boolean[][] visited;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			move(x, d, k);
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != -1) {
					result += map[i][j];
				}
			}
		}

		System.out.println(result);
	}

	static void move(int x, int d, int k) {
		int tmp = x;
		while (tmp - 1 < N) {
			if (d == 0) { //시계방향
				for (int j = 0; j < k; j++) {
					int temp = map[tmp - 1][M - 1];
					for (int i = M - 1; i >= 1; i--) {
						map[tmp - 1][i] = map[tmp - 1][i - 1];
					}
					map[tmp - 1][0] = temp;
				}
			} else if (d == 1) { //반시계방향
				for (int j = 0; j < k; j++) {
					int temp = map[tmp - 1][0];
					for (int i = 0; i < M - 1; i++) {
						map[tmp - 1][i] = map[tmp - 1][i + 1];
					}
					map[tmp - 1][M - 1] = temp;
				}
			}

			tmp += x;
		}

		flag = false;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] != -1) {
					check(i, j, map[i][j]);
				}
			}
		}

		if (!flag) {
			updateMap();
		}
	}

	static void updateMap() {
		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != -1) {
					sum += map[i][j];
					cnt++;
				}
			}
		}

		double avg = (double) (sum) / cnt;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != -1) {
					if (map[i][j] < avg) {
						map[i][j] += 1;
					} else if (map[i][j] > avg) {
						map[i][j] -= 1;
					}
				}
			}
		}
	}

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static void check(int x, int y, int val) {
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int px = x + dx[i];
			int py = y + dy[i];

			if (py < 0) {
				py = M - 1;
			} else if (py >= M) {
				py = 0;
			}

			if (0 <= px && px < N) {
				if (!visited[px][py] && map[px][py] == val) {
					flag = true;
					map[x][y] = -1;
					map[px][py] = -1;
					check(px, py, val);
				}
			}

		}

	}

	
}