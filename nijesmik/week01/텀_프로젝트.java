import java.io.*;
import java.util.*;

public class 텀_프로젝트 {
	static int[] wish;
	static boolean[] visit;
	static int N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase = Integer.parseInt(br.readLine());
		for (int t = 0; t < testcase; t++) {
			N = Integer.parseInt(br.readLine());
			ans = 0;
			wish = new int[N + 1];
			visit = new boolean[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				wish[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 1; i <= N; i++) {
				Set<Integer> team = new HashSet<>();
				dfs(i, team, 0);
			}
			System.out.println(N - ans);
		}
	}

	static int dfs(int idx, Set<Integer> team, int depth) {
		if (visit[idx]) {
			return -1;
		}
		if (team.contains(idx)) {
			ans += depth;
			return idx;
		}
		team.add(idx);
		int end = dfs(wish[idx], team, depth + 1);
		if (idx == end) {
			ans -= depth;
		}
		visit[idx] = true;
		return end;
	}
}