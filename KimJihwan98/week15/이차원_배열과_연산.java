import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 이차원_배열과_연산 {
    static HashMap<Integer, Integer> hm;
    static PriorityQueue<int[]> pq;
    static Queue<int[]> q;
    static int r, c, k, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        answer = 0;
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());
        int[][] arr = new int[3][3];
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        hm = new HashMap<>();
        pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[1]==o2[1]) return o1[0]-o2[0];
            return o1[1]-o2[1];
        });
        q = new LinkedList<>();

//        int[] tmp = sort(new int[] { 3,1,1,2});
//        System.out.println(Arrays.toString(tmp));
        if(r >= 3 || c >= 3) FunctionR(arr, 0);
        else if(arr[r][c] != k) FunctionR(arr, 0);
        System.out.println(answer);
    }
    static int[] sort(int[] line) {
        for(int i = 0 ; i < line.length; i++) {
            if(line[i] == 0) continue;
            int tmp = hm.getOrDefault(line[i],0);
            hm.put(line[i],tmp+1);
        }
        for(int key : hm.keySet()){
            pq.add(new int[] {key, hm.get(key)});
        }

        int[] sortedLine = new int[hm.size()*2];
        int idx = 0;
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            sortedLine[idx++] = now[0];
            sortedLine[idx++] = now[1];
        }
        hm.clear();
        return sortedLine;
    }
    static void FunctionR(int[][] arr, int time) {
        if(time>100) {
            answer = -1;
            return;
        }
        int length = -1;
        for(int i = 0; i < arr.length; i++) {
            int[] tmp = sort(arr[i]);
            length = Math.max(tmp.length, length);
            q.add(tmp);
        }
        if(length>100) length = 100;
        int[][] newArr = new int[arr.length][length];
        for(int i = 0; i < arr.length; i++) {
            int[] tmp = q.poll();
            for(int j= 0; j < length; j++) {
                if(tmp.length<=j) break;
                newArr[i][j] = tmp[j];
            }
        }
        if(r<newArr.length && c<newArr[0].length && newArr[r][c] == k) {
            answer = time+1;
            return;
        }

        if(newArr.length >= length) FunctionR(newArr, time+1);
        else FunctionC(newArr, time+1);
    }
    static void FunctionC(int[][] arr, int time) {
        if(time>100) {
            answer = -1;
            return;
        }
        int length = -1;
        for(int i = 0; i < arr[0].length; i++) {
            int[] tmp = new int[arr.length];
            for(int j = 0; j < arr.length; j++) {
                tmp[j] = arr[j][i];
            }
            tmp = sort(tmp);
            length = Math.max(tmp.length, length);
            q.add(tmp);
        }
        if(length>100) length = 100;
        int[][] newArr = new int[length][arr[0].length];
        for(int i = 0; i < arr[0].length; i++) {
            int[] tmp = q.poll();
            for(int j= 0; j < length; j++) {
                if(tmp.length<=j) break;
                newArr[j][i] = tmp[j];
            }
        }

        if(r<newArr.length && c<newArr[0].length && newArr[r][c] == k) {
            answer = time+1;
            return;
        }
        if(newArr.length >= newArr[0].length) FunctionR(newArr, time+1);
        else FunctionC(newArr, time+1);
    }
}
