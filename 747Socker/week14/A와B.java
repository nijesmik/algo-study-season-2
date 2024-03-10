import java.util.Scanner;

public class A와B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        String T = sc.next();

        StringBuilder sb = new StringBuilder();

        while(S.length() < sb.length()){
            if(sb.charAt(sb.length()-1)=='A'){
                sb.deleteCharAt(sb.length()-1);
            }else if(sb.charAt(sb.length()-1)=='B'){
                sb.deleteCharAt(sb.length()-1);
                sb.reverse();
            }
        }
        if(sb.toString().equals(S)){
            System.out.println("1");
        }else{
            System.out.println("0");
        }

}
