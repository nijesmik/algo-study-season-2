package y23Dec01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Baek2110 {

    static int N;
    static int C;
    static ArrayList<Integer> pos;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        pos = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            pos.add(Integer.parseInt(bf.readLine()));
        }

        Collections.sort(pos);

        int s = 1;
        int e = pos.get(pos.size()-1) - pos.get(0);
        int mid = 0;
        int rec = 0;
        while( s <= e){

            mid = (s+e)/2;
            if(chk(mid)){
                s = mid+1;
                rec = mid;
            }else{
                e = mid-1;
            }

        }
        System.out.println(rec);

    }

    //dis 대로 배치했을때, M보다 크거나 같으면 true -> dis를 늘려야됨
    // M보다 작으면 false -> dis를 좁혀야됨
    static public boolean chk(int dis){
        int now = pos.get(0);
        int cnt = 1;
        int idx = 1;
        //System.out.println(pos.toString());
        while(now <= pos.get(pos.size()-1)){
            while(idx<pos.size()  && pos.get(idx) - now < dis){
                idx+=1;
            }

            if(idx>=pos.size())break;
            cnt++;
            now = pos.get(idx++);
        }
        if(cnt >= C)return true;
        return false;
    }
}
