package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Shark {
	int r = 0;
	int c = 0;
	int s = 0;
	int d = 0;
	int z = 0;

	Shark(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}

public class 낚시왕 {
	static int R, C, M;
	static Shark[][] map;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		// 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			map[r - 1][c - 1] = new Shark(r - 1, c - 1, s, d-1, z);
		}

		for (int i = 0; i < C; i++) {
			fishing(i);
			map = moveShark();
		}

		System.out.println(answer);
	}

	private static void fishing(int column) {
		for (int i = 0; i < R; i++) {
			if (map[i][column] == null)
				continue;

			answer += map[i][column].z;
			map[i][column] = null;
			return;
		}
	}

	static int[] dr = { -1, 1, 0, 0 }; // 위 아래 오른쪽 왼쪽
	static int[] dc = { 0, 0, 1, -1 };

	private static Shark[][] moveShark() {
		Shark[][] tmp = new Shark[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == null)
					continue;

				Shark now = map[i][j];
				int speed = now.s;
				if (now.d == 0 || now.d == 1)
					speed %= (R - 1) * 2;
				else
					speed %= (C - 1) * 2;

				for (int s = 0; s < speed; s++) {
					int nr = now.r + dr[now.d];
					int nc = now.c + dc[now.d];

					if (nr < 0 || nc < 0 || R <= nr || C <= nc) {
						now.r -= dr[now.d];
						now.c -= dc[now.d];

						if (now.d == 0)
							now.d = 1;
						else if (now.d == 1)
							now.d = 0;
						else if (now.d == 2)
							now.d = 3;
						else
							now.d = 2;
						continue;
					}

					now.r = nr;
					now.c = nc;
				}

				if (tmp[now.r][now.c] != null && tmp[now.r][now.c].z > now.z)
					continue;

				tmp[now.r][now.c] = now;
			}
		}
		return tmp;
	}
}
