import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 공주님의_정원 {
  static int N;
  static int[] months = {0,31,28,31,30,31,30,31,31,30,31,30,31};
  static int start = 60;
  static int end = 334;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
      if(o1[0] == o2[0]) {
        return o2[1] - o1[1];
      }
      return o1[0]-o2[0];
    });

    for(int i = 0;i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int s = dateToNumber(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
      int e = dateToNumber(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))-1;
      pq.add(new int[] {s, e});
    }

    int min = start-1;
    int max = start-1;
    int answer = 0;
    while(!pq.isEmpty()) {
      boolean check = false;
      while (pq.peek()[0] <= min+1) {
        int[] now = pq.poll();
        if (now[1] > max) { //endPoint가 max보다 크면
          max = now[1];
          check = true;
        }
        if(pq.isEmpty()) break;
      }
      if(check) {
        answer++;
        min = max;
      } else {
        answer = 0;
        break;
      }
      if(min >= end) break;
    }
    if (max < end) answer = 0;
    System.out.println(answer);

  }
  static int dateToNumber(int m, int d) {
    int num = 0;
    for(int i = 0; i < m; i++) {
      num+= months[i];
    }
    return num+d;
  }

}
