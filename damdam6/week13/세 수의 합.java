package y24Mar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Baek2295 {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(bf.readLine()));
        }
        ArrayList<Integer> sumList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumList.add(arr.get(i) + arr.get(j));
            }
        }
        Collections.sort(sumList);

        Collections.sort(arr, Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int checkInt = arr.get(i) - arr.get(j);
                if (Collections.binarySearch(sumList, checkInt) >= 0) {
                    System.out.println(arr.get(i));
                    return;
                }
            }
        }
    }
}
