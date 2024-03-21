import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static ArrayList<Character> arrS;
    static ArrayList<Character> arrT;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String S = bf.readLine();
        String T = bf.readLine();

        arrS = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            arrS.add(S.charAt(i));
        }
        arrT = new ArrayList<>();
        for (int i = 0; i < T.length(); i++) {
            arrT.add(T.charAt(i));
        }

        int startPoint = 0;
        int size = arrT.size();
        boolean dir = true;

        int answer = 0;

        while (size > arrS.size()) {
            size--;
            if (dir && arrT.get(startPoint + size) == 'A') {
                continue;

            } else if (!dir && arrT.get(startPoint - size) == 'A') {
                continue;
            } else {

                if (dir) {
                    startPoint = startPoint + size - 1;
                } else {
                    startPoint = startPoint - size + 1;
                }
                dir = !dir;
            }

        }
        if (check(startPoint, dir)) {
            answer = 1;
        }
        System.out.println(answer);
    }


    private static boolean check(int start, boolean dir) {

        for (int i = 0; i < arrS.size(); i++) {
            int num = 1;
            if (!dir) {
                num *= -1;
            }
            if (arrT.get(start + i * num) != arrS.get(i)) {
                return false;
            }
        }

        return true;
    }
}
