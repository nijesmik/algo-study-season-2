package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 리모컨 {
	static int N, M;
	static boolean[] controller;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		controller = new boolean[10];
		Arrays.fill(controller, true);
		if(M != 0) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				int a = Integer.parseInt(st.nextToken());
				controller[a] = false;
			}
		}
		
		
		int count = 0;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0 ; i <=999999; i++) {
			String tmp = String.valueOf(i);
			int len = tmp.length();
			boolean check = false;
			for(int j = 0; j < len; j++) {
				if(!controller[tmp.charAt(j)-'0']) {
					check = true;
					break;
				}
			}
			
			if(check) continue;
			
			count = len + Math.abs(i-N);
			
			min = Math.min(count, min);
		}
		int answer = Math.min(min, Math.abs(N-100));
		System.out.println(answer);
	}
}