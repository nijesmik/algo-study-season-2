import java.util.Scanner;

public class 수_이어_쓰기_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), k = sc.nextInt();
        int num = 0, cnt = 0;
        while (num < N && cnt < k) {
            num++;
            int tmp = num;
            while (tmp > 0) {
                tmp /= 10;
                cnt++;
            }
        }
        int ans = -1;
        if (cnt == k) {
            ans = num % 10;
        } else if (cnt > k) {
            int d = (int) Math.pow(10, cnt - k);
            ans = (num / d) % 10;
        }
        System.out.println(ans);
    }
}
