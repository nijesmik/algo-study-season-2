import java.util.*;

public class 놀이_공원 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        Ride[] rides = new Ride[M];
        for (int i = 0; i < M; i++) {
            rides[i] = new Ride(sc.nextInt());
        }

        int lcm = getLCM(rides);
        int denominator = 0;
        for (int i = 0; i < M; i++) {
            denominator += lcm / rides[i].duration;
        }
        N %= denominator;

        int child = 0;
        while (child < N) {
            for (int i = 0; i < M; i++) {
                int time = rides[i].time;
                if (time == 0)
                    child++;
                if (child == N) {
                    System.out.println(i + 1);
                    break;
                }
                rides[i].time = ++time % rides[i].duration;
            }
        }

    }

    static int getLCM(Ride[] rides) {
        if (rides.length == 1) {
            return rides[0].duration;
        }

        int gcd = getGCD(rides[0].duration, rides[1].duration);
        int lcm = rides[0].duration * rides[1].duration / gcd;

        for (int i = 2; i < rides.length; i++) {
            gcd = getGCD(lcm, rides[i].duration);
            lcm = lcm * rides[i].duration / gcd;
        }
        return lcm;
    }

    static int getGCD(int n, int m) {
        if (n % m == 0) {
            return m;
        }
        return getGCD(m, n % m);
    }

    static class Ride {
        int duration, time;

        Ride(int duration) {
            this.duration = duration;
        }
    }
}
