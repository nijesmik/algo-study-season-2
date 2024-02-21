public class 프로그래머스_표현가능한이진트리 {
    public int[] solution(long[] numbers) {
        int[] ans = new int[numbers.length];
        int index = 0;
        for (long l : numbers) {
            String bin = Long.toBinaryString(l);
            int binLen = bin.length();
            int len = 1;
            int now = 1;

            while (binLen > now) {
                len <<= 1;
                now += len;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < now - binLen; i++) {
                sb.append("0");
            }
            sb.append(bin);
            ans[index++] = isPossible(sb.toString()) ? 1 : 0;
        }
        return ans;
    }

    static boolean isPossible(String tree) {
        int len = tree.length();
        if (len == 1) {
            return true;
        }


        char root = tree.charAt(len / 2);

        String left = tree.substring(0, len / 2);
        char leftRoot = left.charAt(len / 4);
        String right = tree.substring(len / 2 + 1);
        char rightRoot = right.charAt(len / 4);

        if (root == '0') {
            if (leftRoot != '0' || rightRoot != '0') {
                return false;
            } else {
                return isPossible(left) && isPossible(right);
            }
        } else {
            return isPossible(left) && isPossible(right);
        }
    }
}
