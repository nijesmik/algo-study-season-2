package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class jewel {
	int w;
	int p;
	
	public jewel(int w, int p) {
		this.w = w;
		this.p = p;
	}
	
}

public class 보석_도둑 {
	static int N, K;
	static long answer;
	static PriorityQueue<Integer> pq;
	static int[] bags;
	static jewel[] jewels;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		jewels = new jewel[N];
		bags = new int[K];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewels[i] = (new jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			bags[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(bags);
		
		// 보석 무게 오름차순 정렬 , 가격 내림차순 정렬
		Arrays.sort(jewels, new Comparator<jewel>() {
			@Override
			public int compare(jewel j1, jewel j2) {
				if(j1.w == j2.w) {
					return j2.p - j2.p;
				}
				return j1.w - j2.w;
			}
		});
		
		answer = 0;
		pq = new PriorityQueue<>(Collections.reverseOrder());
		int idx = 0;
		for(int i = 0; i < K; i++ ) {
			while( idx < N && jewels[idx].w <= bags[i]) {
				pq.add(jewels[idx++].p);
			}
			
			if(!pq.isEmpty()) {
				answer += pq.poll();
			}
		}
		
		System.out.println(answer);
		
	}
}
