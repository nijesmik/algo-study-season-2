package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 수_이어_쓰기2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		int idx = 0;
		while(k>0) {
			long tmp = (long) Math.pow(10,idx++);
			if(k - 9*tmp>0) {
				k -= 9*tmp;
			}
			else break;
		}
		long target = idx==1 ? (k)/idx : (long) Math.pow(10, idx-1) + (k-1)/idx;
		if(target > N) System.out.println(-1);
		else {
			int numIdx = (int)(k-1)%idx; 
			System.out.println(String.valueOf(target).charAt(numIdx));
		}
	}
}
