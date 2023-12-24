import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        ArrayList<num> arr = new ArrayList<>();
        for(int i=0;i<N;i++){
            arr.add(new num(i,Integer.parseInt(bf.readLine())));
        }
        Collections.sort(arr);
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, arr.get(i).startIdx - i);
        }
        System.out.println(max+1);

        //System.out.println(arr.toString());
    }

    static class num implements Comparable<num>{
        int startIdx;
        int n;

        public num(int startIdx, int n) {
            this.startIdx = startIdx;
            this.n = n;
        }

        @Override
        public int compareTo(num o) {
            if(this.n > o.n)return 1;
            if(this.n < o.n)return -1;
            return 0;
        }

        @Override
        public String toString() {
            return "num{" +
                    "startIdx=" + startIdx +
                    ", n=" + n +
                    '}';
        }
    }
}
