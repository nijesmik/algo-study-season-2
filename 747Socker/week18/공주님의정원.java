import java.util.*;

public class 공주님의정원 {

    static class Flower implements Comparable<Flower> {
        int s;
        int e;

        public Flower(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Flower f) {
            if (this.s != f.s) {
                return this.s - f.s;
            } else {
                return -this.e + f.e;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Flower[] flowers = new Flower[N];
        
        for (int i = 0; i < N; i++) {
            int sM = sc.nextInt();
            int sD = sc.nextInt();
            int eM = sc.nextInt();
            int eD = sc.nextInt();
            flowers[i] = new Flower(sM * 100 + sD, eM * 100 + eD);
        }


        int startDay = 301;
        int endDay = 1201;
        int count = 0;
        int max = 0;
        int startIdx = 0;
        boolean flag = false;

        Arrays.sort(flowers);

        while (startDay < endDay) {
            flag = false;
            for (int i = startIdx; i < N; i++) {
                if (flowers[i].s > startDay) {
                    break;
                }
                if (max < flowers[i].e) {
                    max = flowers[i].e;
                    startIdx = i + 1;
                    flag = true;
                }
            }
            if (flag) {
                startDay = max;
                count += 1;
            } else {
                break;
            }
        }
        if (max < endDay) {
            System.out.println(0);
        } else {
            System.out.println(count);
        }

    }


}
