package y23Dec11Dec17;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Baek1377 {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N+1];
        for(int i=1;i<=N;i++){
            arr[i] = Integer.parseInt(bf.readLine());
        }

        boolean ch = false;
        for (int i = 1; i <=N+1; i++) {
            ch = false;
            for (int j = 1; j <N-i; j++) {
                if(arr[j] > arr[j+1]){
                    ch = true;
                    int tmp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tmp;
                }

            }
            if(!ch){
                System.out.println(i);
                break;
            }
        }
    }
}
