import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int[] arr;
    static int[] chk;
    static int cnt;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(bf.readLine());

        for(int tc=1;tc<=T;tc++){
            int N = Integer.parseInt(bf.readLine());

            arr = new int[N+1];
            st = new StringTokenizer(bf.readLine());
            for(int i=1;i<N+1;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            chk = new int[N+1];
            cycle = new ArrayList<>();
            cnt = N;
            for (int i = 1; i < N+1; i++) {
                if(chk[i]==1)continue;
                if(i==arr[i]){
                    chk[i] = 1;
                    cnt--;
                    continue;
                }
                cycle.clear();
                dfs(i);
            }

            sb.append(cnt).append("\n");
        }
        System.out.print(sb);

    }

    static ArrayList<Integer> cycle;
    public static void dfs(int n){

        chk[n] = 1;
            cycle.add(n);

        if(chk[arr[n]]==1){
            if(cycle.contains(arr[n])){

                cnt -= cycle.size() - cycle.indexOf(arr[n]);
            }
            //System.out.println(cycle.toString());
            return;
        }
        dfs(arr[n]);
        return;
    }
}