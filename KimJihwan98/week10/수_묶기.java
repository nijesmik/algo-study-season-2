package 백준;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 수_묶기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int answer = 0;
		List<Integer> pos = new ArrayList<>();
		List<Integer> neg = new ArrayList<>();
		int cntOne = 0;
		boolean isZero = false;
		for(int i = 0; i < N; i++) {
			int tmp = sc.nextInt();
			if(tmp > 1) {
				pos.add(tmp);
			} else if (tmp == 1) {
				cntOne++;
			} else if( tmp == 0) {
				isZero = true;
			} else if ( tmp < 0) {
				neg.add(tmp);
			}
		}
		Collections.sort(pos, Collections.reverseOrder());
		Collections.sort(neg);
		
		for(int i = 1; i < pos.size(); i+=2) {
			answer += pos.get(i) * pos.get(i-1);
		}
		if(pos.size() % 2 == 1) answer += pos.get(pos.size()-1);
		for(int i = 1; i < neg.size(); i+=2) {
			answer += neg.get(i) * neg.get(i-1);
		}
		if(neg.size() % 2 == 1) {
			if(!isZero) answer += neg.get(neg.size()-1); 
		}
		answer += cntOne;
		System.out.println(answer);
		
	}
}
