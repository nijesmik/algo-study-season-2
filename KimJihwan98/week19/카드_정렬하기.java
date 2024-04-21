import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 카드_정렬하기 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int[] num = new int[N];
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for(int i = 0; i < N; i++) {
      int a  = sc.nextInt();
      pq.add(a);
    }
    int answer = 0;
    if(N==1) {
      System.out.println(0);
      return;
    }
    while(!pq.isEmpty()) {
      int a = pq.poll();
      if(pq.isEmpty()) {
        answer += a;
        break;
      }
      int b = pq.poll();
      answer += a+b;
      if(pq.isEmpty()) break;
      pq.add(a+b);
    }
    System.out.println(answer);
  }
}
