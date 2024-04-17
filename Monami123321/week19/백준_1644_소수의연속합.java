import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class 백준_1644_소수의연속합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[n + 1];
		int limit = (int)Math.sqrt(n);
		List<Integer> list = new ArrayList<>();
		for (int i = 2; i <= limit; i++) {
			if (visited[i]) {
				continue;
			}
			if (isPrime(i)) {
				list.add(i);
				int index = i << 1;
				while (index <= n) {
					visited[index] = true;
					index += i;
				}
			}
		}
		for (int i = limit + 1; i <= n; i++) {
			if (visited[i]) {
				continue;
			}
			list.add(i);
		}
		int size = list.size();
		int sum = 0;
		int left = 0;
		int right = 0;
		int ans = 0;
		while (right >= left && right <= size) {
			if (sum == n) {
				ans++;
				if (right < size) {
					sum += list.get(right++);
				} else
					break;
			} else if (sum < n) {
				if (right < size) {
					sum += list.get(right++);
				} else
					break;
			} else {
				sum -= list.get(left++);
			}
		}
		System.out.println(ans);
		br.close();
	}

	static boolean isPrime(int n) {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
