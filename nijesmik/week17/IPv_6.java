package nijesmik.week17;
import java.util.*;

public class IPv_6 {
    static StringBuilder sb;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        input = input.replace("::", ":X:");
        String[] address = input.split(":");

        List<String> list = new ArrayList<>();
        int insert = 0;
        for (int i = 0; i < address.length; i++) {
            if (address[i].equals("")) {
                insert--;
            } else if (address[i].equals("X")) {
                insert += i;
            } else {
                list.add(address[i]);
            }
        }

        while (list.size() < 8) {
            list.add(insert, "0000");
        }

        sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (i > 0) {
                sb.append(':');
            }
            sb.append(String.format("%4s", list.get(i)).replace(' ', '0'));
        }
        System.out.println(sb);
    }
}