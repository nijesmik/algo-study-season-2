import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class IPv6 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    String[] ipv6 = new String[8];
    ipv6 = s.split(":");
    StringBuilder sb = new StringBuilder();
    if(ipv6[0].equals("")) {
      for(int i =1; i< ipv6.length; i++) {
        if (ipv6[i].equals("")) {
          for(int j = 0; j < 8-ipv6.length+2; j++) {
            sb.append("0000:");
          }
          continue;
        }
        int n = 4-ipv6[i].length();
        for(int j = 0; j < n; j++) {
          sb.append("0");
        }
        sb.append(ipv6[i]).append(":");
      }
      sb.deleteCharAt(39);
      String answer = sb.toString();
      System.out.println(answer);
    }else {
      for(int i = 0; i< ipv6.length; i++) {
        if (ipv6[i].equals("")) {
          for(int j = 0; j < 8-ipv6.length+1; j++) {
            sb.append("0000:");
          }
          continue;
        }
        int n = 4-ipv6[i].length();
        for(int j = 0; j < n; j++) {
          sb.append("0");
        }
        sb.append(ipv6[i]).append(":");
      }
      while(sb.length() < 39) {
        sb.append("0000:");
      }
      sb.deleteCharAt(39);
      String answer = sb.toString();
      System.out.println(answer);
    }

  }
}
