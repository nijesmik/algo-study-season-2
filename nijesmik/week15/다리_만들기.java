import java.util.*;

public class 다리_만들기 {
	static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };
	static int N, map[][], id = 2;
	static boolean[][] visited;
	static Queue<Record> q;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					visited = new boolean[N][N];
					dfs(i, j);
					id++;
				}
			}
		}
		while (!q.isEmpty()) {
			Record cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i], nc = cur.c + dc[i];
				if (!cur.isVisitable(nr, nc)) {
					continue;
				}
				if (map[nr][nc] > 0) {
					System.out.println(cur.dist);
					return;
				}
				cur.visited[nr][nc] = true;
				q.add(new Record(nr, nc, cur.dist + 1, cur.visited));
			}
		}
	}

	static void dfs(int r, int c) {
		if (!isInRange(r, c)) {
			return;
		}
		visited[r][c] = true;
		if (map[r][c] == 0) {
			q.add(new Record(r, c, 1, visited));
		} else if (map[r][c] == 1) {
			map[r][c] = id;
			for (int i = 0; i < 4; i++) {
				dfs(r + dr[i], c + dc[i]);
			}
		}
	}

	static boolean isInRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}

	static class Record {
		int r, c, dist;
		boolean[][] visited;

		Record(int r, int c, int dist, boolean[][] visited) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.visited = visited;
		}

		boolean isVisitable(int r, int c) {
			return (isInRange(r, c) && !this.visited[r][c]);
		}
	}
}