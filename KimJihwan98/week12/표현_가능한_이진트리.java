package 백준;

public class 표현_가능한_이진트리 {
    class Solution {
        static boolean checkIf;
        public int[] solution(long[] numbers) {
            int[] answer = new int[numbers.length];
            int idx = 0;
            StringBuilder sb = new StringBuilder();
            loop : for(long l : numbers) {
                String num = Long.toBinaryString(l);

                int len = num.length();
                String lenToBinary = Integer.toBinaryString(len);

                int addZeroCount = 0; // 앞에 0을 추가해야될 개수
                for(int i = 0; i < lenToBinary.length(); i++) {
                    if(lenToBinary.charAt(i)=='0') {
                        addZeroCount += (int) Math.pow(2,lenToBinary.length()-i-1);
                    }
                }
                for(int i= 0; i < addZeroCount; i++) {
                    num = "0" + num;
                }
                checkIf = true;
                if(num.equals("0")) {
                    answer[idx++]=0;
                    continue;
                }

                boolean isZero = false;
                int layer = (int)Math.pow(2, lenToBinary.length()-2);

                check(num, (num.length()-1)/2,isZero,layer);
                if(checkIf) answer[idx++] = 1;
                else answer[idx++] = 0;

            }

            return answer;
        }
        static void check(String num, int centerIdx, boolean isZero, int layer) {
            if(layer<=0) return;

            if(num.charAt(centerIdx)=='0') isZero=true;
            if(isZero) {
                if(num.charAt(centerIdx-layer)=='1' || num.charAt(centerIdx+layer)=='1') {
                    checkIf = false;
                    return;
                }
                check(num, centerIdx-layer, isZero,layer/2);
                check(num, centerIdx+layer, isZero, layer/2);
            } else {
                check(num, centerIdx-layer, isZero,layer/2);
                check(num, centerIdx+layer, isZero,layer/2);
            }
        }
    }
}
