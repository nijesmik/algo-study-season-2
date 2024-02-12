package jechul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 수묶기 {
	static int N;
	static int inpNums[];
	static ArrayList<Integer> pNums = new ArrayList<>();
	static ArrayList<Integer> nNums = new ArrayList<Integer>();
	static int one;
	static int zero;
	static long res;
	
	static long pNum() {
		for(int i=0; i+1<pNums.size(); i+=2) {
			res += pNums.get(i) * pNums.get(i+1);
		}
		if(pNums.size()%2!=0) {
			res+=pNums.get(pNums.size()-1);
		}
		return res;
	}
	
	static long nNum() {
		for(int i=0; i+1<nNums.size(); i+=2) {
			res += nNums.get(i) * nNums.get(i+1);
		}
		if(nNums.size()%2!=0 && zero==0) res+= nNums.get(nNums.size()-1);
		return res;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		inpNums = new int[N];
		for(int i=0; i<N; i++) {
			inpNums[i] = sc.nextInt();
		}
		
		for(int x: inpNums) {
			if(x>1) pNums.add(x);
			else if(x==0) zero++;
			else if(x==1) one++;
			else if(x<0) nNums.add(x);
		}
		
		Collections.sort(pNums, Collections.reverseOrder());
		Collections.sort(nNums);
		
		pNum();
		nNum();
		
		res+=one;
		System.out.println(res);
	}
}
