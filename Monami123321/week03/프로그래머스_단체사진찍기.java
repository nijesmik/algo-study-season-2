import java.util.*;
class Solution {


    static char[] arr;
    static HashSet<String> set;

    static void swap(char[] arr, int a, int b) {
        char tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;

    }

    static void permutation(char[] arr, int depth, String[] condition) {

        if(depth == arr.length) {
            // 조건 검증 로직 작성
            for(String con : condition) {
                char start = con.charAt(0);
                char end = con.charAt(2);
                char cmd = con.charAt(3);
                int dist = (int)(con.charAt(4) -'0');


                int startNum = 0;
                int endNum = 0;

                for(int i = 0; i<arr.length; i++) {
                    if(arr[i] == start) {
                        startNum = i;
                    }
                    if(arr[i] == end) {
                        endNum = i;
                    }

                }

                int caseDist = Math.abs(startNum-endNum)-1;
                if(cmd == '=') {
                    if(caseDist != dist)
                        return;

                } else if (cmd == '<') {
                    if(caseDist >= dist)
                        return;

                } else {
                    if(caseDist <=dist)
                        return;
                }
            } // 검증 끝. 모든 condition통과 ㅡ> 정답셋에추가
            set.add(new String(arr));
            return;
        }


        for(int i = depth; i< arr.length; i++) {
            swap(arr,depth,i);
            permutation(arr,depth+1, condition);
            swap(arr,depth,i);
        }

    }
    public int solution(int n, String[] data) {
        arr = "ACFJMNRT".toCharArray(); // 8자리 문자열
        set = new HashSet<>();

        permutation(arr,0,data);

        return set.size();


    }
}