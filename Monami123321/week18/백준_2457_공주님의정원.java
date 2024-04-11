import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_2457_공주님의정원 {
	static int[] calendar = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	static class Flower {
		int start, end;
		Flower before;

		public Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		Flower[] flowers = new Flower[n];
		int refStart = 0;
		for (int i = 0; i < 3; i++) {
			refStart += calendar[i];
		}
		refStart += 1;
		int refEnd = 0;
		for (int i = 0; i < 11; i++) {
			refEnd += calendar[i];
		}
		refEnd += 30;
		int limit = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			int start = 0;
			for (int j = 0; j < tmp; j++) {
				start += calendar[j];
			}
			start += Integer.parseInt(st.nextToken());

			tmp = Integer.parseInt(st.nextToken());
			int end = 0;
			for (int j = 0; j < tmp; j++) {
				end += calendar[j];
			}
			end += Integer.parseInt(st.nextToken()) - 1;
			flowers[i] = new Flower(start, end);
			limit = Math.max(limit, end);
		}
		Arrays.sort(flowers, (a, b) -> {
			if (a.start == b.start) {
				return b.end - a.end;
			}
			return a.start - b.start;
		});
		if (flowers[0].start > refStart || limit < refEnd) {
			System.out.println(0);
			return;
		}
		int minStart = flowers[0].start;
		int maxEnd = flowers[0].end;
		Stack<Flower> stack = new Stack<>();
		stack.push(flowers[0]);
		for (int i = 1; i < n; i++) {
			if (maxEnd >= refEnd) {
				break;
			}
			Flower prev = stack.peek();
			Flower now = flowers[i];
			// 완전 열등
			if (prev.end >= now.end) {
				continue;
			}

			if (refStart >= now.start) {
				if (now.end > maxEnd) {
					minStart = now.start;
					maxEnd = now.end;
					stack.clear();
					stack.push(now);
					continue;
				}
			}
			
			if (prev.before != null) {
				if (prev.before.end + 1 >= now.start) {
					stack.pop();
					now.before = prev.before;
					stack.push(now);
					maxEnd = now.end;
					continue;
				}
			}
			if (now.start > prev.end + 1) {
				System.out.println(0);
				return;
			}
			now.before = stack.peek();
			maxEnd = now.end;
			stack.push(now);
		}
		System.out.println(stack.size());
		br.close();
	}
}
