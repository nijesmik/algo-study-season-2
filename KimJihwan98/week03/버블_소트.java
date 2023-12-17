package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Bubble implements Comparable<Bubble>{
	int num;
	int idx;
	public Bubble(int num, int idx) {
		this.num = num;
		this.idx = idx;
	}
	
	@Override 
	public int compareTo(Bubble b) {
		return this.num - b.num;
	}
}

public class 버블_소트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		Bubble[] nums = new Bubble[N];
		int[] FianlIdx = new int[N];
		
		for(int i = 0; i < N; i++) {
			nums[i] = new Bubble(Integer.parseInt(br.readLine()), i);
		}
		
		// 이거는 구글링 좀 참고했어요 문제가 이해가안가서,,
		Arrays.sort(nums);
		
		int answer = 0;
		
		for(int i = 0; i < N; i++) {
			if(nums[i].idx - i > answer) answer = nums[i].idx - i;
		}
		
		System.out.println(answer+1);
		
	}
	
}
