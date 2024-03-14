package nijesmik.week15;

import java.util.*;

public class 이차원_배열과_연산 {
	static int sizeR, sizeC, board[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt() - 1;
		int c = sc.nextInt() - 1;
		int k = sc.nextInt();
		board = new int[100][100];
		sizeR = 3;
		sizeC = 3;
		for (int i = 0; i < sizeR; i++) {
			for (int j = 0; j < sizeC; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		int time = 0;
		while (board[r][c] != k && time <= 100) {
			if (sizeR >= sizeC) {
				operationR();
			} else {
				operationC();
			}
			time++;
			// print();
		}
		if (time > 100) {
			time = -1;
		}
		System.out.println(time);
	}

	// static void print() {
	// 	for (int i = 0; i < sizeR; i++) {
	// 		for (int j = 0; j < sizeC; j++) {
	// 			System.out.print(board[i][j] + " ");
	// 		}
	// 		System.out.println();
	// 	}
	// 	System.out.println("---");
	// }

	static Map<Integer, Count> map;
	static void operationR() {
		for (int i = 0; i < sizeR; i++) {
			map = new HashMap<>();
			for (int j = 0; j < sizeC; j++) {
				count(board[i][j]);
			}
			PriorityQueue<Count> pq = new PriorityQueue<>(map.values());
			int idx = 0;
			while (!pq.isEmpty() && idx < 100) {
				Count cur = pq.poll();
				board[i][idx++] = cur.num;
				board[i][idx++] = cur.cnt;
			}
			sizeC = Math.max(sizeC, idx);
			for (int j = idx; j < sizeC; j++) {
				board[i][j] = 0;
			}
		}
	}

	static void operationC() {
		for (int j = 0; j < sizeC; j++) {
			map = new HashMap<>();
			for (int i = 0; i < sizeR; i++) {
				count(board[i][j]);
			}
			PriorityQueue<Count> pq = new PriorityQueue<>(map.values());
			int idx = 0;
			while (!pq.isEmpty() && idx < 100) {
				Count cur = pq.poll();
				board[idx++][j] = cur.num;
				board[idx++][j] = cur.cnt;
			}
			sizeR = Math.max(sizeR, idx);
			for (int i = idx; i < sizeR; i++) {
				board[i][j] = 0;
			}
		}
	}
 
	static void count(int num) {
		if (num == 0) {
			return;
		}
		Count cnt = map.getOrDefault(num, new Count(num));
		cnt.cnt++;
		map.put(num, cnt);
	}

	static class Count implements Comparable<Count>{
		int num, cnt;
		Count(int num) {
			this.num = num;
		}
		@Override
		public int compareTo(Count o) {
			if (cnt == o.cnt) {
				return num - o.num;
			}
			return cnt - o.cnt;
		}
	}
}