import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 소수의_연속합 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    // 에라토스테네스의 체
    boolean[] prime = new boolean[N+1];
    Arrays.fill(prime, true);
    prime[0] = false;
    prime[1] = false;
    for(int i = 2; i <= Math.sqrt(N); i++) {
      if(prime[i]) {
        for(int j = i*i; j <= N; j+= i) {
          prime[j] = false;
        }
      }
    }
    List<Integer> list = new ArrayList<>();
    for(int i = 0; i <= N; i++) {
      if(prime[i]) list.add(i);
    }
    list.add(0); // 투포인터에서 마지막 까지 탐색하기 위해 이거 어렵네

    int start = 0;
    int end = 0;
    int sum = 0;
    int cnt = 0;
    while( start <= end && end < list.size()) {
      if(sum < N) {
        sum += list.get(end++);
      }else {
        if(sum==N) cnt++;
        sum -= list.get(start++);
      }
    }
    System.out.println(cnt);
  }
}
