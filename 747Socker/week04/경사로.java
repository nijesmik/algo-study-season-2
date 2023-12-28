package jechul;

import java.util.Scanner;

public class 경사로 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int L = scanner.nextInt();

		int[][] graph = new int[N][N];
		int ans = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				graph[i][j] = scanner.nextInt();
			}
		}

		int[][] tp = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				tp[c][r] = graph[r][c];
			}
		}

		for (int r = 0; r < N; r++) {
			if (check(graph[r], N, L)) {
				ans++;
			}
			if (check(tp[r], N, L)) {
				ans++;
			}
		}

		System.out.println(ans);
	}

	static boolean check(int[] arr, int N, int L) {
		int h = arr[0];
		int cnt = 1;
		boolean flag = false;
		int i = 1;

		while (i < N) {
			int d = h - arr[i];

			if (d == 1) {
				if (flag && cnt < L) {
					return false;
				}
				cnt = 1;
				flag = true;
				if (cnt == L) {
					cnt = 0;
					flag = false;
				}
			} else if (d == -1) {
				if (cnt < L) {
					return false;
				}
				cnt = 1;
				flag = false;
			} else if (d == 0) {
				cnt++;
				if (flag) {
					if (cnt == L) {
						cnt = 0;
						flag = false;
					}
				}
			} else {
				return false;
			}

			h = arr[i];
			i++;
		}

		if (flag && cnt < L) {
			return false;
		}

		return true;
	}
}
