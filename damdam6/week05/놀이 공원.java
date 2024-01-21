package y24Jan02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baek1561 {
    static int[] time;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        time = new int[M];
        for (int i = 0; i < M; i++) {
            time[i] = Integer.parseInt(st.nextToken());
        }

        if (N <= M) {
            System.out.println(N);
            return;
        }

        long min = 0;
        long max = 2000000000L * 30L; // 최대 시간 설정
        System.out.println(BinarySearch(min, max, N));
    }

    public static long BinarySearch(long min, long max, int N) {
        long s = min;
        long e = max;
        long result = 0;

        while (s <= e) {
            long mid = (s + e) / 2;
            long children = TimeCheck(mid);

            if (children >= N) {
                result = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return LastPerson(result, N);
    }

    public static long TimeCheck(long mid) {
        long children = 0;
        for (int i = 0; i < time.length; i++) {
            children += mid / time[i];
        }
        return children + time.length; // 초기 탑승 아이들 추가
    }

    public static int LastPerson(long mid, int N) {
        long beforeMid = TimeCheck(mid - 1);
        long NthChild = beforeMid + 1;
        for (int i = 0; i < time.length; i++) {
            if (mid % time[i] == 0) {
                if (NthChild == N) {
                    return i + 1;
                }
                NthChild++;
            }
        }
        return -1;
    }
}
