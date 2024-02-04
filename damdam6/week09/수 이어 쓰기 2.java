import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long count = 0;
        long length = 1;
        long range = 9;

        while (k > length * range) {
            k -= length * range;
            count += range;
            length++;
            range *= 10;
        }

        count += (k - 1) / length; 

        if (count >= N) { 
            System.out.println(-1);
            return;
        }

        long num = count + 1; 
        long minusPos = (k - 1) % length; 
        long tenD = num;


        for (long i = 0; i < length - minusPos - 1; i++) {
            tenD /= 10;
        }

        System.out.println(tenD % 10); 
    }
}
