import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 합이_0 {
  static int N, z;
  static long answer;
  static int[][] students;
  static List<Integer> minus, plus;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    z = 0;
    answer = 0;
    students = new int[10001][2];
    minus = new ArrayList<>();
    plus = new ArrayList<>();

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      int a = Integer.parseInt(st.nextToken());
      if (a < 0) {
        students[-a][0]++;
        minus.add(a);
      }
      else if (a == 0)
        z++;
      else {
        students[a][1]++;
        plus.add(a);
      }
    }

    for(int i = 0;i < minus.size(); i++) {
      for(int j = i+1; j < minus.size(); j++) {
        int tmp = minus.get(i) + minus.get(j);
        if(-tmp>10000) continue;
        answer += students[-tmp][1];
      }
    }
    for(int i = 0;i < plus.size(); i++) {
      for(int j = i+1; j < plus.size(); j++) {
        int tmp = plus.get(i) + plus.get(j);
        if(tmp>10000) continue;
        answer += students[tmp][0];
      }
    }

    answer += z*(z-1)*(z-2)/6;
    for(int i = 1; i < 10001; i++) {
      answer += students[i][0]*students[i][1]*z;
    }
    System.out.println(answer);

  }
}
