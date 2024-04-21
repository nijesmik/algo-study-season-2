import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 백준_1715_카드정렬하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue();

		for (int i = 0; i < n; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		int ans = 0;
		int now;
		while (pq.size() > 1) {
			ans += now = pq.poll() + pq.poll();
			pq.add(now);
		}
		System.out.println(ans);
		br.close();
	}
}
