package jechul;

public class 표현가능한이진트리 {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int idx = 0; idx < numbers.length; idx++) {
            long num = numbers[idx];
            String b = Long.toBinaryString(num);
            String nodes = Integer.toBinaryString(b.length() + 1);
            
            if (nodes.substring(1).contains("1")) {
                int dummies = (int)Math.pow(2, nodes.length()) - Integer.parseInt(nodes, 2);
                
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < dummies; i++) {
                    sb.append("0");
                }
                b = sb.toString() + b;
            }

            
            boolean result = dfs(b, b.length() / 2, (b.length() + 1) / 4);
            answer[idx] = result ? 1 : 0;
        }
        return answer;
    }
    
    private boolean dfs(String b, int i, int depth) {
        if (depth == 0) {
            return true;
        } else if (b.charAt(i) == '0') {
            if (b.charAt(i - depth) == '1' || b.charAt(i + depth) == '1') return false;
        }
        
        boolean left = dfs(b, i - depth, depth / 2);
        boolean right = dfs(b, i + depth, depth / 2);
        return left && right;
    }
    

}

