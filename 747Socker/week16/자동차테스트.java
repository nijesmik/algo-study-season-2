import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static int n, q;
    static int[] info;
    static Map<Integer, Integer> hash;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        q = sc.nextInt();

        info = new int[n];
        hash = new HashMap<>();

        for (int i = 0; i < n; i++) {
            info[i] = sc.nextInt();
        }
        Arrays.sort(info);
        for (int i = 0; i < n; i++) {
            hash.put(info[i], i);
        }

        for (int i = 0; i < q; i++) {
            int number = sc.nextInt();
            if (hash.get(number) != null) {
                System.out.println(binarySearch(number));
            } else {
                System.out.println(0);
            }
        }

    }


    static int binarySearch(int number) {
        int left = 0;
        int right = n - 1;
    
        while (left <= right) {
            int mid = left + (right - left) / 2; 
    
            if (info[mid] == number) {
                
                if (mid == 0 || mid == n - 1) {
                    return 0;
                }
                
                return mid * (n - 1 - mid);
            } else if (info[mid] < number) {
                left = mid + 1; 
            } else {
                right = mid - 1; 
            }
        }
    
        return 0;
    }
}
